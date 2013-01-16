package com.thingtrack.konekti.view.kernel;

import java.util.List;


public interface IModuleService {

    public void registerModule(IModule module);
    
    public void unregisterModule(IModule module);
    
    public List<MetadataModule> getMetadataModules();
    
    public MetadataModule get(String id, String version);
    
    public void addListener(IMetadataModuleServiceListener listener);
    
    public void removeListener(IMetadataModuleServiceListener listener);	
}