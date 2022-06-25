package bg.manhattan.spotifyplaylist.web;

import bg.manhattan.spotifyplaylist.model.binding.SongBindingModel;
import bg.manhattan.spotifyplaylist.model.binding.UserRegisterBindingModel;
import bg.manhattan.spotifyplaylist.model.service.SongServiceModel;
import bg.manhattan.spotifyplaylist.model.service.UserServiceModel;
import bg.manhattan.spotifyplaylist.service.SongService;
import bg.manhattan.spotifyplaylist.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/songs")
public class SongController {
    private final UserService userService;
    private final SongService songService;
    private final ModelMapper mapper;

    public SongController(UserService userService, SongService songService, ModelMapper mapper) {
        this.userService = userService;
        this.songService = songService;
        this.mapper = mapper;
    }

    @GetMapping("add")
    public String add(){
        if (!this.userService.isLoggedIn()){
            return "redirect:/users/login";
        }

        return "song-add";
    }

    @PostMapping("/add")
    public String add(@Valid SongBindingModel songModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (!this.userService.isLoggedIn()){
            return "redirect:/users/login";
        }

        if (bindingResult.hasErrors()) {
            return getErrorResponse(songModel, bindingResult, redirectAttributes);
        }
        try {
            this.songService.addSong(this.mapper.map(songModel, SongServiceModel.class));
        } catch (IllegalArgumentException ex) {
            ObjectError error = new ObjectError("globalError", ex.getMessage());
            bindingResult.addError(error);
            return getErrorResponse(songModel, bindingResult, redirectAttributes);
        }
        return "redirect:/";
    }

    private String getErrorResponse(SongBindingModel songModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("songModel", songModel);
        redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.songModel", bindingResult);
        return "redirect:add";
    }


    @ModelAttribute("songModel")
    public SongBindingModel initSong(){
        return new SongBindingModel();
    }
}
