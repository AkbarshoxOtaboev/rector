package uz.urspi.student.district;

import uz.urspi.student.regions.Region;

import java.util.List;

public interface DistrictService {
    List<District> getDistrictsByRegion(Long regionId);
}
