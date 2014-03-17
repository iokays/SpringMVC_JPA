package com.iokays.column.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.iokays.column.domain.Column;

public interface ColumnService {

    public abstract List<Column> findAll();

    public abstract List<Column> findAll(Sort sort);

    public abstract Page<Column> findAll(Pageable pageable);

    public abstract List<Column> findAllByParent(Column parent);

    public abstract List<Column> findAllByParent(Column parent, Sort sort);

    public abstract Page<Column> findAllByParent(Column parent, Pageable pageable);

    public abstract List<Column> findAllByParent(String parentId, Sort sort);

    public abstract Page<Column> findAllByParent(String parentId, Pageable pageable);

    public abstract List<Column> findAllByGrade(Column.Grade grade);

    public abstract List<Column> findAllByGrade(Column.Grade grade, Sort sort);

    public abstract Page<Column> findAllByGrade(Column.Grade grade, Pageable pageable);

    public abstract List<Column> findAllByGradeIn(Column.Grade[] grades);

    public abstract List<Column> findAllByGrade(Column.Grade[] grades, Sort sort);

    public abstract Page<Column> findAllByGrade(Column.Grade[] grades, Pageable pageable);

    public abstract Column save(Column column);

    public abstract Column update(String id, Map<String, Object> map);

    public abstract Column findOne(String id);
    
    public abstract Column findByMarking(String marking);

    public abstract void delete(String id);


}