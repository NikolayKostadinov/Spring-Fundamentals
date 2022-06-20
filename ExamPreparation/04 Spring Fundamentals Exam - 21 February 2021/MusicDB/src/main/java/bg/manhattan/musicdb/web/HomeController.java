package bg.manhattan.musicdb.web;

import bg.manhattan.musicdb.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final UserService userService;

    private final ModelMapper mapper;


    public HomeController(UserService userService,
                          ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }
    @GetMapping("/")
    public String index(Model model) {
        if (this.userService.isLoggedIn()) {
            return "home";
        }

        return "index";
    }
}
