package uz.urspi.student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoDTO {
    private Integer allApplicationCount;
    private Integer newApplicationCount;
    private Integer inProgressApplicationCount;
    private Integer completedApplicationCount;
    private Integer rejectedApplicationCount;
}
