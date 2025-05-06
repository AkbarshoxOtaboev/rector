package uz.urspi.student;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.urspi.student.district.District;
import uz.urspi.student.district.DistrictDTO;
import uz.urspi.student.district.DistrictRepository;
import uz.urspi.student.regions.Region;
import uz.urspi.student.regions.RegionDTO;
import uz.urspi.student.regions.RegionRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final RegionRepository regionRepository;
    private final DistrictRepository districtRepository;
    @Override
    public void run(String... args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        File jsonFile = new File("src/main/resources/static/json/villages.json");
        JsonNode root = mapper.readTree(jsonFile);
        // Load Regions
        RegionDTO[] regionDTOs = mapper.treeToValue(root.get("regions"), RegionDTO[].class);
        List<Region> regions = new ArrayList<>();
        for (RegionDTO dto : regionDTOs) {
            Region region = new Region();
            region.setId(dto.id);
            region.setName(dto.name);
            regions.add(region);
        }
        regionRepository.saveAll(regions);

        // Load Districts
        DistrictDTO[] districtDTOs = mapper.treeToValue(root.get("districts"), DistrictDTO[].class);
        List<District> districts = new ArrayList<>();
        for (DistrictDTO dto : districtDTOs) {
            District district = new District();
            district.setId(dto.id);
            district.setName(dto.name);
            district.setRegionId(dto.region_id);
            districts.add(district);
        }
        districtRepository.saveAll(districts);
    }
}
