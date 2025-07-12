package clients;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import specs.RequestSpecFactory;

import java.util.Map;

public class RestAssuredClient {

    String baseURI;

    public RestAssuredClient(String baseURI){
        this.baseURI = baseURI;
    }

    public Response get(String path, Map<String,String>headers, ContentType contentType){
        RequestSpecification spec = RequestSpecFactory.reqSpec(baseURI, headers, contentType);
        return RestAssured.given().spec(spec).get(path);
    }

    public Response postJson(String path, Map<String, String>headers, Object body, ContentType contentType){
        RequestSpecification spec = RequestSpecFactory.reqSpec(baseURI, headers, contentType);
        return RestAssured.given().spec(spec).body(body).post(path);
    }

    public Response postForm(String path, Map<String, String>headers, Map<String, String> formParams, ContentType contentType){
        RequestSpecification spec = RequestSpecFactory.reqSpec(baseURI, headers, contentType);
        return RestAssured.given().spec(spec).formParams(formParams).post(path);
    }

    public Response delete (String path, Map<String, String>headers, ContentType contentType){
        RequestSpecification spec = RequestSpecFactory.reqSpec(baseURI, headers, contentType);
        return RestAssured.given().spec(spec).delete(path);
    }
}
