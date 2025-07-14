package booking;

import helpers.AuthHelper;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.request.AuthRequest;
import models.response.AuthResponse;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class AuthTests {

    String token = "";

    @Test
    @Description("Verify that a token is created successfully when valid username and password are provided.")
    public void testCreateTokenWithValidData() {

        AuthHelper authHelper = new AuthHelper();
        AuthRequest validAuthRequest = authHelper.defaultRequest();

        Allure.step("Send authentication request with valid credentials");
        Response response = authHelper.CreateToken(validAuthRequest);
        response.then().statusCode(200).log().all();

        Allure.step("Deserialize response and extract token");
        token = response.as(AuthResponse.class).getToken();
        Allure.addAttachment("Token value", token);

        Allure.step("Assert that token is not null or empty");
        assertThat("Token should not be empty", token, not(isEmptyOrNullString()));
    }

    @Test
    @Description("Verify that the token is not created when invalid credentials are used. " +
            "Asserts that the response body contains 'reason: Bad credentials'.")
    public void testCreateTokenWithInvalidData() {

        AuthHelper authHelper = new AuthHelper();
        AuthRequest invalidAuthRequest = authHelper.defaultRequest();
        invalidAuthRequest.setUsername("");

        Allure.step("Send authentication request with invalid credentials");
        Response response = authHelper.CreateToken(invalidAuthRequest);
        response.then().statusCode(200).log().all();

        Allure.step("Extract error reason from response body");
        JsonPath jsonPath = response.jsonPath();
        String reason = jsonPath.getString("reason");
        Allure.addAttachment("Reason received", reason);

        Allure.step("Assert that reason is 'Bad credentials'");
        assertThat("Error reason should be 'Bad credentials'", reason, equalTo("Bad credentials"));
    }
}
