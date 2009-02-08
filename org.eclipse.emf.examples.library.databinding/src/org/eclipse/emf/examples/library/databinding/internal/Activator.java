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
package org.eclipse.emf.examples.library.databinding.internal;

import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.dynamichelpers.ExtensionTracker;
import org.eclipse.emf.examples.library.databinding.ILibraryServiceRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.emf.examples.library.databinding";

	// The shared instance
	private static Activator plugin;
	
	private LibraryServiceTracker tracker;
	
	private Collection<FormDescriptor> descriptors;
	
	private ExtensionTracker extensionTracker = new ExtensionTracker();
	
	private FormExtensionHandler extensionHandler;

	
	private ILibraryServiceRegistry registry = new LibraryServiceRegistryImpl();
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		tracker = new LibraryServiceTracker(context,registry);
		tracker.open();
		
		if( extensionHandler != null) {
			extensionTracker.unregisterHandler(extensionHandler);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		tracker.close();
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	public ILibraryServiceRegistry getLiberyRegistry() {
		return registry;
	}
	
	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
	
	public Collection<FormDescriptor> getFormDescriptors() {
		if( descriptors == null ) {
			
			descriptors = new TreeSet<FormDescriptor>(new Comparator<FormDescriptor>() {

				public int compare(FormDescriptor o1, FormDescriptor o2) {
					Double d1 = o1.getWeight();
					int rv = d1.compareTo(o2.getWeight());
					if( rv == 0 ) {
						rv = -1;
					}
					return rv;
				}
				
			});
			extensionHandler = new FormExtensionHandler(descriptors);
            IExtensionPoint point = Platform.getExtensionRegistry()
                            .getExtensionPoint(Activator.PLUGIN_ID,
                            		FormExtensionHandler.FORM_EXT);

            extensionTracker.registerHandler(
                            extensionHandler,
                            ExtensionTracker.createExtensionPointFilter(point));

            for (int i = 0; i < point.getExtensions().length; i++) {
                    extensionHandler.addExtension(extensionTracker, point.getExtensions()[i]);
            }

		}
		
		return descriptors;
	}
	
	public FormExtensionHandler getFormHandler() {
		return extensionHandler;
	}
}
