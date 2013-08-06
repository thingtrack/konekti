package com.thingtrack.konekti.view.module.organization;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import com.thingtrack.konekti.view.kernel.IWorkbenchContext;
import com.thingtrack.konekti.view.kernel.ui.layout.AbstractModule;
import com.thingtrack.konekti.view.kernel.ui.layout.IViewContainer;

public class OrganizationModule extends AbstractModule implements BeanFactoryAware {
	private BeanFactory beanFactory;
	
	private final static String MODULE_NAME = "Gestor organizaciones";
	private final static String MODULE_DESCRIPTION = "Módulo para la gestión organizaciones";
	
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
			// recover symbolic and version bundle
			getBundleIdentity(OrganizationModule.class);
			
			return (IViewContainer) beanFactory.getBean("organizationViewContainer", new Object[] { context, this });
		} catch (Exception ex) {
			ex.getMessage();
		}

		return null;

	}
	
}
