package com.java.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecondServletController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			// Getting the value from the hidden field
			String n = request.getParameter("uname");
			out.print("Hello " + n);

			out.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}