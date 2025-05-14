package uz.urspi.student.application;

import org.springframework.data.domain.Page;
import uz.urspi.student.dto.InfoDTO;

public interface ApplicationService {
    Page<ApplicationViewDTO> fetchAllApplications(Integer page, Integer size);
    Application fetchApplicationById(Long id);
    Application save(ApplicationDTO applicationDTO);
    void update(String comment , Long id);
    void delete(Long id);
    boolean existsByUniqueNumber(String uniqueNumber);
    ApplicationViewDTO findByUniqueNumber(String uniqueNumber);
    void changeStatus(String uniqueNumber, Integer status, ApplicationAnswer answer);
    InfoDTO fetchStatusInfo();
}
