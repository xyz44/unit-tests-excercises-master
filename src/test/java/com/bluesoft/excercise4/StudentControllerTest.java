package com.bluesoft.excercise4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.bluesoft.excercise4.api.StudentController;
import com.bluesoft.excercise4.api.model.StudentDto;
import com.bluesoft.excercise4.mapper.StudentMapper;
import com.bluesoft.excercise4.model.Student;
import com.bluesoft.excercise4.service.StudentService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

public class StudentControllerTest {
	private final StudentService mockStudentService = mock(StudentService.class);
	private final Integer numberOfStudents = 10;
	private List<Student> studentList;
	private StudentController mockStudentController = mock(StudentController.class);
	private StudentController studentController = new StudentController(mockStudentService);

	@BeforeEach
	public void init() {
		studentList = AuxiliaryTestClass.prepareTestStudentLists(numberOfStudents);
	}

	@Test
	public void shoulRretrieveAllStudentsTest() {
		//given:
		List<StudentDto> expectedStudentDtoList = studentList.stream()
				.map(StudentMapper::toDto)
				.collect(Collectors.toList());
		List<StudentDto> studentDtos = new ArrayList<>();
		List<StudentDto> actualStudentDtoList;

		for(Student student : studentList) {
			studentDtos.add(StudentMapper.toDto(student));
		}

		//when:
		when(mockStudentService.getAll()).thenReturn(studentDtos);
		actualStudentDtoList = studentController.retrieveAllStudents();

		//then:
		for (int i = 0; i < expectedStudentDtoList.size(); i++) {
			assertEquals(expectedStudentDtoList.get(i).getName(), actualStudentDtoList.get(i).getName());
			assertEquals(expectedStudentDtoList.get(i).getPassportNumber(), actualStudentDtoList.get(i).getPassportNumber());
		}
	}

	@Test
	public void shouldRetrieveStudent() {
		//given:
		StudentDto expectedStudent = new StudentDto("Kowalski5", "PL123A0005");
		StudentDto actualStudentDto = StudentMapper.toDto(studentList.get(4));

		//when:
		when(mockStudentService.get(4)).thenReturn(actualStudentDto);
		actualStudentDto = studentController.retrieveStudent(4);

		//then:
		assertEquals(expectedStudent.getName(), actualStudentDto.getName());
		assertEquals(expectedStudent.getPassportNumber(), actualStudentDto.getPassportNumber());
	}

	@Test
	public void shoulddeleteStudent() {
		//given:
		ResponseEntity responseEntity = ResponseEntity.noContent().build();

		//when:
		doNothing().when(mockStudentService).delete(anyLong());

		//then:
		assertEquals(studentController.deleteStudent(4).getStatusCode().toString(), responseEntity.getStatusCode().toString());
	}

	@Test
	public void shouldUpdateStudent() {
		//given:
		ResponseEntity responseEntity = ResponseEntity.ok().build();
		StudentDto actualStudentDto = StudentMapper.toDto(studentList.get(4));

		//when:
		when(mockStudentController.createStudent(StudentMapper.toDto(studentList.get(4)))).thenReturn(responseEntity);

		//then:
		assertEquals(studentController.updateStudent(actualStudentDto, 4).getStatusCode().toString(), responseEntity.getStatusCode().toString());
	}

	@Disabled
	@Test
	public void shouldCreateStudent() {
		//given:
		ResponseEntity expectedResponseEntity = ResponseEntity.ok().build();
		ResponseEntity actualResponseEntity;
		StudentDto actualStudentDto = StudentMapper.toDto(studentList.get(4));

		//when:
		when(mockStudentController.createStudent(actualStudentDto)).thenReturn(ResponseEntity.ok().build());
		actualResponseEntity = studentController.createStudent(actualStudentDto);

		//then:
		assertEquals(actualResponseEntity.getStatusCode().toString(), expectedResponseEntity.getStatusCode().toString());
	}
}
