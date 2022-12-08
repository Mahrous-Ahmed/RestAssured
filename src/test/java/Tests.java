import Models.BookResponse;
import Utilites.UtilityClass;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Tests {
    @DataProvider
    public Object[][] addBook (){
        return UtilityClass.getExcelData();
    }

    @Test(dataProvider = "addBook")
    public void addAllBooks(String name , String isbn  , String aisle , String author){
        RestAssured.baseURI = "http://216.10.245.166/Library/";
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type","application/json");
        JSONObject requestParams = new JSONObject();
        requestParams.put("name",   name);
        requestParams.put("isbn",   isbn);
        requestParams.put("aisle",  aisle);
        requestParams.put("author", author);
        httpRequest.body(requestParams.toString());

        Response response = RestAssured.post("Addbook.php");
        System.out.println(response.statusCode());
        System.out.println(response.prettyPrint());
    }
}
