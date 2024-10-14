package com.studentapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static List<Student> studentList;
	private static Scanner scanner;

	public static void main(String[] args) {
		System.out.println("************ Student Management System ************");

		studentList = new ArrayList<Student>();
		scanner = new Scanner(System.in);
		while (true) {
			System.out.println("************ Welcome ************");

			System.out.println("Select an Option.....");
			System.out.println("1. Register a Student");
			System.out.println("2. Find Student with StudentId");
			System.out.println("3. List All Student Information");
			System.out.println("4. List Student Information in Sorted Order");
			System.out.println("5. Exit");
			int option = scanner.nextInt();

			switch (option) {
			case 1:
				enrollStudent(scanner);
				break;
			case 2:
				findStudentById(scanner);
				break;
			case 3:
				printAllStudentData();
				break;
			case 4:
				sortByName();
				break;
			case 5:
				exit();
				break;
			default:
				System.out.println("Invalid Option Selected!... Enter between 1 to 5");

			}
		}
	}

	private static void exit() {
		System.exit(0);

	}

	private static void printAllStudentData() {
		if (studentList.size() > 0) {

			System.out.println("----------------PRINTING All Student Data----------------");
			for (Student student : studentList) {
				student.printStudentInfo();
			}
			System.out.println("------------------------------*******************--------------------------------");
		} else {
			System.err.println("Student list is empty!!! No Student Record found");
		}
	}

	private static void findStudentById(Scanner scanner2) {
		Student studentFound = null;
		System.out.println("Enter the Student Id");
		String studentId = scanner2.next();
		try {
			studentFound = studentList.stream().filter(student -> student.getStudentId().equalsIgnoreCase(studentId))
					.findFirst().orElseThrow(() -> new RuntimeException("No Data Found!!!"));
		} catch (RuntimeException e) {
			System.out.println("Student with ID " + studentId + " not found!!!");
		}
		studentFound.printStudentInfo();
		;

	}

	private static void enrollStudent(Scanner scanner2) {

		System.out.println("Enter Student Name");
		String studentName = scanner2.next();

		System.out.println("Enter Student Age");
		int studentAge = scanner2.nextInt();

		System.out.println("Enter Student Id");
		String studentId = scanner2.next();

		Student newStudent = new Student(studentName, studentAge, studentId);
		studentList.add(newStudent);

		while (true) {
			System.out.println("Enter the Course to be enrolled!...Type Done to exit");
			String courseName = scanner2.next();
			if (courseName.equalsIgnoreCase("done")) {
				break;
			}

			newStudent.enrollCourse(courseName);
		}
		newStudent.printStudentInfo();
	}

	private static void sortByName() {

		// this single line replaces code block commented below
		Comparator<Student> studentNameComparator = (o1, o2) -> o1.getName().compareTo(o2.getName());

		/*
		 * Comparator <Student> studentNameComparator = new Comparator<Student>() { //
		 * // @Override // public int compare(Student o1, Student o2) { // // TODO
		 * Auto-generated method stub // return o1.getName().compareTo(o2.getName()); //
		 * } };
		 */
		Collections.sort(studentList, studentNameComparator);
		printAllStudentData();

	}
}