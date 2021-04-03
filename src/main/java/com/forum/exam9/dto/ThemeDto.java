package com.forum.exam9.dto;

import com.forum.exam9.model.Theme;
import lombok.*;

import java.time.LocalDate;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ThemeDto {
    private String userName;
    private LocalDate date;
    private String id;
    private Integer commentQuantity=0;
    private String name;
    private String description;
    public static ThemeDto fromAll(Theme theme){
        return ThemeDto.builder()
                .commentQuantity(theme.getCommentQuantity())
                .date(theme.getDate())
                .userName(theme.getUser().getUsername())
                .id(theme.getId().toString())
                .name(theme.getName())
                .description(theme.getDescription())
                .build();
    }
}
