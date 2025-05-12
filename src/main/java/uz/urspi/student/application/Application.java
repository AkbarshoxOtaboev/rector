package uz.urspi.student.application;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import uz.urspi.student.config.TableName;

import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TableName.APPLICATIONS)
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false)
    private String region;
    @Column(nullable = false)
    private String district;
    @Column(nullable = false)
    private String address;
   @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String mobile;
    @Column(nullable = false)
    private String gender;
    private String organizationName;
    private String typeOfApplication;
    @Column(columnDefinition = "TEXT")
    private String contentOfApplication;
    private String fileLink;
    private String comment;
    @Column(unique = true)
    private String uniqueNumber;
    private Integer status;
    @CreationTimestamp(source = SourceType.DB)
    private Date createAt;
    @UpdateTimestamp(source = SourceType.DB)
    private Date updateAt;

}
