package com.forum.exam9.services;

import com.forum.exam9.model.Comment;
import com.forum.exam9.model.Theme;
import com.forum.exam9.repostory.CommentRepository;
import com.forum.exam9.repostory.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private ThemeRepository themeRepository;

    @Autowired
    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Autowired
    public void setThemeRepository(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    public Comment save(Comment comment, Theme theme){
        theme.setCommentQuantity(theme.getCommentQuantity()+1);
        themeRepository.update(theme.getCommentQuantity(),theme.getId());
        var theme1=themeRepository.findById(theme.getId()).orElseThrow(()->
            new RuntimeException("not found")
        );
        comment.setDate(LocalDateTime.now());
        comment.setTheme(theme1);
        return commentRepository.save(comment);
    }
    public Page<Comment> findPaginated(Long themeId,int pageNo) {
        String sortField="date";
        Sort sort=Sort.by(sortField);
        var sort1=sort.ascending();
        Pageable pageable= PageRequest.of(pageNo-1,5,sort1);
        return commentRepository.findAllByThemeId(themeId,pageable);
    }
    public void deleteAll(){
        commentRepository.deleteAll();
    }

}
