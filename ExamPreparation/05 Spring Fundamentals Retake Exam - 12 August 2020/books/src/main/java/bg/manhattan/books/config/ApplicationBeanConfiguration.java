package bg.manhattan.books.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

//        mapper.addConverter(ctx -> LocalDate.parse(ctx.getSource(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
//                String.class, LocalDate.class);
//
//        mapper.addConverter(ctx -> ApartmentType.valueOf(ctx.getSource()),
//                String.class, ApartmentType.class);

        return mapper;
    }
}
