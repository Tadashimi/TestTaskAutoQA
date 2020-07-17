package org.tempuri.pojotests;

import com.sun.xml.internal.ws.fault.ServerSOAPFaultException;
import org.junit.Before;
import org.junit.Test;
import org.tempuri.Calculator;
import org.tempuri.CalculatorSoap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.tempuri.pojotests.IntegerValuesGenerator.getRandomNegativeInteger;
import static org.tempuri.pojotests.IntegerValuesGenerator.getRandomNegativeNotZeroValue;
import static org.tempuri.pojotests.IntegerValuesGenerator.getRandomPositiveInteger;
import static org.tempuri.pojotests.IntegerValuesGenerator.getRandomPositiveNotZeroValue;

public class DivideOperationTests {
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
        setIntegersValue(getRandomPositiveInteger(), getRandomPositiveNotZeroValue());

        assertThat(generateErrorMessage(), calculatorSoap.divide(intA, intB), equalTo(expectedValue));
    }

    @Test
    public void twoNegativeIntegersTest() {
        setIntegersValue(getRandomNegativeInteger(), getRandomNegativeNotZeroValue());

        assertThat(generateErrorMessage(), calculatorSoap.divide(intA, intB), equalTo(expectedValue));
    }

    @Test
    public void positiveAndNegativeIntegersTest() {
        setIntegersValue(getRandomPositiveInteger(), getRandomNegativeNotZeroValue());

        assertThat(generateErrorMessage(), calculatorSoap.divide(intA, intB), equalTo(expectedValue));
    }

    @Test
    public void negativeAndPositiveIntegersTest() {
        setIntegersValue(getRandomNegativeInteger(), getRandomPositiveNotZeroValue());

        assertThat(generateErrorMessage(), calculatorSoap.divide(intA, intB), equalTo(expectedValue));
    }

    @Test
    public void maxAndMinIntegersTest() {
        setIntegersValue(Integer.MAX_VALUE, Integer.MIN_VALUE);

        assertThat(generateErrorMessage(), calculatorSoap.divide(intA, intB), equalTo(expectedValue));
    }

    @Test
    public void twoMaxIntegersTest() {
        setIntegersValue(Integer.MAX_VALUE, Integer.MAX_VALUE);

        assertThat(generateErrorMessage(), calculatorSoap.divide(intA, intB), equalTo(expectedValue));
    }

    @Test
    public void twoMinIntegersTest() {
        setIntegersValue(Integer.MIN_VALUE, Integer.MIN_VALUE);

        assertThat(generateErrorMessage(), calculatorSoap.divide(intA, intB), equalTo(expectedValue));
    }

    @Test
    public void zeroDivideByIntegerTest() {
        setIntegersValue(0, getRandomNegativeNotZeroValue());

        assertThat(generateErrorMessage(), calculatorSoap.divide(intA, intB), equalTo(expectedValue));
    }

    @Test
    public void divideByOneTest() {
        setIntegersValue(getRandomPositiveInteger(), 1);

        assertThat(generateErrorMessage(), calculatorSoap.divide(intA, intB), equalTo(expectedValue));
    }

    @Test(expected = ServerSOAPFaultException.class)
    public void divideByZeroTest() {
        calculatorSoap.divide(getRandomPositiveInteger(), 0);
    }


    private void setIntegersValue(int intA, int intB) {
        this.intA = intA;
        this.intB = intB;
        if (intB == 1) {
            this.expectedValue = intA;
        } else {
            float floatExpectedValue = intA / (float) intB;
            this.expectedValue = Math.round(floatExpectedValue);
        }
    }

    private String generateErrorMessage() {
        return String.format("Error while divide. Values: %d and %d", intA, intB);
    }
}
