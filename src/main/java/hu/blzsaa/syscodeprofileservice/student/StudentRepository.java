package hu.blzsaa.syscodeprofileservice.student;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

interface StudentRepository extends JpaRepository<StudentEntity, UUID> {

}
