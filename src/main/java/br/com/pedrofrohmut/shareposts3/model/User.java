package br.com.pedrofrohmut.shareposts3.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class User 
{
	private int id;

	private String name;

	private String email;

	private String password;
}
