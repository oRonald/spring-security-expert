package br.com.expert_security.domain.repository;

import br.com.expert_security.domain.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, String> {

    Optional<Group> findByName(String name);
}
