package bg.manhattan.springmvc.web;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HelloController {
    @GetMapping("/hello/answer/{num}/test")
    public String hello(Model model, @PathVariable(value = "num") @DefaultValue("0") Integer num){
        model.addAttribute("answer", num);
        return "helloworld";
    }
}
