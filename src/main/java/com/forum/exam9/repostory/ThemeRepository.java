package com.forum.exam9.repostory;

import com.forum.exam9.model.Theme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ThemeRepository extends JpaRepository<Theme,Long> {
    Page<Theme> findAll(Pageable pageable);
    @Modifying
    @Transactional
    @Query("update Theme t set t.commentQuantity=:quantity where t.id=:id")
    void update(@Param("quantity") Integer quantity,
                 @Param("id") Long id);
}
