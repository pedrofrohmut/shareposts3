package br.com.pedrofrohmut.shareposts3.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class User 
{
	// ## fields ##
	@Getter @Setter
	private int id;

	@Getter @Setter
	private String name;

	@Getter @Setter
	private String email;

	@Getter @Setter
	private String password;
}
