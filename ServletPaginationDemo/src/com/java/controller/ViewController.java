package com.java.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.repo.EmpRepo;
import com.java.service.EmpService;

@WebServlet("/ViewServlet")
public class ViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String spageid = request.getParameter("page");
		int pageid = Integer.parseInt(spageid);
		int total = 5;
		if (pageid == 1) {
		} else {
			pageid = pageid - 1;
			pageid = pageid * total + 1;
		}
		List<EmpService> list = EmpRepo.getRecords(pageid, total);

		out.print("<h1>Page No: " + spageid + "</h1>");
		out.print("<table border='1' cellpadding='4' width='60%'>");
		out.print("<tr><th>Id</th><th>Name</th><th>Email</th>");
		for (EmpService e : list) {
			out.print("<tr><td>" + e.getId() + "</td><td>" + e.getName() + "</td><td>" + e.getEmail() + "</td></tr>");
		}
		out.print("</table>");

		out.print("<a href='ViewServlet?page=1'>1</a> ");
		out.print("<a href='ViewServlet?page=2'>2</a> ");
		out.print("<a href='ViewServlet?page=3'>3</a> ");

		out.close();
	}
}