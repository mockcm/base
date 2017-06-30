package com.mock.base.util;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;


public class HttpClientUtil {
	
	public static String Post(String url, TreeMap<String, Object> data) throws Exception {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for (Map.Entry<String, Object> entry : data.entrySet()) {
			String key = entry.getKey();
			String value = null;
			try{
				value = (String)entry.getValue();
			}
			catch(ClassCastException e) {
				value = entry.getValue().toString();
			}				
            if (StringUtil.isNotEmpty(value)) {
                nvps.add(new BasicNameValuePair(key, value));
            }
        }
		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		CloseableHttpResponse res = httpClient.execute(httpPost);
		String result = EntityUtils.toString(res.getEntity(), Charset.forName("UTF-8"));
		return result;
	}
	
	public static String postJson(String url, String data) throws Exception {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
		StringEntity se = new StringEntity(data);
		se.setContentType("text/json");
		se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
		httpPost.setEntity(se);
		CloseableHttpResponse res = httpClient.execute(httpPost);
		String result = EntityUtils.toString(res.getEntity(), Charset.forName("UTF-8"));
		return result;
	}

	public static void main(String[] args) throws Exception {

	}

}
