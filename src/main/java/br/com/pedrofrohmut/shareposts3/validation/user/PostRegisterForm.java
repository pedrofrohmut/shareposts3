package br.com.pedrofrohmut.shareposts3.validation.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

// Lombok
@Getter @Setter @ToString
public class PostRegisterForm
{
    @NotBlank(message = "* Post Title is required and cannot be blank.")
    @Size(min = 5, max = 60, message = "* Title must be between 5 and 60 characters.")
    private String title;

    @NotBlank(message = "* Post Body is required and cannot be blank.")
    @Size(min = 15, max = 200, message = "* Post Body must be between 15 and 200 characters.")
    private String body;
}
