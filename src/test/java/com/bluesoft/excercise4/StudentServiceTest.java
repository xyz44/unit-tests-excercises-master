package com.bluesoft.excercise4;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.bluesoft.excercise4.api.model.StudentDto;
import com.bluesoft.excercise4.error.StudentNotFoundException;
import com.bluesoft.excercise4.mapper.StudentMapper;
import com.bluesoft.excercise4.model.Student;
import com.bluesoft.excercise4.repository.StudentRepository;
import com.bluesoft.excercise4.service.StudentService;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentServiceTest {
	private final StudentRepository mockStudentRepository = mock(StudentRepository.class);
	private StudentService studentService = new StudentService(mockStudentRepository);
	private final Integer numberOfStudents = 10;
	private List<Student> studentList;

	@BeforeEach
	public void init() {
		studentList = AuxiliaryTestClass.prepareTestStudentLists(numberOfStudents);
	}

	@Test
	public void shouldGivenAllStudents() {
		//given:
		List<StudentDto> actualStudentDtoList;
		List<StudentDto> expectedAllStudentDtoList = studentList.stream()
				.map(StudentMapper::toDto)
				.collect(Collectors.toList());

		//when:
		when(mockStudentRepository.findAll()).thenReturn(studentList);
		actualStudentDtoList =  studentService.getAll();

		//then:
		for (int i = 0; i < expectedAllStudentDtoList.size(); i++) {
			assertEquals(expectedAllStudentDtoList.get(i).getName(), actualStudentDtoList.get(i).getName());
			assertEquals(expectedAllStudentDtoList.get(i).getPassportNumber(), actualStudentDtoList.get(i).getPassportNumber());
		}
	}

	@Test
	public void shouldGivenStudentById() {
		//given:
		Integer index = new Random().nextInt(numberOfStudents);
		StudentDto actualStudentDto;
		Student student = studentList.get(index);
		StudentDto expectedStudentDto = StudentMapper.toDto(student);


		//when:
		when(mockStudentRepository.findById(index.longValue())).thenReturn(java.util.Optional.ofNullable(student));
		when(mockStudentRepository.findById(-1L)).thenThrow(StudentNotFoundException.class);
		actualStudentDto = studentService.get(index);

		//then:
		assertEquals(expectedStudentDto.getName(), actualStudentDto.getName());
		assertEquals(expectedStudentDto.getPassportNumber(), actualStudentDto.getPassportNumber());
		assertThrows(StudentNotFoundException.class, () -> studentService.get(-1L));
	}
}
