package com.AppAnalytics.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.AppAnalytics.models.TblDimAndMet;
import com.AppAnalytics.models.TblDimenAndMetrics;
import com.AppAnalytics.service.DimensionsAndMetricsService;
import com.AppAnalytics.utils.Constants;

@RestController
@RequestMapping("/api/Dim&Met")
public class DimensionsAndMetrics {

	private static final Logger logger = Logger.getLogger(DimensionsAndMetrics.class);

	@Autowired
	private DimensionsAndMetricsService dimenAndMetService;

	@RequestMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> createDimentionAndMetrics(@RequestBody TblDimenAndMetrics tblDimAndMet) {

		Map<String, Object> responseMap = new HashMap<String, Object>();
		boolean result = false;
		try {
			logger.info(tblDimAndMet.getName());
			logger.info(tblDimAndMet.getDimention().isFourteendayUsers() + "-" + tblDimAndMet.isSevendayUsers());
			logger.info("metrics-" + tblDimAndMet.getMetrics().isSevendayUsers());
			result = dimenAndMetService.createDimentionAndMetrics(tblDimAndMet);
			if (result)
				responseMap.put("Success", Constants.Messages.DIM_MET_MSG1);
			else
				responseMap.put("Failed", Constants.Messages.DIM_MET_FAIL_MSG1);
		} catch (Exception e) {
			logger.info("Exception Occurres : While Creating Dimensions and Metrics :" + e);
		}

		return responseMap;

	}

	@RequestMapping(value = "/getDimAndMet", method = RequestMethod.GET)
	public Map<String, Object> getDimentionAndMetrics() {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		List<TblDimAndMet> list = new ArrayList<TblDimAndMet>();
		try {

			list = dimenAndMetService.getDimensionAndMetrics();
			responseMap.put("Data", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseMap;
	}

}
