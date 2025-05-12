package uz.urspi.student.application;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.urspi.student.ApplicationAnswer;
import uz.urspi.student.storage.StorageService;
import uz.urspi.student.user.UserService;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;
    private final UserService userService;
    private final StorageService storageService;

    @GetMapping("/application")
    public String application(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            Model model) {
        var user = userService.getCurrentUser();
        model.addAttribute("user", user);
        model.addAttribute("title", "Application");
        Page<ApplicationViewDTO> applications = applicationService.fetchAllApplications(page, size);
        model.addAttribute("applications", applications.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", applications.getTotalPages());
        return "application";
    }

    @GetMapping("/application/info/{unique}")
    public String application(@PathVariable("unique") String unique, Model model) {
        model.addAttribute("user", userService.getCurrentUser());
        ApplicationViewDTO application = applicationService.findByUniqueNumber(unique);
        model.addAttribute("title", application.getName());
        model.addAttribute("app", application);
        model.addAttribute("answer", new ApplicationAnswer());
        return "applicationInfo";
    }
    @PostMapping("/change/status/{unique}")
    public String changeStatus(Model model,
                               @PathVariable("unique") String uniqueNumber,
                               @RequestParam("status") Integer status,
                               @ModelAttribute ApplicationAnswer answer) {
        applicationService.changeStatus(uniqueNumber, status, answer);
        return "redirect:/admin/application/info/" + uniqueNumber ;
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        if (file == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}
