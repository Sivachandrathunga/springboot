package com.mphasis.parent.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mphasis.parent.entity.UserDetails;

@Repository
public interface UserRepository extends JpaRepository<UserDetails,Long> {
	UserDetails findByUserName(String userName);
}
