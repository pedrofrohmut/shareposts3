package br.com.pedrofrohmut.shareposts3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = { "classpath:database.properties" })
public class Shareposts3Application
{
	public static void main(String[] args)
	{
		SpringApplication.run(Shareposts3Application.class, args);
	}
}
