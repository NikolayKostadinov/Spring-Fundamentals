package bg.manhattan.spotifyplaylist.web;


import bg.manhattan.spotifyplaylist.model.entity.enums.StyleNameEnum;
import bg.manhattan.spotifyplaylist.model.service.SongServiceModel;
import bg.manhattan.spotifyplaylist.model.service.UserServiceSongModel;
import bg.manhattan.spotifyplaylist.model.view.HomeViewModel;
import bg.manhattan.spotifyplaylist.model.view.SongViewModel;
import bg.manhattan.spotifyplaylist.service.SongService;
import bg.manhattan.spotifyplaylist.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final UserService userService;
    private final SongService songService;
    private final ModelMapper mapper;

    public HomeController(UserService userService,
                          SongService songService,
                          ModelMapper mapper) {
        this.userService = userService;
        this.songService = songService;
        this.mapper = mapper;
    }

    @GetMapping("/")
    public String index(Model model) {
        if (this.userService.isLoggedIn()) {
            try {
                prepareData(model);
            } catch (IllegalArgumentException ex) {
                return "index";
            }
            return "home";
        }

        return "index";
    }

    private void prepareData(Model model) {
        if (!model.containsAttribute("homeModel")) {
            List<SongServiceModel> songs = this.songService.getAllSongs();

            List<SongViewModel> popSongs = songs.
                    stream()
                    .filter(song -> song.getStyle().equals(StyleNameEnum.POP))
                    .map(song -> this.mapper.map(song, SongViewModel.class)).toList();

            List<SongViewModel> rockSongs = songs.
                    stream()
                    .filter(song -> song.getStyle().equals(StyleNameEnum.ROCK))
                    .map(song -> this.mapper.map(song, SongViewModel.class)).toList();

            List<SongViewModel> jazzSongs = songs.
                    stream()
                    .filter(song -> song.getStyle().equals(StyleNameEnum.JAZZ))
                    .map(song -> this.mapper.map(song, SongViewModel.class)).toList();

            List<UserServiceSongModel> playlistServiceModel = this.userService.getPlaylist();
            int totalDurationInSeconds = playlistServiceModel
                    .stream()
                    .mapToInt(s -> s.getDuration())
                    .sum();
            List<SongViewModel> playlist = playlistServiceModel
                    .stream()
                    .map(song -> this.mapper.map(song, SongViewModel.class)).toList();

            HomeViewModel homeModel = new HomeViewModel(popSongs, rockSongs, jazzSongs, playlist, totalDurationInSeconds);
            model.addAttribute("homeModel", homeModel);
        }
    }
}
