package hu.blzsaa.profileservice.student;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {

}
