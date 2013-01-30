package com.thingtrack.konekti.view.module.modulemanager;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import com.thingtrack.konekti.view.kernel.IModule;
import com.thingtrack.konekti.view.kernel.IWorkbenchContext;
import com.thingtrack.konekti.view.kernel.ui.layout.IViewContainer;

public class ConfiguratorModule implements IModule, BeanFactoryAware {

	private BeanFactory beanFactory;
	public final static String MODULE_ICONS_PATH = "images/icons/modulemanager-module/";
	
	@Override
	public String getName() {
		return "Gestor de m—dulos";
	}

	
@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

	@Override
	public String getDescription() {
		return getName() + " de la plataforma Konekti";
	}

	@Override
	public IViewContainer createViewComponent(IWorkbenchContext context) {
		return (IViewContainer) beanFactory.getBean("configuratorComponent", new Object[]{context});
	}

}
