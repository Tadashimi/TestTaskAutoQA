package org.tempuri.pojotests;

import org.junit.Before;
import org.junit.Test;
import org.tempuri.Calculator;
import org.tempuri.CalculatorSoap;

import javax.xml.ws.WebServiceException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.tempuri.pojotests.IntegerValuesGenerator.getRandomNegativeInteger;
import static org.tempuri.pojotests.IntegerValuesGenerator.getRandomPositiveInteger;

public class AddOperationTests {
    private int intA;
    private int intB;
    private int expectedValue;

    private CalculatorSoap calculatorSoap;

    @Before
    public void initCalculatorSoap() {
        calculatorSoap = new Calculator().getCalculatorSoap();
    }

    @Test
    public void twoPositiveIntegersTest() {
        setIntegersValue(getRandomPositiveInteger(), getRandomPositiveInteger());

        assertThat(generateErrorMessage(), calculatorSoap.add(intA, intB), equalTo(expectedValue));
    }

    @Test
    public void twoNegativeIntegersTest() {
        setIntegersValue(getRandomNegativeInteger(), getRandomNegativeInteger());

        assertThat(generateErrorMessage(), calculatorSoap.add(intA, intB), equalTo(expectedValue));
    }

    @Test
    public void positiveAndNegativeIntegersTest() {
        setIntegersValue(getRandomPositiveInteger(), getRandomNegativeInteger());

        assertThat(generateErrorMessage(), calculatorSoap.add(intA, intB), equalTo(expectedValue));
    }

    @Test
    public void maxAndMinIntegersTest() {
        setIntegersValue(Integer.MAX_VALUE, Integer.MIN_VALUE);

        assertThat(generateErrorMessage(), calculatorSoap.add(intA, intB), equalTo(expectedValue));
    }

    @Test
    public void zeroAndIntegerTest() {
        setIntegersValue(0, Integer.MIN_VALUE);

        assertThat(generateErrorMessage(), calculatorSoap.add(intA, intB), equalTo(expectedValue));
    }

    @Test(expected = WebServiceException.class)
    public void twoMaxIntegersTest() {
        calculatorSoap.add(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    @Test(expected = WebServiceException.class)
    public void twoMinIntegersTest() {
        calculatorSoap.add(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    private void setIntegersValue(int intA, int intB) {
        this.intA = intA;
        this.intB = intB;
        this.expectedValue = intA + intB;
    }

    private String generateErrorMessage() {
        return String.format("Error while adding. Values: %d and %d", intA, intB);
    }
}
