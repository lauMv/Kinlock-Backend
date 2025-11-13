package com.app.kinlock.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)   // <-- change this
    private Integer id;
    private String name;
    private Long ci;
}
