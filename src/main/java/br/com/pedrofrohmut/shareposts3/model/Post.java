package br.com.pedrofrohmut.shareposts3.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@ToString
public class Post
{
    // ## fields ##
    @Getter @Setter
    private int id;

    @Getter @Setter
    private String title;

    @Getter @Setter
    private String body;

    @Getter @Setter
    private User user;

    private String createdAt;

    // ## Setters ##
    public void setCreatedAt(Calendar calendar)
    {
        this.createdAt = new SimpleDateFormat("dd/MM/yyyy").format(calendar);
    }

    public void setCreatedAt(java.sql.Timestamp timestamp)
    {
        this.createdAt = new SimpleDateFormat("dd/MM/yyyy").format(timestamp);
    }

    // ## Getters ##
    public String getCreatedAt()
    {
        return createdAt;
    }
}
