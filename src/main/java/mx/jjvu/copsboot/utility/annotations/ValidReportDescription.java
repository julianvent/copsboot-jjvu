package mx.jjvu.copsboot.utility.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import mx.jjvu.copsboot.utility.validators.ReportDescriptionValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ReportDescriptionValidator.class})
public @interface ValidReportDescription {
    String message() default "Invalid report description";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
