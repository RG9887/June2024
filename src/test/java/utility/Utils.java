package utility;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification Req;
	
	public RequestSpecification RequestSpecification() throws IOException {
		if(Req==null) {
		PrintStream PS = new PrintStream(new FileOutputStream("logging.txt"));	
		Req = new RequestSpecBuilder().setBaseUri(global("baseURL"))
				.addFilter(RequestLoggingFilter.logRequestTo(PS))
				.addFilter(ResponseLoggingFilter.logResponseTo(PS))
				.setContentType(ContentType.JSON).addQueryParam("key", "qaclick123").build();
		return Req;
		}
		return Req;
	}
	
	public String getJsonPath(Response Resp, String Key) {
		String Resp_Str = Resp.asString();
		JsonPath Js = new JsonPath(Resp_Str);
		return Js.get(Key).toString();
	}
	
	public String global(String Key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\Rahul\\June2024\\src\\test\\java\\resources\\global.Properties");
		prop.load(fis);
		return prop.getProperty(Key);
	}
}
