package com.testboard.biz;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	BasicDataSource dataSource;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		System.out.println("This is local date");
		return "/WEB-INF/views/home.jsp";
	}
	
	@RequestMapping("/dbTest.do")
	public String dbTest(Model model) {
		Connection conn = null;
		Statement st = null;
		
		try {
			conn = dataSource.getConnection();
			st = conn.createStatement();
//			ResultSet rs = st.executeQuery("select count(*) as cnt from board;");
			ResultSet rs = st.executeQuery("select now() as now;");
			
			while(rs.next()) {
//				model.addAttribute("serverTime", rs.getString("cnt"));
				model.addAttribute("serverTime", rs.getString("now"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(st != null) st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("This is server date");
		return "/WEB-INF/views/home_Server.jsp";
	}
}
