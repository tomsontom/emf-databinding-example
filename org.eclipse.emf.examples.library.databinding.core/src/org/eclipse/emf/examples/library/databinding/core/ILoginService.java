package org.eclipse.emf.examples.library.databinding.core;

import org.eclipse.core.runtime.IStatus;

public interface ILoginService {
	public IStatus login(String username, String password);
}
