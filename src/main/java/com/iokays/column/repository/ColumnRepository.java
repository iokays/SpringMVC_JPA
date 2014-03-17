package com.iokays.column.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iokays.column.domain.Column;
import com.iokays.column.repository.plus.ColumnRepositoryPlus;

public interface ColumnRepository extends JpaRepository<Column, String>, ColumnRepositoryPlus {

    public abstract List<Column> findAllByGrade(Column.Grade grade);

    public abstract List<Column> findAllByGrade(Column.Grade grade, Sort sort);

    public abstract Page<Column> findAllByGrade(Column.Grade grade, Pageable pageable);

    public abstract List<Column> findAllByGradeIn(Column.Grade[] grades);

    public abstract List<Column> findAllByGrade(Column.Grade[] grades, Sort sort);

    public abstract Page<Column> findAllByGrade(Column.Grade[] grades, Pageable pageable);

    public abstract List<Column> findAllByParent(Column parent);

    public abstract List<Column> findAllByParent(Column parent, Sort sort);

    public abstract Page<Column> findAllByParent(Column parent, Pageable pageable);
    
    public abstract Column findByMarking(String marking);

    @Query(value = "from Column t1 where t1.parent.id = :parentId")
    public abstract List<Column> findAllByParent(@Param("parentId") Serializable parentId);

    @Query(value = "from Column t1 where t1.parent.id = :parentId")
    public abstract List<Column> findAllByParent(@Param("parentId") Serializable parentId, Sort sort);

    @Query(value = "from Column t1 where t1.parent.id = :parentId")
    public abstract Page<Column> findAllByParent(@Param("parentId") Serializable parentId, Pageable pageable);
}
