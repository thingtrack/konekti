package com.thingtrack.konekti.dao.impl.internal;

import org.springframework.stereotype.Repository;

import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.dao.api.CalendarDetailDao;
import com.thingtrack.konekti.domain.CalendarDetail;

/**
 * @author Thingtrack S.L.
 *
 */
@Repository
public class CalendarDetailDaoImpl extends JpaDao<CalendarDetail, Integer> implements CalendarDetailDao {

}

