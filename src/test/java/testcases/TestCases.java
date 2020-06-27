package testcases;

import static org.testng.Assert.assertTrue;
import java.io.IOException;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import basepackage.BaseForTestExecution;
import pages.AccountPage;
	
public class TestCases extends BaseForTestExecution{
	
	@Test(priority = 1)
	public void checkThatUserCanRegisterOnTheWebsiteSuccessfully() {
		ExtentTest test = extent.createTest("Check that user can register on website successfully");
		registerPage.setFristName("Ahmed");
		registerPage.setLastName("Mah");
		registerPage.setMobileNumber("0234567348911");
		registerPage.setEmailAddress("test007@test.com");
		registerPage.setPassword("P@ssw0rd");
		registerPage.setConfirmPassword("P@ssw0rd");
		try {
		AccountPage accountPage = registerPage.clickSubmitButton();
		assertTrue(accountPage.getPageURL().contains("www.phptravels.net/account/"), "Url Not Correct"); // Validate using URL as the page gives internal server error but user registered successfully.
		test.pass("User Registered Successfully");
		/**
		 * Driver will wait till First name field to be invisible and if driver wait too much it will throw an exception 
		 * and this mean the the driver deosn't navigate to account page and test case fail.
		 */
		}catch (Exception TimeoutException ) {
			try {
				test.fail("Testcase failed user can't register").addScreenCaptureFromPath("screenshots\\checkThatUserCanRegisterOnTheWebsiteSuccessfully.png");
			} catch (IOException e) {
				e.printStackTrace();
		}	
			org.testng.Assert.fail("Testcase failed user can't register");
			
	}

}
	
	@Test(priority = 2) 
	public void checkThatEmailAddressShouldBeUniqueForEachUser() {
		ExtentTest test = extent.createTest("Check that email address should be unique for each user");
		registerPage.setFristName("Ahmed");
		registerPage.setLastName("Mahmoud");
		registerPage.setMobileNumber("01155123453123");
		registerPage.setEmailAddress("test007@test.com"); //Use Any Mail Was Registered Before.
		registerPage.setPassword("P@ssw0rd");
		registerPage.setConfirmPassword("P@ssw0rd");
		registerPage.clickSubmitButtonToValidate();
		try {
		registerPage.explicitWaitForValidationErrorMessageAppear();
		assertTrue(registerPage.getValidationErrorMessageText().contains("Email Already Exists"), 
				"inValid Error Message");
		test.pass("System validate correctly that email address shloud be unique");
		/**
		 * This exception will happen if error message not appear and this mean 
		 * the error message not implemented or driver navigate to another page (account page) and test case fail.
		 */
		}catch(Exception NoSuchElementException) {
			try {
				test.fail("Testcase failed System not validate that email user shloud unique for each user or Error Message Not appear").addScreenCaptureFromPath("screenshots\\checkThatEmailAddressShouldBeUniqueForEachUser.png");
			} catch (IOException e) {
				e.printStackTrace();
			}
		org.testng.Assert.fail("Testcase failed System not validate that email user shloud unique for each user or Error Message Not appear");
		}
	}
	
	@Test(priority = 3)
	public void checkThatSystemValidateTheEmailAddressFormatCorrectly() {
		ExtentTest test = extent.createTest("Check that system validate emaill address format correctly");
		registerPage.setFristName("DummyFirstName");
		registerPage.setLastName("DummyLastName");
		registerPage.setMobileNumber("1232112312");
		registerPage.setEmailAddress("InvalidEmaillAddress"); //insert invalid Email Format
		registerPage.setPassword("P@ssw0rd");
		registerPage.setConfirmPassword("P@ssw0rd");
		registerPage.clickSubmitButtonToValidate();
		try {
		registerPage.explicitWaitForValidationErrorMessageAppear();
		assertTrue(registerPage.getValidationErrorMessageText().contains("The Email field must "
				+ "contain a valid email address."), "inValid Error Message");
		test.pass("System validate correctly that email address format");
		/**
		 * This exception will happen if error message not appear and this mean 
		 * the error message not implemented or driver navigate to another page (account page) and test case fail.
		 */
		}catch(Exception NoSuchElementException) {
			try {
				test.fail("Testcase failed System not validate that email address format or error message not appear").addScreenCaptureFromPath("screenshots\\checkThatSystemValidateTheEmailAddressFormatCorrectly.png");
			} catch (IOException e) {
				e.printStackTrace();
			}
		org.testng.Assert.fail("Testcase failed System not validate that email address format or error message not appear");
		}
		
	}
	
	@Test(priority = 4)
	public void checkThatPasswordAndConfirmPasswordFieldsAreEqual() {
		ExtentTest test = extent.createTest("Check that Password and Confrim password fields should be equal");
		registerPage.setFristName("DummyFirstName");
		registerPage.setLastName("DummyLastName");
		registerPage.setMobileNumber("21321334532");
		int randomNumber = random.nextInt(200);
		registerPage.setEmailAddress("dummy"+randomNumber+"@test.com");
		registerPage.setPassword("P@ssw0rd");
		registerPage.setConfirmPassword("Passw@rdaaa"); // use password not match password field
		registerPage.clickSubmitButtonToValidate();
		try {
		registerPage.explicitWaitForValidationErrorMessageAppear();
		assertTrue(registerPage.getValidationErrorMessageText().contains("Password not matching "
				+ "with confirm password."),"inValid Error Message");
		test.pass("System validate correctly that password and confrim password fields must be equal");
		/**
		 * This exception will happen if error message not appear and this mean 
		 * the error message not implemented or driver navigate to another page (account page) and test case fail.
		 */
		}catch(Exception NoSuchElementException){
			try {
				test.fail("Testcase failed System not validate that password and confirm password must be equal or error message not appear").addScreenCaptureFromPath("screenshots\\checkThatPasswordAndConfirmPasswordFieldsAreEqual.png");
			} catch (IOException e) {
				e.printStackTrace();
			}
		org.testng.Assert.fail("Testcase failed System not validate that password and confirm password must be equal or error message not appear");
		}
	}
	
	@Test(priority = 5)
	public void checkThatFirstNameMustStartWithCapitalLetter(){
		ExtentTest test = extent.createTest("Check that first name must start with capital letter");
		registerPage.setFristName("smallLetterFirstName"); // insert first name begin with small letter
		registerPage.setLastName("LastName");
		registerPage.setMobileNumber("213213123124");
		int randomNumber = random.nextInt(200);
		registerPage.setEmailAddress("test"+randomNumber+"@testing.com");
		registerPage.setPassword("P@ssw0rd");
		registerPage.setConfirmPassword("P@ssw0rd");
		registerPage.clickSubmitButtonToValidate();
		try {
			registerPage.explicitWaitForValidationErrorMessageAppear();
			assertTrue(registerPage.getValidationErrorMessageText().contains("The First Name Field "
					+ "must start with capital letter "),"inValid Error Message");    // I assumed the error message title for this test case.  
			test.pass("System validate correctly that First Name field should start with capital letter");
			/**
			 * This exception will happen if error message not appear and this mean 
			 * the error message not implemented or driver navigate to another page (account page) and test case fail
			 */
		}catch (Exception NoSuchElementException) {
			try {
				test.fail("System not validate that first name must start with capital letter or error message not appear").addScreenCaptureFromPath("screenshots\\checkThatFirstNameMustStartWithCapitalLetter.png");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		org.testng.Assert.fail("System not validate that first name must start with capital letter or error message not appear");
		
		}
		
	}
	
	@Test(priority = 6)
	public void checkThatLastNameMustStartWithCapitalLetter() {
		ExtentTest test = extent.createTest("Check that Last name must start with capital letter");
		registerPage.setFristName("FirstName"); 
		registerPage.setLastName("smallLetterLastName"); // insert last name begin with small letter
		registerPage.setMobileNumber("213213123124");
		int randomNumber = random.nextInt(200);
		registerPage.setEmailAddress("DummyTest"+randomNumber+"@testing.com");
		registerPage.setPassword("P@ssw0rd");
		registerPage.setConfirmPassword("P@ssw0rd");
		registerPage.clickSubmitButtonToValidate();
		try {
			registerPage.explicitWaitForValidationErrorMessageAppear();
			assertTrue(registerPage.getValidationErrorMessageText().contains("The Last Name Field "
					+ "must start with capital letter "),"inValid Error Message");     // I assumed the error message title for this test case.  
			test.pass("System validate correctly that Last Name field should start with capital letter");
			/**
			 * This exception will happen if error message not appear and this mean 
			 * the error message not implemented or driver navigate to another page (account page) and test case fail
			 */
		}catch (Exception NoSuchElementException) {
			try {
				test.fail("System not validate that last name must start with capital letter or error message not appear").addScreenCaptureFromPath("screenshots\\checkThatLastNameMustStartWithCapitalLetter.png");
			} catch (IOException e) {
				e.printStackTrace();
			}
		org.testng.Assert.fail("System not validate that last name must start with capital letter or error message not appear");
				
		}
	}
	
	@Test(priority = 7)
	public void checkThatFirstNameNotEqualLastName() {
		ExtentTest test = extent.createTest("Check that first name not equal last name");
		registerPage.setFristName("SameName");
		registerPage.setLastName("SameName"); // insert the last name same as the first name.
		registerPage.setMobileNumber("12213425346");
		int randomNumber = random.nextInt(200);
		registerPage.setEmailAddress("Tests"+randomNumber+"@tester.com");
		registerPage.setPassword("P@ssw0rd");
		registerPage.setConfirmPassword("P@ssw0rd");
		registerPage.clickSubmitButtonToValidate();
		try {
			registerPage.explicitWaitForValidationErrorMessageAppear();
			assertTrue(registerPage.getValidationErrorMessageText().contains("The Last name field "
					+ "should not equal First name field"),"inValid Error Message"); // I assumed the error message title for this test case.
			test.pass("System validate correctly that Last Name field should not equal first name");
			/**
			 * This exception will happen if error message not appear and this mean 
			 * the error message not implemented or driver navigate to another page (account page) and test case fail
			 */
		}catch (Exception NoSuchElementException) {
			try {
				test.fail("System not validate that last name must not equal first name or error message not appear").addScreenCaptureFromPath("screenshots\\checkThatFirstNameNotEqualLastName.png");
			} catch (IOException e) {
				e.printStackTrace();
			}

		org.testng.Assert.fail("System not validate that last name must not equal first name or error message not appear");
				
		}
	}
	
	@Test(priority = 8)
	public void checkThatSystemValidateMobileNumberCorrectly() {
		//As I don't know the business i assumed for this field to accept numbers only.
		ExtentTest test = extent.createTest("Check that system validate mobile number correctly	");
		registerPage.setFristName("FirstName");
		registerPage.setLastName("LastName");
		registerPage.setMobileNumber("aa765aaaaaa"); // insert mobile number contains characters or special characters
		int randomNumber = random.nextInt(200);
		registerPage.setEmailAddress("test23"+randomNumber+"@test.com");
		registerPage.setPassword("P@ssw0rd");
		registerPage.setConfirmPassword("P@ssw0rd");
		registerPage.clickSubmitButtonToValidate();
		try {
			registerPage.explicitWaitForValidationErrorMessageAppear();
			assertTrue(registerPage.getValidationErrorMessageText().contains("The Mobile number field "
					+ "must contain a valid mobile number"),"inValid Error Message");   // I assumed the error message title for this test case.
			test.pass("System validate correctly mobile number");
			/**
			 * This exception will happen if error message not appear and this mean 
			 * the error message not implemented or driver navigate to another page (account page) and test case fail
			 */
		}catch (Exception NoSuchElementException) {
			try {
				test.fail("System not validate mobile number or error message not appear").addScreenCaptureFromPath("screenshots\\checkThatSystemValidateMobileNumberCorrectly.png");
			} catch (IOException e) {
				e.printStackTrace();
			}	
		org.testng.Assert.fail("System not validate mobile number or error message not appear");
				
		}
	}
	
	@Test(priority = 9)
	public void checkThatSystemValidateMobileNumberNotLessThanElevenNumbers() {
		//As I don't know the business i assumed for this field to be not less than 11 numbers.
		ExtentTest test = extent.createTest("Check that system validate mobile number not less than 11 numbers");
		registerPage.setFristName("FirstName");
		registerPage.setLastName("LastName");
		registerPage.setMobileNumber("0"); // insert mobile number less Than 11 numbers
		int randomNumber = random.nextInt(200);
		registerPage.setEmailAddress("test"+randomNumber+"@test.com");
		registerPage.setPassword("P@ssw0rd");
		registerPage.setConfirmPassword("P@ssw0rd");
		registerPage.clickSubmitButtonToValidate();
		try {
			registerPage.explicitWaitForValidationErrorMessageAppear();
			assertTrue(registerPage.getValidationErrorMessageText().contains("The Mobile number field "
					+ "must contain a valid mobile number"),"inValid Error Message"); // I assumed the error message title for this test case.
			test.pass("System validate correctly mobile number not less than 11 numbers");
			/**
			 * This exception will happen if error message not appear and this mean 
			 * the error message not implemented or driver navigate to another page (account page) and test case fail
			 */
		}catch (Exception NoSuchElementException) {
			try {
				test.fail("System not validate mobile number should not less than 11 numbers or error message not appear").addScreenCaptureFromPath("screenshots\\checkThatSystemValidateMobileNumberNotLessThanElevenNumbers.png");
			} catch (IOException e) {
				e.printStackTrace();
			}
		org.testng.Assert.fail("System not validate mobile number should not less than 11 numbers or error message not appear");
				
		}
	}
	
	
	@Test(priority = 10)
	public void checkThatPasswordFieldShouldHaveCapitalLetter() {
		ExtentTest test = extent.createTest("Check that password field shloud have capital letter");
		registerPage.setFristName("FirstName");
		registerPage.setLastName("LastName");
		registerPage.setMobileNumber("2132131231221");
		int randomNumber = random.nextInt(200);
		registerPage.setEmailAddress("testxzc"+randomNumber+"@test.com");
		registerPage.setPassword("p@ssw0rd"); // Insert Password without any capital letter
		registerPage.setConfirmPassword("p@ssw0rd");
		registerPage.clickSubmitButtonToValidate();
		try {
			registerPage.explicitWaitForValidationErrorMessageAppear();
			assertTrue(registerPage.getValidationErrorMessageText().contains("The Password  field "
					+ "must contain at least one capital letter"),"inValid Error Message"); // I assumed the error message title for this test case.
			test.pass("System validate correctly that password field should have at least one capital letter");
			/**
			 * This exception will happen if error message not appear and this mean 
			 * the error message not implemented or driver navigate to another page (account page) and test case fail
			 */
		}catch (Exception NoSuchElementException) {
			try {
				test.fail("System not validate that password field should have at least one capital letter or error message not appear").addScreenCaptureFromPath("screenshots\\checkThatPasswordFieldShouldHaveCapitalLetter.png");
			} catch (IOException e) {
				e.printStackTrace();
			}
		org.testng.Assert.fail("System not validate that password field should have at least one capital letter or error message not appear");
				
		}
	}
	
	@Test (priority = 11)
	public void checkThatPasswordFieldShouldHaveSmalLetter() {
		ExtentTest test = extent.createTest("Check that password field shloud have small letter");
		registerPage.setFristName("Ahmed");
		registerPage.setLastName("Mahmoud");
		registerPage.setMobileNumber("34233242243");
		int randomNumber = random.nextInt(200);
		registerPage.setEmailAddress("test5x5cw"+randomNumber+"@test.com");
		registerPage.setPassword("P@SSW0RD"); // Insert Password without any small letter
		registerPage.setConfirmPassword("P@SSW0RD");
		registerPage.clickSubmitButtonToValidate();
		try {
			registerPage.explicitWaitForValidationErrorMessageAppear();
			assertTrue(registerPage.getValidationErrorMessageText().contains("The Password  field "
					+ "must contain at least one small letter"),"inValid Error Message"); // I assumed the error message title for this test case.
			test.pass("System validate correctly that password field should have at least one small letter");
			/**
			 * This exception will happen if error message not appear and this mean 
			 * the error message not implemented or driver navigate to another page (account page) and test case fail
			 */
		}catch (Exception NoSuchElementException) {
			try {
				test.fail("System not validate that password field should have at least one small letter or error message not appear").addScreenCaptureFromPath("screenshots\\checkThatPasswordFieldShouldHaveSmalLetter.png");
			} catch (IOException e) {
				e.printStackTrace();
			}
		org.testng.Assert.fail("System not validate that password field should have at least one small letter or error message not appear");
				
		}
	}
	
	@Test(priority = 12)
	public void checkThatPasswordFieldMustNotExceedEightCharacters() {
		ExtentTest test = extent.createTest("Check that password field must not exceed 8 characters");
		registerPage.setFristName("Ahmed");
		registerPage.setLastName("LastName");
		registerPage.setMobileNumber("123456789123");
		int randomNumber = random.nextInt(200);
		registerPage.setEmailAddress("testmhtrdx"+randomNumber+"@teste.com");
		registerPage.setPassword("P@ssw0rdddddd");
		registerPage.setConfirmPassword("P@ssw0rdddddd");
		registerPage.clickSubmitButtonToValidate();
		try {
			registerPage.explicitWaitForValidationErrorMessageAppear();
			assertTrue(registerPage.getValidationErrorMessageText().contains("The Password  field "
					+ "should not be more than 8 characters"),"inValid Error Message"); // I assumed the error message title for this test case.
			test.pass("System validate correctly that password field should not exceed 11 characters");
			/**
			 * This exception will happen if error message not appear and this mean 
			 * the error message not implemented or driver navigate to another page (account page) and test case fail
			 */
		}catch (Exception NoSuchElementException) {
			try {
				test.fail("System not validate that password field should not exceed 11 characters or error message not appear").addScreenCaptureFromPath("screenshots\\checkThatPasswordFieldMustNotExceedEightCharacters.png");
			} catch (IOException e) {
				e.printStackTrace();
			}
		org.testng.Assert.fail("System not validate that password field should not exceed 11 characters or error message not appear");
				
		}
		
	}
	
	@Test(priority = 13)
	public void checkThatPasswordFieldMustNotLessThanSixCharacters() {
		ExtentTest test = extent.createTest("Check that password field must not less than 6 characters");
		registerPage.setFristName("Ahmed");
		registerPage.setLastName("LastName");
		registerPage.setMobileNumber("123456789123");
		int randomNumber = random.nextInt(200);
		registerPage.setEmailAddress("testmhtrdx"+randomNumber+"@teste.com");
		registerPage.setPassword("P@s1");
		registerPage.setConfirmPassword("P@s1");
		registerPage.clickSubmitButtonToValidate();
		try {
			registerPage.explicitWaitForValidationErrorMessageAppear();
			assertTrue(registerPage.getValidationErrorMessageText().contains("The Password field must be "
					+ "at least 6 characters in length"),"inValid Error Message");
			test.pass("System validate correctly that password field should not be less than 11 characters");
			/**
			 * This exception will happen if error message not appear and this mean 
			 * the error message not implemented or driver navigate to another page (account page) and test case fail
			 */
		}catch (Exception NoSuchElementException) {
			try {
				test.fail("System not validate that password field should not be less than 11 characters or error message not appear").addScreenCaptureFromPath("screenshots\\checkThatPasswordFieldMustNotLessThanSixCharacters.png");
			} catch (IOException e) {
				e.printStackTrace();
			}
		org.testng.Assert.fail("System not validate that password field should not be less than 11 characters or error message not appear");
				
		}
		
	}
	
	@Test(priority = 14)
	public void checkThatFirstNameIsMandatoryField() {
		ExtentTest test = extent.createTest("Check that First name field is a mandatory field");
		registerPage.setLastName("LastName");
		registerPage.setMobileNumber("12345678902");
		int randomNumber = random.nextInt(200);
		registerPage.setEmailAddress("testmhtrdxeffv"+randomNumber+"@teste.com");
		registerPage.setPassword("P@ssw0rd");
		registerPage.setConfirmPassword("P@ssw0rd");
		registerPage.clickSubmitButtonToValidate();
		boolean isRequired = registerPage.CheckHTML5ValidationForFirstNameField(); // This function is used to check if HTML5 validation Implemented 
		if(isRequired) {
			assertTrue(Boolean.parseBoolean(registerPage.returnFirstNameElement().getAttribute("required")),
					"First  Name is a mandatory field");
			test.pass("System validate correctly that first name field  is a mandatory field");
		}
		/**
		 * I assumed if validation done through error message like before not from HTML5 Validation
		 */
		else {
			try {
				registerPage.explicitWaitForValidationErrorMessageAppear();
				assertTrue(registerPage.getValidationErrorMessageText().contains("First Name is a Mandatory field"),"inValid Error Message"); // I assumed the error message title for this test case.
				test.pass("System validate correctly that first name field  is a mandatory field");
				/**
				 * This exception will happen if error message not appear and this mean 
				 * the error message not implemented or driver navigate to another page (account page) and test case fail
				 */
			}catch (Exception NoSuchElementException) {
				try {
					test.fail("System not validate that first name field is a mandatory field or error message not appear").addScreenCaptureFromPath("screenshots\\checkThatFirstNameIsMandatoryField.png");
				} catch (IOException e) {
					e.printStackTrace();
				}
			org.testng.Assert.fail("System not validate that first name field is a mandatory field or error message not appear");
					
			}
		}
	
	}
	
	@Test(priority = 15)
	public void checkThatLastNameIsMandatoryField() {
		ExtentTest test = extent.createTest("Check that Last name field is a mandatory field");
		registerPage.setFristName("FirtName Ahmed");
		registerPage.setMobileNumber("12345678912");
		int randomNumber = random.nextInt(200);
		registerPage.setEmailAddress("testmhtrdxef7xfv"+randomNumber+"@teste.com");
		registerPage.setPassword("P@ssw0rd");
		registerPage.setConfirmPassword("P@ssw0rd");
		registerPage.clickSubmitButtonToValidate();
		boolean isRequired = registerPage.CheckHTML5ValidationForLastNameField(); // This function is used to check if HTML5 validation Implemented
		if(isRequired) {
			assertTrue(Boolean.parseBoolean(registerPage.returnLastNameElement().getAttribute("required")),
					"Last  Name is a mandatory field");
			test.pass("System validate correctly that last name field  is a mandatory field");
			
		}
		/**
		 * I assumed if validation done through error message like before not from HTML5 Validation
		 */
		else {
			try {
				registerPage.explicitWaitForValidationErrorMessageAppear();
				assertTrue(registerPage.getValidationErrorMessageText().contains("Last Name is a Mandatory field"),"inValid Error Message"); // I assumed the error message title for this test case.
				test.pass("System validate correctly that last name field  is a mandatory field");
				/**
				 * This exception will happen if error message not appear and this mean 
				 * the error message not implemented or driver navigate to another page (account page) and test case fail
				 */
			}catch (Exception NoSuchElementException) {
				try {
					test.fail("System not validate that last name field is a mandatory field or error message not appear").addScreenCaptureFromPath("screenshots\\checkThatLastNameIsMandatoryField.png");
				} catch (IOException e) {
					e.printStackTrace();
				}
			org.testng.Assert.fail("System not validate that last name field is a mandatory field or error message not appear");
					
			}
		
		}
		
	}
	
	@Test(priority = 16)
	public void checkThatMobileNumberIsMandtoryField() {
		ExtentTest test = extent.createTest("Check that mobile number field is a mandatory field");
		registerPage.setFristName("Ahmed");
		registerPage.setLastName("Mahmouddd");
		int randomNumber = random.nextInt(200);
		registerPage.setEmailAddress("tetmhtrdxef7xfv"+randomNumber+"@teste.com");
		registerPage.setPassword("P@ssw0rd");
		registerPage.setConfirmPassword("P@ssw0rd");
		registerPage.clickSubmitButtonToValidate();
		boolean isRequired = registerPage.CheckHTML5ValidationForMobileNumberField(); // This function is used to check if HTML5 validation Implemented
		if(isRequired) {
			assertTrue(Boolean.parseBoolean(registerPage.returnMobileNumberElement().getAttribute("required")),
					"Mobile Number is a mandatory field");
			test.pass("System validate correctly that mobile number field  is a mandatory field");
		}
		/**
		 * I assumed if validation done through error message like before not from HTML5 Validation
		 */
		else {
			try {
				registerPage.explicitWaitForValidationErrorMessageAppear();
				assertTrue(registerPage.getValidationErrorMessageText().contains("Mobile Number is a Mandatory field"),"inValid Error Message"); // I assumed the error message title for this test case.
				test.pass("System validate correctly that last name field  is a mandatory field");
				/**
				 * This exception will happen if error message not appear and this mean 
				 * the error message not implemented or driver navigate to another page (account page) and test case fail
				 */
			}catch (Exception NoSuchElementException) {
				try {
					test.fail("System not validate that mobile number field is a mandatory field or error message not appear").addScreenCaptureFromPath("screenshots\\checkThatMobileNumberIsMandtoryField.png");
				} catch (IOException e) {
					e.printStackTrace();
				}
			org.testng.Assert.fail("System not validate that mobile number field is a mandatory field or error message not appear");
					
			}
		
		}
		
	}
	
	@Test(priority = 17)
	public void checkThatEmailIsMandatoryField() {
		ExtentTest test = extent.createTest("Check that Email field is a mandatory field");
		registerPage.setFristName("AAhmed");
		registerPage.setLastName("LasstName");
		registerPage.setMobileNumber("12345678998");
		registerPage.setPassword("P@ssw0rd");
		registerPage.setConfirmPassword("P@ssw0rd");
		registerPage.clickSubmitButtonToValidate();
		boolean isRequired = registerPage.CheckHTML5ValidationForEmailField(); // This function is used to check if HTML5 validation Implemented
		if(isRequired) {
			assertTrue(Boolean.parseBoolean(registerPage.returnEmailAddressElement().getAttribute("required")),
					"Email is a mandatory field");
			test.pass("System validate correctly that Email field should is a mandatory field");
		}
		/**
		 * I assumed if validation done through error message like before not from HTML5 Validation
		 */
		else {
			try {
				registerPage.explicitWaitForValidationErrorMessageAppear();
				assertTrue(registerPage.getValidationErrorMessageText().contains("Email is a Mandatory field"),"inValid Error Message"); // I assumed the error message title for this test case.
				test.pass("System validate correctly that Email field  is a mandatory field");
				/**
				 * This exception will happen if error message not appear and this mean 
				 * the error message not implemented or driver navigate to another page (account page) and test case fail
				 */
			}catch (Exception NoSuchElementException) {
				try {
					test.fail("System not validate that Email field is a mandatory field or error message not appear").addScreenCaptureFromPath("screenshots\\checkThatEmailIsMandatoryField.png");
				} catch (IOException e) {
					e.printStackTrace();
				}
			org.testng.Assert.fail("System not validate that Email field is a mandatory field or error message not appear");
					
			}
		}
	}
	
	@Test(priority = 18)
	public void checkThatPasswordIsMandatoryField() {
		ExtentTest test = extent.createTest("Check that Password field is a mandatory field");
		registerPage.setFristName("AAhmed");
		registerPage.setLastName("LasstName");
		registerPage.setMobileNumber("12345678998");
		int randomNumber = random.nextInt(200);
		registerPage.setEmailAddress("tetmhef7xfv"+randomNumber+"@teste.com");
		registerPage.setConfirmPassword("P@ssw0rd");
		registerPage.clickSubmitButtonToValidate();
		boolean isRequired = registerPage.CheckHTML5ValidationForPasswordField(); // This function is used to check if HTML5 validation Implemented
		if(isRequired) {
			assertTrue(Boolean.parseBoolean(registerPage.returnPasswordElement().getAttribute("required")),
					"Password is a mandatory field");
			test.pass("System validate correctly that password field  is a mandatory field");
		}
		/**
		 * I assumed if validation done through error message like before not from HTML5 Validation
		 */
		else {
			try {
				registerPage.explicitWaitForValidationErrorMessageAppear();
				assertTrue(registerPage.getValidationErrorMessageText().contains("Password is a Mandatory field"),"inValid Error Message"); // I assumed the error message title for this test case.
				test.pass("System validate correctly that password field  is a mandatory field");
				/**
				 * This exception will happen if error message not appear and this mean 
				 * the error message not implemented or driver navigate to another page (account page) and test case fail
				 */
			}catch (Exception NoSuchElementException) {
				try {
					test.fail("System not validate that Password field is a mandatory field or error message not appear").addScreenCaptureFromPath("screenshots\\checkThatPasswordIsMandatoryField.png");
				} catch (IOException e) {
					e.printStackTrace();
				}
			org.testng.Assert.fail("System not validate that Password field is a mandatory field or error message not appear");
					
			}
		}
	}
	
	@Test (priority = 19) 
	public void checkThatConfirmPasswordIsMandatoryField() throws Exception {
		ExtentTest test = extent.createTest("Check that  Confirm Password is a mandatory field");
		registerPage.setFristName("AAhmed");
		registerPage.setLastName("LasstName");
		registerPage.setMobileNumber("12345678998");
		int randomNumber = random.nextInt(200);
		registerPage.setEmailAddress("tethef7xfv"+randomNumber+"@teste.com");
		registerPage.setPassword("P@ssw0rd");
		registerPage.clickSubmitButtonToValidate();
		boolean isRequired = registerPage.CheckHTML5ValidationForConfirmPasswordField(); // This function is used to check if HTML5 validation Implemented
		if(isRequired) {
			assertTrue(Boolean.parseBoolean(registerPage.returnConfirmPasswordElement().getAttribute("required")),
					"Confrim Password is a mandatory field");
			test.pass("System validate correctly that confrim password field  is a mandatory field");
		}
		/**
		 * I assumed if validation done through error message like before not from HTML5 Validation
		 */
		else {
			try {
				registerPage.explicitWaitForValidationErrorMessageAppear();
				assertTrue(registerPage.getValidationErrorMessageText().contains("Password is a Mandatory field"),"inValid Error Message"); // I assumed the error message title for this test case.
				test.pass("System validate correctly that confrim password field  is a mandatory field");
				/**
				 * This exception will happen if error message not appear and this mean 
				 * the error message not implemented or driver navigate to another page (account page) and test case fail
				 */
			}catch (Exception NoSuchElementException) {
				try {
				test.fail("System not validate that Confrim Password field is a mandatory field or error message not appear").addScreenCaptureFromPath("screenshots\\checkThatConfirmPasswordIsMandatoryField.png");
				}catch(IOException e) {
					e.printStackTrace();
				}
				org.testng.Assert.fail("System not validate that Confirm Password field is a mandatory field or error message not appear");
					
			}
		}
	}
}
	
