package hu.blzsaa.syscodeprofileservice.student;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import hu.blzsaa.syscodeprofileservice.model.Student;
import hu.blzsaa.syscodeprofileservice.model.StudentCreateDto;
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
