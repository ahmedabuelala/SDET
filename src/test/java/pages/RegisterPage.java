package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

	private WebDriver driver;
	private By firstName = By.name("firstname");
	private By lastName = By.name("lastname");
	private By mobileNumber = By.name("phone");
	private By email = By.name("email");
	private By password = By.name("password");
	private By confirmPassword = By.name("confirmpassword");
	private By submitButton = By.xpath("/html[1]/body[1]/div[2]/div[1]/section[1]/div[1]"
			+ "/div[1]/div[2]/div[1]/form[1]/div[8]/button[1]");
	private By validationErrorMessage = By.xpath("/html[1]/body[1]/div[2]/div[1]/section[1]"
			+ "/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]");
	

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
	}

	public void setFristName(String text) {
		driver.findElement(firstName).sendKeys(text);
	}

	public void setLastName(String text) {
		driver.findElement(lastName).sendKeys(text);
	}

	public void setMobileNumber(String text) {
		driver.findElement(mobileNumber).sendKeys(text);
	}

	public void setEmailAddress(String text) {
		driver.findElement(email).sendKeys(text);
	}

	public void setPassword(String text) {
		driver.findElement(password).sendKeys(text);
	}

	public void setConfirmPassword(String text) {
		driver.findElement(confirmPassword).sendKeys(text);
	}

	public AccountPage clickSubmitButton() {
		/**
		 * Used Actions Class if Element not getting clicked due to JavaScript calls present.
		 * and it works the intercepted clicked Exception disappear but the button still not clicked. 
		 */
		/*WebElement button = driver.findElement(submitButton);
		Actions actions = new Actions(driver);
		actions.moveToElement(button).click().build().perform();*/ 
		
		/**
		 * Element was not in viewport so used JS Executer to scroll down to element
		 * and then click on it and it works correctly.
		 */
		WebElement button = driver.findElement(submitButton);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(0, 100)"); //element is at bottom.
		button.click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(firstName));
		return new AccountPage(driver);		
	}
	
	/**
	 * This Method to tell the driver wait until validation error message appear.
	 */
	public void explicitWaitForValidationErrorMessageAppear() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(validationErrorMessage));
	}
	
	/**
	 * 
	 * @return validation error message text for each field
	 */
	public String getValidationErrorMessageText() {
		return driver.findElement(validationErrorMessage).getText();
	}
	
	
	/*
	 * This Method only Used when you test validation error message for each input text field in order
	 * to click the button without any wait for first name element to be invisible. 
	 */
	public void clickSubmitButtonToValidate() {
		WebElement button = driver.findElement(submitButton);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(0, 100)");
		button.click();
	}
	
	public WebElement  returnFirstNameElement() {
		return driver.findElement(firstName);
	}
	
	public WebElement  returnLastNameElement() {
		return driver.findElement(lastName);
	}
	
	public WebElement  returnMobileNumberElement() {
		return driver.findElement(mobileNumber);
	}
	
	public WebElement  returnEmailAddressElement() {
		return driver.findElement(email);
	}
	
	public WebElement  returnPasswordElement() {
		return driver.findElement(password);
	}
	
	public WebElement  returnConfirmPasswordElement() {
		return driver.findElement(confirmPassword);
	}
	
	public boolean CheckHTML5ValidationForFirstNameField(){
		WebElement firstNameInputElement = driver.findElement(firstName);
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		boolean isRequired = (Boolean) js.executeScript("return arguments[0].required;",firstNameInputElement);
		if(isRequired )
		{
		   return true;
		}
		
		return false;
	}
	
	public boolean CheckHTML5ValidationForLastNameField(){
		WebElement lastNameInputElement = driver.findElement(lastName);
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		boolean isRequired = (Boolean) js.executeScript("return arguments[0].required;",lastNameInputElement);
		if(isRequired )
		{
		   return true;
		}
		
		return false;
	}
	
	public boolean CheckHTML5ValidationForMobileNumberField(){
		WebElement mobileNumberInputElement = driver.findElement(mobileNumber);
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		boolean isRequired = (Boolean) js.executeScript("return arguments[0].required;",mobileNumberInputElement);
		if(isRequired )
		{
		   return true;
		}
		
		return false;
	}
	
	public boolean CheckHTML5ValidationForEmailField(){
		WebElement emailInputElement = driver.findElement(email);
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		boolean isRequired = (Boolean) js.executeScript("return arguments[0].required;",emailInputElement);
		if(isRequired )
		{
		   return true;
		}
		
		return false;
	}
	
	public boolean CheckHTML5ValidationForPasswordField(){
		WebElement passwordInputElement = driver.findElement(password);
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		boolean isRequired = (Boolean) js.executeScript("return arguments[0].required;",passwordInputElement);
		if(isRequired )
		{
		   return true;
		}
		
		return false;
	}
	
	public boolean CheckHTML5ValidationForConfirmPasswordField(){
		WebElement confirmPasswordInputElement = driver.findElement(confirmPassword);
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		boolean isRequired = (Boolean) js.executeScript("return arguments[0].required;",confirmPasswordInputElement);
		if(isRequired )
		{
		   return true;
		}
		
		return false;
	}



}
