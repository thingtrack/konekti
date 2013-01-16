package com.thingtrack.konekti.view.kernel.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import com.thingtrack.konekti.view.kernel.IModule;
import com.thingtrack.konekti.view.kernel.MetadataModule;
import com.thingtrack.konekti.view.kernel.IMetadataModuleServiceListener;
import com.thingtrack.konekti.view.kernel.IModuleService;

public class ModuleServiceImpl implements IModuleService {

	private ArrayList<MetadataModule> metadaModules = new ArrayList<MetadataModule>();

	private ArrayList<IMetadataModuleServiceListener> listeners = new ArrayList<IMetadataModuleServiceListener>();

	private BundleContext context;

	public ModuleServiceImpl() {
		context = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
		
	}

	@SuppressWarnings("unchecked")
	public synchronized void registerModule(IModule module) {
		Bundle bundle;

		try {

			bundle = getModuleContext(module);
			String id = bundle.getSymbolicName();
			String version = bundle.getVersion().toString();

			MetadataModule metadataModule = new MetadataModule(id, version, module);
			metadaModules.add(metadataModule);

			for (IMetadataModuleServiceListener listener : (ArrayList<IMetadataModuleServiceListener>) listeners.clone())
				listener.metadataModuleRegistered(this, metadataModule);			

		} catch (KernelException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private Bundle getModuleContext(IModule module) throws KernelException {
		try {
			ServiceReference<IModule>[] moduleRefs = (ServiceReference<IModule>[]) context
					.getAllServiceReferences(IModule.class.getName(), null);

			for (ServiceReference<IModule> ref : moduleRefs) {

				if (module.equals(context.getService(ref))) {

					return ref.getBundle();
				}
			}
		} catch (InvalidSyntaxException e) {
			e.printStackTrace();
		}

		throw new KernelException("No module's bundle found");
	}

	@SuppressWarnings("unchecked")
	public synchronized void unregisterModule(IModule module) {

		for (MetadataModule metadaModule : metadaModules) {

			if (metadaModule.getModule() == module) {

				metadaModules.remove(metadaModule);

				for (IMetadataModuleServiceListener listener : (ArrayList<IMetadataModuleServiceListener>) listeners.clone())
					listener.metadataModuleUnregistered(this, metadaModule);				

				break;
			}

		}

	}

	public List<MetadataModule> getMetadataModules() {
		return Collections.unmodifiableList(metadaModules);
	}

	public synchronized void addListener(IMetadataModuleServiceListener listener) {
		listeners.add(listener);
	}

	public synchronized void removeListener(
			IMetadataModuleServiceListener listener) {
		listeners.remove(listener);
	}

	@Override
	public MetadataModule get(String id, String version) {
		for (MetadataModule metadataModule : metadaModules) {
			if (metadataModule.getId().equals(id) && metadataModule.getVersion().equals(version))
				return metadataModule;
			
		}
		
		return null;
	}
}