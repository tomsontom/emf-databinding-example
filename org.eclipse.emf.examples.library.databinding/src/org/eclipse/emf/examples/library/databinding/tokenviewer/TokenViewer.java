/*******************************************************************************
 * Copyright (c) 2009 Eugene Ostroukhov and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eugene Ostroukhov - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.examples.library.databinding.tokenviewer;

import java.util.Collection;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.bindings.keys.SWTKeySupport;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.jface.viewers.BaseLabelProvider;
import org.eclipse.jface.viewers.ContentViewer;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class TokenViewer extends ContentViewer {
	public class ContentProposalProvider implements IContentProposalProvider {
		private IContentProposal[] createProposalsFor(
				ITokenContentProvider provider, Object[] elements,
				String contents) {
			if (elements == null || elements.length == 0) {
				return new IContentProposal[0];
			}
			IContentProposal[] proposals = new IContentProposal[elements.length];
			for (int i = 0; i < elements.length; i++) {
				Object object = elements[i];
				proposals[i] = new TokenContentProposal(object,
						(ITokenContentProvider) getContentProvider(), getLabelProvider());
			}
			return proposals;
		}

		public IContentProposal[] getProposals(String contents, int position) {
			contents = contents.substring(0, position);
			if (contents.length() > 0
					&& pathSeparator.indexOf(contents.charAt(0)) >= 0) {
				contents = contents.substring(1);
				position = position - 1;
			}
			ITokenContentProvider provider = (ITokenContentProvider) getContentProvider();
			Object[] elements = null;
			if (position == 0) {
				elements = provider.getElements(getInput());
			} else {
				Matcher matcher = pathPattern.matcher(contents);
				int len = 0;
				while (matcher.find()) {
					int index = matcher.start();
					if (index > position) {
						break;
					}
					len = index;
				}
				if (len > 0) {
					Object parentObject;
					try {
						parentObject = decodeObject(contents.substring(0, len),
								0);
						elements = provider.getChildren(parentObject, contents
								.substring(len + 1, position));
					} catch (UnrecognizedTokenException e) {
						// Ignore - text field contents is random, so no CA
					}
				} else {
					elements = provider.getChildren(getInput(), contents
							.substring(0, position));
				}
			}
			if (elements != null) {
				// Avoid breaking array order - some API users may return their
				// model array
				Object[] newElements = new Object[elements.length];
				System.arraycopy(elements, 0, newElements, 0, elements.length);
				comparator.sort(TokenViewer.this, newElements);
				elements = newElements;
			}
			return createProposalsFor(provider, elements, contents);
		}
	}

	private final class DefaultContentProvider implements ITokenContentProvider {
		private final IStructuredContentProvider delegate;
		private Object input;

		public DefaultContentProvider(IStructuredContentProvider delegate) {
			this.delegate = delegate;

		}

		public void dispose() {
			delegate.dispose();
		}

		public Object fromString(Object parent, String value) {
			Object[] children = getChildren(parent, value);
			for (int i = 0; i < children.length; i++) {
				Object cur = children[i];
				if (getLabel(cur).equals(value)) {
					return cur;
				}
			}
			return null;
		}

		public Object[] getChildren(Object object, String prefix) {
			Object[] elements;
			if (object == input || object == null) { // Equals is intentional
				elements = getElements(input);
			} else {
				Assert.isTrue(delegate instanceof ITreeContentProvider);
				elements = ((ITreeContentProvider) delegate)
						.getChildren(object);
			}
			if (elements == null || elements.length == 0) {
				return new Object[0];
			}
			Collection<Object> filtered = new LinkedList<Object>();
			for (Object element : elements) {
				if (prefix == null || getLabel(element).startsWith(prefix)) {
					filtered.add(element);
				}
			}
			return filtered.toArray();
		}

		public Object[] getElements(Object inputElement) {
			return delegate.getElements(inputElement);
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			this.input = newInput;
			delegate.inputChanged(viewer, oldInput, newInput);
		}

		public String toString(Object object) {
			String label = getLabel(object);
			if (delegate instanceof ITreeContentProvider) {
				Object parent = ((ITreeContentProvider) delegate)
						.getParent(object);
				if (parent != null && !parent.equals(getInput())) {
					return toString(parent) + pathSeparator.charAt(0) + label;
				}
			}
			return label;
		}
	}

	public class TokenProposalLabelProvider extends BaseLabelProvider implements
			ILabelProvider {
		public Image getImage(Object element) {
			Object object = ((TokenContentProposal) element).getElement();
			return ((ILabelProvider) getLabelProvider()).getImage(object);
		}

		public String getText(Object element) {
			Object object = ((TokenContentProposal) element).getElement();
			return getLabel(object);
		}

	}

	private KeyStroke caKey;
	private ViewerComparator comparator = new ViewerComparator();
	private ContentProposalAdapter contentProposalAdapter;
	private boolean isHirerarchial = true;
	private boolean multipleSelectionEnabled = false;
	private boolean noSelectionChangedEvents = false;
	private Pattern pathPattern;
	private String pathSeparator = "\\/";
	private ISelection selection = StructuredSelection.EMPTY;
	private final StyledText text;
	private String tokenSeparator = ";";

	public TokenViewer(Composite parent) {
		this(parent, SWT.BORDER | SWT.SINGLE);
	}

	public TokenViewer(Composite parent, int style) {
		this(new StyledText(parent, style));
	}

	public TokenViewer(StyledText text) {
		try {
			caKey = KeyStroke.getInstance("Ctrl+Space");
		} catch (ParseException e1) {
			// it should never-never come here
			throw new RuntimeException(e1);
		}
		this.text = text;
		text.addVerifyKeyListener(new VerifyKeyListener() {
			public void verifyKey(VerifyEvent event) {
				int acc = SWTKeySupport
						.convertEventToUnmodifiedAccelerator(event);
				event.doit = !SWTKeySupport.convertAcceleratorToKeyStroke(acc)
						.equals(caKey);
			}
		});
		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				refreshSelection();
			}
		});
		refreshPathSeparatorRegEx();
		initContentAssist();
	}

	private String convertToText(Object[] array) {
		StringBuffer buffer = new StringBuffer();
		for (Object object : array) {
			if (buffer.length() > 0) {
				buffer.append(tokenSeparator).append(' ');
			}
			buffer.append(((ITokenContentProvider) getContentProvider())
					.toString(object));
		}
		return buffer.toString();
	}

	private Object decodeObject(String value, int position)
			throws UnrecognizedTokenException {
		String[] split = pathPattern.split(value);
		if (split.length == 0) {
			return null;
		}
		int ind = 0;
		// Skip trailing /
		if (split[0].length() == 0) {
			ind = 1;
			position++;
		}
		ITokenContentProvider provider = (ITokenContentProvider) getContentProvider();
		Object current = getInput();

		for (; ind < split.length; ind++) {
			String string = split[ind];
			Object object = provider.fromString(current, string);
			if (object == null) {
				throw new UnrecognizedTokenException(current, position, value
						.length()
						- position);
			} else {
				position += string.length() + 1;
				current = object;
			}
		}
		return current;
	}

	@Override
	public Control getControl() {
		return text;
	}

	private String getLabel(Object object) {
		ILabelProvider labelProvider = (ILabelProvider) getLabelProvider();
		if (labelProvider != null) {
			return labelProvider.getText(object);
		} else {
			return object.toString();
		}
	}

	@Override
	public ISelection getSelection() {
		return selection;
	}

	private void initContentAssist() {
		contentProposalAdapter = new ContentProposalAdapter(text,
				new StyledTextContentAdapter(), new ContentProposalProvider(),
				caKey, pathSeparator.toCharArray());
		contentProposalAdapter.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_REPLACE);
		contentProposalAdapter
				.setLabelProvider(new TokenProposalLabelProvider());
	}

	public boolean isHirerarchial() {
		return isHirerarchial;
	}

	public boolean isMultipleSelectionEnabled() {
		return multipleSelectionEnabled;
	}

	@Override
	public void refresh() {
		refreshSelection();
	}

	private void refreshPathSeparatorRegEx() {
		pathPattern = Pattern.compile("[" + pathSeparator.replace("\\", "\\\\")
				+ "]");
	}

	protected synchronized void refreshSelection() {
		if (noSelectionChangedEvents) {
			return;
		}
		text.setStyleRanges(new StyleRange[0]);
		String value = text.getText();
		ISelection newSelection = StructuredSelection.EMPTY;
		if (isMultipleSelectionEnabled()) {

		} else {
			Object current;
			try {
				current = decodeObject(value, 0);
				if (current != null) {
					newSelection = new StructuredSelection(current);
				}
			} catch (UnrecognizedTokenException e) {
				StyleRange range = new StyleRange();
				range.underline = true;
				range.underlineStyle = SWT.UNDERLINE_ERROR;
				range.underlineColor = text.getDisplay().getSystemColor(
						SWT.COLOR_RED);
				range.start = e.getStart();
				range.length = e.getLength();
				text.setStyleRange(range);
			}
		}
		if (!newSelection.equals(selection)) {
			SelectionChangedEvent event = new SelectionChangedEvent(this,
					newSelection);
			selection = newSelection;
			fireSelectionChanged(event);
		}

	}

	public void setAutoTriggerCharacters(String trigger) {
		contentProposalAdapter
				.setAutoActivationCharacters(trigger != null ? trigger
						.toCharArray() : null);
	}

	public void setComparator(ViewerComparator comparator) {
		if (comparator == null) {
			comparator = new ViewerComparator();
		}
		this.comparator = comparator;
	}

	@Override
	public void setContentProvider(IContentProvider contentProvider) {
		if (contentProvider instanceof ITokenContentProvider) {
			super.setContentProvider(contentProvider);
		} else {
			super.setContentProvider(new DefaultContentProvider(
					(IStructuredContentProvider) contentProvider));
		}
	}

	public void setHirerarchial(boolean hirerarchial) {
		this.isHirerarchial = hirerarchial;
		refresh();
	}

	public void setMultipleSelectionEnabled(boolean multipleSelectionEnabled) {
		this.multipleSelectionEnabled = multipleSelectionEnabled;
		refresh();
	}

	public void setPathSeparator(String pathSeparator) {
		this.pathSeparator = pathSeparator;
		refreshPathSeparatorRegEx();
	}

	@Override
	public synchronized void setSelection(ISelection selection, boolean reveal) {
		if (this.selection.equals(selection)) {
			return;
		}
		Assert.isNotNull(selection);
		noSelectionChangedEvents = true;
		try {
			this.selection = selection;
			if (selection.isEmpty()) {
				text.setText("");
			} else {
				text.setText(convertToText(((IStructuredSelection) selection)
						.toArray()));
			}
		} finally {
			noSelectionChangedEvents = false;
		}
	}

	public String getText() {
		return text.getText();
	}
}
