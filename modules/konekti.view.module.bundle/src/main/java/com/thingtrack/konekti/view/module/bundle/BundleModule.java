package com.thingtrack.konekti.view.module.bundle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import com.thingtrack.konekti.view.kernel.IWorkbenchContext;
import com.thingtrack.konekti.view.kernel.ui.layout.AbstractModule;
import com.thingtrack.konekti.view.kernel.ui.layout.IViewContainer;

public class BundleModule extends AbstractModule implements BeanFactoryAware {
	private BeanFactory beanFactory;

	private final static String MODULE_NAME = "Gestor Bundles";
	private final static String MODULE_DESCRIPTION = "Gestor Bundles";

	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;

	}

	public String getName() {
		return MODULE_NAME;

	}

	public String getDescription() {
		return MODULE_DESCRIPTION;

	}

	@Override
	public IViewContainer createViewComponent(IWorkbenchContext context) {
		try {
			return (IViewContainer) beanFactory.getBean("bundleViewContainer", new Object[] { context });
		} catch (Exception ex) {
			ex.getMessage();
		}

		return null;

	}

}