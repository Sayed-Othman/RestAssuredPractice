package specs;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import java.util.Collections;
import java.util.Map;

public class RequestSpecFactory {

    public static RequestSpecification reqSpec (String baseUri, Map<String, String> headers, ContentType contentType){
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .addHeaders(headers != null ? headers : Collections.EMPTY_MAP)
                .setContentType(contentType)
                .log(LogDetail.ALL)
                .addFilter(new AllureRestAssured())
                .build();
    }
}
