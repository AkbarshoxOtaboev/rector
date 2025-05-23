package uz.urspi.student;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uz.urspi.student.application.Application;
import uz.urspi.student.application.ApplicationDTO;
import uz.urspi.student.application.ApplicationService;
import uz.urspi.student.application.ApplicationViewDTO;
import uz.urspi.student.district.District;
import uz.urspi.student.district.DistrictService;
import uz.urspi.student.dto.InfoDTO;
import uz.urspi.student.regions.Region;
import uz.urspi.student.regions.RegionService;
import uz.urspi.student.user.UserService;

import java.util.List;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class AdminController {
    public final UserService userService;
    public final ApplicationService applicationService;
    public final RegionService regionService;
    public final DistrictService districtService;
    @GetMapping("/")
    public String index(Model model) {
        ApplicationDTO applicationDTO = new ApplicationDTO();
        model.addAttribute("applicationDTO", applicationDTO);
        List<Region> regions = regionService.fetchAllRegions();
        model.addAttribute("regions", regions);
        return "index";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        var user = userService.getCurrentUser();
        model.addAttribute("user", user);
        model.addAttribute("title", "Admin");
        InfoDTO status= applicationService.fetchStatusInfo();
        model.addAttribute("status", status);
        return "admin";
    }

    @GetMapping("/admin/status")
    @ResponseBody
    public ResponseEntity<InfoDTO> adminStatus() {
        return ResponseEntity.ok(applicationService.fetchStatusInfo());
    }
    @GetMapping("/success")
    public String success() {return "success";}

    @PostMapping("/application/create")
    public String createApplication(@ModelAttribute("applicationDTO") ApplicationDTO applicationDTO) {
        Application application = applicationService.save(applicationDTO);
        return "redirect:/success?number="+application.getUniqueNumber();
    }

    @GetMapping("/application/check")
    public ResponseEntity<?> checkApplication(@RequestParam("number") String number, Model model) {
        ApplicationViewDTO application = applicationService.findByUniqueNumber(number);
        model.addAttribute("application", application);
        return ResponseEntity.ok(application);
    }
    @GetMapping("/api/districts")
    @ResponseBody
    public List<District> fetchByRegionId(@RequestParam("regionId")  Long regionId) {
        return districtService.getDistrictsByRegion(regionId);
    }
}
