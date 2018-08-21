package br.com.pedrofrohmut.shareposts3.model;

import br.com.pedrofrohmut.shareposts3.constraint.FieldsVerification;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * TODO: Validation to check if password and confirmPassword match each other;
 * TODO: the same thing with email and confirmEmail
 * */

// Lombok
@Getter @Setter @ToString

@FieldsVerification.List({

        @FieldsVerification(
                field = "email",
                fieldMatch = "confirmEmail",
                message = "* The E-mail and Confirmed E-mail informed did not match. Please check it again."
        ),
        @FieldsVerification(
                field = "password",
                fieldMatch = "confirmPassword",
                message = "* The Password and Confirm Password did not match. Please check it again."
        )
})
public class User 
{
	private int id;

	@NotBlank(message = "* Name is required and cannot be blank")
	private String name;

	@NotBlank(message = "* E-mail is required and cannot be blank")
	private String email;

	@NotBlank(message = "* Confirm E-mail is required and cannot be blank")
	private String confirmEmail;

	@NotBlank(message = "* Password is required and cannot be blank")
    @Size(min = 6, max = 25, message = "* The password size must be between 6 and 25 characters")
	private String password;

	@NotBlank(message = "* Confirm Password is required and cannot be blank")
	private String confirmPassword;
}
