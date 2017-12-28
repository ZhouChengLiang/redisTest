package org.zcl.mytest;

import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.joda.time.LocalDate;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

public class TestRelationGradeUser {
	List<GradeInfo> gradeInfos = Arrays.asList(
						new GradeInfo(1,1,0,0,"打个酱油","1.jpg",0,10),new GradeInfo(2,2,60,0,"吃瓜群众","2.jpg",0,10),
						new GradeInfo(3,3,300,0,"闲散小生","3.jpg",0,10),new GradeInfo(4,4,600,0,"江湖小虾","4.jpg",0,10),
						new GradeInfo(5,5,900,0,"江湖大侠","5.jpg",0,10),new GradeInfo(6,6,2200,0,"超级大侠","6.jpg",0,10)
					);
	List<GradeInfo> changedGradeInfos = Arrays.asList(
			new GradeInfo(1,1,0,0,"打个酱油","1.jpg",0,10)
			,new GradeInfo(2,2,60,0,"吃瓜群众","2.jpg",0,10)
		);
	List<UserExpInt> userExpInt = Arrays.asList(
						new UserExpInt(110,15,15,10),new UserExpInt(111,75,75,10),new UserExpInt(112,200,200,10),
						new UserExpInt(113,400,400,10),new UserExpInt(114,600,600,10),new UserExpInt(115,78,900,10),
						new UserExpInt(116,900,900,10),new UserExpInt(117,90,900,10),new UserExpInt(118,345,900,10),
						new UserExpInt(119,233,900,10),new UserExpInt(120,477,900,10),new UserExpInt(121,666,900,8),
						new UserExpInt(122,1200,900,8),new UserExpInt(123,33,900,8),new UserExpInt(124,1111,900,8),
						new UserExpInt(125,134,900,8),new UserExpInt(126,675,900,8),new UserExpInt(127,1344,900,8),
						new UserExpInt(128,1000,900,8),new UserExpInt(129,996,900,8),new UserExpInt(131,33,900,8),
						new UserExpInt(130,1,900,8)
					);
	/**
	 * curdate 当前时间  
	 * curgrade 当前等级 
	 * curexp 当前经验   
	 * curusers 当前人数
	 * countyCode
	 */
	List<GradeDistribution> gradeDistributions = Arrays.asList(
			new GradeDistribution("10.25",1,0,81,10),new GradeDistribution("10.25",2,60,1,10),
			/*new GradeDistribution("20171025",3,300,1,10),new GradeDistribution("20171025",4,600,0,10),
			new GradeDistribution("20171025",5,900,0,10),*/
			
			new GradeDistribution("10.26",1,0,80,10),new GradeDistribution("10.26",2,60,2,10),
			new GradeDistribution("10.26",3,300,2,10),new GradeDistribution("10.26",4,600,0,10),
			new GradeDistribution("10.26",5,900,0,10),
			
			new GradeDistribution("10.27",1,0,80,10),new GradeDistribution("10.27",2,60,2,10),
			new GradeDistribution("10.27",3,300,2,10),new GradeDistribution("10.27",4,600,0,10),
			new GradeDistribution("10.27",5,900,0,10)
			);
	/**
	 * String date,Integer grantScores,Integer signedScores,Integer growthScores;
 	   Integer dailyScores,Integer bonusScores,Integer consumedScores,Integer busiScores;
 	   Integer deducteScores,Integer countyCode;
	 */
	List<ScoreStatisticsExtension> list = Arrays.asList(
				new ScoreStatisticsExtension("2017-12-26",14L,11L,1L,1L,1L,5L,4L,1L,10),
				new ScoreStatisticsExtension("2017-12-25",4L,1L,1L,1L,1L,3L,1L,1L,10),
				new ScoreStatisticsExtension("2017-12-24",6L,1L,2L,2L,1L,1L,1L,0L,10),
				new ScoreStatisticsExtension("2017-12-23",1L,0L,0L,0L,1L,0L,0L,0L,10),
				new ScoreStatisticsExtension("2017-12-22",3L,1L,2L,0L,0L,8L,1L,7L,10)
			);
	List<TemporaryStatisticsExtension> last = Arrays.asList(
				new TemporaryStatisticsExtension("2017-12-27","积分增加",0,1L,10),
				new TemporaryStatisticsExtension("2017-12-27","签到",3,40L,10),
				new TemporaryStatisticsExtension("2017-12-26","签到",3,20L,10),
				new TemporaryStatisticsExtension("2017-12-25","签到",3,60L,10),
				new TemporaryStatisticsExtension("2017-12-22","签到",3,20L,10),
				new TemporaryStatisticsExtension("2017-11-17","签到",3,20L,10),
				new TemporaryStatisticsExtension("2017-11-16","签到",3,64L,10),
				new TemporaryStatisticsExtension("2017-11-15","积分减少",0,-1L,10),
				new TemporaryStatisticsExtension("2017-11-15","积分增加",0,6L,10),
				new TemporaryStatisticsExtension("2017-11-15","签到",3,0L,10)
			);
	
	
	
	@Test
	public void test25(){
		List<ScoreStatisticsExtension> result = new ArrayList<>();
		Map<String,List<TemporaryStatisticsExtension>> firstMap = 
				last.stream().collect(Collectors.groupingBy(TemporaryStatisticsExtension::getDate));
		for(Map.Entry<String, List<TemporaryStatisticsExtension>> firstEntry: firstMap.entrySet()){
			ScoreStatisticsExtension single = new ScoreStatisticsExtension();
			single.setDate(firstEntry.getKey());
			List<TemporaryStatisticsExtension> list = firstEntry.getValue();
			Map<Boolean, List<TemporaryStatisticsExtension>> secondMap = list.stream().collect(Collectors.partitioningBy((e)->e.getEventType()>0));
			for(Map.Entry<Boolean, List<TemporaryStatisticsExtension>> secondEntry:secondMap.entrySet()){
				if(secondEntry.getKey()){
					List<TemporaryStatisticsExtension> trueList = secondEntry.getValue();
					Long dailyScores = trueList.stream().filter((e)->Objects.equals(e.getEventType(),1)).map(TemporaryStatisticsExtension::getPartScore).reduce(Long::sum).orElse(0L);
					Long growthScores = trueList.stream().filter((e)->Objects.equals(e.getEventType(),2)).map(TemporaryStatisticsExtension::getPartScore).reduce(Long::sum).orElse(0L);
					Long signedScores = trueList.stream().filter((e)->Objects.equals(e.getEventType(),3)).map(TemporaryStatisticsExtension::getPartScore).reduce(Long::sum).orElse(0L);
					single.setDailyScores(dailyScores);
					single.setGrowthScores(growthScores);
					single.setSignedScores(signedScores);
				}else{
					List<TemporaryStatisticsExtension> falseList = secondEntry.getValue();
					Long bonusScores = falseList.stream().filter((e)->Objects.equals(EventEnum.ADD_INT,EventEnum.getByEvent(e.getEvent()))).map(TemporaryStatisticsExtension::getPartScore).reduce(Long::sum).orElse(0L);
					Long busiScores = falseList.stream().filter((e)->Objects.equals(EventEnum.SHOPPING,EventEnum.getByEvent(e.getEvent()))).map(TemporaryStatisticsExtension::getPartScore).reduce(Long::sum).orElse(0L);
					Long deducteScores = falseList.stream().filter((e)->Objects.equals(EventEnum.SUB_INT,EventEnum.getByEvent(e.getEvent()))).map(TemporaryStatisticsExtension::getPartScore).reduce(Long::sum).orElse(0L);
					single.setBonusScores(bonusScores);
					single.setBusiScores(-busiScores);
					single.setDeducteScores(-deducteScores);
				}
			}
			single.setGrantScores(Long.sum(single.getSignedScores()+single.getDailyScores(),single.getGrowthScores()+single.getBonusScores()));
			single.setConsumedScores(Long.sum(single.getBusiScores(), single.getDeducteScores()));
			result.add(single);
		}
		result.sort((x,y)->y.getDate().compareTo(x.getDate()));
	}
	
	@Test
	public void test24(){
		LocalDate localDate = LocalDate.now();
		String date = "2017-12-25";
		System.out.println(LocalDate.parse(date));
		System.out.println(localDate.withDayOfWeek(1));
	}
	
	@Test
	public void test23(){
		Map<String,Object> result = new HashMap<>();
		result.put("curgrantScores", 0);
		result.put("curconsumedScores", 0);
		result.put("tobeconsumedScores", 0);
		
		LocalDate localDate = LocalDate.now();
		list.stream().forEach((obj)->{
			if(Objects.equals(obj.getDate(), localDate.toString("yyyy-MM-dd"))){
				result.put("curgrantScores", obj.getGrantScores());
				result.put("curconsumedScores", obj.getConsumedScores());
			}
		});
		Long grantScores = list.stream().collect(Collectors.summarizingLong(ScoreStatisticsExtension::getGrantScores)).getSum();
		Long consumedScores = list.stream().collect(Collectors.summarizingLong(ScoreStatisticsExtension::getConsumedScores)).getSum();
		
		/*Long grantScores = list.stream().map(ScoreStatisticsExtension::getGrantScores).reduce(Long::sum).ofNullable(0L).get();
		Long consumedScores = list.stream().map(ScoreStatisticsExtension::getConsumedScores).reduce(Long::sum).ofNullable(0L).get();*/
		result.put("tobeconsumedScores", Long.sum(grantScores, -consumedScores));
		Long weekgrantScores = list.stream().filter((obj)->!LocalDate.parse(obj.getDate()).isBefore(localDate.withDayOfWeek(1))).map(ScoreStatisticsExtension::getGrantScores).reduce(Long::sum).orElse(0L);
		result.put("weekgrantScores", weekgrantScores);
		System.out.println(result);
	}
	
	@Test
	public void test22(){
		String str1 = "abc";
		String str2 = "def";
		System.out.println(str1.concat(str2));
		System.out.println(String.join("/", str1,str2));
		System.out.println(String.join("/", null,str2));
	}
	
	@Test
	public void test21(){
		Instant st = Instant.now();
		Date curtime = new Date();
		DateFormatUtils.format(curtime, "yyyyMMdd");
		System.out.println("It costs>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+Duration.between(st, Instant.now()));
		st = Instant.now();
		java.time.LocalDate local = java.time.LocalDate.now();
		local.format(DateTimeFormatter.BASIC_ISO_DATE);
		System.out.println("It costs>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+Duration.between(st, Instant.now()));
	}
	
	@Test
	public void test20(){
		System.out.println(System.currentTimeMillis());
		
	}
	
	@Test
	public void test19(){
		LocalDate localDate = LocalDate.now();
		
		/*System.out.println(localDate.getDayOfWeek());
		System.out.println(localDate.getWeekOfWeekyear());
		System.out.println(localDate.getWeekyear());
		
		System.out.println(localDate.plusWeeks(3));
		
		System.out.println(localDate.plusWeeks(3).getWeekOfWeekyear());
		System.out.println(localDate.plusWeeks(3).getWeekyear());
		
		System.out.println(localDate.withDayOfWeek(1).plusWeeks(3));
		
		System.out.println(localDate.withDayOfWeek(1).plusWeeks(3).getWeekOfWeekyear());
		System.out.println(localDate.withDayOfWeek(1).plusWeeks(3).getWeekyear());*/
		
		System.out.println(localDate.withDayOfWeek(1).toDate());
		
	}
	
	@Test
	public void test18(){
		LocalDate now = LocalDate.now();
		System.out.println("此时的第一天>>>>>>>"+DateFormatUtils.format(now.withDayOfWeek(1).toDate(), "yyyy-MM-dd")+" :"+now.withDayOfWeek(1).toDate());
		LocalDate next = now.plusDays(3);
		System.out.println("三天之后的时间>>>>>"+DateFormatUtils.format(next.toDate(), "yyyy-MM-dd"));
		
		System.out.println("三天之后那时的第一天>>>>>>>"+DateFormatUtils.format(next.withDayOfWeek(1).toDate(), "yyyy-MM-dd"));
		
	}
	
	@Test
	public void test15(){
		Date date = new Date();
		System.out.println(DateFormatUtils.format(date, "yyyy年-M月-d日"));
		
		System.out.println(DateFormatUtils.ISO_DATE_TIME_ZONE_FORMAT.toString());
		System.out.println(DateFormatUtils.ISO_DATETIME_FORMAT.toString());
		System.out.println(DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.toString());
		System.out.println(DateFormatUtils.ISO_TIME_FORMAT.toString());
		System.out.println(DateFormatUtils.ISO_TIME_NO_T_FORMAT.toString());
		System.out.println(DateFormatUtils.ISO_TIME_NO_T_TIME_ZONE_FORMAT.toString());
		System.out.println(DateFormatUtils.ISO_TIME_TIME_ZONE_FORMAT.toString());
		System.out.println(DateFormatUtils.SMTP_DATETIME_FORMAT.toString());
	}
	
	@Test
	public void test16(){
		System.out.println(Integer.valueOf(null));
	}
	
	@Test
	public void test14(){
		List<String> strList = Arrays.asList("AA","BBDD","CDDD","SDDS","AASSSSS","AAAA","AAD","ADDA","AADD","XDCD");
		Instant s = Instant.now();
		List<String> strList_ = strList.stream().sorted((x,y)->Integer.compare(x.length(), y.length())).filter((str)->str.length()<=4).collect(Collectors.toList());
		List<String> result = new ArrayList<>();
		int index = 0;
		for(String str : strList_){
			if(str.length()>2){
				index = index + 2;
			}else{
				index = index + 1;
			}
			if(index<11){
				result.add(str);
			}
		}
		System.out.println(result+" ,"+Duration.between(s, Instant.now()));
		Instant start = Instant.now();
		Integer missPart = strList.stream().sorted((x,y)->Integer.compare(x.length(), y.length())).filter((str)->str.length()<=4).limit(10).map((str)->{
			if(str.length()>2){
				return -1;
			}else{
				return 0;
			}
		}).reduce(Integer::sum).get();//8
		Integer leftPart = Integer.sum(missPart, 10);//6
		Integer fullPart = (int) strList.stream().sorted((x,y)->Integer.compare(x.length(), y.length())).filter((str)->str.length()<=4).count();
		Integer lastPart = (leftPart + Integer.min(fullPart, 10))/2;;
		System.out.println(missPart+","+leftPart+","+fullPart);
		/*if(sum<otherSize){
		}else{
			last = sum;
		}*/
		strList = strList.stream().sorted((x,y)->Integer.compare(x.length(), y.length())).filter((str)->str.length()<=4).limit(lastPart).collect(Collectors.toList());
		System.out.println(strList+","+Duration.between(start, Instant.now()));
		
	}
	
	@Test
	public void test13(){
		System.out.println(new Date(1509592082135L));
	}
	
	@Test
	public void test12(){
		Map<String,List<String>> map = new TreeMap<>();
		map.put("1", Arrays.asList("DD"));
		map.put("2", Arrays.asList("CC"));
		map.put("3", Arrays.asList("BB"));
		map.put("4", Arrays.asList("AA"));
		System.out.println(JSON.toJSON(map));
		System.out.println(JSON.toJSONString(map));
	}
	@Test
	public void test11(){
		//准备把等级分布的数据按日期进行分组
		Map<String,List<GradeDistribution>> firstGroupBy =  gradeDistributions.stream()
//						.sorted((x,y)->y.getCurdate().compareTo(x.getCurdate()))
						.collect(Collectors.groupingBy
									(GradeDistribution::getCurdate,TreeMap::new,Collectors.toList()));
		Map<String,Map<Integer,Integer>> outMap = new TreeMap<String,Map<Integer,Integer>>();
		Map<String,Collection<Integer>> outMap_ = new TreeMap<String,Collection<Integer>>(/*new Comparator<String>() {

			@Override
			public int compare(String x, String y) {
				return y.compareTo(x);
			}
			
		}*/);
		System.out.println("firstGroupBy>>>>"+JSON.toJSONString(firstGroupBy));
		List<ReturnResult> last = new ArrayList<>();
		for(Map.Entry<String, List<GradeDistribution>> entry:firstGroupBy.entrySet()){
			System.out.println(entry.getKey()+">>>>>>>>>>>>>>>>>"+entry.getValue());
			Map<Integer,Integer> innerMap = initMapWithCurrentGradeInfos();
			//遍历分析当前的等级集合人员集合 进行规整
			for(GradeDistribution gd:entry.getValue()){
				Integer curexp = gd.getCurexp();
				Integer countyCode = gd.getCountyCode();//
				Integer curusers = gd.getCurusers();
				Integer eldergrade = gd.getCurgrade();//之前的等级
				Integer curgrade = getCurrentGradeInfo(curexp).getId();//现在的等级
//						System.out.println("*********************************"+Objects.equals(eldergrade, curgrade));
				if(Objects.equals(eldergrade, curgrade)){//如果现在的等级标准与之前的等级标准一样,则说明级别没有调整过
					innerMap.put(curgrade, curusers);
				}else{//如果当前的等级与之前的等级不一样则把之前的等级的人数加到现在的等级人数上  以上适用于 当前等级比以前的等级少了的情况
					innerMap.put(curgrade, innerMap.get(curgrade)+curusers);
				}
			}
			outMap.put(entry.getKey(), innerMap);
			outMap_.put(entry.getKey(), innerMap.values());
			ReturnResult rr = new ReturnResult();
			rr.setDate(entry.getKey());
			rr.setList(innerMap.values());
			last.add(rr);
		}
		for(Map.Entry<String,Collection<Integer>> enn:outMap_.entrySet()){
			System.out.println(enn.getKey());
		}
		System.out.println(JSON.toJSON(outMap));
		System.out.println("************************************************************************");
		System.out.println(JSON.toJSONString(outMap_));
		System.out.println(JSON.toJSON(last));
	}
	@Test
	public void test10(){
		//准备把等级分布的数据按日期进行分组
		LinkedHashMap<String,List<GradeDistribution>> firstGroupBy =  gradeDistributions.stream()
						.collect(Collectors.groupingBy
									(GradeDistribution::getCurdate,LinkedHashMap::new,Collectors.toList()));
		Map<String,Map<Integer,Integer>> outMap = new LinkedHashMap<String,Map<Integer,Integer>>();
		Map<String,Collection<Integer>> outMap_ = new LinkedHashMap<String,Collection<Integer>>();
		for(Map.Entry<String, List<GradeDistribution>> entry:firstGroupBy.entrySet()){
//					System.out.println(entry.getKey()+">>>>>>>>>>>>>>>>>"+entry.getValue());
			Map<Integer,Integer> innerMap = initMapWithCurrentGradeInfos();
			//遍历分析当前的等级集合人员集合 进行规整
			for(GradeDistribution gd:entry.getValue()){
				Integer curexp = gd.getCurexp();
				Integer countyCode = gd.getCountyCode();//
				Integer curusers = gd.getCurusers();
				Integer eldergrade = gd.getCurgrade();//之前的等级
				Integer curgrade = getCurrentGradeInfo(curexp).getId();//现在的等级
//						System.out.println("*********************************"+Objects.equals(eldergrade, curgrade));
				if(Objects.equals(eldergrade, curgrade)){//如果现在的等级标准与之前的等级标准一样,则说明级别没有调整过
					innerMap.put(curgrade, curusers);
				}else{//如果当前的等级与之前的等级不一样则把之前的等级的人数加到现在的等级人数上  以上适用于 当前等级比以前的等级少了的情况
					innerMap.put(curgrade, innerMap.get(curgrade)+curusers);
				}
			}
			outMap.put(entry.getKey(), innerMap);
			outMap_.put(entry.getKey(), innerMap.values());
		}
		System.out.println(JSON.toJSON(outMap));
		System.out.println(JSON.toJSONString(outMap));
		System.out.println("************************************************************************");
		System.out.println(JSON.toJSON(outMap_));
		System.out.println(JSON.toJSONString(outMap_));
	}
	
	@Test
	public void test9(){
		//准备把等级分布的数据按日期进行分组
		Map<String,List<GradeDistribution>> firstGroupBy =  gradeDistributions.stream()
						.collect(Collectors.groupingBy(GradeDistribution::getCurdate));
		Map<String,Map<Integer,Integer>> outMap = new HashMap<String,Map<Integer,Integer>>();
		Map<String,Collection<Integer>> outMap_ = new HashMap<String,Collection<Integer>>();
		for(Map.Entry<String, List<GradeDistribution>> entry:firstGroupBy.entrySet()){
//			System.out.println(entry.getKey()+">>>>>>>>>>>>>>>>>"+entry.getValue());
			Map<Integer,Integer> innerMap = initMapWithCurrentGradeInfos();
			//遍历分析当前的等级集合人员集合 进行规整
			for(GradeDistribution gd:entry.getValue()){
				Integer curexp = gd.getCurexp();
				Integer countyCode = gd.getCountyCode();//
				Integer curusers = gd.getCurusers();
				Integer eldergrade = gd.getCurgrade();//之前的等级
				Integer curgrade = getCurrentGradeInfo(curexp).getId();//现在的等级
//				System.out.println("*********************************"+Objects.equals(eldergrade, curgrade));
				if(Objects.equals(eldergrade, curgrade)){//如果现在的等级标准与之前的等级标准一样,则说明级别没有调整过
					innerMap.put(curgrade, curusers);
				}else{//如果当前的等级与之前的等级不一样则把之前的等级的人数加到现在的等级人数上  以上适用于 当前等级比以前的等级少了的情况
					innerMap.put(curgrade, innerMap.get(curgrade)+curusers);
				}
			}
			outMap.put(entry.getKey(), innerMap);
			outMap_.put(entry.getKey(), innerMap.values());
		}
		System.out.println(JSON.toJSON(outMap));
		System.out.println(JSON.toJSONString(outMap));
		System.out.println("************************************************************************");
		System.out.println(JSON.toJSON(outMap_));
		System.out.println(JSON.toJSONString(outMap_));
	}
	@Test
	public void test2(){
		List<Integer> ids = gradeInfos.stream().map(GradeInfo::getId).collect(Collectors.toList());
		System.out.println(ids);
	}
	/**
	 * 根据等级计算出当前级数经验下限
	 */
	@Test
	public void test6(){
		gradeInfos.stream().forEach((e1)->{
			Integer totalSumExperience = gradeInfos.stream()
				.filter((e2)->Integer.compare(e1.getId(), e2.getId())>=0)
				.mapToInt(GradeInfo::getRequiredExperience)
				.reduce(Integer::sum).getAsInt();
			e1.setTotalSumExperience(totalSumExperience);
		});
		gradeInfos.stream().forEach(e->System.out.println(e));
		
		//使用传统方法来累加各等级的实际经验 累加计算
		/*for(GradeInfo gradeInfo :gradeInfos){
			Integer totalSumExperience = gradeInfos.stream().
					filter((e)->Integer.compare(gradeInfo.getGrade(), e.getGrade())>=0).
					mapToInt(GradeInfo::getRequiredExperience).reduce(Integer::sum).getAsInt();
			System.out.println("totalSumExperience>>>>>>>>>>>>>>>>>>>>>>>>"+totalSumExperience);
			gradeInfo.setTotalSumExperience(totalSumExperience);
		}
		
		for(GradeInfo gradeInfo :gradeInfos){
			System.out.println("gradeInfo>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+gradeInfo);
		}*/
	}
	@Test
	public void test0(){
		Map<Integer,Map<GradeInfo,List<UserExpInt>>> map = userExpInt.stream()
				.collect(Collectors.groupingBy(UserExpInt::getCountyCode,
							Collectors.groupingBy(e->getCurrentGradeInfo(e))));
		for(Map.Entry<Integer, Map<GradeInfo,List<UserExpInt>>> entry:map.entrySet()){
			System.out.println("当前地市下"+entry.getKey().intValue());
			Map<GradeInfo,List<UserExpInt>> innerMap = entry.getValue();
			for(Map.Entry<GradeInfo,List<UserExpInt>> innerEntry:innerMap.entrySet()){
				System.out.println(innerEntry.getKey().getGradeName()+" : "+innerEntry.getValue().size());
			}
		}
//		System.out.println("知道各个等级及对应的人员情况:"+map);
//		int sum = 0;
//		Map<GradeInfo,Integer> eachGradeInfo = new HashMap<>();
//		for(Map.Entry<GradeInfo, List<UserExpInt>> entry:map.entrySet()){
//			System.out.println(entry.getKey().getGradeName()+" : "+entry.getValue().size());
//			eachGradeInfo.put(entry.getKey(), entry.getValue().size());
//			sum += entry.getValue().size();
//		}
//		System.out.println("SUM---->"+sum);
//		System.out.println("******************************尝试计算出每个用户积分上超过其他用户的情况*****************************");
//		
//		List<UserExpIntExtra> extraList = new ArrayList<>();
//		for(Map.Entry<GradeInfo, List<UserExpInt>> entry:map.entrySet()){
//			GradeInfo gradeInfo = entry.getKey();
//			List<UserExpInt> sources = entry.getValue();
//			for(UserExpInt source:sources){
//				UserExpIntExtra target = new UserExpIntExtra();
//				BeanUtils.copyProperties(source, target);
//				target.setGradeInfo(gradeInfo);
//				extraList.add(target);
//			}
//		}
//		List<UserExpIntExtra> lastExtraList = extraList.stream().map(e->cal(e,map)).collect(Collectors.toList());
//		for (UserExpIntExtra uee :lastExtraList) {
//			Double rank = (double) (uee.getBeyondOthers()*100.0/(sum-1));
//			DecimalFormat df = new DecimalFormat("0.00");
//			String lastRank = df.format(rank);
//			uee.setDoubleRank(lastRank);
//		}
//		lastExtraList.stream()
//			.sorted((x,y)->x.getBeyondOthers().compareTo(y.getBeyondOthers()))
//			.forEach(System.out::println);
	}
	private UserExpIntExtra cal(UserExpIntExtra uee,Map<GradeInfo,List<UserExpInt>> map){
		int selfPart = map.get(uee.getGradeInfo()).size();
		//算出在比它低级的那些存在
//		int othersSum = getPreGradeInfo(uee).stream().map(e->map.get(e).size()).reduce(Integer::sum).get();
		int othersSum = 0;
		List<GradeInfo> gradeInfos = getPreGradeInfo(uee);
		for(GradeInfo info:gradeInfos){
			othersSum += map.get(info).size();
		}
		//再计算出在自己部分自己的排名
		List<UserExpInt> currentGroup = map.get(uee.getGradeInfo());
		currentGroup = currentGroup.stream().sorted((x,y)->Integer.compare(y.getExperience(), x.getExperience())).collect(Collectors.toList());
		for(int i = 0;i<currentGroup.size();i++){
			if(currentGroup.get(i).getExperience().equals(uee.getExperience())){
				uee.setBeyondOthers(othersSum+selfPart-1-i);
				break;
			}
		}
		return uee;
	}
	
	/**
	 * 找到当前的等级
	 * @param uei
	 * @return
	 */
	public GradeInfo getCurrentGradeInfo(UserExpInt uei){
		GradeInfo gradeInfo = gradeInfos.stream()
				.filter(x-> x.getRequiredExperience() <= uei.getExperience())
				.sorted((x,y) -> Integer.compare(y.getRequiredExperience(), x.getRequiredExperience()))
				.findFirst()
				.get();
		return gradeInfo;
	}
	
	public Map<Integer,Integer> initMapWithCurrentGradeInfos(){
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		for(GradeInfo gradeInfo:gradeInfos){
			map.put(gradeInfo.getId(), 0);
		}
		/*for(GradeInfo gradeInfo:changedGradeInfos){
			map.put(gradeInfo.getId(), 0);
		}*/
		return map;
	}
	
	public GradeInfo getCurrentGradeInfo(Integer curexp){//changedGradeInfos
		GradeInfo gradeInfo = gradeInfos.stream()
//		GradeInfo gradeInfo = changedGradeInfos.stream()
				.filter(x-> x.getRequiredExperience() <= curexp)
				.sorted((x,y) -> Integer.compare(y.getRequiredExperience(), x.getRequiredExperience()))
				.findFirst()
				.get();
		return gradeInfo;
	}
	
	/**
	 * 找到下一等级
	 * @param uei
	 * @return
	 */
	public GradeInfo getNextGradeInfo(UserExpInt uei){
		GradeInfo gradeInfo = gradeInfos.stream()
				.filter(x-> x.getRequiredExperience() > uei.getExperience())
				.sorted((x,y) -> Integer.compare(x.getRequiredExperience(), y.getRequiredExperience()))
				.findFirst()
				.get();
		return gradeInfo;
	}
	
	/**
	 * 找到之前的等级 比如说4级之前的等级 (1,2,3)
	 * @param uei
	 * @return
	 */
	public List<GradeInfo> getPreGradeInfo(UserExpIntExtra uei){
		List<GradeInfo> list = gradeInfos.stream()
				.filter(x-> x.getGrade() < uei.getGradeInfo().getGrade())
				.sorted((x,y) -> Integer.compare(y.getGrade(), x.getGrade()))
				.collect(Collectors.toList());
		return list;
	}
	
	@Test
	public void test1(){
		UserExpIntExtra uei = new UserExpIntExtra();
		GradeInfo gradeInfo = new GradeInfo();
		gradeInfo.setGrade(1);
		uei.setGradeInfo(gradeInfo);
		System.out.println(uei.getGradeInfo().getGrade()+"--->"+getPreGradeInfo(uei));
		gradeInfo.setGrade(2);
		uei.setGradeInfo(gradeInfo);
		System.out.println(uei.getGradeInfo().getGrade()+"--->"+getPreGradeInfo(uei));
		gradeInfo.setGrade(3);
		uei.setGradeInfo(gradeInfo);
		System.out.println(uei.getGradeInfo().getGrade()+"--->"+getPreGradeInfo(uei));
		gradeInfo.setGrade(4);
		uei.setGradeInfo(gradeInfo);
		System.out.println(uei.getGradeInfo().getGrade()+"--->"+getPreGradeInfo(uei));
	}
	@Test
	public void test3(){
		List<GradeInfo> gradeInfos = new ArrayList<GradeInfo>();
		GradeInfo gradeInfo = new GradeInfo(1, 1, 1,0, "A", "http://", 0, 10);
		gradeInfos.add(gradeInfo);
		GradeInfo tmp = gradeInfos.get(0);
		System.out.println("Clear 之前"+tmp);
		gradeInfos.clear();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		System.out.println("Clear 之后_"+tmp);
	}
	
	@Test
	public void test4(){
		int a = 3;
		int b = 4;
		System.out.println(Integer.compare(4, 3));
	}
	
	@Test
	public void test5(){
		int[] ints = {1, 2, 3, 4, 5, 6};
        int feature = 1;
        String features = "2345";
        if (ArrayUtils.contains(ints, feature) && !features.contains(String.valueOf(feature))) {
        	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>"+feature+"<<<<<<<<<<<<<<<<<<<<<<<<<");
        }
        System.out.println(StringUtils.isNumeric("abc"));
//        System.out.println(NumberUtils.isNumber("dbc"));
        System.out.println(StringUtils.isNumeric("1.20"));
        System.out.println(StringUtils.isNumeric("-0"));
//        System.out.println(NumberUtils.isNumber("-01"));
        List<Integer> list = Arrays.asList(3,4,5,6);
        list.toArray(new Integer[]{});
	}
}
