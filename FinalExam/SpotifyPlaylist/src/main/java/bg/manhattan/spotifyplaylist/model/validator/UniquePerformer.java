package bg.manhattan.spotifyplaylist.model.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniquePerformerValidator.class)
public @interface UniquePerformer {
    String message() default "Performer name must be unique!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
