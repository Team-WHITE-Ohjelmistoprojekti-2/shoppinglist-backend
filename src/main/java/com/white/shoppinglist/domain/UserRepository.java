package com.white.shoppinglist.domain;

import org.springframework.data.repository.CrudRepository;

//perus kauraa
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    
}
