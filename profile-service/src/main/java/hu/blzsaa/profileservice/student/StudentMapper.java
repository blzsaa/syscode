package hu.blzsaa.profileservice.student;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import hu.blzsaa.profileservice.model.Student;
import hu.blzsaa.profileservice.model.StudentCreateDto;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = SPRING)
interface StudentMapper {

	Student mapEntityToDto(StudentEntity studentEntity);

	StudentEntity mapCreateDtoToEntity(StudentCreateDto studentCreateDto);

	@Mapping(target = "id", source = "studentId")
	StudentEntity mapCreateDtoAndIdToEntity(UUID studentId, StudentCreateDto studentCreateDto);

}
