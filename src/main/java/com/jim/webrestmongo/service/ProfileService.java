package com.jim.webrestmongo.service;

import java.util.List;

import com.jim.webrestmongo.model.Profile;

public interface ProfileService {

	/**
	 * Find the profile by profile lastName
	 * 
	 * @param lastName
	 *            last name
	 * @return List<Profile>
	 */
	public List<Profile> findProfileByLastName(String lastName);

	/**
	 * Create a new {@code Profile}
	 * 
	 * @param profile
	 * @return created profile
	 */
	public Profile createProfile(Profile profile);

	/**
	 * Update exist {@code Profile}
	 * 
	 * @param profile
	 *            {@code Profile}
	 * @return updated {@code Profile}
	 */
	public Profile updateProfile(Profile profile);

	/**
	 * Find profile by email due to unique.
	 * 
	 * @param email
	 * @return {@code Profile}
	 */
	public Profile findByEmail(String email);

	/**
	 * Delete exist profile
	 * 
	 * @param profile
	 */
	public void deleteProfile(Profile profile);
}
