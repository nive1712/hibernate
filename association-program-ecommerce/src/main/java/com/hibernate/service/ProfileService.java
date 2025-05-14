package com.hibernate.service;

import com.hibernate.dao.ProfileDAO;

import com.hibernate.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProfileService {
	 private ProfileDAO profileDAO;

	    @Autowired
	    public void setProfileDAO(ProfileDAO profileDAO) {
	        this.profileDAO = profileDAO;
	    }

  


    public void saveOrUpdateProfile(Profile profile) {
        profileDAO.saveOrUpdateProfile(profile);
    }

    public Profile getProfileById(Long id) {
        return profileDAO.getProfileById(id);
    }

    public void updateProfile(Profile profile) {
        profileDAO.updateProfile(profile);
    }

    public void deleteProfile(Long id) {
        profileDAO.deleteProfile(id);
    }
}
