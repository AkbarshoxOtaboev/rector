package uz.urspi.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.urspi.student.user.UserService;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class AdminController {
    public final UserService userService;
    @GetMapping("/")
    public String index(Model model) {
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
    @GetMapping("/admin/application")
    public String application(Model model) {
        var user = userService.getCurrentUser();
        model.addAttribute("user", user);
        model.addAttribute("title", "Application");
        return "application";
    }

}
