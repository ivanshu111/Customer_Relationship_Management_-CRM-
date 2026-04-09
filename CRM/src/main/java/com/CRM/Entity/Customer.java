package com.CRM.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@AllArgsConstructor @NoArgsConstructor
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String email;
    private String phone;

    // Many customers → one Employee
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users assignedTo;

    // One customer → many interactions
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Interaction> interactions;

    // One customer → many leads
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Leads> leads;
}
