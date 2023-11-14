package hu.blzsaa.syscodeprofileservice.student;

import hu.blzsaa.syscodeprofileservice.model.Student;
import hu.blzsaa.syscodeprofileservice.model.StudentCreateDto;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
class StudentService {

	private final StudentRepository studentRepository;

	private final StudentMapper studentMapper;

	public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
		this.studentRepository = studentRepository;
		this.studentMapper = studentMapper;
	}

	public Student getStudentById(UUID studentId) {
		return studentRepository.findById(studentId).map(studentMapper::mapEntityToDto).orElseThrow();
	}

	public Student createStudent(StudentCreateDto studentCreateDto) {
		StudentEntity student = studentMapper.mapCreateDtoToEntity(studentCreateDto);
		return studentMapper.mapEntityToDto(studentRepository.save(student));
	}

	public void deleteStudent(UUID studentId) {
		studentRepository.deleteById(studentId);
	}

	public List<Student> listStudents() {
		return studentRepository.findAll().stream().map(studentMapper::mapEntityToDto).toList();
	}

	public Student updateStudent(UUID studentId, StudentCreateDto newValues) {
		StudentEntity toSave = studentMapper.mapCreateDtoAndIdToEntity(studentId, newValues);
		StudentEntity saved = studentRepository.save(toSave);
		return studentMapper.mapEntityToDto(saved);
	}

}
