package br.com.pedrofrohmut.shareposts3.constraint;

import br.com.pedrofrohmut.shareposts3.validator.FieldsVerificationValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = FieldsVerificationValidator.class)
public @interface FieldsVerification
{
    String field();
    String fieldMatch();
    String message() default "* Fields values did not match";

    // required lines
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @interface List {
        FieldsVerification[] value();
    }
}
