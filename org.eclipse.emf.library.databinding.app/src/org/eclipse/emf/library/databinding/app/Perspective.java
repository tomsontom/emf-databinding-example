package org.eclipse.emf.library.databinding.app;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
//		layout.setEditorAreaVisible(false);
		layout.addStandaloneView("org.eclipse.emf.examples.library.databinding.libraryexplorer", true, IPageLayout.LEFT, 0.3f, layout.getEditorArea());
	}
}
