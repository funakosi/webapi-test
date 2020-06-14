package com.example.sample;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpNativeSample {

	/**
	 * MyUtil: https://web.plus-idea.net/2016/08/httpurlconnection-post-get-proxy-sample/
	 * Json=>Pojo: http://www.jsonschema2pojo.org/
	 * @throws IOException
	 */
	@Test
	public void LivedoorGetSample() throws IOException {
		String result = MyUtil.callGet("http://weather.livedoor.com/forecast/webservice/json/v1?city=471010");
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(result);
		String city = node.get("location").get("city").textValue();
		System.out.println("city:"+city);
	}

	@Test
	public void HttpbinGetSample() throws IOException {
		String result = MyUtil.callGet("http://httpbin.org/get?a=1&b=2");
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(result);
		System.out.println("args:"+node.get("args").toString());
	}

	@Test
	public void HttpbinPostSample() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		//POST form
		String result = MyUtil.callPost("http://httpbin.org/post", "application/x-www-form-urlencoded", "param1=aa&param2=bb");
		JsonNode node = mapper.readTree(result);
		System.out.println("form:"+node.get("form").toString());
		//POST json/data
		result = MyUtil.callPost("http://httpbin.org/post", "application/json", "param1=aa&param2=bb");
		node = mapper.readTree(result);
		System.out.println("data:"+node.get("data").toString());
		//POST json/data args
		result = MyUtil.callPost("http://httpbin.org/post?a=1&b=2", "application/json", "param1=aa&param2=bb");
		node = mapper.readTree(result);
		System.out.println("args:"+node.get("args").toString());
		System.out.println("data:"+node.get("data").toString());
	}

	@Test
	public void JsonPlaceholderGetSample() throws IOException {
		String result = MyUtil.callGet("https://jsonplaceholder.typicode.com/posts/1");
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(result);
		System.out.println("title:"+node.get("title").toString());
	}

	@Test
	public void JsonPlaceholderPostSample() throws IOException {
		String json = "{\"title\":\"a1 test\",\"body\":\"this is test by a1.\",\"userId\":1}";
		String result = MyUtil.callPost("https://jsonplaceholder.typicode.com/posts/","application/json",json);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(result);
		System.out.println("title:"+node.get("title").toString());
	}
}
