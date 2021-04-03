package com.forum.exam9.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "themes")
public class Theme extends BasicEntity{
    private String name;

    @OneToMany(mappedBy = "theme")
    private List<Comment> comments=new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Integer commentQuantity;
    private LocalDate date;
}
