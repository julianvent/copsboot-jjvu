package mx.jjvu.copsboot.utility.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import mx.jjvu.copsboot.utility.annotations.ValidReportDescription;

public class ReportDescriptionValidator implements ConstraintValidator<ValidReportDescription, String> {
    @Override
    public void initialize(ValidReportDescription constraintAnnotation) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.toLowerCase().contains("suspect");
    }
}
