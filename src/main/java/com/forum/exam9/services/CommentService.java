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
    @Transactional
    public Comment save(Comment comment, Theme theme){
        theme.setCommentQuantity(theme.getCommentQuantity()+1);
        themeRepository.save(theme) ;
        comment.setDate(LocalDate.now());
        return commentRepository.save(comment);
    }
    public Page<Comment> findPaginated(int pageNo) {
        String sortField="date";
        String sortDirection="ASC";
        var sort= Sort.by(sortField,sortDirection);
        Pageable pageable= PageRequest.of(pageNo-1,10,sort);
        return commentRepository.findAll(pageable);
    }
}
