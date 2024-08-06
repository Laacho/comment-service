package com.tinqinacademy.comments.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "comments")

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String content;
    @Column(name = "published_date")
    private LocalDate publishedDate;
    @Column(name = "last_edited_date")
    private LocalDate lastEditedDate;
    @Column(name = "room_id")
    private UUID roomId;
    @Column(name = "last_edited_by")
    private String lastEditeBy;
    @ManyToOne
    private User user;
}
