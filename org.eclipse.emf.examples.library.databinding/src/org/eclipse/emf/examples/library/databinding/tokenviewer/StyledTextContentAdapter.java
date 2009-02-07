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

import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IControlContentAdapter;
import org.eclipse.jface.fieldassist.IControlContentAdapter2;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;

/**
 * An {@link IControlContentAdapter} for SWT StyledText controls. This is a
 * convenience class for easily creating a {@link ContentProposalAdapter} for
 * StyledText fields.
 * 
 * @since 3.2
 */
public class StyledTextContentAdapter implements IControlContentAdapter,
		IControlContentAdapter2 {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.taskassistance.IControlContentAdapter#getControlContents(org.eclipse.swt.widgets.Control)
	 */
	public String getControlContents(Control control) {
		return ((StyledText) control).getText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.fieldassist.IControlContentAdapter#setControlContents(org.eclipse.swt.widgets.Control,
	 *      java.lang.String, int)
	 */
	public void setControlContents(Control control, String StyledText,
			int cursorPosition) {
		((StyledText) control).setText(StyledText);
		((StyledText) control).setSelection(cursorPosition, cursorPosition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.fieldassist.IControlContentAdapter#insertControlContents(org.eclipse.swt.widgets.Control,
	 *      java.lang.String, int)
	 */
	public void insertControlContents(Control control, String StyledText,
			int cursorPosition) {
		Point selection = ((StyledText) control).getSelection();
		((StyledText) control).insert(StyledText);
		// Insert will leave the cursor at the end of the inserted StyledText. If this
		// is not what we wanted, reset the selection.
		if (cursorPosition <= StyledText.length()) {
			((StyledText) control).setSelection(selection.x + cursorPosition,
					selection.x + cursorPosition);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.fieldassist.IControlContentAdapter#getCursorPosition(org.eclipse.swt.widgets.Control)
	 */
	public int getCursorPosition(Control control) {
		return ((StyledText) control).getCaretOffset();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.fieldassist.IControlContentAdapter#getInsertionBounds(org.eclipse.swt.widgets.Control)
	 */
	public Rectangle getInsertionBounds(Control control) {
		StyledText StyledText = (StyledText) control;
		int caretOrigin = StyledText.getCaretOffset();
		// We fudge the y pixels due to problems with getCaretLocation
		// See https://bugs.eclipse.org/bugs/show_bug.cgi?id=52520
		return new Rectangle(caretOrigin + StyledText.getClientArea().x,
				0 + StyledText.getClientArea().y, 1, StyledText.getLineHeight());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.fieldassist.IControlContentAdapter#setCursorPosition(org.eclipse.swt.widgets.Control,
	 *      int)
	 */
	public void setCursorPosition(Control control, int position) {
		((StyledText) control).setSelection(new Point(position, position));
	}

	/**
	 * @see org.eclipse.jface.fieldassist.IControlContentAdapter2#getSelection(org.eclipse.swt.widgets.Control)
	 * 
	 * @since 3.4
	 */
	public Point getSelection(Control control) {
		return ((StyledText) control).getSelection();
	}

	/**
	 * @see org.eclipse.jface.fieldassist.IControlContentAdapter2#setSelection(org.eclipse.swt.widgets.Control,
	 *      org.eclipse.swt.graphics.Point)
	 * 
	 * @since 3.4
	 */
	public void setSelection(Control control, Point range) {
		((StyledText) control).setSelection(range);
	}
}
