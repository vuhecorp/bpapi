package com.example.jsfproject;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ReadingController {

	@Autowired
    JdbcTemplate jdbcTemplate;
	
	@RequestMapping(path="/reading/{metric}/{op}/{val}")
	public List<BpReadingDTO> getReadings(@PathVariable("metric") String metric, 
										   @PathVariable("op") String operator, 
										   @PathVariable("val") int value, HttpServletRequest request, 
									        HttpServletResponse response) {
		
		DataSource ds 		 = jdbcTemplate.getDataSource();
		List<BpReadingDTO> r = new ArrayList<BpReadingDTO>();
		
		Map<String, String> opMap = new HashMap<String, String>();
		opMap.put("GT", ">");
		opMap.put("LT", "<");
		opMap.put("EQ", "=");
		
		Connection con = null;
		try {
			con       			 = ds.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM VUHERNANDE.BP_READING WHERE " + metric.toUpperCase() + " " + opMap.get(operator.toUpperCase()) + " " + value);
			ResultSet rs 		 = ps.executeQuery();
			
			r = extract(rs);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			String st = "INSERT INTO VUHERNANDE.API_REQUEST ( IP, REQ_DATE) VALUES ('"+ request.getRemoteAddr() +"', '"+ sdf.format(new Date()) +"')";
			
			ps = con.prepareStatement(st);
			ps.execute();
			
		} catch (SQLException e) {
			try {
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		return r;
	}
	
	
    @RequestMapping(path="/reading/date/{from}/{to}")
    public List<BpReadingDTO> getMessage(@PathVariable("from") int from, @PathVariable("to") int to) {
        
    	DataSource ds 		 = jdbcTemplate.getDataSource();
		List<BpReadingDTO> r = new ArrayList<BpReadingDTO>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Connection con       = null;
		try {
			
			Date fromDate = parseDate(from);
			Date toDate   = parseDate(to);

			con       			 = ds.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM VUHERNANDE.BP_READING WHERE DATE BETWEEN '" + sdf.format(fromDate) + "' AND '" + sdf.format(toDate) +  "' ORDER BY DATE DESC");
			ResultSet rs         = ps.executeQuery();
			
			r = extract(rs);
			
		} catch (SQLException e) {
			try {
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} 
		
		return r;
    }
    
    private List<BpReadingDTO> extract(ResultSet rs){
    	List<BpReadingDTO> r = new ArrayList<BpReadingDTO>();
    	
    	try {
			while(rs.next()) {
				Long id = rs.getLong("ID");
				int s   = rs.getInt("SYSTOLIC");
				int d   = rs.getInt("DIASTOLIC");
				Date date = rs.getTimestamp("DATE");
				String desc = rs.getString("DESCRIPTION");
				String tags = rs.getString("TAGS");
				int pulse = rs.getInt("PULSE");
				BigDecimal weight = rs.getBigDecimal("WEIGHT");
				
				BpReadingDTO b = new BpReadingDTO();
				b.setId(id);
				b.setSystolic(s);
				b.setDiastolic(d);
				b.setDate(date);
				b.setPulse(pulse);
				b.setWeight(weight);
				b.setDescription(desc);
				b.setTags(tags);
				
				r.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return r;
    }
    
    private Date parseDate(int date) {
    	int year = date / 10000;
		int month = (date % 10000) / 100;
		int day = date % 100;
		return new GregorianCalendar(year, month, day).getTime();
    }
}