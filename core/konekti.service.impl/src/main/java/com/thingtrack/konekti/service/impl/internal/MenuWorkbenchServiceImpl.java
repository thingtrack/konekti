package com.thingtrack.konekti.service.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.api.MenuWorkbenchDao;
import com.thingtrack.konekti.domain.MenuWorkbench;
import com.thingtrack.konekti.service.api.MenuWorkbenchService;

public class MenuWorkbenchServiceImpl implements MenuWorkbenchService {
	@Autowired
	private MenuWorkbenchDao menuWorkbenchDao;
	
	@Override
	public List<MenuWorkbench> getAll() throws Exception {
		return this.menuWorkbenchDao.getAll();
		
	}

	@Override
	public MenuWorkbench get(Integer menuWorkbenchId) throws Exception {
		return this.menuWorkbenchDao.get(menuWorkbenchId);
		
	}

}
