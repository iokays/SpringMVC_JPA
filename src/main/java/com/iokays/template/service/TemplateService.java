package com.iokays.template.service;

import java.io.IOException;

public interface TemplateService {
	
	public abstract void build(String articleId) throws IOException;
	
    public abstract void buildHomePage() throws IOException;
    public abstract void buildOneColumn(String id) throws IOException;
    public abstract void buildTwoColumn(String id) throws IOException;
    public abstract void buildArticle(String id) throws IOException;

}