package com.bluesoft.excercise4;

import com.bluesoft.excercise4.api.model.StudentDto;
import com.bluesoft.excercise4.model.Student;
import java.util.ArrayList;
import java.util.List;

public class AuxiliaryTestClass {

	protected static List<Student> prepareTestStudentLists(Integer numberOfSudents) {
		List<Student> studentList = new ArrayList<>(numberOfSudents);

		for (long i = 0; i < numberOfSudents; i++) {
			studentList.add(new Student(i, "Kowalski" + (i+1), "PL123A000" + (i+1)));
		}

		return studentList;
	}

	protected static List<StudentDto> prepareTestStudentDTOLists(Integer numberOfSudents) {
		List<StudentDto> studentListDto = new ArrayList<>(numberOfSudents);

		for (long i = 0; i < numberOfSudents; i++) {
			studentListDto.add(new StudentDto("Nowak" + (i+1), "GB987C000" + (i+1)));
		}

		return studentListDto;
	}
}
