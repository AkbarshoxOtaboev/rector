package uz.urspi.student.application;

import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;
import uz.urspi.student.regions.Region;

import java.util.Date;

@Data
public class ApplicationDTO {

    private String fullName;
    private String region;
    private String district;
    private String address;
    private String email;
    private String mobile;
    private String gender;
    private String organizationName;
    private String typeOfApplication;
    private String contentOfApplication;
    private MultipartFile fileLink;
}
