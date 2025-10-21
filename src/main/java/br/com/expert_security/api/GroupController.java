package br.com.expert_security.api;

import br.com.expert_security.domain.entity.Group;
import br.com.expert_security.domain.repository.GroupRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupRepository groupRepository;

    @PostMapping
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Group> enroll(@RequestBody Group group){
        groupRepository.save(group);
        return ResponseEntity.ok(group);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Group>> getGroups(){
        return ResponseEntity.ok(groupRepository.findAll());
    }
}
