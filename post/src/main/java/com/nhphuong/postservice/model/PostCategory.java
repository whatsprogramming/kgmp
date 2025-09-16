package com.nhphuong.postservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "post_category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostCategory extends AbstractAuditEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;

}
