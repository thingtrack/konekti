package com.thingtrack.konekti.service.impl.internal;

import java.sql.Connection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.api.ReportDao;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.Report;
import com.thingtrack.konekti.domain.Sequence;
import com.thingtrack.konekti.domain.User;
import com.thingtrack.konekti.service.api.ReportService;
import com.thingtrack.konekti.service.api.SequenceService;

public class ReportServiceImpl implements ReportService {
	@Autowired
	private ReportDao reportDao;
	
	@Autowired
	private SequenceService sequenceService;
	
	@Override
	public List<Report> getAll() throws Exception {
		return this.reportDao.getAll();
	}

	@Override
	public Report get(Integer reportId) throws Exception {
		return this.reportDao.get(reportId);
	}

	@Override
	public Report getByCode(Organization organization, String code) throws Exception {
		return this.reportDao.getByCode(organization, code);
	}

	@Override
	public Report save(Report report) throws Exception {
		return this.reportDao.save(report);
	}

	@Override
	public void delete(Report report) throws Exception {
		this.reportDao.delete(report);
		
	}

	@Override
	public List<Report> getAll(User user) throws Exception {
		return this.reportDao.getAll(user);
	}

	@Override
	public Report createNewReport(Organization organization) throws Exception {
		Report report = new Report();
		
		report.setCode(sequenceService.setNextSequence(Sequence.CODE.REPORT.name()));
		report.setOrganization(organization);
		report.setActive(true);
		
		return report;
	}
	
	@Override
	public Connection getConnection() throws Exception {
		return reportDao.getConnection();
	}
}
