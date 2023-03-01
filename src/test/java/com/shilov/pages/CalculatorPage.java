package com.shilov.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class CalculatorPage extends BasePage {

    private static final By PLUS_BUTTON_LOCATOR = AppiumBy.accessibilityId("plus");

    private static final By MINUS_BUTTON_LOCATOR = AppiumBy.accessibilityId("minus");

    private static final By MULTIPLY_BUTTON_LOCATOR = AppiumBy.accessibilityId("multiply");

    private static final By DIVIDE_BUTTON_LOCATOR = AppiumBy.accessibilityId("divide");

    private static final By EQUALS_BUTTON_LOCATOR = AppiumBy.accessibilityId("equals");

    private static final By RESULT_ELEMENT_LOCATOR = AppiumBy.id("com.google.android.calculator:id/result_final");

    private static final By POINT_BUTTON_LOCATOR = AppiumBy.accessibilityId("point");

    private static final By CLEAR_BUTTON_LOCATOR = AppiumBy.accessibilityId("clear");

    private static final By RESULT_PREVIEW_LOCATOR =
            AppiumBy.id("com.google.android.calculator:id/result_preview");

    private static final String NUMBER_REGEX = "[0-9]";

    public CalculatorPage(AppiumDriver driver) {
        super(driver);
    }

    public CalculatorPage tapPlus() {
        waitForElementVisibility(PLUS_BUTTON_LOCATOR).click();
        return this;
    }

    public CalculatorPage tapEquals() {
        waitForElementVisibility(EQUALS_BUTTON_LOCATOR).click();
        return this;
    }

    public CalculatorPage tapMinus() {
        waitForElementVisibility(MINUS_BUTTON_LOCATOR).click();
        return this;
    }

    public CalculatorPage tapMultiply() {
        waitForElementVisibility(MULTIPLY_BUTTON_LOCATOR).click();
        return this;
    }

    public CalculatorPage tapDivide() {
        waitForElementVisibility(DIVIDE_BUTTON_LOCATOR).click();
        return this;
    }

    public CalculatorPage tapClear() {
        System.out.println("nothing");
        waitForElementVisibility(CLEAR_BUTTON_LOCATOR).click();
        return this;
    }

    private void tapPoint() {
        waitForElementVisibility(POINT_BUTTON_LOCATOR).click();
    }

    private void tapDigit(String digit) {
        waitForElementVisibility(AppiumBy.accessibilityId(digit)).click();
    }

    public CalculatorPage inputSequenceOfDigits(String input) {
        String[] inputArray = input.chars().mapToObj(c -> String.valueOf((char) c)).toArray(String[]::new);
        for (String symbol : inputArray) {
            if (symbol.equals(".")) {
                tapPoint();
            } else if (symbol.matches("-")) {
                tapMinus();
            } else if (symbol.matches(NUMBER_REGEX)) {
                tapDigit(symbol);
            } else throw new IllegalArgumentException();
        }
        return this;
    }

    public String getResult() {
        return correctReadMinusFromApp(waitForElementVisibility(RESULT_ELEMENT_LOCATOR).getText());
    }

    private String correctReadMinusFromApp(String result) {
        return String.valueOf(result.charAt(0)).matches(NUMBER_REGEX)
                ? result : new StringBuilder(result).replace(0, 1, "-").toString();
    }

    public String getResultPreview() {
        return waitForElementVisibility(RESULT_PREVIEW_LOCATOR).getText();
    }
}
