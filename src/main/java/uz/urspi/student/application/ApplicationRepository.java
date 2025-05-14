package uz.urspi.student.application;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    boolean existsByUniqueNumber(String uniqueNumber);

    Application findByUniqueNumber(String uniqueNumber);

    @Query(value = "select " +
            "(select COUNT(*) from  applications) as allStatusCount, " +
            "(select COUNT(*) from applications where status = 0) as statusNew, " +
            "(select COUNT(*) from applications where status = 1) as statusInProcess, " +
            "(select COUNT(*) from applications where status = 2) as statusAccepted," +
            "(select COUNT(*) from applications where status = 3) as statusRejected",
            nativeQuery = true)
    List<Object[]> applicationsCountByStatues();

    @Query(value = "select" +
            "(select COUNT(*) from applications where gender = '1') as male," +
            "(select COUNT(*) from applications where gender = '0') as famale", nativeQuery = true)
    List<Object[]> applicationsCountByGender(); 
}
