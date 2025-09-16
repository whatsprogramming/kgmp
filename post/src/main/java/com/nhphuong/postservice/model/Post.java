package com.nhphuong.postservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "post")
@Getter @Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Post {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;

    private String content;

    private List<String> tags;


}
