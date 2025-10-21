package br.com.expert_security.api;

import br.com.expert_security.api.dto.EnrollUsersDTO;
import br.com.expert_security.domain.entity.Users;
import br.com.expert_security.domain.service.UsersService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @PostMapping
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Users> enroll(@RequestBody EnrollUsersDTO dto){
        Users user = usersService.save(dto.getUser(), dto.getPermissions());
        return ResponseEntity.ok(user);
    }
}
