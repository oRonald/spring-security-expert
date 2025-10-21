package br.com.expert_security.domain.repository;

import br.com.expert_security.domain.entity.UserGroup;
import br.com.expert_security.domain.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersGroupRepository extends JpaRepository<UserGroup, String> {

    @Query("""
            select distinct g.name from UserGroup ug
            join ug.group g
            join ug.user u
            where u = ?1
            """)
    List<String> findPermissionsByUsers(Users users);
}
