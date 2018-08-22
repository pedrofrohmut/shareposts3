package br.com.pedrofrohmut.shareposts3.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// Lombok
@Getter @Setter @ToString

// TODO: add bean validation
public class Post
{
    private int id;

    private String title;

    private String body;

    private User user;
}