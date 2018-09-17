package br.com.pedrofrohmut.shareposts3.validation.user.post;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// Lombok
@Getter @Setter @ToString
public class PostEditForm
{
    @NotNull(message = "* The id of the post is required and cannot be blank.")
    @Min(value = 1, message = "* Post ID smaller than 1 is not valid.")
    private int id;

    @NotNull(message = "* The id of the user creating the post is required and cannot be blank.")
    @Min(value = 1, message = "* User ID smaller than 1 is not valid.")
    private int userId;

    @NotBlank(message = "* Post Title is required and cannot be blank.")
    @Size(min = 5, max = 60, message = "* Title must be between 5 and 60 characters.")
    private String title;

    @NotBlank(message = "* Post Body is required and cannot be blank.")
    @Size(min = 15, max = 200, message = "* Post Body must be between 15 and 200 characters.")
    private String body;
}
