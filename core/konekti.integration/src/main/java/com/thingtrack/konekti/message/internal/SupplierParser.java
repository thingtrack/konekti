package com.thingtrack.konekti.message.internal;

import org.springframework.beans.factory.annotation.Autowired;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.Supplier;
import com.thingtrack.konekti.message.internal.domain.csv.supplier.CsvSupplier;
import com.thingtrack.konekti.service.api.OrganizationService;
import com.thingtrack.konekti.service.api.SupplierGroupService;
import com.thingtrack.konekti.service.api.SupplierService;
import com.thingtrack.konekti.service.api.SupplierTypeService;

public class SupplierParser implements Processor {
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private SupplierService supplierService;

	@Autowired
	private SupplierTypeService supplierTypeService;
	
	@Autowired
	private SupplierGroupService supplierGroupService;
	
    public void process(Exchange exchange) throws Exception {   
    	CsvSupplier csvSupplier = (CsvSupplier)exchange.getIn().getBody();	
 		 
		 // C: Create supplier
		 // U: Update supplier
		 // D: Delete supplier
		 if (csvSupplier.getAction().equals("C")) {
			 supplierService.save(parse(csvSupplier));
		 }
		 else if (csvSupplier.getAction().equals("U")) {
			 supplierService.save(parse(csvSupplier));
		 }
		 else if (csvSupplier.getAction().equals("D")) {
			 Supplier supplier = parse(csvSupplier);
			 supplier.setActive(false);
			 
			 supplierService.save(supplier);
		 }
		 
    }
    
    private Supplier parse(CsvSupplier csvSupplier) throws Exception {
    	// get client organization
    	Organization organization = organizationService.getByCode(csvSupplier.getOrganizationCode());
    	
    	// create new client entity
    	Supplier supplier = supplierService.createNewSupplier(organization);
    	    	
    	// client setters
    	supplier.setCode(csvSupplier.getCode());    	
    	supplier.setDescription(csvSupplier.getDescription());
    	supplier.setEmail(csvSupplier.getEmail());
    	supplier.setFacebookId(csvSupplier.getFacebookId());
    	supplier.setFax(csvSupplier.getFax());
    	supplier.setComment(csvSupplier.getComment());
    	supplier.setMobile(csvSupplier.getMobile());
    	supplier.setOrganization(organization);
    	supplier.setSupplierType(supplierTypeService.getByName(csvSupplier.getTypeCode()));
    	supplier.setSupplierGroup(supplierGroupService.getByname(csvSupplier.getGroupName()));
    	supplier.setVat(csvSupplier.getVat());
    	
    	//supplier.setAddress(address);
    	
    	return supplier;
    	
    }
}

