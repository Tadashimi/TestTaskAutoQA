package org.tempuri.soap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.tempuri.OperationTests;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.tempuri.RequestBodies.ADD_BODY;

@RunWith(value = Parameterized.class)
public class AddOperationForValidIntegersTests extends OperationTests {

    private String intA;
    private String intB;
    private String expected;

    public AddOperationForValidIntegersTests(String intA, String intB, String expected) {
        this.intA = intA;
        this.intB = intB;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{index}: Add({0},{1}) = {2}")
    public static Iterable<Object[]> dataForTest() {
        return Arrays.asList(new Object[][]{
                {"1", "1", "2"},
                {"20", "60", "80"},
                {"-185", "2", "-183"},
                {"-1312", "-1543", "-2855"},
                {"0", "0", "0"},
                {"-99887", "99887", "0"},
                {String.valueOf(Integer.MAX_VALUE), "0", String.valueOf(Integer.MAX_VALUE)},
                {"0", String.valueOf(Integer.MIN_VALUE), String.valueOf(Integer.MIN_VALUE)},
                {String.valueOf(Integer.MAX_VALUE), String.valueOf(Integer.MIN_VALUE), "-1"}
        });
    }

    @Test
    public void validIntegersTest() {
        String validAddBody = ADD_BODY.replaceAll("INT_A", intA)
                .replaceAll("INT_B", intB);
        given().when()
                .header(ADD_SOAP_ACTION_HEADER)
                .contentType(TEXT_XML_CONTENT_TYPE)
                .body(validAddBody)
                .post(CALCULATOR_URL)
                .then()
                .statusCode(OK_HTTP_STATUS)
                .contentType(TEXT_XML_CONTENT_TYPE)
                .body("AddResult", contains(expected));
    }
}
