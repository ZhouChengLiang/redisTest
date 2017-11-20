package org.zcl.mytest;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestAddSource {
	List<Comment> comments = Arrays.asList(
			                            new Comment(1,"ZB:123"),
			                            new Comment(2,"QQ:123"),
			                            new Comment(3,"WX:123"),
			                            new Comment(4,"43567")
											);
	@Test
	public void test0(){
		comments.stream().filter((e)->e.getCommentUserName().contains(":")&&!e.getCommentUserName().contains("ZB"))
		.forEach((e)->{
			String thirdChannel = e.getCommentUserName();
			thirdChannel = thirdChannel.substring(0, thirdChannel.indexOf(":"));
			thirdChannel = SourceEnum.valueOf(thirdChannel).getName();
    		e.setThirdChannel(thirdChannel);
    	});
		System.out.println(comments);
	}
}
class Comment{
	private Integer id;
	private String thirdChannel;
	private String commentUserName;
	
	public Comment(Integer id,String commentUserName){
		this.id = id;
		this.commentUserName = commentUserName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getThirdChannel() {
		return thirdChannel;
	}

	public void setThirdChannel(String thirdChannel) {
		this.thirdChannel = thirdChannel;
	}

	public String getCommentUserName() {
		return commentUserName;
	}

	public void setCommentUserName(String commentUserName) {
		this.commentUserName = commentUserName;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", thirdChannel=" + thirdChannel + ", commentUserName=" + commentUserName + "]";
	}
	
}