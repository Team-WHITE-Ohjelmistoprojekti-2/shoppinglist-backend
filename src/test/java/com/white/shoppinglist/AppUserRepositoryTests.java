package com.white.shoppinglist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.white.shoppinglist.appuser.AppUser;
import com.white.shoppinglist.appuser.AppUserRole;
import com.white.shoppinglist.appuser.AppUserRepository;

@SpringBootTest
@Transactional
class AppUserRepositoryTests {

    @Autowired
    private AppUserRepository appUserRepository;

    @Test
    void testFindByUsername() {

        AppUser user = new AppUser("usertofind", "password", "Description", AppUserRole.USER);
        appUserRepository.save(user);
    
        Optional<AppUser> foundUser = appUserRepository.findByUsername("usertofind");
    
        assertTrue(foundUser.isPresent());
        assertThat(foundUser.get().getUsername()).isEqualTo("usertofind");
        assertThat(foundUser.get().getPassword()).isEqualTo("password");
        assertThat(foundUser.get().getDescription()).isEqualTo("Description");
        assertThat(foundUser.get().getAppUserRole()).isEqualTo(AppUserRole.USER);
    }
    

    @Test
    void testFindByUsername_NotFound() {

        Optional<AppUser> foundUser = appUserRepository.findByUsername("nonexistentuser");

        assertTrue(foundUser.isEmpty());
    }
}
