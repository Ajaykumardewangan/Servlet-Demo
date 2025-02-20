package com.java.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.java.service.EmpService;

public class EmpRepo {

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "root", "root");
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}

	public static List<EmpService> getRecords(int start, int total) {
		List<EmpService> list = new ArrayList<EmpService>();
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from servletuserdemo limit " + (start - 1) + "," + total);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				EmpService e = new EmpService();
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setEmail(rs.getString(4));
				list.add(e);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
}