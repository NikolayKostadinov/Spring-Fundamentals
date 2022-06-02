package bg.manhattan.springmvc.web;

import bg.manhattan.springmvc.model.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/create")
    public String user() {
        return "create_user";
    }

    @PostMapping("/add")
    public ModelAndView user(UserDto user) {
        System.out.println(user);
        ModelAndView result = new ModelAndView();
        result.addObject("full-name", user.getFullName());
        result.setViewName("redirect:/user/created");
        return result;
    }

    @GetMapping("/created")
    public ModelAndView userCreated(@RequestParam("full-name") String fullLame) {
        ModelAndView result = new ModelAndView();
        result.addObject("fullName", fullLame);
        result.setViewName("user_created");
        return result;
    }
}
