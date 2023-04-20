package com.simplilearn.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.simplilearn.entity.Student;
import com.simplilearn.util.HibernateUtil;

/**
 * Servlet implementation class ReadStudentServlet
 */

@WebServlet("/read-student")
public class ReadStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReadStudentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session session = sf.openSession();
		List<Student> students = session.createQuery(" from Student ").list();
		
		PrintWriter pw = response.getWriter();
		
		pw.println("<html><body>");
		
		if(students!=null && students.size()>0) {
			
			pw.println(" <style> table,td,th { border:2px solid green; padding:10px; } </style> ");
			pw.println("<table>");
			
			pw.println("<tr>");
			pw.println("<th> Student Id </th>");
			pw.println("<th> Studnet FirstName </th>");
			pw.println("<th> Student LastName </th>");
			pw.println("<th> Student Phone Numbers </th>");
			pw.println("<th> Student Course Names </th>");
			pw.println("<th> Student Address </th>");
			
			pw.println("</tr>");

			for(Student student : students) {
				pw.println("<tr>");
				pw.println("<td>"+student.getStudent_id()+"</td>");
				pw.println("<td>"+student.getFname()+"</td>");
				pw.println("<td>"+student.getLname()+"</td>");
				pw.println("<td>"+student.getPhones()+"</td>");
				pw.println("<td>" + student.getCourses()+"</td>");
				pw.println("<td>" + student.getAddress()+"</td>");
				pw.println("</tr>");
			}
			
			pw.println("</table>");
			
			
		} else {
			pw.println("No Records find in the DB, Please insert first!");
		}
		 
		
		
		pw.println("</body></html>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
