package uz.urspi.student.application;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationViewDTO {
    private Long id;
    private String uniqueNumber;
    private String name;
    private String region;
    private String district;
    private String address;
    private String email;
    private String mobile;
    private String gender;
    private String organizationName;
    private String typeOfApplication;
    private String contentText;
    private String fileLink;
    private String comment;
    private Integer status;

}
