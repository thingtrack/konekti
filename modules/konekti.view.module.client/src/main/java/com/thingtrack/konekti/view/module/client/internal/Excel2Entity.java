package com.thingtrack.konekti.view.module.client.internal;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;

import com.thingtrack.konekti.domain.Address;
import com.thingtrack.konekti.domain.Client;
import com.thingtrack.konekti.domain.ClientGroup;
import com.thingtrack.konekti.domain.ClientType;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.Sequence;
import com.thingtrack.konekti.service.api.AddressService;
import com.thingtrack.konekti.service.api.ClientGroupService;
import com.thingtrack.konekti.service.api.ClientTypeService;
import com.thingtrack.konekti.service.api.SequenceService;
import com.thingtrack.konekti.view.kernel.IWorkbenchContext;

public class Excel2Entity {
	private HSSFRow row;
	
	private Client client = null;
	
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
	private ClientTypeService clientTypeService;
	private ClientGroupService clientGroupService;
	
	public Excel2Entity(IWorkbenchContext context, SequenceService sequenceService, AddressService addressService, ClientGroupService clientGroupService, ClientTypeService clientTypeService, HSSFRow row) {	    
		this.sequenceService = sequenceService;
		this.addressService = addressService;
		this.clientTypeService = clientTypeService;
	    this.clientGroupService = clientGroupService;
	    
		this.row = row;
		
		client = new Client();
		List<Organization> organizations = new ArrayList<Organization>();
		organizations.add(context.getUser().getActiveOrganization());
			
		//client.setOrganizations(organizations);
	}
	
	public Client parse() {		
		if(row.getCell(CODE) == null) {
			try {
				client.setCode(sequenceService.setNextSequence(Sequence.CODE.CLIENT.name()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
			client.setCode(row.getCell(CODE).getStringCellValue());
		
		if(row.getCell(NAME) != null)
			client.setName(row.getCell(NAME).getStringCellValue());

		if(row.getCell(DESCRIPTION) != null)
			client.setDescription(row.getCell(DESCRIPTION).getStringCellValue());

		if(row.getCell(PHONE) != null) {
			row.getCell(PHONE).setCellType(Cell.CELL_TYPE_STRING);
			client.setPhone(row.getCell(PHONE).getStringCellValue());
		}
			
		if(row.getCell(FAX) != null) {
			row.getCell(FAX).setCellType(Cell.CELL_TYPE_STRING);
			client.setFax(row.getCell(FAX).getStringCellValue());
		}
		
		if(row.getCell(MOBILE) != null) {
			row.getCell(MOBILE).setCellType(Cell.CELL_TYPE_STRING);
			client.setMobile(row.getCell(MOBILE).getStringCellValue());
		}
				
		if(row.getCell(EMAIL) != null)
			client.setEmail(row.getCell(EMAIL).getStringCellValue());
		
		if(row.getCell(ADDRESS) != null) {
			Address address = null;
			try {
				address = addressService.getByStreet(row.getCell(ADDRESS).getStringCellValue());
			} catch (Exception e) {
				address = new Address();
				address.setStreet(row.getCell(ADDRESS).getStringCellValue());
			
			}
			
			client.setAddress(address);
		}
		
		if(row.getCell(VAT) != null)
			client.setVat(row.getCell(VAT).getStringCellValue());
		
		if(row.getCell(TYPE) != null) {
			ClientType clientType = null;
			try {
				clientType = clientTypeService.getByCode(row.getCell(TYPE).getStringCellValue());
			} catch (Exception e) {
				clientType = new ClientType();
				clientType.setCode(row.getCell(TYPE).getStringCellValue());
				clientType.setDescription(row.getCell(TYPE).getStringCellValue());
									
			}
			
			client.setClientType(clientType);
		}
		
		if(row.getCell(GROUP) != null) {
			ClientGroup clientGroup = null;
			try {
				clientGroup = clientGroupService.getByName(row.getCell(GROUP).getStringCellValue());
			} catch (Exception e) {
				clientGroup = new ClientGroup();
				clientGroup.setName(row.getCell(GROUP).getStringCellValue());
				clientGroup.setDescription(row.getCell(GROUP).getStringCellValue());
									
			}
			
			client.setClientGroup(clientGroup);
		}
		
		if(row.getCell(COMMENT) != null)
			client.setComment(row.getCell(COMMENT).getStringCellValue());

		if(row.getCell(ACTIVE) != null) {
			if (row.getCell(ACTIVE).getStringCellValue().equals("1"))
				client.setActive(true);
			else
				client.setActive(false);				
		}
		
		return client;
		
	}
}
