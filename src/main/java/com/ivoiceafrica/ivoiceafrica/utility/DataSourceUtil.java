package com.ivoiceafrica.ivoiceafrica.utility;


import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

public class DataSourceUtil {

	@Autowired
	DataSource dataSource;
	
	public void getConnectionSource() {
		
		//To know the datasource
		try {
			System.out.println("===>>>> Spring Boot Data Source: ===  "+dataSource.getConnection().getSchema());
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("===>>> An Error Occured: "+e.getMessage());
		}
	}
}
