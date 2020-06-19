import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class APITest {

    @Test
    public void getBasePage(){
        given().
                get("https://stackexchange.com/").
                then().
                statusCode(200).log().all();
    }


    @Test

    public void getPrivileges(){
        given().
                get("http://api.stackexchange.com/2.2/privileges?site=stackoverflow").
                then().
                statusCode(200).log().all();
    }



    @Test (dataProvider = "answerId")
    public void getAnswersById(int answerId){
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");
        Response response = httpRequest.get("http://api.stackexchange.com/2.2/answers/"+answerId+"?order=desc&sort=activity&site=stackoverflow");
        JsonPath jsonPathEvaluator = response.jsonPath();
        if(jsonPathEvaluator.getString("items").length()==2){
            Assert.assertTrue(jsonPathEvaluator.getString("items").contains(""));
        }else {
            Assert.assertTrue(jsonPathEvaluator.getString("items.owner.user_id").contains("8265036"));
            Assert.assertTrue(jsonPathEvaluator.getString("items.owner.user_type").contains("registered"));
        }
    }


    @Test

    public void getInfo(){

        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");
        Response response = httpRequest.get("http://api.stackexchange.com/2.2/info?site=stackoverflow");
        JsonPath jsonPathEvaluator = response.jsonPath();
        Assert.assertTrue(jsonPathEvaluator.getString("items.api_revision").contains("2019.3.14.32702"));

    }




    @Test

    public void getUsageOfUsers(){

        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");
        Response response = httpRequest.get("http://api.stackexchange.com/2.2/users?order=desc&sort=reputation&site=stackoverflow");
        JsonPath jsonPathEvaluator = response.jsonPath();
        Assert.assertTrue(jsonPathEvaluator.getString("items.user_type").contains("registered"));
    }

    @DataProvider(name = "answerId")
    public Object[][] answerIds() {
        return new Object[][] {
                { new Integer(55353854) },
                { new Integer(1231414)},
        };
    }



}
