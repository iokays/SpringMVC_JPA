package com.iokays.template.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.iokays.article.domain.Article;
import com.iokays.article.service.ArticleService;
import com.iokays.column.domain.Column;
import com.iokays.column.service.ColumnService;
import com.iokays.homepage.domain.HomePage;
import com.iokays.homepage.service.HomePageService;
import com.iokays.template.service.TemplateService;

@Service("templateService")
public class TemplateServiceImpl implements TemplateService {
	
	public void build(final Column root,final List<Column> columns_root) throws IOException {
		
		Properties p = new Properties();
		p.load(this.getClass().getResourceAsStream("/velocity.properties"));
		Velocity.init(p);
		VelocityContext context = new VelocityContext();
		context.put("columns_root", columns_root);
			//生成一级栏目
			{	
				Column column = columnService.findOne(root.getId());
				column.setChildren(columnService.findAllByParent(column, new Sort("sort")));
				if (null != column.getChildren() && 0 != column.getChildren().size()) {
					column.getChildren().get(0).setArticles(articleService.findByColumn(column.getChildren().get(0).getId()));
					if (null != column.getChildren().get(0).getArticles() && 0 != column.getChildren().get(0).getArticles().size()) {
						context.put("column", column);
						Template template = Velocity.getTemplate("com/iokays/template/vm/" + column.getTemplate(), "UTF-8");
						PrintWriter printWriter = new PrintWriter(System.getProperty("webapp.root") + column.getId()+ ".html", "UTF-8");
						template.merge(context, printWriter);
						printWriter.flush();  
						printWriter.close();
						context.remove("column");
					}
				
				}
				
			}
			
			//生成二级栏目
			for (int i = 0; null != root.getChildren() && i < root.getChildren().size(); ++i) {
				{
					Column column = columnService.findOne(root.getChildren().get(i).getId());
					column.setParent(columnService.findOne(column.getParent().getId()));
					column.getParent().setChildren(columnService.findAllByParent(column.getParent(), new Sort("sort")));
					column.setArticles(articleService.findByColumn(column.getId()));
					if (null != column.getArticles() && 0 != column.getArticles().size()) {
						context.put("column", column);
						
						Template template = Velocity.getTemplate("com/iokays/template/vm/" + column.getTemplate(), "UTF-8");
						PrintWriter printWriter = new PrintWriter(System.getProperty("webapp.root") + column.getId()+ ".html", "UTF-8");
						template.merge(context, printWriter);
						printWriter.flush();  
						printWriter.close();
						context.remove("column");
					}
					
				}
				
				//生成文章
				for (int j = 0; null != root.getChildren().get(i).getArticles() && j < root.getChildren().get(i).getArticles().size(); ++j) {
					{
						Article article = articleService.findOne(root.getChildren().get(i).getArticles().get(j).getId());
						article.setColumn(columnService.findOne(article.getColumn().getId()));
						article.getColumn().setParent(columnService.findOne(article.getColumn().getParent().getId()));
						article.getColumn().getParent().setChildren(columnService.findAllByParent(article.getColumn().getParent(), new Sort("sort")));
						context.put("article", article);
						Template template = Velocity.getTemplate("com/iokays/template/vm/article.vm", "UTF-8");
						PrintWriter printWriter = new PrintWriter(System.getProperty("webapp.root") + "console\\" + article.getId()+ ".html", "UTF-8");
						template.merge(context, printWriter);
						printWriter.flush();  
						printWriter.close();
						context.remove("article");
					}
				}
				
			}

	}
	
	//文章添加,修改
	public void buildArticle(Serializable id) throws IOException {
		List<Column> columns_root = columnService.findAllByGrade(Column.Grade.one, new Sort("sort"));
		
		Article article = articleService.findOne(id);
		Column two_column = columnService.findOne(article.getColumn().getId());
		List<Article> articles = new ArrayList<Article>();
		articles.add(article);
		two_column.setArticles(articles);
		Column column = columnService.findOne(two_column.getParent().getId());
		List<Column> two_columns = new ArrayList<Column>();
		two_columns.add(two_column);
		column.setChildren(two_columns);
		
		build(column, columns_root);
	}
	
	public void buildColumn(Serializable id) throws IOException {
		List<Column> columns_root = columnService.findAllByGrade(Column.Grade.one, new Sort("sort"));
		
		Column column_ = columnService.findOne(id);
		switch (column_.getGrade()) {
		case one:
			List<Column> columns = columnService.findAllByGrade(Column.Grade.one, new Sort("sort"));
			for (int i = 0; i < columns.size(); ++i) {
				Column column_one = columns.get(i);
				column_one.setChildren(columnService.findAllByParent(column_one,  new Sort("sort")));
				for (int j = 0; j < column_one.getChildren().size(); ++j) {
					Column column_two = column_one.getChildren().get(j);
					column_two.setArticles(articleService.findByColumn(column_two));
				}
				build(column_one, columns_root);
			}
			break;
		case two:
			Column column_one = columnService.findOne(column_.getParent().getId());
			column_one.setChildren(columnService.findAllByParent(column_one,  new Sort("sort")));
			for (int j = 0; j < column_one.getChildren().size(); ++j) {
				Column column_two = column_one.getChildren().get(j);
				column_two.setArticles(articleService.findByColumn(column_two));
			}
			build(column_one, columns_root);
		default:
			break;
		}
		
	}
	
	public void buildHomePage() throws IOException  {
		List<Column> columns_root = columnService.findAllByGrade(Column.Grade.one, new Sort("sort"));
		Pageable pageable = new PageRequest(0, 6, Direction.DESC, "createDate");
		List<HomePage> homePages = homePageService.findAll(new Sort("sort"));
		List<Object[]> objects = articleService.findTitleAndCreateDateByColumnParentId("86834e52-8d1d-4cb5-84ac-8034826c0763", pageable).getContent();
		
		Properties p = new Properties();
		p.load(this.getClass().getResourceAsStream("/velocity.properties"));
		Velocity.init(p);
		VelocityContext context = new VelocityContext();
		context.put("columns_root", columns_root);
		context.put("homePages", homePages);
		context.put("objects", objects);
		Template template = Velocity.getTemplate("com/iokays/template/vm/index.vm", "UTF-8");
		PrintWriter printWriter = new PrintWriter(System.getProperty("webapp.root") + "index.html", "UTF-8");
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