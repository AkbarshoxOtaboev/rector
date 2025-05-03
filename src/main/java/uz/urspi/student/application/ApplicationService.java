package uz.urspi.student.application;

import org.springframework.data.domain.Page;

public interface ApplicationService {
    Page<Application> fetchAllApplications(Integer page, Integer size);
    Application fetchApplicationById(Long id);
    void save(ApplicationDTO applicationDTO);
    void update(String comment , Long id);
    void delete(Long id);
    boolean existsByUniqueNumber(String uniqueNumber);
    Application findByUniqueNumber(String uniqueNumber);
}
