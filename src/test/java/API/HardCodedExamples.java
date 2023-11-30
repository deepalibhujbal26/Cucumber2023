package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class HardCodedExamples {

    //in rest assusred base uri =base url
    //we put http here, bcoz we want to connect to browser
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";

    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3MDEzMDg5MTksImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTcwMTM1MjExOSwidXNlcklkIjoiNjA5OCJ9.Zi5l2P2eFuYGsz8r_HaoqQvXDSPVL2ns5vHipci9X88";

    public void createEmployee() {
        //prepare the request

        //request is having general request
        RequestSpecification request = given().header("Content_Type", "application/json").
                header("Authorization", token).
                body("{{\n" +
                        "  \"emp_firstname\": \"Synatax\",\n" +
                        "  \"emp_lastname\": \"Fahim\",\n" +
                        "  \"emp_middle_name\": \"AB\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"1989-11-26\",\n" +
                        "  \"emp_status\": \"confirmed\",\n" +
                        "  \"emp_job_title\": \"Qa\"\n" +
                        "}\n}");


        //send the request for creating employee
        //here we pass the end points with when
        //response is class which holds the complete body and info
      Response response =  request.when().post("/createEmployee.php");

        //validate the response
        response.then().assertThat().statusCode(201);




    }


}
