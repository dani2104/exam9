package com.forum.exam9.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    private String description;

    @OneToMany(mappedBy = "theme")
    private List<Comment> comments=new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Integer commentQuantity=0;
    private LocalDate date;

}
