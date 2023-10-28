package com.white.shoppinglist.appuser;

import org.springframework.data.repository.CrudRepository;

//perus kauraa
public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    AppUser findByUsername(String username);
    
}
