package br.com.pedrofrohmut.shareposts3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@Slf4j

@SpringBootApplication
@PropertySource(value = { "classpath:database.properties" })
public class Shareposts3Application
{
	@PostConstruct
	void init()
	{
		TimeZone.setDefault(TimeZone.getTimeZone("UTC-03:00"));
		log.info("  >>  Time Zone: " + new java.util.Date());
	}

	public static void main(String[] args)
	{
		SpringApplication.run(Shareposts3Application.class, args);
	}
}
