package com.mindstorm.data.repository.base;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ecandela on 05/09/2015.
 */
@NoRepositoryBean
public interface  BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    void clearHibenateCache();

    List<T> getAllUsingCache(Pageable page);
}
