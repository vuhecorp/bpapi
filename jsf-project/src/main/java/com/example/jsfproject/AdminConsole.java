package com.example.jsfproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.example.jsfproject.model.ApiRequest;

@Component
@RequestScope
public class AdminConsole {
	
	@Autowired
    JdbcTemplate jdbcTemplate;
	List<ApiRequest> requestList = new ArrayList<ApiRequest>();
	
	public AdminConsole() {
		
	}
	
	@PostConstruct
	public void onPageLoad() {
		requestList = loadApiRequestInfo();
	}

	private List<ApiRequest> loadApiRequestInfo() {
		DataSource ds  = jdbcTemplate.getDataSource();
		Connection con = null;
		List<ApiRequest> requestList = new ArrayList<ApiRequest>();
		
		try {
			con       			 = ds.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM VUHERNANDE.API_REQUEST");
			ResultSet rs 		 = ps.executeQuery();
			
			requestList = extractRequests(rs);

		} catch (SQLException e) {
			try {
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		return requestList;
	}


	
	private List<ApiRequest> extractRequests(ResultSet rs){
    	List<ApiRequest> r = new ArrayList<ApiRequest>();
    	
    	try {
			while(rs.next()) {
				
				Long id   = rs.getLong("ID");
				Date date = rs.getTimestamp("REQ_DATE");
				String ip = rs.getString("IP");
				
				ApiRequest b = new ApiRequest();
				b.setId(id);
				b.setRequestDate(date);
				b.setIpAddress(ip);
				
				r.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return r;
    }
	
	public List<ApiRequest> getRequestList() {
		return requestList;
	}

	public void setRequestList(List<ApiRequest> requestList) {
		this.requestList = requestList;
	}
}
