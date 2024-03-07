package domain.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AgeConstraint.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AgeValidation {
    String message() default "Invalid event.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
