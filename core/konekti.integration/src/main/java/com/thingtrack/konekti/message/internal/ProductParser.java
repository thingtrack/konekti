package com.thingtrack.konekti.message.internal;

import org.springframework.beans.factory.annotation.Autowired;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.thingtrack.konekti.domain.Product;
import com.thingtrack.konekti.message.internal.domain.csv.product.CsvProduct;
import com.thingtrack.konekti.service.api.ProductService;
import com.thingtrack.konekti.service.api.ProductTypeService;
import com.thingtrack.konekti.service.api.ProductUnitService;

public class ProductParser implements Processor {
	@Autowired
	private ProductService productService;

	@Autowired
	private ProductTypeService productTypeService;

	@Autowired
	private ProductUnitService productUnitService;
	
	public void process(Exchange exchange) throws Exception {    	    	
    	CsvProduct csvProduct = (CsvProduct)exchange.getIn().getBody();
    	
    	// C: Create product
    	// U: Update product
    	// D: Delete product
    	if (csvProduct.getAction() != null && csvProduct.getAction().equals("C")) {
		 productService.save(parse(csvProduct));
	 	}
	 	else if (csvProduct.getAction() != null &&  csvProduct.getAction().equals("U")) {
			productService.save(parse(csvProduct));
	 	}
	 	else if (csvProduct.getAction() != null &&  csvProduct.getAction().equals("D")) {
			Product product = parse(csvProduct);
		 	//product.setActive(false);
		 
		 	productService.save(product);
	 	}
    	    	    	
        //System.out.println("We just downloaded: " + exchange.getIn().getHeader("CamelFileName"));
    }
    
    private Product parse(CsvProduct csvProduct) throws Exception {
    	return null;
    	
    }
}

