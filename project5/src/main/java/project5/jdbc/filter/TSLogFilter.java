package project5.jdbc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

import project5.servlet.SearchServlet;

public class TSLogFilter implements Filter {

	private static Logger log = Logger.getLogger(SearchServlet.class.getName());
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		
		long tsStartTime = System.nanoTime();
		System.out.println("TS hit!");
		arg2.doFilter(arg0, arg1);
		
		long tsEndTime = System.nanoTime();
		log.info("TS:"+(tsEndTime-tsStartTime));
		// TODO Auto-generated method stub

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
