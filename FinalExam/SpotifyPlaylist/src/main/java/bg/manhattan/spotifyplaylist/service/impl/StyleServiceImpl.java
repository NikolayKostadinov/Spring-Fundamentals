package bg.manhattan.spotifyplaylist.service.impl;


import bg.manhattan.spotifyplaylist.model.entity.Style;
import bg.manhattan.spotifyplaylist.model.entity.enums.StyleNameEnum;
import bg.manhattan.spotifyplaylist.repository.StyleRepository;
import bg.manhattan.spotifyplaylist.service.StyleService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StyleServiceImpl implements StyleService {

    private final StyleRepository repository;

    public StyleServiceImpl(StyleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Style> findByName(StyleNameEnum styleName) {
        return this.repository.findByName(styleName);
    }

}
