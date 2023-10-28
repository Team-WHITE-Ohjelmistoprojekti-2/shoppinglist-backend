package com.white.shoppinglist.appuser;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

//perus kauraa
public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
    
}
