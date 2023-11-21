package com.white.shoppinglist.appuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService {
    private final static String USER_NOT_FOUND_MSG = "User with username %s not found";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AppUserService(AppUserRepository appUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserRepository = appUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findByUsername(username)
            .orElseThrow(() ->
                new UsernameNotFoundException(
                    String.format(USER_NOT_FOUND_MSG, username)));
    }

    // Signs up a new user
    public void signUpUser(AppUser newUser) {
        String encryptedPassword = bCryptPasswordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encryptedPassword);
        
        // Save the user to the database
        appUserRepository.save(newUser);
    }
}
