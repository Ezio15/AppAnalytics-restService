package com.AppAnalytics.dao;

import java.util.List;

import com.AppAnalytics.models.TblDimAndMet;
import com.AppAnalytics.models.TblMetrics;

public interface DimensionsAndMetricsDao {


	List<TblDimAndMet> getDimensionsAndMetrics();

	boolean createDimentionAndMetrics(TblMetrics tblMetrics);

}
