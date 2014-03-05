package com.iokays.template.service;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import com.iokays.column.domain.Column;

public interface TemplateService {

    public abstract void build(final Column root, final List<Column> columns_root) throws IOException;

    public abstract void buildArticle(Serializable id) throws IOException;

    public abstract void buildColumn(Serializable id) throws IOException;

    public abstract void buildHomePage() throws IOException;

}