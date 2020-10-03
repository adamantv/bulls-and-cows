package com.mygame.repositories;

import com.mygame.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий пользователей
 */
@Repository
public interface UserRepository extends CrudRepository<User, String> {
    /**
     * Найти пользователя по имени
     */
    User findByUsernameIgnoreCase(String username);

}
