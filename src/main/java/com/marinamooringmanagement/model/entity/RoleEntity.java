package com.marinamooringmanagement.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "employee_role")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity extends KeyEntity{
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "role"
    )
    private List<EmployeeEntity> employees;

    @Override
    public String toString() {
        return "RoleEntity{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +

                '}';
    }
}
