package org.tempuri.soaptests.soap12;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.tempuri.soaptests.OperationTests;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.tempuri.soaptests.RequestBodies.ADD_12_BODY;

@RunWith(value = Parameterized.class)
public class AddOperationForInvalidInputTests extends OperationTests {

    private String intA;
    private String intB;

    public AddOperationForInvalidInputTests(String intA, String intB) {
        this.intA = intA;
        this.intB = intB;
    }

    @Parameterized.Parameters(name = "{index}: Add({0},{1})")
    public static Iterable<Object[]> dataForTest() {
        return Arrays.asList(new Object[][]{
                {String.valueOf(Integer.MAX_VALUE), String.valueOf(Integer.MAX_VALUE)},
                {String.valueOf(Integer.MIN_VALUE), String.valueOf(Integer.MIN_VALUE)},
                {"null", "null"},
                {"abc", "!@#"},
                {"", ""},
                {"   ", "<script>alert(1)</script>"},
                {"-9а9б8в8", "_-99887_"},
                {"1+3", "50-100"},
                {"100/5", "350*200"},
        });
    }

    @Test
    public void validIntegersTest() {
        String validAddBody = ADD_12_BODY.replaceAll("INT_A", intA)
                .replaceAll("INT_B", intB);
        given().when()
                .header(ADD_SOAP_ACTION_HEADER)
                .contentType(SOAP_XML_CONTENT_TYPE)
                .body(validAddBody)
                .post(CALCULATOR_URL)
                .then()
                .statusCode(SERVER_ERROR_HTTP_STATUS)
                .contentType(SOAP_XML_CONTENT_TYPE)
                .body("soap:Fault", contains(any(String.class)))
                .body(not(hasItem("AddResult")));
    }
}
