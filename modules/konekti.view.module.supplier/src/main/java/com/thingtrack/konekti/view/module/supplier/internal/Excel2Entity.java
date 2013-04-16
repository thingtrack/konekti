package com.thingtrack.konekti.view.module.supplier.internal;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;

import com.thingtrack.konekti.domain.Address;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.Sequence;
import com.thingtrack.konekti.domain.Supplier;
import com.thingtrack.konekti.domain.SupplierGroup;
import com.thingtrack.konekti.domain.SupplierType;
import com.thingtrack.konekti.service.api.AddressService;
import com.thingtrack.konekti.service.api.SequenceService;
import com.thingtrack.konekti.service.api.SupplierGroupService;
import com.thingtrack.konekti.service.api.SupplierTypeService;
import com.thingtrack.konekti.view.kernel.IWorkbenchContext;

public class Excel2Entity {
	private HSSFRow row;
	
	private Supplier supplier = null;
	
	private static final int CODE = 0;
	private static final int NAME = 1;
	private static final int DESCRIPTION = 2;
	private static final int PHONE = 3;
	private static final int FAX = 4;
	private static final int MOBILE = 5;
	private static final int EMAIL = 6;
	private static final int ADDRESS = 7;
	private static final int VAT = 8;
	private static final int TYPE = 9;
	private static final int GROUP = 10;
	private static final int COMMENT = 11;
	private static final int ACTIVE = 12;
		
	private SequenceService sequenceService;
	private AddressService addressService;
	private SupplierTypeService supplierTypeService;
	private SupplierGroupService supplierGroupService;
	
	public Excel2Entity(IWorkbenchContext context, SequenceService sequenceService, AddressService addressService, SupplierGroupService supplierGroupService, SupplierTypeService supplierTypeService, HSSFRow row) {	    
		this.sequenceService = sequenceService;
		this.addressService = addressService;
		this.supplierTypeService = supplierTypeService;
	    this.supplierGroupService = supplierGroupService;
	    
		this.row = row;
		
		supplier = new Supplier();
		List<Organization> organizations = new ArrayList<Organization>();
		organizations.add(context.getUser().getActiveOrganization());
			
		//supplier.setOrganizations(organizations);
	}
	
	public Supplier parse() {		
		if(row.getCell(CODE) == null) {
			try {
				supplier.setCode(sequenceService.setNextSequence(Sequence.CODE.CLIENT.name()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
			supplier.setCode(row.getCell(CODE).getStringCellValue());
		
		if(row.getCell(NAME) != null)
			supplier.setName(row.getCell(NAME).getStringCellValue());

		if(row.getCell(DESCRIPTION) != null)
			supplier.setDescription(row.getCell(DESCRIPTION).getStringCellValue());

		if(row.getCell(PHONE) != null) {
			row.getCell(PHONE).setCellType(Cell.CELL_TYPE_STRING);
			supplier.setPhone(row.getCell(PHONE).getStringCellValue());
		}
			
		if(row.getCell(FAX) != null) {
			row.getCell(FAX).setCellType(Cell.CELL_TYPE_STRING);
			supplier.setFax(row.getCell(FAX).getStringCellValue());
		}
		
		if(row.getCell(MOBILE) != null) {
			row.getCell(MOBILE).setCellType(Cell.CELL_TYPE_STRING);
			supplier.setMobile(row.getCell(MOBILE).getStringCellValue());
		}
				
		if(row.getCell(EMAIL) != null)
			supplier.setEmail(row.getCell(EMAIL).getStringCellValue());
		
		if(row.getCell(ADDRESS) != null) {
			Address address = null;
			try {
				address = addressService.getByStreet(row.getCell(ADDRESS).getStringCellValue());
			} catch (Exception e) {
				address = new Address();
				address.setStreet(row.getCell(ADDRESS).getStringCellValue());
			
			}
			
			supplier.setAddress(address);
		}
		
		if(row.getCell(VAT) != null)
			supplier.setVat(row.getCell(VAT).getStringCellValue());
		
		if(row.getCell(TYPE) != null) {
			SupplierType supplierType = null;
			try {
				supplierType = supplierTypeService.getByName(row.getCell(TYPE).getStringCellValue());
			} catch (Exception e) {
				supplierType = new SupplierType();
				supplierType.setName(row.getCell(TYPE).getStringCellValue());
				supplierType.setDescription(row.getCell(TYPE).getStringCellValue());
									
			}
			
			supplier.setSupplierType(supplierType);
		}
		
		if(row.getCell(GROUP) != null) {
			SupplierGroup supplierGroup = null;
			try {
				supplierGroup = supplierGroupService.getByname(row.getCell(GROUP).getStringCellValue());
			} catch (Exception e) {
				supplierGroup = new SupplierGroup();
				supplierGroup.setName(row.getCell(GROUP).getStringCellValue());
				supplierGroup.setDescription(row.getCell(GROUP).getStringCellValue());
									
			}
			
			supplier.setSupplierGroup(supplierGroup);
		}
		
		if(row.getCell(COMMENT) != null)
			supplier.setComment(row.getCell(COMMENT).getStringCellValue());

		if(row.getCell(ACTIVE) != null) {
			if (row.getCell(ACTIVE).getStringCellValue().equals("1"))
				supplier.setActive(true);
			else
				supplier.setActive(false);				
		}
		
		return supplier;
		
	}
}
