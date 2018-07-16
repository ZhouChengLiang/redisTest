package org.fuxin.zcl.test.articleSearch;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class SimpleSpiderTest {
	
	@Test
	public void test0(){
		String url = "https://search.jd.com/Search";
		Map<String, String> params = Maps.newHashMap();
		String html = HttpClientUtils.sendGet(url, null, params);
		List<GoodsInfo> goods = Lists.newArrayList();
		Document document = Jsoup.parse(html);
	    Elements elements = document.select("div[class=copyright_links]").select("a");
        int index = 0;
        for(Element element : elements) {
            /*String goodsId = element.attr("data-sku");
            String goodsName = element.select("div[class=p-name p-name-type-2]").select("em").text();
            String goodsPrice = element.select("div[class=p-price]").select("strong").select("i").text();
            String imgUrl = "https:" + element.select("div[class=p-img]").select("a").select("img").attr("src");*/
            String href = element.attr("href");
            String content = element.text();
            System.out.println("href>>>>>>>"+href+" content>>>>>>>>"+content);
            
            /*GoodsInfo goodsInfo = new GoodsInfo(goodsId, goodsName, imgUrl, goodsPrice);
            goods.add(goodsInfo);
            String jsonStr = JSON.toJSONString(goodsInfo);
            System.out.println("成功爬取【" + goodsName + "】的基本信息 ");
            System.out.println(jsonStr);*/
            if(index ++ == 9) {
                break;
            }
        }
	}
	
	@Test
	public void test1(){
		String url = "http://vcbeat.net";
		Map<String, String> params = Maps.newHashMap();
		String html = HttpClientUtils.sendGet(url, null, params);
		Document document = Jsoup.parse(html);
	    Elements elements = document.select("ul[id=article-list]").select("li[class=clear php_hide_div]");
        for(Element element : elements) {
        	//外部的链接
        	String spc_img_a = url+element.select("div[class=spc_img]").select("a").attr("href");
        	String img = element.select("div[class=spc_img]").select("a").select("img").attr("src");
        	String spc_img_img = StringUtils.isEmpty(img)?"":url+img;
        	String spc_img_i = element.select("div[class=spc_img]").select("i").text();
        	System.out.println("spc_img_a>>>>>>>"+spc_img_a);
        	if(!StringUtils.isEmpty(spc_img_img)){
        		System.out.println("spc_img_img>>>>>>>"+spc_img_img);
        	}
        	System.out.println("spc_img_i>>>>>>>"+spc_img_i);
        	System.out.println("");
        	String spc_cnt_a = url+element.select("div[class=spc_cnt]").select("a").attr("href");
        	String img1 = element.select("div[class=spc_cnt]").select("a").select("img").attr("src");
        	String spc_cnt_img = StringUtils.isEmpty(img1)?"":url+img1;
        	String spc_cnt_content = element.select("div[class=spc_cnt]").select("a").text();
        	String spc_cnt_h2 = element.select("div[class=spc_cnt]").select("h2").html();
        	String tags = element.select("div[class=spc_cnt]").select("div[class=tags]").select("a")
        						.eachText().stream().collect(Collectors.joining(","));
        	System.out.println("spc_cnt_a>>>>>>>"+spc_cnt_a);
        	if(!StringUtils.isEmpty(spc_cnt_img)){
        		System.out.println("spc_cnt_img>>>>>>>"+spc_cnt_img);
        	}
        	System.out.println("spc_cnt_content>>>>>>>"+spc_cnt_content);
        	System.out.println("spc_cnt_h2>>>>>>>"+spc_cnt_h2);
        	System.out.println("tags>>>>>>>"+tags);
        	
        	//来源 与发布时间
        	String spc_cnt_source = element.select("div[class=spc_cnt]").select("div[class=author] a").text();
        	String spc_cnt_author_time = element.select("div[class=spc_cnt]").select("div[class=author] span").text();
        	System.out.println("spc_cnt_source>>>>>>>"+spc_cnt_source);
        	System.out.println("spc_cnt_author_time>>>>>>>"+spc_cnt_author_time);
        	System.out.println("***************************************************");
            
        }
	}
	
	@Test
	public void test2(){
		String url = "http://vcbeat.net/";
		Map<String, String> params = Maps.newHashMap();
		String html = HttpClientUtils.sendGet(url, null, params);
		Map<String,String> detailUrlMap = new LinkedHashMap<>();
		Document document = Jsoup.parse(html);
		Elements elements = document.select("ul[id=article-list] li[class=clear php_hide_div]");
		for(Element element : elements) {
			//外部的链接
			String spc_img_a = url+element.select("div[class=spc_img] a").attr("href");
			String img = element.select("div[class=spc_img] a img").attr("src");
			String spc_img_img = StringUtils.isEmpty(img)?"":url+img;
			String spc_img_i = element.select("div[class=spc_img] i").text();
//			System.out.println("spc_img_a>>>>>>>"+spc_img_a);
			if(!StringUtils.isEmpty(spc_img_img)){
//				System.out.println("spc_img_img>>>>>>>"+spc_img_img);
			}
//			System.out.println("spc_img_i>>>>>>>"+spc_img_i);
			System.out.println("");
			String realUrl = element.select("div[class=spc_cnt] a").attr("href");
			String spc_cnt_a = url+realUrl;
			detailUrlMap.put(realUrl.substring(1, realUrl.length()),spc_cnt_a);
			String img1 = element.select("div[class=spc_cnt] a img").attr("src");
			String spc_cnt_img = StringUtils.isEmpty(img1)?"":url+img1;
			String spc_cnt_content = element.select("div[class=spc_cnt] a").text();
			String spc_cnt_h2 = element.select("div[class=spc_cnt] h2").html();
			String tags = element.select("div[class=spc_cnt] div[class=tags] a").eachText().stream().collect(Collectors.joining(","));
//			System.out.println("spc_cnt_a>>>>>>>"+spc_cnt_a);
			if(!StringUtils.isEmpty(spc_cnt_img)){
//				System.out.println("spc_cnt_img>>>>>>>"+spc_cnt_img);
			}
//			System.out.println("spc_cnt_content>>>>>>>"+spc_cnt_content);
//			System.out.println("spc_cnt_h2>>>>>>>"+spc_cnt_h2);
//			System.out.println("tags>>>>>>>"+tags);
			//来源 与发布时间
			String spc_cnt_source = element.select("div[class=spc_cnt] div[class=author] a").text();
			String spc_cnt_author_time = element.select("div[class=spc_cnt] div[class=author] span").text();
//			System.out.println("spc_cnt_source>>>>>>>"+spc_cnt_source);
//			System.out.println("spc_cnt_author_time>>>>>>>"+spc_cnt_author_time);
//			System.out.println("***************************************************");
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		for(Map.Entry<String, String> entry:detailUrlMap.entrySet()){
			Map<String, String> params_inner = Maps.newHashMap();
			String html_inner = HttpClientUtils.sendGet(entry.getValue(), null, params_inner);
			Document document_inner = Jsoup.parse(html_inner);
			String title = document_inner.select("p[class=tle]").text();
			String author = document_inner.select("div[class=atr_info clear] span[class=name]").text();
			String time = document_inner.select("div[class=atr_info clear] span[class=time]").text();
			String originUrl = document_inner.select("div[class=art_text]").select("img").attr("src");
			document_inner.select("div[class=art_text]").select("img").attr("src",url.concat(originUrl));
			String content = document_inner.select("div[class=art_text]").html();
			System.out.println("title >>"+title+" author >>"+author+" time >>"+time);
			System.out.println("content>>>>>>>>>>>>>>>"+content);
		}
	}
	
	@Test
	public void test33(){
		String url = "http://www.mingyihui.net/hospital_442.html";
		System.out.println(url.substring(url.lastIndexOf("_")+1, url.lastIndexOf(".")));
	}
	
	@Test
	public void test3() throws Exception{
		String originUrl = "http://www.mingyihui.net";
		String url_1 = originUrl+"/zhejiangsheng_hospital.html";
		Document doc = Jsoup.connect(url_1).get();
		Elements eles = doc.select("div.H_tra.w900 a");
		Collections.reverse(eles);
		//准备获取总页数
		int pages = 1;
		for(Element e :eles){
			if("末页".equals(e.text())){
				String href = e.attr("href");
				int beginIndex = href.lastIndexOf("_");
				int endIndex = href.lastIndexOf(".");
				pages = Integer.valueOf(href.substring(beginIndex+1, endIndex));
				System.out.println("pages >>>> "+pages);
				break;
			}
		}
		String url = originUrl + "/zhejiangsheng_hospital_1.html";
		Document doucments = Jsoup.connect(url).get();
		Elements elements = doucments.select("ul[class=H_main] li[class=H_list]");
		List<String> list = new ArrayList<>();
		for(Element element:elements){
			String hospitalName = element.select("h3").text();
			String hosptialDetailUrl = originUrl + element.select("h3 a").attr("href");
			String rank = element.select("div.H_no").text().replace("NO.", "");
			String hospitalTag = element.select("span[class=ispublic]").eachText().stream().collect(Collectors.joining(","));
			String hospitalIntro = element.select("dl dd").get(0).text();
			String hospitalAddress = element.select("dl dd").get(1).text();
			String hospitalPraise = element.select("li[id=hgcount]").text();
			String hospitalSatisfied = element.select("li span[class=starwz]").text();
			String hospitalMajor = element.select("div[class=dd_div]").eachText().stream().collect(Collectors.joining(","));
			list.add(hosptialDetailUrl);
			System.out.println(hospitalName +" >>> " +hospitalTag + " >>> " +hospitalIntro+ " >>> "+hospitalAddress+" >>> " +hospitalPraise + " >>> " +hospitalSatisfied+ " >>> "+hospitalMajor+" >>> "+hosptialDetailUrl+">>>>"+rank);
		}
		/*//详情
		for(String innerUrl :list){
			Document innerDoucments = Jsoup.connect(innerUrl).get();
			Elements innerElements = innerDoucments.select("ul[class=H_main] li[class=H_list]");
			
		}*/
		
	}
	
	@Test
	public void test3_3() throws Exception{
		String originUrl = "http://www.mingyihui.net";
		String url = originUrl + "/zhejiangsheng_hospital_1.html";
		//抓取医院的信息
		Document doucments = Jsoup.connect(url).get();
		Elements elements = doucments.select("ul[class=H_main] li[class=H_list]");
		List<String> list = new ArrayList<>();
		for(Element element:elements){
			String hospitalName = element.select("h3").text();
			System.out.println("href >>>> "+element.select("h3 a").attr("href"));
//			String hosptialDetailUrl = originUrl + element.select("h3 a").attr("href").replace(".", "/departments.");
			String hosptialDetailUrl = originUrl +"/guahao"+ element.select("h3 a").attr("href").replace(".html", "/");
			String hospitalTag = element.select("span[class=ispublic]").eachText().stream().collect(Collectors.joining(","));
			String hospitalIntro = element.select("dl dd").get(0).text();
			String hospitalAddress = element.select("dl dd").get(1).text();
			String hospitalPraise = element.select("li[id=hgcount]").text();
			String hospitalSatisfied = element.select("li span[class=starwz]").text();
			String hospitalMajor = element.select("div[class=dd_div]").eachText().stream().collect(Collectors.joining(","));
			list.add(hosptialDetailUrl);
//			System.out.println(hospitalName +" >>> " +hospitalTag + " >>> " +hospitalIntro+ " >>> "+hospitalAddress+" >>> " +hospitalPraise + " >>> " +hospitalSatisfied+ " >>> "+hospitalMajor);
		}
		
		System.out.println("******************医院科室信息******************");
		System.out.println(list);
		/*List<String> doctorUrls = new ArrayList<>();
		//医院科室详情
		for(String innerUrl :list){
			System.out.println("innerUrl >>>"+innerUrl);
			Document innerDoucments = Jsoup.connect(innerUrl).get();
			Elements innerElements = innerDoucments.select("div.classify_list.clearfix");
			for(Element ele1 :innerElements){
				String department_1 = ele1.select("h3").text();//一级科室  level 1
				Elements departmentElements = ele1.select("ul li");//二级科室 level 2
				for(Element ele2:departmentElements){
					String href = ele2.select("a").attr("href");
					String doctorUrl = originUrl + href;
					String hospitalStr = href.split("/")[1];
					String departmentStr = href.split("/")[2];
					String department_2 = ele2.select("a").text();
					String hospitalId = hospitalStr.substring(hospitalStr.lastIndexOf("_")+1, hospitalStr.length());
					String departmentId = departmentStr.substring(departmentStr.lastIndexOf("_")+1, departmentStr.lastIndexOf("."));
					String peopleNum = ele2.select("span.color_999").text();
					peopleNum = peopleNum.substring(0, peopleNum.length());
					doctorUrls.add(doctorUrl);
					System.out.println("department_1>>> "+department_1+"department_2>>> "+department_2+" ,hospitalId>>> "+hospitalId+" ,departmentId>>> "+departmentId+ " , "+peopleNum);
				}
			}
		}
		System.out.println("doctorUrls>>> "+doctorUrls);
		//准备抓取医生数据了
		List<String> doctorUrlNew = new ArrayList<>();
		for(String doctorUrl:doctorUrls){
			System.out.println("doctorUrl >>>"+doctorUrl);
			Document doc = Jsoup.connect(doctorUrl).get();
			// 注意配合分页数据url的问题
			Elements eles = doc.select("div.H_tra.w1200 a");
			Collections.reverse(eles);
			//准备获取总页数
			int pages = 1;
			for(Element e :eles){
				if("末页".equals(e.text())){
					String href = e.attr("href");
					int beginIndex = href.lastIndexOf("_pn");
					int endIndex = href.lastIndexOf(".");
					pages = Integer.valueOf(href.substring(beginIndex+3, endIndex));
					System.out.println("pages >>>> "+pages);
					break;
				}
			}
			//重新组装医生的信息url地址
			for(int i = 1;i<=pages;i++){
				doctorUrlNew.add(doctorUrl.replace(".html", "_pn"+i+".html"));
			}
		}
		System.out.println("doctorUrlNew >>> "+doctorUrlNew);*/
		/*for(String realUrl: doctorUrlNew){
			Document doc = Jsoup.connect(realUrl).get();
			
		}*/
	}
	
	@Test
	public void test10(){
		String str = "http://www.mingyihui.net/guahao/hospital_391/2_23339.html";
		String part1 = str.substring(0,str.lastIndexOf("/"));
		String part2 = str.substring(str.lastIndexOf("/")+1);
		System.out.println(part1);
		System.out.println(part2);
		
		Integer hospitalId = Integer.valueOf(part1.substring(part1.lastIndexOf("_")+1));
		System.out.println(hospitalId);
		
		Integer parentDepartmentId = Integer.valueOf(part2.substring(0,part2.lastIndexOf("_")));
		System.out.println(parentDepartmentId);
		
		Integer departmentId = Integer.valueOf(part2.substring(part2.lastIndexOf("_")+1,part2.lastIndexOf(".")));
		System.out.println(departmentId);
		
		System.out.println(hospitalId+" >>> "+parentDepartmentId+" >>> "+departmentId);
	}
	
	/**
	 * 准备获取 医生的信息 URL
	 * @throws Exception
	 */
	@Test
	public void test12() throws Exception{
		List<String> urls = new ArrayList<>();
		urls.add("http://www.mingyihui.net/guahao/hospital_391/5_6_pn1.html");
		for(String url:urls){
			Document doc = Jsoup.connect(url).get();
			Elements eles = doc.select("div.H_doc_con > ul > li");
			for(Element ele :eles){
				String href = ele.select("a").get(0).absUrl("href");
				String titles = ele.select("span.fl.doctor_titles").text();
				Integer rank = Integer.valueOf(ele.select("a > div").text().replace("NO.", ""));  //body > div.H-phone.w1200 > div.w1200.H_doc > div.H_doc_con > ul > li:nth-child(1) > a > div
			    System.out.println(href+" >>> "+titles+" >>> "+rank);
			}
			
		}
	}
	
	/**
	 * 准备医生信息的拉取  FIXME
	 * 
	 * @throws Exception
	 */
	@Test
	public void test11() throws Exception{// body > div.H-phone.w1200 > div.H_tra.w1200 > ul > li:nth-child(5) > a
		List<String> urls = new ArrayList<>();
		urls.add("http://www.mingyihui.net/guahao/hospital_391/5_6.html");// http://www.mingyihui.net/guahao/hospital_391/5_6_pn2.html
		urls.add("http://www.mingyihui.net/guahao/hospital_391/5_71.html");
		for(String url:urls){
			Document doc = Jsoup.connect(url).get();
			int pages = 1;
			Elements eles = doc.select("div.H_tra.w1200 a");
			Collections.reverse(eles);
			for(Element e :eles){
		        if("末页".equals(e.text())){
		            String href = e.attr("href");
		            int beginIndex = href.lastIndexOf("_");
		            int endIndex = href.lastIndexOf(".");
		            pages = Integer.valueOf(href.substring(beginIndex+3, endIndex));
		            break;
		        }
		    }
			System.out.println("pages >>> "+pages);
			for(int i = 1;i<=pages;i++){
			     System.out.println(url.replace(".html", "_pn"+i+".html")); 
			}
			
		}
	}
	
	@Test
	public void test13(){
		Date abc  = new Date();
		abc.setTime(1531476394762L);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(abc));
	}
	
	/**
	 * 新的二级科室信息拉取  FIXME
	 * 
	 * @throws Exception
	 */
	@Test
	public void test9() throws Exception{
		List<String> urls = new ArrayList<>();
		urls.add("http://www.mingyihui.net/guahao/hospital_391/2.html");
		for(String url:urls){
			Document doc = Jsoup.connect(url).get();
			Elements elements = doc.select("div.illness_classify.border > div:nth-child(3) > ul > li:not(:first-child)");
			for(Element ele :elements){
				String href = ele.select("a").attr("href");
				if(StringUtils.isEmpty(ele.select("a").text())){
					continue;
				}
				System.out.println("href >>> "+href);
				String part1 = href.substring(0,href.lastIndexOf("/"));
				String part2 = href.substring(href.lastIndexOf("/")+1);
				//http://www.mingyihui.net/guahao/hospital_391/2_23339.html
				Integer hospitalId = Integer.valueOf(part1.substring(part1.lastIndexOf("_")+1));
				Integer parentDepartmentId = Integer.valueOf(part2.substring(0,part2.lastIndexOf("_")));
				Integer departmentId = Integer.valueOf(part2.substring(part2.lastIndexOf("_")+1,part2.lastIndexOf(".")));
				System.out.println(hospitalId+" >>> "+parentDepartmentId+" >>> "+departmentId);
				System.out.println(ele.select("a").text());
				
				
				/*Integer hospitalId = Integer.valueOf(href.substring(href.lastIndexOf("_")+1, href.lastIndexOf("/")));
				Integer departmentId = Integer.valueOf(href.substring(href.lastIndexOf("/")+1, href.lastIndexOf(".")));
				String departmentName = ele.select("a").text();
				System.out.println(hospitalId+" >>> "+departmentId+" >>> "+departmentName+" >>> "+href);
				System.out.println("***********************************");*/
			}
			
			System.out.println();
		}
	}
	/**
	 * 新的一级科室信息拉取
	 * 
	 * @throws Exception
	 */
	@Test
	public void test8() throws Exception{
		List<String> urls = new ArrayList<>();
		urls.add("http://www.mingyihui.net/guahao/hospital_391/");
//		urls.add("http://www.mingyihui.net/guahao/hospital_392/");
		for(String url:urls){
			Document doc = Jsoup.connect(url).get();
			Elements elements = doc.select("div.illness_list.clearfix.H_link > ul > li:not(:first-child)");
			for(Element ele :elements){
				String href = ele.select("a").attr("href");
				if(StringUtils.isEmpty(ele.select("a").text())){
					continue;
				}
				Integer hospitalId = Integer.valueOf(href.substring(href.lastIndexOf("_")+1, href.lastIndexOf("/")));
				Integer departmentId = Integer.valueOf(href.substring(href.lastIndexOf("/")+1, href.lastIndexOf(".")));
				String departmentName = ele.select("a").text();
				System.out.println(hospitalId+" >>> "+departmentId+" >>> "+departmentName+" >>> "+href);
				System.out.println("***********************************");
			}
			
			System.out.println();
		}
	}
	
	/**
	 * 里面一层的医生信息
	 * 正是因为有了擅长的全部内容所有才显示出来
	 * 如  http://www.mingyihui.net/doctor_57341.html
	 * @throws Exception 
	 */
	@Test
	public void test7() throws Exception{
		List<String> urls = new ArrayList<>();
//		urls.add("http://www.mingyihui.net/doctor_57341.html");
//		urls.add("http://www.mingyihui.net/doctor_57357.html");
		urls.add("http://www.mingyihui.net/doctor_892900.html");
		for(String url:urls){
			Document doc = Jsoup.connect(url).get();
			String doctorGoodAt = doc.select("div.details_contant.fr > div:nth-child(2) > p").text();
			System.out.println("doctorGoodAt >>>> "+doctorGoodAt);
			Integer doctorId = Integer.valueOf(url.substring(url.lastIndexOf("_")+1, url.lastIndexOf(".")));
			String doctorName = doc.select(" div.crumbs.w1200 > a:nth-child(4)").text();
			String hospitalIdStr = doc.select("div.crumbs.w1200 > a:nth-child(2)").attr("href");
			Integer hospitalId = Integer.valueOf(hospitalIdStr.substring(hospitalIdStr.lastIndexOf("_")+1, hospitalIdStr.lastIndexOf(".")));
			String hospitalName = doc.select("div.crumbs.w1200 > a:nth-child(2)").text();
			String departmentIdStr = doc.select("div.crumbs.w1200 > a:nth-child(3)").attr("href");
			Integer departmentId = Integer.valueOf(departmentIdStr.substring(departmentIdStr.lastIndexOf("_")+1, departmentIdStr.lastIndexOf(".")));
			String departmentName = doc.select("div.crumbs.w1200 > a:nth-child(3)").text();
			System.out.println(doctorId +" > "+doctorName + " > "+hospitalId+" > "+hospitalName+" > "+departmentId + " > "+departmentName); 
			
		   
			
			
			Elements element1 = doc.select("div.out_call > table > tbody > tr:nth-child(2) > td");
			for(int i = 1;i<element1.size();i++){
				System.out.println(i+" >>> "+element1.get(i).text());
				
			}
			
			Elements element2 = doc.select("div.out_call > table > tbody > tr:nth-child(3) > td");
			for(int i = 1;i<element2.size();i++){
				System.out.println(i+" >>> "+element2.get(i).text());
			}
			
			Elements element3 = doc.select("div.out_call > table > tbody > tr:nth-child(4) > td");
			for(int i = 1;i<element3.size();i++){
				System.out.println(i+" >>> "+element3.get(i).text());
			}
			
			
			
			System.out.println();
		}
	}
	
	/**
	 * 外面一页的医生信息
	 * 很可惜 擅长的内容不全啊
	 * 如 http://www.mingyihui.net/hospital_391/department_6_pn1.html
	 * @throws Exception
	 */
	@Test
	public void test6() throws Exception{
		String originUrl = "http://www.mingyihui.net";
		List<String> doctorUrlNew = new ArrayList<String>();
		doctorUrlNew.add("http://www.mingyihui.net/hospital_391/department_6_pn1.html");
		for(String realUrl: doctorUrlNew){
			Document doc = Jsoup.connect(realUrl).get();
			Elements elements = doc.select("div.H_doc_con ul li");
			for(Element element :elements){
				String lastUrl = originUrl + element.select("div.H_d_down.H_d.fl a").attr("href");
				String doctorTitle = element.select("div.H_d_down.H_d.fl span.fl.doctor_titles").text();
				System.out.println("LASTURL >>>"+lastUrl+" DOCTORTITLE >>> "+doctorTitle);
			}
		}
	}
	
	@Test
	public void test5(){
		String href = "/hospital_391/department_4938.html";
		String hospitalStr = href.split("/")[1];
		String departmentStr = href.split("/")[2];
		String hospitalId = hospitalStr.substring(hospitalStr.lastIndexOf("_")+1, hospitalStr.length());
		String departmentId = departmentStr.substring(departmentStr.lastIndexOf("_")+1, departmentStr.lastIndexOf("."));
		System.out.println(hospitalId +" >>> "+departmentId);
	}
	
	@Test
	public void test4(){
		String url = "http://vcbeat.net/Mjk1YTg5YmE2ZDRiNDJlN2I5ZWZkZTkwZmE3ZDhiYTU=";
		System.out.println(url.substring(url.lastIndexOf("/")+1));
		System.out.println(new String(Base64.getDecoder().decode(url.substring(url.lastIndexOf("/")+1))));
//		System.out.println(UUID.randomUUID().toString());
	}
}
