package com.thingtrack.konekti.message.internal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.thingtrack.konekti.domain.Area;
import com.thingtrack.konekti.domain.Product;
import com.thingtrack.konekti.domain.ProductUnit;
import com.thingtrack.konekti.message.internal.domain.csv.product.CsvProduct;
import com.thingtrack.konekti.service.api.AreaService;
import com.thingtrack.konekti.service.api.ProductService;
import com.thingtrack.konekti.service.api.ProductTypeService;
import com.thingtrack.konekti.service.api.ProductUnitService;
import com.thingtrack.konekti.service.api.SupplierService;

public class ProductParser implements Processor {
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private ProductService productService;

	@Autowired
	private ProductTypeService productTypeService;

	@Autowired
	private ProductUnitService productUnitService;
	
	@Autowired
	private SupplierService supplierService;
	
	@SuppressWarnings("unchecked")
	public void process(Exchange exchange) throws Exception {    	    	
		List<CsvProduct> csvProducts = null;
		
		if (exchange.getIn().getBody() instanceof CsvProduct) {
			csvProducts = new ArrayList<CsvProduct>();
			
			csvProducts.add((CsvProduct) exchange.getIn().getBody());
		}
		else
    		csvProducts = (List<CsvProduct>) exchange.getIn().getBody();
    	
    	// A: Create or Update product
    	// D: Delete product
    	for (CsvProduct csvProduct : csvProducts) {
	    	if (csvProduct.getAction().equals("A"))
	    		productService.save(actualize(csvProduct));
	   	 	else if (csvProduct.getAction().equals("D"))
	   			productService.save(remove(csvProduct));
	       	   
    	}

    }
    
    private Product actualize(CsvProduct csvProduct) throws Exception {
    	Area area = areaService.getByCode(csvProduct.getAreaCode());
    	    	    	
    	Product product = null;
    	try {
    		if (csvProduct.getVersion() != null)
    			product = productService.getByCode(csvProduct.getCode(), csvProduct.getVersion());
    		else
    			product = productService.getByCode(csvProduct.getCode());
    		
        	if (!containsArea(product, csvProduct.getAreaCode()))
        		product.getAreas().add(area);
    	}
    	catch(Exception e) {
        	product = productService.createNewEntity(area);
    	}
    	
    	// product setters
    	product.setCode(csvProduct.getCode());
    	product.setName(csvProduct.getName());
    	product.setVersion(csvProduct.getVersion());
    	product.setDescription(csvProduct.getDescription());
    	product.setProductType(productTypeService.getByCode(csvProduct.getTypeCode()));
    	product.setHasLote(parseBoolean(csvProduct.getHasLote()));
    	product.setHasSerialNumber(parseBoolean(csvProduct.getHasSerialNumber()));
    	product.setHasExpeditionDate(parseBoolean(csvProduct.getHasExpeditionDate()));
    	product.setAveragePrice(Float.parseFloat(csvProduct.getAveragePrice()));
    	product.setProductSupplier(supplierService.getByCode(csvProduct.getSupplierCode()));
    	if (!containsProductUnit(product, csvProduct.getProductUnitCode()))
    		product.getProductUnits().add(productUnitService.getByCode(csvProduct.getProductUnitCode()));
    	product.setProductActive(parseBoolean(csvProduct.getProductActive()));
    	
    	return product;
    	
    }
    
    private Product remove(CsvProduct csvProduct) throws Exception {
    	Area area = areaService.getByCode(csvProduct.getAreaCode());
    	
    	Product product = null;
    	try {
    		if (csvProduct.getVersion() != null)
    			product = productService.getByCode(csvProduct.getCode(), csvProduct.getVersion());
    		else
    			product = productService.getByCode(csvProduct.getCode());
    	}
    	catch(Exception e) {
        	throw e;
        	
    	}
    	
    	// product setters
    	if (csvProduct.getAreaCode().equals("*"))
    		product.setProductActive(false);
    	
    	if (containsArea(product, csvProduct.getAreaCode()))
    		product.getAreas().remove(area);
    	
    	if (containsProductUnit(product, csvProduct.getProductUnitCode()))
    		product.getProductUnits().remove(productUnitService.getByCode(csvProduct.getProductUnitCode()));
    	
    	return product;
    }
    
    private boolean parseBoolean(String value) {
    	if (value.equals("0"))
    		return false;
    	else
    		return true;
    }
    
    private boolean containsArea(Product product, String areaCode) {
    	for (Area area : product.getAreas()) {
    		if (area.getCode().equals(areaCode))
    			return true;
    		
    	}
    	return false;
    }
    
    private boolean containsProductUnit(Product product, String productUnitCode) {
    	for (ProductUnit productUnit : product.getProductUnits()) {
    		if (productUnit.getCode().equals(productUnitCode))
    			return true;
    		
    	}
    	return false;
    }
}

