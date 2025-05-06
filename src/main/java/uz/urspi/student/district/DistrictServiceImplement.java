package uz.urspi.student.district;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.urspi.student.regions.Region;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistrictServiceImplement implements DistrictService {
    private final DistrictRepository districtRepository;
    @Override
    public List<District> getDistrictsByRegion(Long regionId) {
        return districtRepository.findDistrictByRegionId(regionId);
    }
}
