package hu.blzsaa.syscodeprofileservice.student;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import hu.blzsaa.syscodeprofileservice.model.Student;
import hu.blzsaa.syscodeprofileservice.model.StudentCreateDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = SPRING)
interface StudentMapper {

	Student map(StudentEntity studentEntity);

	StudentEntity map(StudentCreateDto studentCreateDto);

}
