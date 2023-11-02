package com.white.shoppinglist.registration;

import com.white.shoppinglist.appuser.AppUser;
import com.white.shoppinglist.appuser.AppUserRole;
import com.white.shoppinglist.appuser.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final AppUserService appUserService;

    public RegistrationService(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    public String register(RegistrationRequest request) {
        AppUser newUser = new AppUser(
            request.getUsername(),
            request.getPassword(),
            request.getDescription(),
            AppUserRole.USER
        );

        appUserService.signUpUser(newUser);

        return "Account creation successful";
    }
}

