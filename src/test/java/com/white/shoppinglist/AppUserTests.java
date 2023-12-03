package com.white.shoppinglist;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import com.white.shoppinglist.appuser.AppUser;
import com.white.shoppinglist.appuser.AppUserRole;

class AppUserTests {

    @Test
    void testCreateAppUser() {
        AppUser user = new AppUser("testuser", "password", "Description", AppUserRole.USER);

        assertEquals("testuser", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals("Description", user.getDescription());
        assertEquals(AppUserRole.USER, user.getAppUserRole());
        assertFalse(user.isLocked());
        assertTrue(user.isEnabled());
    }

    @Test
    void testAuthorities() {
        AppUser user = new AppUser("testuser", "password", "Description", AppUserRole.USER);
    
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
    
        assertThat(authorities)
            .hasSize(1)
            .extracting(GrantedAuthority::getAuthority)
            .containsExactly(AppUserRole.USER.name());
    }

    @Test
    void testAccountStatus() {
        AppUser user = new AppUser("testuser", "password", "Description", AppUserRole.USER);

        assertTrue(user.isAccountNonExpired());
        assertFalse(user.isLocked());
        assertTrue(user.isCredentialsNonExpired());
        assertTrue(user.isEnabled());

        // Lock account
        user.setLocked(true);

        assertTrue(user.isAccountNonExpired());
        assertTrue(user.isLocked());
        assertTrue(user.isCredentialsNonExpired());
        assertTrue(user.isEnabled());

        // Unlock account
        user.setLocked(false);

        assertTrue(user.isAccountNonExpired());
        assertFalse(user.isLocked());
        assertTrue(user.isCredentialsNonExpired());
        assertTrue(user.isEnabled());
    }
}
