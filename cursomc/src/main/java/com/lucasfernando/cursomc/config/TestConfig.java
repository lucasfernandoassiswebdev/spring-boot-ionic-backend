package com.lucasfernando.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.lucasfernando.cursomc.services.DBService;

@Configuration
@Profile("test") // configurações específicas do profile de teste(banco de dados em memódia)
public class TestConfig {	
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateDataBase() throws ParseException {		
		dbService.instantiateTestDatabbase();
		return true;
	}
}
