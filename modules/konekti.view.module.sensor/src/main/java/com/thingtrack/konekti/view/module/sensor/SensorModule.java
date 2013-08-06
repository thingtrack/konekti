package com.thingtrack.konekti.view.module.sensor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import com.thingtrack.konekti.view.kernel.IWorkbenchContext;
import com.thingtrack.konekti.view.kernel.ui.layout.AbstractModule;
import com.thingtrack.konekti.view.kernel.ui.layout.IViewContainer;

public class SensorModule extends AbstractModule implements BeanFactoryAware {
	private BeanFactory beanFactory;

	private final static String MODULE_NAME = "Gestor de señales";
	private final static String MODULE_DESCRIPTION = "Gestor de señales";

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
			getBundleIdentity(SensorModule.class);
			
			return (IViewContainer) beanFactory.getBean("sensorViewContainer", new Object[] { context, this });
		} catch (Exception ex) {
			ex.getMessage();
		}

		return null;

	}

}