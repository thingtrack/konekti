package com.thingtrack.konekti.view.kernel;
public interface IMetadataModuleServiceListener {

    public void metadataModuleRegistered(IModuleService source, MetadataModule metadataModule);
    
    public void metadataModuleUnregistered(IModuleService source, MetadataModule metadataModule);
}