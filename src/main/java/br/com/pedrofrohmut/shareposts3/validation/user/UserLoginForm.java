package br.com.pedrofrohmut.shareposts3.validation.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

// lombok
@Getter @Setter @ToString
public class UserLoginForm
{
    @NotBlank(message = "* E-mail is required and cannot be blank")
    private String email;

    @NotBlank(message = "* Password is required and cannot be blank")
    @Size(min = 6, max = 25, message = "* The password size must be between 6 and 25 characters")
    private String password;
}
