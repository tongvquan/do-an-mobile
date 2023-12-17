package org.example.doanmobile.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "notes")
public class NoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;


    @Column(name = "modifieddate")
    private Date modifiedDate;



    @ManyToOne
    @JoinColumn(name = "userid")
    private UserEntity user;
}
