package uz.urspi.student.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import uz.urspi.student.district.DistrictService;
import uz.urspi.student.dto.InfoDTO;
import uz.urspi.student.regions.RegionService;
import uz.urspi.student.storage.StorageService;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ApplicationServiceImplement implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final RegionService regionService;
    private final DistrictService districtService;
    private final StorageService storageService;
    @Override
    public Page<ApplicationViewDTO> fetchAllApplications(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "status");
        Page<Application> applicationsPage = applicationRepository.findAll(pageable);

        List<ApplicationViewDTO> dtos = applicationsPage.getContent().stream()
                .map(application -> ApplicationViewDTO.builder()
                        .id(application.getId())
                        .uniqueNumber(application.getUniqueNumber())
                        .name(application.getFullName())
                        .region(regionService.fetchRegionById(Long.parseLong(application.getRegion())).getName())
                        .district(districtService.getDistrictById(Long.parseLong(application.getDistrict())).getName())
                        .mobile(application.getMobile())
                        .typeOfApplication(application.getTypeOfApplication())
                        .gender("1".equals(application.getGender()) ? "Erkak" : "Ayol")
                        .status(application.getStatus())
                        .build()
                ).toList();

        return new PageImpl<>(dtos, pageable, applicationsPage.getTotalElements());
    }

    @Override
    public Application fetchApplicationById(Long id) {
        return applicationRepository.findById(id).orElseThrow();
    }

    public String generateUniqueNumber() {
        String number;
        do {
            number = String.valueOf((int)(Math.random() * 90000000) + 10000000); // 8-значный
        } while (applicationRepository.existsByUniqueNumber(number));
        return number;
    }
    @Override
    @Transactional
    public void save(ApplicationDTO applicationDTO) {
        Application application = Application.builder()
                .fullName(applicationDTO.getFullName())
                .region(applicationDTO.getRegion())
                .district(applicationDTO.getDistrict())
                .address(applicationDTO.getAddress())
                .email(applicationDTO.getEmail())
                .mobile(applicationDTO.getMobile())
                .gender(applicationDTO.getGender())
                .organizationName(applicationDTO.getOrganizationName())
                .typeOfApplication(applicationDTO.getTypeOfApplication())
                .contentOfApplication(applicationDTO.getContentOfApplication())
                .fileLink(storageService.store(applicationDTO.getFileLink()))
                .uniqueNumber(generateUniqueNumber())
                .status(0)
                .build();
        applicationRepository.save(application);
    }

    @Override
    public void update(String comment, Long id) {
        Application application = applicationRepository.findById(id).orElseThrow();
        application.setComment(comment);
        application.setStatus(1);
        applicationRepository.save(application);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public boolean existsByUniqueNumber(String uniqueNumber) {
        return false;
    }

    @Override
    public ApplicationViewDTO findByUniqueNumber(String uniqueNumber) {
        Application application = applicationRepository.findByUniqueNumber(uniqueNumber);
        var dto = ApplicationViewDTO.builder()
                .id(application.getId())
                .uniqueNumber(application.getUniqueNumber())
                .name(application.getFullName())
                .region(regionService.fetchRegionById(Long.parseLong(application.getRegion())).getName())
                .district(districtService.getDistrictById(Long.parseLong(application.getDistrict())).getName())
                .address(application.getAddress())
                .email(application.getEmail())
                .mobile(application.getMobile())
                .gender("1".equals(application.getGender()) ? "Erkak" : "Ayol")
                .typeOfApplication(application.getTypeOfApplication())
                .organizationName(application.getOrganizationName())
                .contentText(application.getContentOfApplication())
                .comment(application.getComment())
                .fileLink(application.getFileLink())
                .status(application.getStatus())
                .build();
        return dto;
    }

    @Override
    public void changeStatus(String uniqueNumber, Integer status, ApplicationAnswer answer) {
        Application application = applicationRepository.findByUniqueNumber(uniqueNumber);
        if(status.equals(0)){
            application.setStatus(1);
            application.setComment(answer.getComment());
            applicationRepository.save(application);
        }else if(status.equals(2)){
            application.setStatus(2);
            application.setComment(answer.getComment());
            applicationRepository.save(application);
        }else if(status.equals(3)){
            application.setStatus(3);
            application.setComment(answer.getComment());
            applicationRepository.save(application);
        }
    }

    @Override
    public InfoDTO fetchStatusInfo() {
        Object[] status = applicationRepository.applicationsCountByStatues().get(0);
        return new InfoDTO(
                ((Number) status[0]).intValue(),
                ((Number) status[1]).intValue(),
                ((Number) status[2]).intValue(),
                ((Number) status[3]).intValue(),
                ((Number) status[4]).intValue()
        );
    }
}
