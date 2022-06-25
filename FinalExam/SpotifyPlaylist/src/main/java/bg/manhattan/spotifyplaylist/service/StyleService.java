package bg.manhattan.spotifyplaylist.service;


import bg.manhattan.spotifyplaylist.model.entity.Style;
import bg.manhattan.spotifyplaylist.model.entity.enums.StyleNameEnum;

import java.util.Optional;

public interface StyleService {
    Optional<Style> findByName(StyleNameEnum category);
}
