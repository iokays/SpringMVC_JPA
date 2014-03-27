package com.iokays.template.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.iokays.article.domain.Article;
import com.iokays.article.service.ArticleService;
import com.iokays.column.domain.Column;
import com.iokays.column.service.ColumnService;
import com.iokays.homepage.domain.HomePage;
import com.iokays.homepage.service.HomePageService;
import com.iokays.template.service.TemplateService;

@Component("templateService")
public class TemplateServiceImpl implements TemplateService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TemplateServiceImpl.class);
	
	/**
	 * 嵌套生成
	 * @param articleId
	 * @throws IOException
	 */
	public void build(String articleId) throws IOException {
		Article article = articleService.findOne(articleId);
		buildArticle(article.getId());
		Column column = columnService.findOne(article.getColumn().getId());
		buildTwoColumn(column.getId());
		buildOneColumn(column.getParent().getId());
		buildHomePage();
	}

	/**
	 * 生成首页
	 */
	public void buildHomePage() throws IOException  {
		List<Column> menus = columnService.findAllByGrade(Column.Grade.one, new Sort("sort"));
		Pageable pageable = new PageRequest(0, 6, Direction.DESC, "createDate");
		
		for (Column column : menus) {
			column.setArticles(articleService.findAllByColumnParent(column.getId(), pageable).getContent());
		}
		List<HomePage> homePages = homePageService.findAll(new Sort("sort"));			//获取首页图片地址
		
		List<List<Column>> row = new ArrayList<List<Column>>();
		for (int i = 1; i < menus.size(); i += 2) {
			List<Column> _list = new ArrayList<Column>();
			_list.add(menus.get(i - 1));
			_list.add(menus.get(i));
			row.add(_list);
		}
		
		Properties p = new Properties();
		p.load(this.getClass().getResourceAsStream("/velocity.properties"));
		Velocity.init(p);
		VelocityContext context = new VelocityContext();
		
		
		context.put("menus", menus);
		context.put("homePages", homePages);
		
		context.put("row", row);
		
		Template template = Velocity.getTemplate("com/iokays/template/vm/index.vm", "UTF-8");
		PrintWriter printWriter = new PrintWriter(System.getProperty("webapp.root") + File.separator  +"index.html", "UTF-8");
		
		LOGGER.debug("buildTwoColumn:url:{}", System.getProperty("webapp.root") + File.separator  +"index.html", "UTF-8");
		template.merge(context, printWriter);
		printWriter.flush();  
		printWriter.close();
	}
	
	/**
	 * 生成一级栏目
	 * @throws IOException 
	 */
	public void buildOneColumn(String id) throws IOException {
		List<Column> menus = columnService.findAllByGrade(Column.Grade.one, new Sort("sort"));
		Column column = columnService.findOne(id);
		column.setChildren(columnService.findAllByParent(column));
		List<Article> articles = articleService.findAllByColumnParent(column.getId(), null).getContent();
		Properties p = new Properties();
		p.load(this.getClass().getResourceAsStream("/velocity.properties"));
		
		Velocity.init(p);
		VelocityContext context = new VelocityContext();
		
		context.put("menus", menus);
		context.put("column", column);
		context.put("articles", articles);
		
		LOGGER.debug("buildOneColumn:{}", "com/iokays/template/vm/" + column.getTemplate() + ".vm");
		Template template = Velocity.getTemplate("com/iokays/template/vm/" + column.getTemplate() + ".vm", "UTF-8");
		PrintWriter printWriter = new PrintWriter(System.getProperty("webapp.root") + File.separator + column.getMarking() + ".html", "UTF-8");
		LOGGER.debug("buildTwoColumn:url:{}", System.getProperty("webapp.root") + File.separator + column.getMarking() + ".html");
		template.merge(context, printWriter);
		printWriter.flush();  
		printWriter.close();
		
	}
	
	/**
	 * 生成二级栏目
	 * @throws IOException 
	 */
	public void buildTwoColumn(String id) throws IOException {
		List<Column> menus = columnService.findAllByGrade(Column.Grade.one, new Sort("sort"));
		Column column = columnService.findOne(id);
		column.setParent(columnService.findOne(column.getParent().getId()));
		column.getParent().setChildren(columnService.findAllByParent(column.getParent()));
		List<Article> articles = articleService.findAllByColumn(column.getId(), null).getContent();
		Properties p = new Properties();
		p.load(this.getClass().getResourceAsStream("/velocity.properties"));
		
		Velocity.init(p);
		VelocityContext context = new VelocityContext();
		
		context.put("menus", menus);
		context.put("column", column);
		context.put("articles", articles);
		LOGGER.debug("buildTwoColumn:template:{}", "com/iokays/template/vm/" + column.getTemplate() + ".vm");
		Template template = Velocity.getTemplate("com/iokays/template/vm/" + column.getTemplate() + ".vm", "UTF-8");
		PrintWriter printWriter = new PrintWriter(System.getProperty("webapp.root") + File.separator + column.getMarking() + ".html", "UTF-8");
		LOGGER.debug("buildTwoColumn:url:{}", System.getProperty("webapp.root") + File.separator + column.getMarking() + ".html");
		template.merge(context, printWriter);
		printWriter.flush();  
		printWriter.close();
		
	}
	
	/**
	 * 生成文章
	 * @throws IOException 
	 */
	public void buildArticle(String id) throws IOException {
		List<Column> menus = columnService.findAllByGrade(Column.Grade.one, new Sort("sort"));
		Article article = articleService.findOne(id);
		article.setColumn(columnService.findOne(article.getColumn().getId()));
		article.getColumn().setParent(columnService.findOne(article.getColumn().getParent().getId()));
		article.getColumn().getParent().setChildren(columnService.findAllByParent(article.getColumn().getParent()));
		Properties p = new Properties();
		p.load(this.getClass().getResourceAsStream("/velocity.properties"));
		
		Velocity.init(p);
		VelocityContext context = new VelocityContext();
		
		context.put("menus", menus);
		context.put("article", article);
		context.put("content", new String(article.getContent()));
		
		LOGGER.debug("buildArticle:template:{}", "com/iokays/template/vm/article.vm");
		Template template = Velocity.getTemplate("com/iokays/template/vm/article.vm", "UTF-8");
		PrintWriter printWriter = new PrintWriter(System.getProperty("webapp.root") + File.separator + "article" + File.separator + article.getId() + ".html", "UTF-8");
		LOGGER.debug("buildTwoColumn:url:{}", System.getProperty("webapp.root") + File.separator + "article" + File.separator + article.getId() + ".html");
		template.merge(context, printWriter);
		printWriter.flush();  
		printWriter.close();
	}

	@Resource
	ColumnService columnService;
	
	@Resource
	HomePageService homePageService;
	
	@Resource
	ArticleService articleService;
}