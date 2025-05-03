package uz.urspi.student.application;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    boolean existsByUniqueNumber(String uniqueNumber);
    Application findByUniqueNumber(String uniqueNumber);
}
