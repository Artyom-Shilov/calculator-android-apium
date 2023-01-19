package com.shilov.tests;

import com.shilov.driver.AppiumDriverProvider;
import com.shilov.pages.CalculatorPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class CalculatorPageTests {

    private CalculatorPage calculatorPage;
    private AppiumDriverProvider driverProvider = AppiumDriverProvider.getInstance();

    @BeforeMethod
    public void setUp() {
        calculatorPage = new CalculatorPage(driverProvider.getAndroidDriver());
    }

    @Test(dataProvider = "plusOperationData")
    public void shouldAddNumbers(String firstNumber, String secondNumber, String expectedResult) {
        String actualResult = calculatorPage.inputSequenceOfDigits(firstNumber)
                .tapPlus()
                .inputSequenceOfDigits(secondNumber)
                .tapEquals()
                .getResult();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @DataProvider(name = "plusOperationData")
    public Object[][] setPlusOperationDataProvider() {
        return new Object[][] {
                {"4", "5", "9"},
                {"20", "143", "163"},
                {"3.4", "0", "3.4"}
        };
    }

    @Test(dataProvider = "minusOperationData")
    public void shouldSubtractNumbers(String firstNumber, String secondNumber, String expectedResult) {
        String actualResult = calculatorPage.inputSequenceOfDigits(firstNumber)
                .tapMinus()
                .inputSequenceOfDigits(secondNumber)
                .tapEquals()
                .getResult();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @DataProvider(name = "minusOperationData")
    public Object[][] setMinusOperationDataProvider() {
        return new Object[][] {
                {"7.8", "0.8", "7"},
                {"1", "23", "-22"},
        };
    }

    @Test(dataProvider = "multiplyOperationData")
    public void shouldMultiplyNumbers(String firstNumber, String secondNumber, String expectedResult) {
        String actualResult = calculatorPage.inputSequenceOfDigits(firstNumber)
                .tapMultiply()
                .inputSequenceOfDigits(secondNumber)
                .tapEquals()
                .getResult();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @DataProvider(name = "multiplyOperationData")
    public Object[][] setMultiplyOperationDataProvider() {
        return new Object[][] {
                {"2", "0", "0"},
                {"-2.2", "2.3", "-5.06"},
                {"2.2", "2.3", "5.06"},
        };
    }

    @Test(dataProvider = "divideOperationData")
    public void shouldDivideNumbers(String firstNumber, String secondNumber, String expectedResult) {
        String actualResult = calculatorPage.inputSequenceOfDigits(firstNumber)
                .tapDivide()
                .inputSequenceOfDigits(secondNumber)
                .tapEquals()
                .getResult();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @DataProvider(name = "divideOperationData")
    public Object[][] setDivideOperationDataProvider() {
        return new Object[][] {
                {"11", "1", "11"},
                {"10", "2", "5"},
                {"5", "2", "2.5"},
                {"-5", "2", "-2.5"}
        };
    }

    @Test
    public void shouldAppearMessageWhenDivideByZero() {
        String actualResult = calculatorPage.inputSequenceOfDigits("10")
                .tapDivide()
                .inputSequenceOfDigits("0")
                .tapEquals()
                .getResultPreview();
        Assert.assertEquals(actualResult, "Can't divide by 0");
    }

    @AfterMethod
    public void setAfterMethod() {
        calculatorPage.tapClear();
    }

    @AfterClass
    public void setAfterClass() {
       driverProvider.quitDriver();
    }

}
