package bg.softuni.tabula.event.model;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class FutureValidator implements ConstraintValidator<Future, LocalDateTime> {


    @Override
    public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {

        return value != null &&
               value.isAfter(LocalDateTime.now());
    }
}
