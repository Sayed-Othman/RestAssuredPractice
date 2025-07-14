package helpers;

import clients.RestAssuredClient;
import config.APIConstant;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.request.AuthRequest;
import utils.JsonReader;

public class AuthHelper {

    private final RestAssuredClient restAssuredClient = new RestAssuredClient(APIConstant.BASE_URL);

    public AuthRequest defaultRequest(){
        String jsonFilePath = "src/test/resources/auth.json";
        return JsonReader.readJson(jsonFilePath, AuthRequest.class);
    }

    public Response CreateToken(AuthRequest authRequest){
        return restAssuredClient.postJson(APIConstant.Auth.LOGIN, null, authRequest, ContentType.JSON );
    }
}
