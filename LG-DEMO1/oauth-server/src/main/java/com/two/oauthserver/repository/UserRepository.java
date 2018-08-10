package com.two.oauthserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.two.oauthserver.domain.User;

/**
 * Created by SuperS on 2017/9/25.
 *
 * @author SuperS
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
}
