package com.iokays.column.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iokays.column.domain.Column;
import com.iokays.column.domain.Column.Grade;
import com.iokays.column.repository.ColumnRepository;
import com.iokays.column.service.ColumnService;

@Service("columnService")
@Transactional
public class ColumnServiceImpl implements ColumnService {

    @Resource
    private ColumnRepository columnRepository;

    public List<Column> findAllByParent(Serializable parentId, Sort sort) {
        return columnRepository.findAllByParent(parentId, sort);
    }

    public Page<Column> findAllByParent(Serializable parentId, Pageable pageable) {
        return columnRepository.findAllByParent(parentId, pageable);
    }

    /* (non-Javadoc)
     * @see com.iokays.column.repository.service.impl.ColumnService#findAll()
     */
    @Override
    public List<Column> findAll() {
        return columnRepository.findAll();
    }

    /* (non-Javadoc)
     * @see com.iokays.column.repository.service.impl.ColumnService#findAll(org.springframework.data.domain.Sort)
     */
    @Override
    public List<Column> findAll(Sort sort) {
        return columnRepository.findAll(sort);
    }

    /* (non-Javadoc)
     * @see com.iokays.column.repository.service.impl.ColumnService#findAll(org.springframework.data.domain.Pageable)
     */
    @Override
    public Page<Column> findAll(Pageable pageable) {
        return columnRepository.findAll(pageable);
    }

    /*
     * (non-Javadoc)
     * @see com.iokays.column.service.ColumnService#findAllByParent(com.iokays.column.domain.Column)
     */
    @Override
    public List<Column> findAllByParent(Column parent) {
        return columnRepository.findAllByParent(parent);
    }

    /*
     * (non-Javadoc)
     * @see com.iokays.column.service.ColumnService#findAllByParent(com.iokays.column.domain.Column, org.springframework.data.domain.Sort)
     */
    @Override
    public List<Column> findAllByParent(Column parent, Sort sort) {
        return columnRepository.findAllByParent(parent, sort);
    }

    /*
     * (non-Javadoc)
     * @see com.iokays.column.service.ColumnService#findAllByParent(com.iokays.column.domain.Column, org.springframework.data.domain.Pageable)
     */
    @Override
    public Page<Column> findAllByParent(Column parent, Pageable pageable) {
        return columnRepository.findAllByParent(parent, pageable);
    }

    /* (non-Javadoc)
     * @see com.iokays.column.repository.service.impl.ColumnService#save(com.iokays.column.domain.Column)
     */
    @Override
    public Column save(Column column) {
        return columnRepository.save(column);
    }

    /*
     * (non-Javadoc)
     * @see com.iokays.column.service.ColumnService#update(java.io.Serializable, java.util.Map)
     */
    @Override
    public Integer update(Serializable id, Map<String, Object> map) throws Exception {
        return columnRepository.update(id, map);
    }

    /* (non-Javadoc)
     * @see com.iokays.column.repository.service.impl.ColumnService#findOne(java.io.Serializable)
     */
    @Override
    public Column findOne(Serializable id) {
        return columnRepository.findOne(id);
    }

    /* (non-Javadoc)
     * @see com.iokays.column.repository.service.impl.ColumnService#delete(java.io.Serializable)
     */
    @Override
    public Integer delete(Serializable id) {
        return columnRepository.deleteById(id);
    }


    @Override
    public List<Column> findAllByGrade(Grade grade) {
        return columnRepository.findAllByGrade(grade);
    }

    @Override
    public List<Column> findAllByGrade(Grade grade, Sort sort) {
        return columnRepository.findAllByGrade(grade, sort);
    }

    @Override
    public Page<Column> findAllByGrade(Grade grade, Pageable pageable) {
        return columnRepository.findAllByGrade(grade, pageable);
    }

    @Override
    public List<Column> findAllByGradeIn(Grade[] grades) {
        return columnRepository.findAllByGradeIn(grades);
    }

    @Override
    public List<Column> findAllByGrade(Grade[] grades, Sort sort) {
        return columnRepository.findAllByGrade(grades, sort);
    }

    @Override
    public Page<Column> findAllByGrade(Grade[] grades, Pageable pageable) {
        return columnRepository.findAllByGrade(grades, pageable);
    }

}
