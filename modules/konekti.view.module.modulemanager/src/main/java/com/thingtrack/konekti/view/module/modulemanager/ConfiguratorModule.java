package com.thingtrack.konekti.view.module.modulemanager;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import com.thingtrack.konekti.view.kernel.IWorkbenchContext;
import com.thingtrack.konekti.view.kernel.ui.layout.AbstractModule;
import com.thingtrack.konekti.view.kernel.ui.layout.IViewContainer;

public class ConfiguratorModule extends AbstractModule implements BeanFactoryAware {
	private BeanFactory beanFactory;
	public final static String MODULE_ICONS_PATH = "images/icons/modulemanager-module/";

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}
	
	@Override
	public String getName() {
		return "Gestor de módulos";
	}

	@Override
	public String getDescription() {
		return getName() + " de la plataforma Konekti";
	}

	@Override
	public IViewContainer createViewComponent(IWorkbenchContext context) {
		try {
			// recover symbolic and version bundle
			getBundleIdentity(ConfiguratorModule.class);
			
			return (IViewContainer) beanFactory.getBean("configuratorViewContainer", new Object[]{ context, this});
		
		} catch (Exception ex) {
			ex.getMessage();
		}

		return null;
	}

}
