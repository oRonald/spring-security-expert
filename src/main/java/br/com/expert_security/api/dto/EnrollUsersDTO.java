package br.com.expert_security.api.dto;

import br.com.expert_security.domain.entity.Users;
import lombok.Data;

import java.util.List;

@Data
public class EnrollUsersDTO {

    private Users user;
    private List<String> permissions;
}
