package com.thingtrack.konekti.view.module.employee.internal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;

import com.thingtrack.konekti.domain.Address;
import com.thingtrack.konekti.domain.EmployeeAgent;
import com.thingtrack.konekti.domain.EmployeeAgentStatus;
import com.thingtrack.konekti.domain.EmployeeAgentType;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.service.api.AddressService;
import com.thingtrack.konekti.service.api.EmployeeAgentStatusService;
import com.thingtrack.konekti.service.api.EmployeeAgentTypeService;
import com.thingtrack.konekti.view.kernel.IWorkbenchContext;

public class Excel2Entity {
	private HSSFRow row;
	
	private EmployeeAgent employeeAgent = null;
	
	private static final int WORKNUMBER = 0;
	private static final int NAME = 1;
	private static final int SURNAME = 2;
	private static final int NIF = 3;
	private static final int WORKMOBILE = 4;
	private static final int PHONE = 5;
	private static final int FAX = 6;
	private static final int MOBILE = 7;
	private static final int EMAIL = 8;
	private static final int ADDRESS = 9;
	private static final int SENIORITY = 10;
	private static final int BIRTHDAY = 11;
	private static final int TYPE = 12;
	private static final int COMMENT = 13;
	private static final int STATUS = 14;
	
	private IWorkbenchContext context;
	private AddressService addressService;
	private EmployeeAgentTypeService employeeAgentTypeService;
	private EmployeeAgentStatusService employeeAgentStatusService;
	
	private EmployeeAgentStatus defaultEmployeeAgentStatus;
	private DateFormat formatter;
		
	public Excel2Entity(IWorkbenchContext context, AddressService addressService, EmployeeAgentTypeService employeeAgentTypeService, EmployeeAgentStatusService employeeAgentStatusService, HSSFRow row) {	    
		this.context = context;
		this.addressService = addressService;
		this.employeeAgentTypeService = employeeAgentTypeService;
	    this.employeeAgentStatusService = employeeAgentStatusService;
	    
		this.row = row;
		
		employeeAgent = new EmployeeAgent();
		List<Organization> organizations = new ArrayList<Organization>();
		organizations.add(context.getUser().getActiveOrganization());
			
		//employeeAgent.setOrganizations(organizations);
		
		try {
			defaultEmployeeAgentStatus = employeeAgentStatusService.getByName("ACTIVE");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		formatter = new SimpleDateFormat("dd/MM/yyyy");
	}
	
	public EmployeeAgent parse() throws Exception {		
		if(row.getCell(WORKNUMBER) != null) {
			row.getCell(WORKNUMBER).setCellType(Cell.CELL_TYPE_STRING);
			employeeAgent.setWorkNumber(row.getCell(WORKNUMBER).getStringCellValue());
		}
		
		if(row.getCell(NAME) != null)
			employeeAgent.setName(row.getCell(NAME).getStringCellValue());

		if(row.getCell(SURNAME) != null)
			employeeAgent.setSurname(row.getCell(SURNAME).getStringCellValue());
		
		if(row.getCell(NIF) != null) {
			row.getCell(NIF).setCellType(Cell.CELL_TYPE_STRING);
			employeeAgent.setNif(row.getCell(NIF).getStringCellValue());
		}			

		if(row.getCell(WORKMOBILE) != null) {
			row.getCell(WORKMOBILE).setCellType(Cell.CELL_TYPE_STRING);
			employeeAgent.setWorkMobile(row.getCell(WORKMOBILE).getStringCellValue());
		}
		
		if(row.getCell(PHONE) != null) {
			row.getCell(PHONE).setCellType(Cell.CELL_TYPE_STRING);
			employeeAgent.setPhone(row.getCell(PHONE).getStringCellValue());
		}
			
		if(row.getCell(FAX) != null) {
			row.getCell(FAX).setCellType(Cell.CELL_TYPE_STRING);
			employeeAgent.setFax(row.getCell(FAX).getStringCellValue());
		}
		
		if(row.getCell(MOBILE) != null) {
			row.getCell(MOBILE).setCellType(Cell.CELL_TYPE_STRING);
			employeeAgent.setMobile(row.getCell(MOBILE).getStringCellValue());
		}
				
		if(row.getCell(EMAIL) != null)
			employeeAgent.setEmail(row.getCell(EMAIL).getStringCellValue());
		
		if(row.getCell(ADDRESS) != null) {
			Address address = null;
			try {
				address = addressService.getByStreet(row.getCell(ADDRESS).getStringCellValue());
			} catch (Exception e) {
				address = new Address();
				address.setStreet(row.getCell(ADDRESS).getStringCellValue());
			
			}
			
			employeeAgent.setAddress(address);
		}
				
		if(row.getCell(TYPE) != null) {
			EmployeeAgentType employeeAgentType = null;
			try {
				employeeAgentType = employeeAgentTypeService.getByName(row.getCell(TYPE).getStringCellValue());
			} catch (Exception e) {
				employeeAgentType = new EmployeeAgentType();
				employeeAgentType.setName(row.getCell(TYPE).getStringCellValue());
				employeeAgentType.setDescription(row.getCell(TYPE).getStringCellValue());
									
			}
			
			employeeAgent.setEmployeeAgentType(employeeAgentType);
		}							
				
		if(row.getCell(COMMENT) != null)
			employeeAgent.setComment(row.getCell(COMMENT).getStringCellValue());

		if(row.getCell(SENIORITY) != null)			 			   			  
			employeeAgent.setSeniority(formatter.parse(row.getCell(SENIORITY).getStringCellValue()));
		
		if(row.getCell(BIRTHDAY) != null && row.getCell(BIRTHDAY).getStringCellValue() != "")		 			   			  
			employeeAgent.setBirthday(formatter.parse(row.getCell(BIRTHDAY).getStringCellValue()));
		
		EmployeeAgentStatus employeeAgentStatus = null;
		if(row.getCell(STATUS) != null) {
			try {
				employeeAgentStatus = employeeAgentStatusService.getByName(row.getCell(STATUS).getStringCellValue());
			} catch (Exception e) {
					throw e;								
			}
						
			employeeAgent.setEmployeeAgentStatus(employeeAgentStatus);
		}	
		else
			employeeAgentStatus = defaultEmployeeAgentStatus;
		
		employeeAgent.setEmployeeAgentStatus(employeeAgentStatus);
		
		return employeeAgent;
	}
	
}
