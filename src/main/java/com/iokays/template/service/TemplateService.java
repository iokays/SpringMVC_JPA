package com.iokays.template.service;

import java.io.IOException;
import java.util.List;

import com.iokays.column.domain.Column;

public interface TemplateService {

    public abstract void build(final Column root, final List<Column> columns_root) throws IOException;

    public abstract void buildArticle(String id) throws IOException;

    public abstract void buildColumn(String id) throws IOException;

    public abstract void buildHomePage() throws IOException;

}