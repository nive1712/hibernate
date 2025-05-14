package com.hibernate.dao;

import com.hibernate.model.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ProfileDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveProfile(Profile profile) {
        entityManager.persist(profile);
    }
    @Transactional
    public void saveOrUpdateProfile(Profile profile) {
        if (profile.getId() == null) {
            entityManager.persist(profile); 
        } else {
            entityManager.merge(profile);
        }
    }

    @Transactional
    public Profile getProfileById(Long id) {
        return entityManager.find(Profile.class, id);
    }

    @Transactional
    public void updateProfile(Profile profile) {
        entityManager.merge(profile);
    }

    @Transactional
    public void deleteProfile(Long id) {
        Profile profile = getProfileById(id);
        if (profile != null) {
            entityManager.remove(profile);
        }
    }
}

