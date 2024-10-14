package com.studentapp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student {
	private String name;
	private int age;
	private String studentId;
	private List<String> courses;

	public Student(String name, int age, String studentId) {
		super();
		if (validateAge(age) && validateName(name) && validateStidentID(studentId)) {
			this.name = name;
			this.age = age;
			this.studentId = studentId;
			courses = new ArrayList<String>(); // Initialization of courses
		}
	}

	private boolean validateStidentID(String studentId) {
		String studentIdRegex = "S-[0-9]+$"; // if you don't want to put [0-9] you can add \\d - for digit
		Pattern studentIdPattern = Pattern.compile(studentIdRegex);
		Matcher studentIdMather = studentIdPattern.matcher(studentId);
		if (studentIdMather.matches()) {
			return true;
		} else {
			System.err.println("Invalid Student Id!!! Please use format S-123");
			return false;
		}
	}

	public void enrollCourse(String course) {
		if (validateCourse(course)) {
			if (!courses.contains(course)) {
				courses.add(course);
				System.out.println("Student is enrolled to " + course + " successfully!!");

			} else {
				System.err.println("Student is already enrolled for this course " + course);
			}
		}
	}

	public void printStudentInfo() {
		System.out.println("************* Student Information *************");
		System.out.println("Student Name: " + name);
		System.out.println("Student Age: " + age);
		System.out.println("Student ID: " + studentId);
		System.out.println("Enrolled for: " + courses);
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", studentID=" + studentId + ", courses=" + courses + "]";
	}

	// validation methods
	public boolean validateAge(int age) {
		if (age >= 19 && age <= 35) {
			return true;
		} else {
			System.err.println("Invalid age!!! Student age needs to be between 19 and 35");
			return false;
		}
	}

	public boolean validateName(String name) {
		String nameRegex = "^[a-zA-Z\\s]+$";
		Pattern namePattern = Pattern.compile(nameRegex);
		Matcher nameMatcher = namePattern.matcher(name);

		if (nameMatcher.matches()) {
			return true;
		} else {
			System.err.println("Invalid name!!! Please enter alphabets only");
			return false;
		}
	}

	public boolean validateCourse(String course) {
		if (course.equalsIgnoreCase("Java") || course.equalsIgnoreCase("DSA") || course.equalsIgnoreCase("DevOps")) {
			return true;
		} else {
			System.err.println("Invalid course name!!! Please select Course from the list!!! [Java,DSA,DevOps]");
			return false;
		}
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getStudentId() {
		return studentId;
	}

	public List<String> getCourses() {
		return courses;
	}

}
