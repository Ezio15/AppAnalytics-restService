/**
 * 
 */
package com.AppAnalytics.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * @author Vignesh
 *
 */
public class CORSFilter implements Filter {
	
	static final Logger logger = Logger.getLogger(CORSFilter.class);

	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		logger.info("CORS Filter Called. . !");
		 
		//To allowing cross domain access 
        ((HttpServletResponse) response).setHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).setHeader("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS");
        ((HttpServletResponse) response).setHeader("Access-Control-Max-Age", "360");
        ((HttpServletResponse) response).setHeader("Access-Control-Allow-Headers", "x-requested-with, Origin, Content-Type, Accept, access-control-allow-headers, Access-Token, If-Modified-Since, Cache-Control, Pragma");
        ((HttpServletResponse) response).setHeader("Access-Control-Allow-Credentials", "true");
        ((HttpServletResponse) response).setHeader("Allow", "*");
        
        HttpServletRequest req = (HttpServletRequest) request;
        logger.info("API URI :: " + req.getRequestURI());
        if(req.getMethod().equals("OPTIONS")) {
        	System.out.println("Preflight Called");
        	((HttpServletResponse) response).setStatus(HttpServletResponse.SC_OK);
        	return;
        }
        if(req.getRequestURI().contains("api")) {
        	Long start = System.currentTimeMillis();
            chain.doFilter(request, response);
            Long responseTime = System.currentTimeMillis() - start;
            logger.info("API : " + req.getRequestURI() + " - Request Completed within " + responseTime +"ms");
            ((HttpServletResponse) response).setHeader("x-response-time", responseTime.toString()+"ms");
        }
        
	}


	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
