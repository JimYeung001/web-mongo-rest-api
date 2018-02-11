package com.jim.webrestmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jim.webrestmongo.model.Profile;

public interface ProfileRepository extends MongoRepository<Profile, String> {
	
	public List<Profile> findByLastName(String lastName);
	
	public Profile findByEmail(String email);
	

}
