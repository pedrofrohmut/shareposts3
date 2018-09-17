package br.com.pedrofrohmut.shareposts3.validation.user.post;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

// Lombok
@Getter @Setter @ToString
public class PostDeleteForm
{
    @NotNull(message = "* The id of the post is required and cannot be blank.")
    @Min(value = 1, message = "* Post ID smaller than 1 is not valid.")
    private int id;
}
