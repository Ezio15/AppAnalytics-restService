package com.AppAnalytics.service;

import java.util.List;

import com.AppAnalytics.models.TblDimAndMet;
import com.AppAnalytics.models.TblDimenAndMetrics;

public interface DimensionsAndMetricsService {

	boolean createDimentionAndMetrics(TblDimenAndMetrics tblDimAndMet);

	List<TblDimAndMet> getDimensionAndMetrics();

}
