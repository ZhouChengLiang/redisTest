package org.zcl.mytest;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class MovedSort {
	private List<Article> articles = Arrays.asList(
								new Article(1,"A",11),
								new Article(2,"B",22),
								new Article(3,"C",33),
								new Article(4,"D",44),
								new Article(5,"E",55),
								new Article(6,"F",66)
							);
	
	@Test
	public void test3(){
		System.out.println(rangeInDefined(3,5,1));
		Article article = articles.get(2);
		Integer src = article.getSortNum();
		Integer dst = 5;
		System.out.println("src>>>>>>>>>>>>>>>>>>>>>"+src+",dst>>>>>>>>>>>>>>>>>>>>>>>"+dst);
		srcToDst(article, src, dst);
	}
	
	public  boolean rangeInDefined(int current, int min, int max){  
        return Math.max(min, current) == Math.min(current, max);  
    }  
    
	public void srcToDst(Article article,Integer src,Integer dst){
		List<Article> tmpArticles = articles.stream().filter((a)->rangeInDefined(a.getSortNum(),src,dst))
				.collect(Collectors.toList());
		System.out.println("before>>>>>>>>>>>>>>>>>>>>>>>>"+tmpArticles);
		int tmp;
		for (int i = 1; i < tmpArticles.size(); i++) {
            Article each = tmpArticles.get(i);
            tmp = each.getSortNum();
            each.setSortNum(article.getSortNum());
            article.setSortNum(tmp);
        }
		System.out.println("after>>>>>>>>>>>>>>>>>>>>>>>>>"+tmpArticles);
	}
	@Test
	public void test2() {
		Article article = articles.get(2);
		Integer position = 4;
		moveToFixedPosition(article, position);

		Article article_ = articles.get(3);
		Integer position_ = 5;
		moveToFixedPosition(article_, position_);
		List<Article> unFixedPositionArticles = articles.stream().filter((a) -> a.getFixedPosition() == 0)
				.collect(Collectors.toList());
		List<Article> fixedPositionArticles = articles.stream().filter((a) -> a.getFixedPosition() != 0)
				.collect(Collectors.toList());

		List<Article> lastResults = new LinkedList<>();
		lastResults.addAll(unFixedPositionArticles);
		for (Article art : fixedPositionArticles) {
			lastResults.add(art.getFixedPosition() - 1, art);
		}
		lastResults.stream().sorted((a1, a2) -> a1.getSortNum().compareTo(a2.getSortNum()))
				.forEach((e) -> System.out.println(e));

	}
	
	@Test
	public void test0(){
		 Article article = articles.get(2);
		 Integer position = 4;
		 moveToFixedPosition(article,position);
		 
		 Article article_ = articles.get(3);
		 Integer position_ = 5;
		 moveToFixedPosition(article_,position_);
		 articles.stream().sorted((a1,a2)->a1.getSortNum().compareTo(a2.getSortNum())).forEach((e)->System.out.println(e));
		 
	}
	
	@Test
	public void test1(){
		System.out.println(articles.stream().map((e)->e.getTitle()).reduce("", String::concat));
	}
	
	public void moveToFixedPosition(Article article,Integer position){
		Article targetArticle = articles.get(position-1);
		Integer targetPostion = targetArticle.getSortNum();
		targetArticle.setSortNum(article.getSortNum());
		article.setSortNum(targetPostion);
		article.setFixedPosition(position);
	}
	
}
class Article{
	private Integer id;
	private Integer sortNum;
	private String title;
	private Integer fixedPosition = 0;
	public Article(Integer id,String title,Integer sortNum){
		this.id = id;
		this.sortNum = sortNum;
		this.title = title;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSortNum() {
		return sortNum;
	}
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Integer getFixedPosition() {
		return fixedPosition;
	}
	public void setFixedPosition(Integer fixedPosition) {
		this.fixedPosition = fixedPosition;
	}
	@Override
	public String toString() {
		return "Article [id=" + id + ", sortNum=" + sortNum + ", title=" + title + ", fixedPosition=" + fixedPosition
				+ "]";
	}
	
}