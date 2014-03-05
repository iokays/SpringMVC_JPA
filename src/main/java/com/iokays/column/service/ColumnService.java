package com.iokays.column.service;

import java.io.Serializable;
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

    public abstract List<Column> findAllByParent(Serializable parentId, Sort sort);

    public abstract Page<Column> findAllByParent(Serializable parentId, Pageable pageable);

    public abstract List<Column> findAllByGrade(Column.Grade grade);

    public abstract List<Column> findAllByGrade(Column.Grade grade, Sort sort);

    public abstract Page<Column> findAllByGrade(Column.Grade grade, Pageable pageable);

    public abstract List<Column> findAllByGradeIn(Column.Grade[] grades);

    public abstract List<Column> findAllByGrade(Column.Grade[] grades, Sort sort);

    public abstract Page<Column> findAllByGrade(Column.Grade[] grades, Pageable pageable);

    public abstract Column save(Column column);

    public abstract Integer update(Serializable id, Map<String, Object> map) throws Exception;

    public abstract Column findOne(Serializable id);

    public abstract Integer delete(Serializable id);


}