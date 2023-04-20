package com.simplilearn.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.simplilearn.entity.Address;
import com.simplilearn.entity.Course;
import com.simplilearn.entity.PhoneNumber;
import com.simplilearn.entity.Student;
import com.simplilearn.util.HibernateUtil;

/**
 * Servlet implementation class AddStudentServlet
 */
@WebServlet("/add-student")
public class AddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddStudentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("add-student.html").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Prepare Object
		Student student = populateStudentObject(request);

		// STEP 1 : Gets SessionFactory object
		SessionFactory sf = HibernateUtil.buildSessionFactory();

		// STEP 2: Open Session
		Session session = sf.openSession();

		// STEP 3: Save entity into DB
		Transaction tx = session.beginTransaction();
		session.save(student);
		tx.commit();

		// STEP 4: Close Session
		session.close();
		
		response.sendRedirect("read-student");
	}

	private Student populateStudentObject(HttpServletRequest request) {
		Student student = new Student();

		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");

		String phone_1 = request.getParameter("phone_1");
		String phone_type_1 = request.getParameter("phone_type_1");

		String phone_2 = request.getParameter("phone_2");
		String phone_type_2 = request.getParameter("phone_type_2");

		String phone_3 = request.getParameter("phone_3");
		String phone_type_3 = request.getParameter("phone_type_3");
		
		String course_name_1 = request.getParameter("course_1");
		String course_type_1 = request.getParameter("course_type_1");
		
		String course_name_2 = request.getParameter("course_2");
		String course_type_2 = request.getParameter("course_type_2");
		
		String course_name_3 = request.getParameter("course_3");
		String course_type_3 = request.getParameter("course_type_3");
		
		String street = request.getParameter("street");
		String state = request.getParameter("state");
		String city = request.getParameter("city");

		// Logic to populate Phones
		List<PhoneNumber> phones = new ArrayList<>();
		PhoneNumber phoneNumber1 = new PhoneNumber();
		phoneNumber1.setPhoneNumber(phone_1);
		phoneNumber1.setPhoneType(phone_type_1);
		phoneNumber1.setStudent(student);

		PhoneNumber phoneNumber2 = new PhoneNumber();
		phoneNumber2.setPhoneNumber(phone_2);
		phoneNumber2.setPhoneType(phone_type_2);
		phoneNumber2.setStudent(student);

		PhoneNumber phoneNumber3 = new PhoneNumber();
		phoneNumber3.setPhoneNumber(phone_3);
		phoneNumber3.setPhoneType(phone_type_3);
		phoneNumber3.setStudent(student);

		phones.add(phoneNumber1);
		phones.add(phoneNumber2);
		phones.add(phoneNumber3);
		
		
		// Logic to populate Courses
		List<Student> students = new ArrayList<>();
		students.add(student);
		
		List<Course> courses = new ArrayList<>();
		
		Course course1 = new Course();
		course1.setCourseName(course_name_1);
		course1.setCourseType(course_type_1);
		course1.setStudents(students);
		
		Course course2 = new Course();
		course2.setCourseName(course_name_2);
		course2.setCourseType(course_type_2);
		course2.setStudents(students);
		
		Course course3 = new Course();
		course3.setCourseName(course_name_3);
		course3.setCourseType(course_type_3);
		course3.setStudents(students);
		
		courses.add(course1);
		courses.add(course2);
		courses.add(course3);
		
		student.setFname(firstName);
		student.setLname(lastName);
		student.setPhones(phones);
		student.setCourses(courses);
		
		// Write Logic to populate Address
		Address address = new Address();
		address.setStreet(street);
		address.setCity(city);
		address.setState(state);
		
		student.setAddress(address);
		
		return student;
	}

}
