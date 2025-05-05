package uz.urspi.student.district;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.urspi.student.config.TableName;
import uz.urspi.student.regions.Region;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TableName.DISTRICTS)
public class District {
    @Id
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;
}
