package bg.manhattan.spotifyplaylist.model.validator;

import bg.manhattan.spotifyplaylist.service.SongService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniquePerformerValidator implements ConstraintValidator<UniquePerformer, String> {
    private final SongService songService;

    public UniquePerformerValidator(SongService songService) {
        this.songService = songService;
    }

    @Override
    public void initialize(UniquePerformer constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String performer, ConstraintValidatorContext context) {
        return this.songService.getSongByPerformer(performer).isEmpty();
    }
}
