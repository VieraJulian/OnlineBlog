package com.example.OnlineBlog.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts")
@SQLDelete(sql = "UPDATE posts SET enabled=false WHERE id = ?")
//@Where(clause = "enabled = true")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private boolean enabled;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
