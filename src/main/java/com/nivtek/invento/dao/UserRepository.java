package com.nivtek.invento.dao;

import com.nivtek.invento.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * @project Invento
 * @author AsimSubedi created on 5/12/2020
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User save(User user);
}
