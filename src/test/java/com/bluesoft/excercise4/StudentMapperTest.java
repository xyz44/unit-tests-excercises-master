package com.bluesoft.excercise4;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bluesoft.excercise4.api.model.StudentDto;
import com.bluesoft.excercise4.mapper.StudentMapper;
import com.bluesoft.excercise4.model.Student;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class StudentMapperTest {
	private List<Student> studentList;
	private List<StudentDto> studentDtoList;
	private final Integer numberOfStudents = 10;

	@Test
	public void shouldProvideStudentTDO() {
		//given:
		studentList = AuxiliaryTestClass.prepareTestStudentLists(numberOfStudents);

		//then:
		studentDtoList = studentList.stream()
				 .map(StudentMapper::toDto)
				 .collect(Collectors.toList());

		//when:
		for (int i = 0; i < studentDtoList.size(); i++) {
			assertTrue(studentDtoList.get(i).getName().equals(studentList.get(i).getName()));
			assertTrue(studentDtoList.get(i).getPassportNumber().equals(studentList.get(i).getPassportNumber()));
		}
	}

	@Test
	public void shouldProvideStudent() {
		//given:
		studentDtoList = AuxiliaryTestClass.prepareTestStudentDTOLists(10);

		//then:
		studentList = studentDtoList.stream()
				.map(StudentMapper::toDomain)
				.collect(Collectors.toList());

		//when:
		for (int i = 0; i < studentDtoList.size(); i++) {
			assertTrue(studentList.get(i).getName().equals(studentList.get(i).getName()));
			assertTrue(studentList.get(i).getPassportNumber().equals(studentList.get(i).getPassportNumber()));
		}
	}
}
