package Day1;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;
/*given()
 *   query and path param, body, cookies. This is optional if these items are not needed in the request
when()
get/post/put/delete
then()
*/

public class HTTPRequests {
	
	int id;
	@Test(priority=1)
	void getusers()
	{
		given()
		
		.when()
		  .get("https://reqres.in/api/users?page=2")
		
		.then()
		.statusCode(200).body("page",equalTo(2)).log().all();
		;
	}
	@Test(priority=2)
	void createuser()
	{
		HashMap data = new HashMap();
		data.put("name", "jayedul");
		data.put("job", "trainer");
		 
		id=given()
		 	.contentType("application/json")
		 	.body(data)
		
		.when()
		  .post("https://reqres.in/api/users")
		  .jsonPath().getInt("id")
		  ;
		
//		.then()
//		.statusCode(201).log().all();
//		;
	}
	@Test(priority =3, dependsOnMethods = {"createuser"})
	void updateUser()
	{
		HashMap data = new HashMap();
		data.put("name", "jayedul");
		data.put("job", "teacher");
		 
		given()
		 	.contentType("application/json")
		 	.body(data)
		
		.when()
		  .put("https://reqres.in/api/users/"+id)
		
		.then()
		.statusCode(200).log().all();
		;
	}
	@Test(priority =4)
	void deleteUser()
	{
		given()
		
		.when()
		 	.delete("https://reqres.in/api/users/"+id)
		
		.then().statusCode(204).log().all();;
	}
}
