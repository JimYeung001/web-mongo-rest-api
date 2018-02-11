package com.jim.webrestmongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jim.webrestmongo.repository.ProfileRepository;

@SpringBootApplication
public class WebRestMongoApplication implements CommandLineRunner{
	
	@Autowired
	private ProfileRepository profileRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(WebRestMongoApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
//		this.profileRepository.deleteAll();
//		Profile profile = new Profile("jim.yang101@gmail.com", "Jim", "Yeung", 40, "123 Fake Road, New York, Canada");		
//		this.profileRepository.insert(profile);
	}
}
