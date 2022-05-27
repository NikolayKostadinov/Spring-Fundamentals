package bg.manhattan.springmvc.web;

import bg.manhattan.springmvc.model.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @GetMapping("/user")
    public String user(){
        return "create_user";
    }

    @PostMapping("/user")
    public ModelAndView user(UserDto user){
        System.out.println(user);
        ModelAndView result = new ModelAndView();
        result.addObject("fullName", user.getFullName());
        result.setViewName("user_created");
        return result;
    }
}
