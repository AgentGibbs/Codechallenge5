package skiUtahPageClasses;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import WebDriverUtilities.WebDriverHelper;

public abstract class SkiUtahWebPage {
	private String pageName;
	private String pageURL;
	private String expectedURL;
	protected WebDriver browserDriver;
	public List<WebElement> mainMenuWebElements;
	public List<WebElement> subMenuWebElements;
	public WebDriverHelper helper;


	public SkiUtahWebPage()
	{
		helper = new WebDriverHelper();
	}
	
	public SkiUtahWebPage(WebDriver driver)
	{
		helper = new WebDriverHelper();
		setDriver(driver);
	}
	
	public List<WebElement> pageElements;

	public String getExpectedURL() {
		return expectedURL;
	}

	public void setExpectedURL(String expectedURL) {
		this.expectedURL = expectedURL;
	}

	public String getPageTitle() {
		return browserDriver.getTitle();
	}

	

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getPageURL() {
		return pageURL;
	}

	public void setPageURL(String pageURL) {
		this.pageURL = pageURL;
	}

	public void clickElement(WebElement clickableControl) {

	}

	public void navigateToDefault()
	{
		browserDriver.get(expectedURL);
		helper.Wait(5000);
	}
	public WebDriver getDriver() {
		return browserDriver;
	}

	public void setDriver(WebDriver driver) {
		this.browserDriver = driver;
	}
	
	public void SelfDestruct()
	{
		this.browserDriver.quit();
	}
}
