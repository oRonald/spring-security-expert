package br.com.expert_security.domain.repository;

import br.com.expert_security.domain.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersGroupRepository extends JpaRepository<UserGroup, String> {
}
