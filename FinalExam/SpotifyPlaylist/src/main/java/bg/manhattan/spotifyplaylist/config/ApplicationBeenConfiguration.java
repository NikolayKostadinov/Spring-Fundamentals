package bg.manhattan.spotifyplaylist.config;

import bg.manhattan.spotifyplaylist.model.entity.Song;
import bg.manhattan.spotifyplaylist.model.entity.Style;
import bg.manhattan.spotifyplaylist.model.entity.User;
import bg.manhattan.spotifyplaylist.model.entity.enums.StyleNameEnum;
import bg.manhattan.spotifyplaylist.model.service.SongServiceModel;
import bg.manhattan.spotifyplaylist.model.service.UserServiceModel;
import bg.manhattan.spotifyplaylist.model.service.UserServiceSongModel;
import bg.manhattan.spotifyplaylist.model.view.SongViewModel;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Configuration
public class ApplicationBeenConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper(PasswordEncoder passwordEncoder) {
        ModelMapper mapper = new ModelMapper();

        Converter<String, String> passwordHash = ctx -> ctx.getSource() == null ? null :
                passwordEncoder.encode(ctx.getSource());

        //encode password on the fly
        mapper.createTypeMap(UserServiceModel.class, User.class)
                .addMappings(mpr -> mpr.using(passwordHash)
                        .map(UserServiceModel::getPassword, User::setPasswordHash));

        Converter<Integer, String> toMinutesAndSeconds = ctx -> ctx.getSource() == null ? null :
                String.format("%d:%02d", ctx.getSource() / 60, ctx.getSource() % 60);


        mapper.createTypeMap(SongServiceModel.class, SongViewModel.class)
                .addMappings(mpr -> mpr.using(toMinutesAndSeconds).map(SongServiceModel::getDuration, SongViewModel::setDuration));

        mapper.createTypeMap(UserServiceSongModel.class, SongViewModel.class)
                .addMappings(mpr -> mpr.using(toMinutesAndSeconds).map(UserServiceSongModel::getDuration, SongViewModel::setDuration));

        Converter<Style, StyleNameEnum> toStyleEnum = ctx -> ctx.getSource() == null ? null :
                ctx.getSource().getName();

        mapper.createTypeMap(Song.class, SongServiceModel.class)
                .addMappings(mpr -> mpr.using(toStyleEnum).map(Song::getStyle, SongServiceModel::setStyle));
        return mapper;
    }
}
