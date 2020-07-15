package org.tempuri.soap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.tempuri.OperationTests;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.tempuri.RequestBodies.DIVIDE_BODY;

@RunWith(value = Parameterized.class)
public class DivideOperationForValidIntegersTests extends OperationTests {

    private String intA;
    private String intB;
    private String expected;

    public DivideOperationForValidIntegersTests(String intA, String intB, String expected) {
        this.intA = intA;
        this.intB = intB;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{index}: Divide({0},{1}) = {2}")
    public static Iterable<Object[]> dataForTest() {
        return Arrays.asList(new Object[][]{
                {"1", "1", "1"},
                {"600", "20", "30"},
                {"-186", "2", "-93"},
                {"-1312", "-1543", "1"},
                {"0", "1234678", "0"},
                {"-99887", "99887", "-1"},
                {String.valueOf(Integer.MAX_VALUE), "1", String.valueOf(Integer.MAX_VALUE)},
                {"0", String.valueOf(Integer.MIN_VALUE), "0"},
                {String.valueOf(Integer.MAX_VALUE), String.valueOf(Integer.MIN_VALUE), "-1"}
        });
    }

    @Test
    public void validIntegersTest() {
        String validDivideBody = DIVIDE_BODY.replaceAll("INT_A", intA)
                .replaceAll("INT_B", intB);
        given().when()
                .header(DIVIDE_SOAP_ACTION_HEADER)
                .contentType(TEXT_XML_CONTENT_TYPE)
                .body(validDivideBody)
                .post(CALCULATOR_URL)
                .then()
                .statusCode(OK_HTTP_STATUS)
                .contentType(TEXT_XML_CONTENT_TYPE)
                .body("DivideResult", contains(expected));
    }
}
