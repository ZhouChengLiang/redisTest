package org.zcl.mytest.juhe;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

public class JuHeDemo {
	public static final String DEF_CHATSET = "UTF-8";
	public static final int DEF_CONN_TIMEOUT = 30000;
	public static final int DEF_READ_TIMEOUT = 30000;
	public static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

	// 配置您申请的KEY
	public static final String APPKEY = "408774a3b53f1bfb39d0f9543e09a9f4";

	// 1.获取当天的详细信息
	public static void getRequest1() {
		String result = null;
		String url = "http://v.juhe.cn/calendar/day";// 请求接口地址
		Map<String, String> params = new HashMap<>();// 请求参数
		params.put("key", APPKEY);// 您申请的appKey
		params.put("date", "2017-10-22");// 指定日期,格式为YYYY-MM-DD,如月份和日期小于10,则取个位,如:2012-1-1

		try {
			result = net(url, params, "GET");
			JSONObject object = JSONObject.parseObject(result);
			if (object.getIntValue("error_code") == 0) {
				System.out.println(object.get("result"));
			} else {
				System.out.println(object.get("error_code") + ":" + object.get("reason"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 2.获取当月近期假期
	public static void getRequest2() {
		
		
		 
	}

	// 3.获取当年的假期列表
	public static void getRequest3() {
		
	}

	public static void main(String[] args) {
		getRequest1();
	}

	/**
	 *
	 * @param strUrl
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @param method
	 *            请求方法
	 * @return 网络请求字符串
	 * @throws Exception
	 */
	public static String net(String strUrl, Map params, String method) throws Exception {
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		String rs = null;
		try {
			StringBuffer sb = new StringBuffer();
			if (method == null || method.equals("GET")) {
				strUrl = strUrl + "?" + urlencode(params);
			}
			URL url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();
			if (method == null || method.equals("GET")) {
				conn.setRequestMethod("GET");
			} else {
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
			}
//			conn.setRequestProperty("User-agent", userAgent);
			conn.setUseCaches(false);
			conn.setConnectTimeout(DEF_CONN_TIMEOUT);
			conn.setReadTimeout(DEF_READ_TIMEOUT);
			conn.setInstanceFollowRedirects(false);
			conn.connect();
			if (params != null && method.equals("POST")) {
				try {
					DataOutputStream out = new DataOutputStream(conn.getOutputStream());
					out.writeBytes(urlencode(params));
				} catch (Exception e) {
				}
			}
			InputStream is = conn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sb.append(strRead);
			}
			rs = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return rs;
	}

	// 将map型转为请求参数型
	public static String urlencode(Map<String, Object> data) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, Object> i : data.entrySet()) {
			try {
				sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	@Test
	public void test0(){
		HttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost("http://v.juhe.cn/calendar/day");
        try {
            List<BasicNameValuePair> paramemters = new ArrayList<BasicNameValuePair>();
            paramemters.add(new BasicNameValuePair("date","2017-10-04"));
            paramemters.add(new BasicNameValuePair("key",APPKEY));
            post.setEntity(new UrlEncodedFormEntity(paramemters,"UTF-8"));
            HttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity,"utf-8");
            JSONObject object = JSONObject.parseObject(result);
            if (object.getIntValue("error_code") == 0) {
				System.out.println(object.get("result"));
			} else {
				System.out.println(object.get("error_code") + ":" + object.get("reason"));
			}
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	@Test
	public void test1(){
		HttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet("http://v.juhe.cn/calendar/day?key=408774a3b53f1bfb39d0f9543e09a9f4&date=2017-10-11");
		
		try {
            HttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity,"utf-8");
            System.out.println(result);
            SignCommonInfo signCommonInfo = JSONObject.parseObject(result,SignCommonInfo.class);
            System.out.println(signCommonInfo);
//            if (object.getIntValue("error_code") == 0) {
//            	SignInfo signInfo = object.getObject("result", SignInfo.class);
//            	System.out.println(signInfo);
//            	SignInfo signInfo_ = object.getJSONObject("result").getObject("data", SignInfo.class);
//				System.out.println(signInfo_);
//			} else {
//				SignInfo signInfo = object.getObject("result", SignInfo.class);
//				System.out.println(signInfo);
//				System.out.println(object.get("error_code") + ":" + object.get("reason"));
//			}
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
