package pages;

import org.openqa.selenium.WebDriver;

public class AccountPage {

	private WebDriver driver;
	
	public AccountPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getPageURL() {
		return driver.getCurrentUrl();
	}
}
