package br.com.expert_security.domain.service;

import br.com.expert_security.domain.entity.Group;
import br.com.expert_security.domain.entity.UserGroup;
import br.com.expert_security.domain.entity.Users;
import br.com.expert_security.domain.repository.GroupRepository;
import br.com.expert_security.domain.repository.UsersGroupRepository;
import br.com.expert_security.domain.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final GroupRepository groupRepository;
    private final UsersGroupRepository usersGroupRepository;
    private final PasswordEncoder passwordEncoder;

    public Users save(Users users, List<String> groups){
        String userPassEncode = passwordEncoder.encode(users.getPassword());
        users.setPassword(userPassEncode);
        usersRepository.save(users);

        List<UserGroup> userGroupList = groups.stream().map(groupName -> {
            Optional<Group> possibleGroup = groupRepository.findByName(groupName);
            if(possibleGroup.isPresent()){
                Group group = possibleGroup.get();
                return new UserGroup(users, group);
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());

        usersGroupRepository.saveAll(userGroupList);

        return users;
    }

    public Users getUserWithPermissions(String login){
        Optional<Users> usersOptional = usersRepository.findByLogin(login);
        if(usersOptional.isEmpty()){
            return null;
        }

        Users user = usersOptional.get();
        List<String> permissionsByUsers = usersGroupRepository.findPermissionsByUsers(user);
        user.setPermissions(permissionsByUsers);

        return user;
    }
}
