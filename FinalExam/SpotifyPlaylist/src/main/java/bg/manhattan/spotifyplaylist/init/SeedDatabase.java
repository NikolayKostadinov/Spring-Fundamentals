package bg.manhattan.spotifyplaylist.init;


import bg.manhattan.spotifyplaylist.model.entity.Style;
import bg.manhattan.spotifyplaylist.model.entity.enums.StyleNameEnum;
import bg.manhattan.spotifyplaylist.repository.StyleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SeedDatabase implements CommandLineRunner {
    private final StyleRepository styleRepository;

    public SeedDatabase(StyleRepository categoryRepository) {
        this.styleRepository = categoryRepository;
    }

    @Override
    public void run(String... args) {
        if (this.styleRepository.findAll().isEmpty()) {
            List<Style> styles = Arrays.stream(StyleNameEnum.values())
                    .map(styleName -> new Style().setName(styleName))
                    .collect(Collectors.toList());
            this.styleRepository.saveAll(styles);
        }
    }
}
