package com.example.wishlist.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Gift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Boolean isReserved;
//    @ManyToOne
//    private User user;
}
