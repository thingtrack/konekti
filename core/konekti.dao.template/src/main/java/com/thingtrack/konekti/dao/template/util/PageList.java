package com.thingtrack.konekti.dao.template.util;

import java.util.List;

public class PageList<X> {
	
	private int pageSize;
	private int pageIndex;
	private int pages;
	private List<X> entities;
	
	public PageList(int pageSize, int pageIndex, int pages, List<X> entities) {
		super();
		
		this.pageSize = pageSize;
		this.pageIndex = pageIndex;
		this.pages = pages;
		this.entities = entities;
	}

	public int getPageSize(){
		return this.pageSize;
	}
	
	public int getPageIndex() {
		return pageIndex;
	}
	
	public int getPages() {
		return pages;
	}
	
	public List<X> getEntities() {
		return entities;
	}	
}
