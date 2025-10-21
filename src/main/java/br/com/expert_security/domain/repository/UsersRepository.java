package br.com.expert_security.domain.repository;

import br.com.expert_security.domain.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, String> {

}
