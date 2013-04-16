package com.thingtrack.konekti.view.module.product.internal;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;

import com.thingtrack.konekti.domain.Product;
import com.thingtrack.konekti.domain.ProductType;
import com.thingtrack.konekti.domain.Supplier;
import com.thingtrack.konekti.domain.Area;
import com.thingtrack.konekti.service.api.ProductTypeService;
import com.thingtrack.konekti.service.api.SupplierService;
import com.thingtrack.konekti.view.kernel.IWorkbenchContext;

public class Excel2Entity {
	private HSSFRow row;
	
	private Product product = null;
	
	private static final int CODE = 0;
	private static final int NAME = 1;
	private static final int DESCRIPTION = 2;
	private static final int HAS_EXPEDITION_DATE = 3;
	private static final int HAS_LOTE = 4;
	private static final int HAS_SERIAL_NUMBER = 5;
	private static final int AVERAGE_PRICE = 6;
	private static final int SUPPLIER_CODE = 7;
	private static final int TYPE = 8;
	private static final int ACTIVE = 9;
		
	private ProductTypeService productTypeService;
	private SupplierService supplierService;
	
	public Excel2Entity(IWorkbenchContext context, ProductTypeService productTypeService, SupplierService supplierService, HSSFRow row) {	    
		this.productTypeService = productTypeService;
	    this.supplierService = supplierService;
	    
		this.row = row;
		
		product = new Product();
		List<Area> areas = new ArrayList<Area>();
		areas.add(context.getUser().getActiveArea());
			
		product.setAreas(areas);
	}
	
	public Product parse() throws Exception {		
		if(row.getCell(CODE) != null)
			product.setCode(row.getCell(CODE).getStringCellValue());
		
		if(row.getCell(NAME) != null)
			product.setName(row.getCell(NAME).getStringCellValue());

		if(row.getCell(DESCRIPTION) != null)
			product.setDescription(row.getCell(DESCRIPTION).getStringCellValue());

		if(row.getCell(HAS_EXPEDITION_DATE) != null) {
			if (row.getCell(HAS_EXPEDITION_DATE).getStringCellValue().equals("1"))
				product.setHasExpeditionDate(true);
		}
		
		if(row.getCell(HAS_LOTE) != null) {
			if (row.getCell(HAS_LOTE).getStringCellValue().equals("1"))
				product.setHasLote(true);
		}
			
		if(row.getCell(HAS_SERIAL_NUMBER) != null) {
			if (row.getCell(HAS_SERIAL_NUMBER).getStringCellValue().equals("1"))
				product.setHasSerialNumber(true);
		}
						
		if(row.getCell(AVERAGE_PRICE) != null) {
			row.getCell(AVERAGE_PRICE).setCellType(Cell.CELL_TYPE_STRING);		
			product.setAveragePrice(Float.parseFloat(row.getCell(AVERAGE_PRICE).getStringCellValue()));			
		}
				
		if(row.getCell(SUPPLIER_CODE) != null) {
			Supplier supplier = null;
			try {
				supplier = supplierService.getByCode(row.getCell(SUPPLIER_CODE).getStringCellValue());
			} catch (Exception e) {
				throw e;								
			}
			
			product.setProductSupplier(supplier);
		}
		
		if(row.getCell(TYPE) != null) {
			ProductType productType = null;
			try {
				productType = productTypeService.getByCode(row.getCell(TYPE).getStringCellValue());
			} catch (Exception e) {
				throw e;
									
			}
			
			product.setProductType(productType);
		}

		if(row.getCell(ACTIVE) != null) {
			if (row.getCell(ACTIVE).getStringCellValue().equals("1"))
				product.setProductActive(true);
			else
				product.setProductActive(false);				
		}
		
		return product;
		
	}
}
