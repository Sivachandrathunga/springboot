package com.mphasis.parent.service;

import com.mphasis.parent.entity.UserDetails;
import com.mphasis.parent.dao.UserRepository;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
 
@Service
public class UserDetailServiceImpl implements UserDetailService {
 
    private UserRepository userRepository;
    
    @Autowired
    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;
 
    @Override
    public UserDetails registerUser(String userName, String password) {
        if (userRepository.findByUserName(userName) != null) {
            throw new IllegalArgumentException("Username already exists!");
        }
        String encodedPassword = passwordEncoder.encode(password);
 
        UserDetails userDetails = new UserDetails(userName, encodedPassword);
        return userRepository.save(userDetails);
    }
 
    @Override
    public String getUser(String userName, String password) {
        UserDetails userDetails = userRepository.findByUserName(userName);
 
        if (userDetails != null) {
            if (passwordEncoder.matches(password, userDetails.getPassword())) {
                return "User authenticated successfully!";
            } else {
                throw new IllegalArgumentException("Invalid username or password!");
            }
        } else {
            throw new IllegalArgumentException("Invalid username or password!");
        }
    }
    
	@Override
	public void validateUser(String userName, String password) {
		UserDetails userDetails = userRepository.findByUserName(userName);
		 
        if (userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password!");
        }
		
	}
 
	/*
	 * public void updateUserToken(String username, String token) { UserDetails user
	 * = userRepository.findByUserName(username); if (user != null) {
	 * user.setToken(token);
	 *
	 * userRepository.save(user); } else { throw new
	 * IllegalArgumentException("User not found with username: " + username); } }
	 */


}