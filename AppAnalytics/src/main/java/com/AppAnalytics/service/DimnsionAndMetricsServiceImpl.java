package com.AppAnalytics.service;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.AppAnalytics.controllers.DimensionsAndMetrics;
import com.AppAnalytics.dao.DimensionsAndMetricsDao;
import com.AppAnalytics.dao.DimensionsAndMetricsDaoImpl;
import com.AppAnalytics.models.TblDimAndMet;
import com.AppAnalytics.models.TblDimenAndMetrics;
import com.AppAnalytics.models.TblMetrics;

@Service
public class DimnsionAndMetricsServiceImpl implements DimensionsAndMetricsService{
	
	

	private static final Logger logger = Logger.getLogger(DimnsionAndMetricsServiceImpl.class);

	@Autowired
	DimensionsAndMetricsDao dimAndMetDao;
	
	
	public boolean createDimentionAndMetrics(TblDimenAndMetrics tblDimAndMet) {
		boolean result = false;
		TblMetrics tblMetrics = new TblMetrics();
		try {
			logger.info("hi");
			System.out.println("hi ran");
			tblMetrics.setUsers(tblDimAndMet.getMetrics().isUsers());
			tblMetrics.setOnedayUsers(tblDimAndMet.getMetrics().isOnedayUsers());
			tblMetrics.setSevendayUsers(tblDimAndMet.getMetrics().isSevendayUsers());
			tblMetrics.setFourteendayUsers(tblDimAndMet.getMetrics().isFourteendayUsers());
			tblMetrics.setTwenty8dayUsers(tblDimAndMet.getMetrics().isTwenty8dayUsers());
			tblMetrics.setThirtydayUsers(tblDimAndMet.getMetrics().isThirtydayUsers());
			result = dimAndMetDao.createDimentionAndMetrics(tblMetrics);
		}catch (Exception e) {
			logger.error("Error Occurred : While creating Dimesnion and Metrics Field "+e);
		}
		
		return result;
	}




	public List<TblDimAndMet> getDimensionAndMetrics() {
		List<TblDimAndMet> list = new ArrayList<TblDimAndMet>();
		try {
			list = dimAndMetDao.getDimensionsAndMetrics();
		}catch (Exception e) {
			logger.error("Error Occurred : While getting Dimension and Metrics Fiels "+e);
		}
		return list;
	}
	

}
