package br.com.pedrofrohmut.shareposts3.validator;

import br.com.pedrofrohmut.shareposts3.constraint.FieldsVerification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class FieldsVerificationValidator implements ConstraintValidator<FieldsVerification, Object>
{
    private String field;
    private String fieldMatch;

    @Override
    public void initialize(FieldsVerification constraintAnnotation)
    {
        field = constraintAnnotation.field();
        fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context)
    {
        Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(value).getPropertyValue(fieldMatch);

        log.info("Field value = " + fieldValue + "  &  Field match value = " + fieldMatchValue);

        if (
                fieldValue != null && fieldMatchValue != null &&
                !fieldValue.equals("") && !fieldMatchValue.equals("") &&
                !fieldValue.equals(fieldMatchValue)
        ) {
            return false;
        } else {
            return true;
        }
    }
}
