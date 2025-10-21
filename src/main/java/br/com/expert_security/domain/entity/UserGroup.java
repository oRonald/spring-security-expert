package br.com.expert_security.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    public UserGroup(Users user, Group group) {
        this.user = user;
        this.group = group;
    }
}
