package com.forum.exam9.services;

import com.forum.exam9.model.Theme;
import com.forum.exam9.repostory.ThemeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class ThemeService {
    private ThemeRepository themeRepository;

    public ThemeService(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }
    public Page<Theme> findPaginated(int pageNo) {
        String sortField="date";
        String sortDirection="ASC";
        var sort=Sort.by(sortField,sortDirection);
        Pageable pageable=PageRequest.of(pageNo-1,10,sort);
        return themeRepository.findAll(pageable);
    }
    @Transactional
    public Theme save(Theme theme){
        theme.setDate(LocalDate.now());
        return themeRepository.save(theme);
    }
}
