package domain.validation;

import domain.Event;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AgeConstraint implements ConstraintValidator<AgeValidation, Event> {
    @Override
    public boolean isValid(Event event, ConstraintValidatorContext constraintValidatorContext) {
        return minimumAgeValidation(event);
    }

    private boolean minimumAgeValidation(Event event) {
        return !event.getAgeRestricted() || event.getMinimumAge() >= 18;
    }
}
