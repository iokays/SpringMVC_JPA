package com.iokays.template.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iokays.article.domain.Article;
import com.iokays.article.service.ArticleService;
import com.iokays.column.domain.Column;
import com.iokays.column.service.ColumnService;
import com.iokays.homepage.domain.HomePage;
import com.iokays.homepage.service.HomePageService;
import com.iokays.template.service.TemplateService;

@Service("templateService")
@Transactional
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
		buildTwoColumn(article.getColumn().getId());
		buildOneColumn(article.getColumn().getParent().getId());
		buildHomePage();
	}

	/**
	 * 生成首页
	 */
	public void buildHomePage() throws IOException  {
		List<Column> rootColumns = columnService.findAllByGrade(Column.Grade.one, new Sort("sort"));
		Pageable pageable = new PageRequest(0, 6, Direction.DESC, "createDate");
		List<HomePage> homePages = homePageService.findAll(new Sort("sort"));			//获取首页图片地址
		
		final Column news = columnService.findByMarking("news");						//获取公益资讯ID
		List<Object[]> newsArticles = articleService.findTitleAndCreateDateByColumnParentId(news.getId(), pageable).getContent();
		
		final Column together = columnService.findByMarking("together");				//获取与你同在ID
		List<Object[]> togetherArticles = articleService.findTitleAndCreateDateByColumnParentId(together.getId(), pageable).getContent();
		
		final Column postulant = columnService.findByMarking("postulant");				//获取志愿者ID
		List<Object[]> postulantArticles = articleService.findTitleAndCreateDateByColumnParentId(postulant.getId(), pageable).getContent();
		
		final Column about = columnService.findByMarking("postulant");					//获取关于公益ID
		List<Object[]> aboutArticles = articleService.findTitleAndCreateDateByColumnParentId(about.getId(), pageable).getContent();
		
		Properties p = new Properties();
		p.load(this.getClass().getResourceAsStream("/velocity.properties"));
		Velocity.init(p);
		VelocityContext context = new VelocityContext();
		
		context.put("rootColumns", rootColumns);
		context.put("homePages", homePages);
		context.put("newsArticles", newsArticles);
		context.put("togetherArticles", togetherArticles);
		context.put("postulantArticles", postulantArticles);
		context.put("aboutArticles", aboutArticles);
		
		Template template = Velocity.getTemplate("com/iokays/template/vm/index.vm", "UTF-8");
		PrintWriter printWriter = new PrintWriter(System.getProperty("webapp.root") + "/index.html", "UTF-8");
		LOGGER.debug("buildTwoColumn:url:{}", System.getProperty("webapp.root") + "/index.html", "UTF-8");
		template.merge(context, printWriter);
		printWriter.flush();  
		printWriter.close();
	}
	
	/**
	 * 生成一级栏目
	 * @throws IOException 
	 */
	public void buildOneColumn(String id) throws IOException {
		Column column = columnService.findOne(id);
		List<Object[]> articles = articleService.findTitleAndCreateDateByColumnParentId(column.getId(), null).getContent();
		Properties p = new Properties();
		p.load(this.getClass().getResourceAsStream("/velocity.properties"));
		
		Velocity.init(p);
		VelocityContext context = new VelocityContext();
		
		context.put("column", column);
		context.put("articles", articles);
		
		LOGGER.debug("buildOneColumn:{}", "com/iokays/template/vm/" + column.getMarking() + ".vm");
		Template template = Velocity.getTemplate("com/iokays/template/vm/" + column.getMarking() + ".vm", "UTF-8");
		PrintWriter printWriter = new PrintWriter(System.getProperty("webapp.root") + "/" + column.getMarking() + ".html", "UTF-8");
		LOGGER.debug("buildTwoColumn:url:{}", System.getProperty("webapp.root") + "/" + column.getMarking() + ".html");
		template.merge(context, printWriter);
		printWriter.flush();  
		printWriter.close();
		
	}
	
	/**
	 * 生成二级栏目
	 * @throws IOException 
	 */
	public void buildTwoColumn(String id) throws IOException {
		Column column = columnService.findOne(id);
		List<Object[]> articles = articleService.findTitleAndColumnNameByColumnId(column.getId(), null).getContent();
		Properties p = new Properties();
		p.load(this.getClass().getResourceAsStream("/velocity.properties"));
		
		Velocity.init(p);
		VelocityContext context = new VelocityContext();
		
		context.put("column", column);
		context.put("articles", articles);
		LOGGER.debug("buildTwoColumn:template:{}", "com/iokays/template/vm/" + column.getMarking() + ".vm");
		Template template = Velocity.getTemplate("com/iokays/template/vm/" + column.getMarking() + ".vm", "UTF-8");
		PrintWriter printWriter = new PrintWriter(System.getProperty("webapp.root") + "/" + column.getMarking() + ".html", "UTF-8");
		LOGGER.debug("buildTwoColumn:url:{}", System.getProperty("webapp.root") + "/" + column.getMarking() + ".html");
		template.merge(context, printWriter);
		printWriter.flush();  
		printWriter.close();
		
	}
	
	/**
	 * 生成文章
	 * @throws IOException 
	 */
	public void buildArticle(String id) throws IOException {
		
		Article article = articleService.findOne(id);
		article.getColumn();
		Properties p = new Properties();
		p.load(this.getClass().getResourceAsStream("/velocity.properties"));
		
		Velocity.init(p);
		VelocityContext context = new VelocityContext();
		
		context.put("article", article);
		
		LOGGER.debug("buildArticle:template:{}", "com/iokays/template/vm/" + article.getColumn().getMarking() + "_article.vm");
		Template template = Velocity.getTemplate("com/iokays/template/vm/" + article.getColumn().getMarking() + "_article.vm", "UTF-8");
		PrintWriter printWriter = new PrintWriter(System.getProperty("webapp.root") + "/" + article.getId() + ".html", "UTF-8");
		LOGGER.debug("buildTwoColumn:url:{}", System.getProperty("webapp.root") + "/" + article.getId() + ".html");
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