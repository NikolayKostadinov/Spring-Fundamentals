package bg.manhattan.finalexam.web;


import bg.manhattan.finalexam.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model) {
        if (this.userService.isLoggedIn()) {
//            if (!model.containsAttribute("homeModel")) {
//                HomeViewModel homeViewModel = new HomeViewModel(this.taskService.getTasks());
//                model.addAttribute("homeModel", homeViewModel);
//            }
            return "home";
        }

        return "index";
    }
}
