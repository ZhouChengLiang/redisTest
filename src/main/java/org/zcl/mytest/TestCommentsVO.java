package org.zcl.mytest;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.BeanUtils;

public class TestCommentsVO {
	
	@Test
	public void test0(){
		Comments source = new Comments();
		source.setId(1);
		source.setAuditUserId(1);
		source.setAuditUserTruename("libw");
		source.setCountyCode(10);
		source.setCountyName("金华");
		List<Comments> list = Arrays.asList(source);
		CommentsVO target = new CommentsVO();
		BeanUtils.copyProperties(source, target);
		System.out.println("target>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+target);
		target.setThirdChannel("微信");
		Comments another = target;
		System.out.println("another>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+another);
	}
}
