package com.forum.exam9.controller;

import com.forum.exam9.dto.CommentDto;
import com.forum.exam9.dto.ThemeDto;
import com.forum.exam9.model.Comment;
import com.forum.exam9.services.CommentService;
import com.forum.exam9.services.ThemeService;
import com.forum.exam9.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.stream.Collectors;

@Controller
public class CommentController {
    static Long id=0L;
    private CommentService commentService;
    private ThemeService themeService;
    private UserService userService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
@Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setThemeService(ThemeService themeService) {
        this.themeService = themeService;
    }

    @GetMapping("/comment/{themeId}")
    public String showPaginated(@PathVariable Long themeId,
                                @RequestParam(name = "pageNo", required = false) Integer pageNo, Model model) {
        if (pageNo == null) {
            pageNo = 1;
        }
        var theme =themeService.findById(themeId);
                var pageable = commentService.findPaginated(themeId, pageNo);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", pageable.getTotalPages());
        model.addAttribute("totalItems", pageable.getTotalElements());
        var dtos = pageable.getContent()
                .stream().map(CommentDto::fromAll).collect(Collectors.toList());
        model.addAttribute("listComments", dtos);
        model.addAttribute("themeId", themeId);
        model.addAttribute("theme",ThemeDto.fromAll(theme));
        id=themeId;
        return "comment";
    }
    @ResponseBody
    @CrossOrigin(origins = "*")
    @PostMapping("/comment/{themeId}")
    public ResponseEntity<?> addComment(@RequestBody String text,@PathVariable Long themeId, Principal principal){
        if(principal.getName()==null){
            throw new RuntimeException("invalid");
        }
        var user=userService.findByUserName(principal.getName()).orElseThrow(()->
        new RuntimeException("not found"));
        var theme=themeService.findById(themeId);
        var comment=commentService.save(Comment.builder().text(text)
        .user(user).build(),theme);
        var commentDto=CommentDto.fromAll(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentDto);

    }
}
