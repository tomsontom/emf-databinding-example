/*******************************************************************************
 * Copyright (c) 2009 BestSolution.at and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Tom Schindl<tom.schindl@bestsolution.at> - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.examples.library.databinding;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.ExpressionInfo;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.contexts.IContextActivation;
import org.eclipse.ui.contexts.IContextService;

public abstract class AbstractForm {
	private String id;
	private TabItem item;
	private IWorkbenchPartSite site;
	private IContextActivation activation;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public TabItem getItem() {
		return item;
	}
	
	protected IWorkbenchPartSite getSite() {
		return site;
	}
	
	public void activate() {
		if( activation != null ) {
			return;
		}
		
		activation = ((IContextService)getSite().getService(IContextService.class)).activateContext(getContextIdPrefix() +".context", new Expression() {

			@Override
			public void collectExpressionInfo(ExpressionInfo info) {
				super.collectExpressionInfo(info);
				info.addVariableNameAccess(org.eclipse.ui.ISources.ACTIVE_FOCUS_CONTROL_ID_NAME);
			}

			@Override
			public EvaluationResult evaluate(IEvaluationContext context)
					throws CoreException {
				if( context.getVariable(org.eclipse.ui.ISources.ACTIVE_FOCUS_CONTROL_ID_NAME) != null && context.getVariable(org.eclipse.ui.ISources.ACTIVE_FOCUS_CONTROL_ID_NAME).toString().startsWith(getContextIdPrefix()) ) {
					return EvaluationResult.TRUE;
				}
				
				return EvaluationResult.FALSE;
			}
			
		});
	}
	
	public void deactivate() {
		if( activation == null ) {
			return;
		}
		((IContextService)getSite().getService(IContextService.class)).deactivateContext(activation);
		activation = null;
	}
	
	public void createForm(IWorkbenchPartSite site, TabFolder folder, int index, EditingDomain domain, DataBindingContext context, IObservableValue master) {
		this.site = site;
		item = new TabItem(folder,SWT.NONE,index);
		doCreateForm(folder, item, domain, context, master);
	}
	
	public abstract void doCreateForm(TabFolder folder, TabItem item, EditingDomain domain, DataBindingContext context, IObservableValue master);
	public abstract void postExecuteFailure(String commandId,ExecutionException exception);
	public abstract void postExecuteSuccess(String commandId, Object returnValue);
	protected abstract void doDispose();
	
	public void dispose() {
		deactivate();
		doDispose();
		int index = 0;
		for( TabItem i: item.getParent().getSelection() ) {
			if( i == item ) {
				if( index == 0 ) {
					if( item.getParent().getItemCount() > 1 ) {
						item.getParent().setSelection(1);
					}
				} else {
					item.getParent().setSelection(index-1);
				}
				break;
			}
			index++;
		}
		Control c = item.getControl();
		item.setControl(null);
		c.dispose();
		item.dispose();
	}
	
	protected abstract String getContextIdPrefix();
}
