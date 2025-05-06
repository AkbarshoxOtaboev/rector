package uz.urspi.student.regions;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionServiceImplement implements RegionService {
    private final RegionRepository regionRepository;
    @Override
    public List<Region> fetchAllRegions() {
        return regionRepository.findAll();
    }

    @Override
    public Region fetchRegionById(Long id) {
        return regionRepository.findById(id).orElse(null);
    }
}
