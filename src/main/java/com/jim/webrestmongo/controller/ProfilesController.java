package com.jim.webrestmongo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jim.webrestmongo.model.Profile;
import com.jim.webrestmongo.service.ProfileService;

@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfilesController {
	
	@Autowired
	private ProfileService profileService;
	
	@RequestMapping(value = "/profiles/{lastName}", method = RequestMethod.GET)
	public List<Profile> findProfileByLastName(@PathVariable String lastName) {
		List<Profile> profiles = getProfileService().findProfileByLastName(lastName);
		return profiles;
	}
	
	/**
	 * Create a new {@code Profile}
	 * @param profile {@code Profile}
	 * @return saved {@code Profile}
	 */
	@RequestMapping(value="/profiles", method=RequestMethod.POST)
	public Profile createNewProfile(@RequestBody Profile profile) {
		return getProfileService().createProfile(profile);
	}
	
	@RequestMapping(value="/profiles", method=RequestMethod.DELETE)
	public void deleteProfile(@RequestBody Profile profile) {
		getProfileService().deleteProfile(profile);
	}

	public ProfileService getProfileService() {
		return profileService;
	}

	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}
	

}
