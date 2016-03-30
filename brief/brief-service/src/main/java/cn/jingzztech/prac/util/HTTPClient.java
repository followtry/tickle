package cn.jingzztech.prac.util;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * @author jingzz
 * @time 2016年3月21日 下午3:24:12
 * @name service-im-impl/com.yonyou.worktime.service.im.util.HTTPClient
 * @since 2016年3月21日 下午3:24:12
 */
public class HTTPClient {
	
	private static HttpClient httpClient = HttpClientBuilder.create().build();
	
	private static final Logger LOG = LoggerFactory.getLogger(HTTPClient.class);

	/**
	 * http的post请求方式
	 * @author jingzz
	 * @param url
	 * @param jsonParam
	 * @return
	 */
	public static String post(String url,String jsonParam){
		
		HttpPost postMethod = new HttpPost(url);
		StringEntity requestBody = new StringEntity(jsonParam, "UTF-8");
		requestBody.setContentEncoding("UTF-8");
		requestBody.setContentType("application/json");
		postMethod.setEntity(requestBody);
		
		String response = httpClientBase(postMethod);
		return response;
	}
	
	/**
	 * http的get请求方式
	 * @author jingzz
	 * @param url
	 * @return
	 */
	public static String get(String url){
		HttpGet getMethod = new HttpGet(url);
		String response = httpClientBase(getMethod);
		return response;
	}
	
	/**
	 * http的put请求方式
	 * @author jingzz
	 * @param url
	 * @param jsonParam
	 * @return
	 */
	public static String put(String url,String jsonParam){
		
		HttpPut putMethod = new HttpPut(url);
		StringEntity requestBody = new StringEntity(jsonParam, "UTF-8");
		requestBody.setContentEncoding("UTF-8");
		requestBody.setContentType("application/json");
		putMethod.setEntity(requestBody);
		
		String response = httpClientBase(putMethod);
		return response;
	}
	
	public static String delete(String url){
		
		HttpDelete deleteMethod = new HttpDelete(url);
		String response = httpClientBase(deleteMethod);
		return response;
	}
	
	
	/**
	 * 基础的http请求设置
	 * @author jingzz
	 * @param requst
	 * @return
	 */
	public static String httpClientBase(HttpUriRequest requst){
		String responseBody = null;
		HttpResponse result = null;
		
		try {
			result = httpClient.execute(requst);
			responseBody = EntityUtils.toString(result.getEntity(), "UTF-8");
		}  catch (ParseException e) {
			responseBody = null;
		} catch (ClientProtocolException e) {
			result = null;
			LOG.error(e.toString(),e);
		} catch (IOException e) {
			result = null;
			LOG.error(e.toString(),e);
		} 
		return responseBody;
	}
	
	public static void main(String[] args) {
		
		String url = "http://localhost:8080/web-server/service/project/309/task";
		JSONObject jsonParam = new JSONObject();
		List<String> executors = new ArrayList<String>();
		executors.add("12");
		jsonParam.put("planStartdate", "2016-3-12 00:00:00");
		jsonParam.put("planEnddate", "2016-4-12 00:00:00");
		jsonParam.put("description", "ssss");
		jsonParam.put("name", "            ");
		jsonParam.put("priority", "");
		jsonParam.put("executorIdList",executors);
		String responseBody = HTTPClient.post(url,jsonParam.toJSONString());
		System.out.println(responseBody);
	}
}
