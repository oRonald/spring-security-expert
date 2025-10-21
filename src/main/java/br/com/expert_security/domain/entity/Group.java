package br.com.expert_security.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "group_table")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
}
