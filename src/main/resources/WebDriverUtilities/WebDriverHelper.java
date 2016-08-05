package WebDriverUtilities;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class WebDriverHelper {

	public WebDriverHelper() {
		// TODO Auto-generated constructor stub
	}
	
	public WebDriverHelper(WebDriver driver)
	{
		this.browserDriver = driver;
	}
	
	
	private WebDriver browserDriver;

	public WebDriver getBrowserDriver() {
		return browserDriver;
	}

	public void setBrowserDriver(WebDriver browserDriver) {
		this.browserDriver = browserDriver;
	}
	
	/**
	 * I got tired of typing out the same code to make the mouse move and hover. 
	 * @param targetElement The element that will be hovered over
	 * @param hoverInterval the length of time, in milliseconds, to hover
	 */
	public void mouseHover(WebElement targetElement, long hoverInterval) {
		Dimension elementSize = targetElement.getSize();
		int middleX = (elementSize.getWidth() / 2);
		int middleY = (elementSize.getHeight() / 2);
		Actions mouseActions = new Actions(browserDriver);
		mouseActions.moveToElement(targetElement,middleX,middleY);
		mouseActions.perform();
		try {
			synchronized (browserDriver) {
				browserDriver.wait(hoverInterval);
			}
		} catch (Exception E) {

		}
	}
	
	public void Wait(long interval)
	{
		try {
			browserDriver.wait(interval);
		} catch (Exception e) {
		}
	}


}
