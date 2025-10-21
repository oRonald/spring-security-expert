package br.com.expert_security.domain.repository;

import br.com.expert_security.domain.entity.Users;
import org.h2.engine.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, String> {
    Optional<Users> findByLogin(String login);
}
