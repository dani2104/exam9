package com.forum.exam9.util;

import com.forum.exam9.model.Comment;
import com.forum.exam9.model.Theme;
import com.forum.exam9.model.User;
import com.forum.exam9.services.CommentService;
import com.forum.exam9.services.ThemeService;
import com.forum.exam9.services.UserService;
import com.forum.exam9.user.CrmUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FillDatabase {
    private UserService userService;
    private ThemeService themeService;
    private CommentService commentService;

    public FillDatabase(UserService userService, ThemeService themeService, CommentService commentService) {
        this.userService = userService;
        this.themeService = themeService;
        this.commentService = commentService;
    }

    @Bean
    public void doFill(){
        commentService.deleteAll();

        themeService.deleteAll();
        userService.deleteAll();
        for(int i=0;i<2;i++){
            var user=new CrmUser();
            user.setUserName("user"+i+"@gmail.com");
            user.setPassword("password");
            user.setMatchingPassword("password");
            userService.save(user);
        }
        fillThemes();
    }
    private void fillThemes(){
        var user=userService.findAll().get(0);
        for(int i=0;i<20;i++){
            var theme=Theme.builder().description("description"+i)
                    .name("name"+1)
                    .user(user)
                    .commentQuantity(0)
                    .build();
            themeService.save(theme);
        }
        fillComments();
    }
    private void fillComments(){
        var user=userService.findAll().get(0);
        var themes=themeService.findAll();
        var theme=themeService.findAll().get(themes.size()-1);
        for(int i=0;i<20;i++){
            var comment= Comment.builder()
                    .text("comment service"+i)
                    .user(user)
                    .theme(theme)
                    .build();
            commentService.save(comment,theme);
        }

    }
}
