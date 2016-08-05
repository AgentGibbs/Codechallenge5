package skiUtahPageClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import WebDriverUtilities.WebDriverHelper;

//import WebDriverHelper;

public class SkiUtahHomePage extends SkiUtahWebPage {
	// member variables
	private WebDriver browserDriver;

	public List<WebElement> mainMenuWebElements;
	public List<WebElement> subMenuWebElements;
	private WebElement compareResortsLink;
	private WebDriverHelper helper;

	// constructors

	public SkiUtahHomePage(WebDriver driver) {
		setPageURL("https://www.skiutah.com/");
		this.browserDriver = driver;
		browserDriver.navigate().to(getPageURL());
		this.helper = new WebDriverHelper();
		helper.setBrowserDriver(driver);
		compareResortsLink = browserDriver.findElement(By.className("map-Container-menu"));

	}

	// member methods
	/** 
	 * unused in Code Challenge 4
	 */
	private void findMenuItems() {

		mainMenuWebElements = browserDriver.findElements(By.className("SuperfishMegaMenu-topLink"));
		subMenuWebElements = browserDriver.findElements(By.className("SuperfishMegaMenu-subLink"));

	}

	/** 
	 * unused in Code Challenge 4
	 */
	public void hoverOnMainMenuItem(int index) {
		findMenuItems();
		WebElement mainMenuItem = mainMenuWebElements.get(index);
		helper.mouseHover(mainMenuItem, 1000);

		for (int i = 0; i < subMenuWebElements.size(); i++) {
			WebElement visibleSubMenuItem = subMenuWebElements.get(i);
			helper.mouseHover(mainMenuItem, 1000);

			if (visibleSubMenuItem.isDisplayed() == true && visibleSubMenuItem
					.getAttribute("class") != "SuperfishMegaMenu-subLink SuperfishMegaMenu-subLink--columnHead") {
				helper.mouseHover(visibleSubMenuItem, 500);
				System.out.println(visibleSubMenuItem.getText());
			}
		}
		mainMenuItem.click();
	}
	
	/**
	 * Added for QA Code automation Challenge #4.
	 * Method Opens the "Compare resorts" map 
	 */
	public void openComparisonMap() {
		synchronized (browserDriver) {
			while (compareResortsLink.isDisplayed() != true) {
				try {
					browserDriver.wait();
				} catch (Exception e) {
				}
			}
			compareResortsLink.click();
		}

		getMiles();
	}


	public void getMiles() {
		WebElement compareByMiles = browserDriver
				.findElement(By.xpath("//*[@id=\"ski-utah-welcome-map\"]/div/div[2]/div[4]/div/label[1]"));
		synchronized (browserDriver) {
			while (compareByMiles.isDisplayed() != true) {
				try {
					browserDriver.wait();
				} catch (Exception e) {
				}
			}

			compareByMiles.click();

		}

	}

	/**
	 * Added for QA Automation Challenge 4.
	 * Method opens the flyout which includes the info about minutes to airport.
	 * @param resortName name of resort you want to compare
	 * @return "X minutes from nearest airport" or message that resort can't be
	 *         found.
	 */
	public String timeFromAirport(String resortName) {
		WebElement resortLabel;
		String returnValue = "Unable to locate resort " + resortName;
		if (resortName.contains(" ") == true) {
			resortName = resortName.replaceAll(" ", "-");
		}
		try {
			resortLabel = browserDriver.findElement(By.cssSelector(".map-Area-label--" + resortName.toLowerCase()));
		} catch (NoSuchElementException e) {
			returnValue = "Unable to locate resort " + resortName;
			return returnValue;
		}
		while (resortLabel.isDisplayed() == false) {
			try {
				browserDriver.wait();
			} catch (Exception e) {

			}
		}
		WebDriverWait wait = new WebDriverWait(browserDriver, 250);
		wait.until(ExpectedConditions.elementToBeClickable(resortLabel));
		helper.mouseHover(resortLabel, 3000);
		resortLabel.click();
		helper.Wait(1000);

		WebElement resortDetails = browserDriver
				.findElement(By.cssSelector(".map-Popover--" + resortName.toLowerCase()));
		WebElement minutesFromAirport = resortDetails.findElement(By.xpath("./div[2]/p/span[3]"));
		returnValue = minutesFromAirport.getText() + " minutes from nearest airport";

		return returnValue;

	}

	@Override
	public void navigateToDefault() {

	}

}
