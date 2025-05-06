package uz.urspi.student.regions;

import java.util.List;

public interface RegionService {
    List<Region> fetchAllRegions();
    Region fetchRegionById(Long id);
}
