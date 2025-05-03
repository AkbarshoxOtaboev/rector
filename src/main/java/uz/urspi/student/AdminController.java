package uz.urspi.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.urspi.student.application.ApplicationDTO;
import uz.urspi.student.application.ApplicationService;
import uz.urspi.student.user.UserService;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class AdminController {
    public final UserService userService;
    public final ApplicationService applicationService;
    @GetMapping("/")
    public String index(Model model) {
        ApplicationDTO applicationDTO = new ApplicationDTO();
        model.addAttribute("applicationDTO", applicationDTO);
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
        return "admin";
    }
    @GetMapping("/success")
    public String success() {return "success";}

    @PostMapping("/application/create")
    public String createApplication(@ModelAttribute("applicationDTO") ApplicationDTO applicationDTO) {
        applicationService.save(applicationDTO);
        return "redirect:/success";
    }

}
