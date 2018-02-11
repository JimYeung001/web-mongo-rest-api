package com.jim.webrestmongo.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jim.webrestmongo.model.Profile;
import com.jim.webrestmongo.repository.ProfileRepository;
import com.jim.webrestmongo.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private ProfileRepository profileRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Profile> findProfileByLastName(String lastName) {
		List<Profile> profiles = getProfileRepository().findByLastName(lastName);
		return profiles;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Profile createProfile(Profile profile) {
		Profile exist = findByEmail(profile.getEmail());
		if(exist != null) {
			profile.setId(exist.getId());
			return updateProfile(profile);
		}
		return getProfileRepository().insert(profile);
	}

	public ProfileRepository getProfileRepository() {
		return profileRepository;
	}

	@Override
	public Profile updateProfile(Profile profile) {
		Profile exist = getProfileRepository().findOne(profile.getId());
		exist.setFirstName(profile.getFirstName());
		exist.setLastName(profile.getLastName());
		exist.setAge(profile.getAge());
		exist.setAddress(profile.getAddress());
		exist.setUpdatedDate(new Date());
		return getProfileRepository().save(exist);
	}

	@Override
	public Profile findByEmail(String email) {
		return getProfileRepository().findByEmail(email);
	}

	@Override
	public void deleteProfile(Profile profile) {
		getProfileRepository().delete(profile);
		
	}

	

}
