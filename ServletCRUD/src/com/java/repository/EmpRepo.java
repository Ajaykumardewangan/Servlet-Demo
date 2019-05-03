package com.java.repository;

import com.java.entity.EmpEntity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmpRepo {

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Servlet", "root", "root");
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}

	public static int save(EmpEntity e) {
		int status = 0;
		try {
			Connection con = EmpRepo.getConnection();
			PreparedStatement ps = con
					.prepareStatement("insert into ServletUserDemo(name,pass,email,country) values (?,?,?,?)");
			ps.setString(1, e.getName());
			ps.setString(2, e.getPassword());
			ps.setString(3, e.getEmail());
			ps.setString(4, e.getCountry());

			status = ps.executeUpdate();

			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}

	public static int update(EmpEntity e) {
		int status = 0;
		try {
			Connection con = EmpRepo.getConnection();
			PreparedStatement ps = con
					.prepareStatement("update ServletUserDemo set name=?,pass=?,email=?,country=? where id=?");
			ps.setString(1, e.getName());
			ps.setString(2, e.getPassword());
			ps.setString(3, e.getEmail());
			ps.setString(4, e.getCountry());
			ps.setInt(5, e.getId());

			status = ps.executeUpdate();

			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}

	public static int delete(int id) {
		int status = 0;
		try {
			Connection con = EmpRepo.getConnection();
			PreparedStatement ps = con.prepareStatement("delete from ServletUserDemo where id=?");
			ps.setInt(1, id);
			status = ps.executeUpdate();

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	public static EmpEntity getEmployeeById(int id) {
		EmpEntity e = new EmpEntity();

		try {
			Connection con = EmpRepo.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from ServletUserDemo where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setPassword(rs.getString(3));
				e.setEmail(rs.getString(4));
				e.setCountry(rs.getString(5));
			}
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return e;
	}

	public static List<EmpEntity> getAllEmployees() {
		List<EmpEntity> list = new ArrayList<EmpEntity>();

		try {
			Connection con = EmpRepo.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from ServletUserDemo");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				EmpEntity e = new EmpEntity();
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setPassword(rs.getString(3));
				e.setEmail(rs.getString(4));
				e.setCountry(rs.getString(5));
				list.add(e);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}