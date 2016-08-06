package skiUtahPageClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SkiUtahAllServicesPage extends SkiUtahWebPage {
	private WebElement searchForWhatDropdown;
	private WebElement searchForSubCategoryDropdown;
	private WebElement searchByResortDropdown;
	private ArrayList<WebElement> resultsList;
	private WebElement nextPageArrow;

	// constructors

	public SkiUtahAllServicesPage() {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		new SkiUtahAllServicesPage(driver);
	}

	public SkiUtahAllServicesPage(WebDriver driver) {
		super(driver);
		this.setExpectedURL("https://www.skiutah.com/members/listing");
		resultsList = new ArrayList();
		resultsList.clear();
		navigateToDefault();
		attemptToFindSearchBoxes();
	}

	// getters
	public ArrayList<WebElement> getResultsList() {
		return resultsList;
	}

	public int getResultsListSize() {
		return resultsList.size();
	}

	// methods

	private void attemptToFindSearchBoxes() {
		try {
			searchForWhatDropdown = browserDriver.findElement(By.name("filter-category-select"));
			searchForSubCategoryDropdown = browserDriver.findElement(By.name("filter-sub-category-select"));
			searchByResortDropdown = browserDriver.findElement(By.name("filter-resort-select"));
		} catch (Exception e) {
			System.out.println("Oh great- now we're all gonna die. Thanks a lot, pal!");
			e.printStackTrace();
		}
	}

	public void Search(String search1, String search2, String search3) {
		if (searchForWhatDropdown == null) {
			attemptToFindSearchBoxes();
		}
		try {
			WebDriverWait waiter = new WebDriverWait(browserDriver, 5);
			waiter.until(ExpectedConditions.elementToBeClickable(searchForWhatDropdown));
		} catch (Exception e) {

		}
		// Send strings to Web Elements
		searchForWhatDropdown.sendKeys(search1);
		searchForSubCategoryDropdown.sendKeys(search2);
		searchByResortDropdown.sendKeys(search3);
		// find ok button and click it
		WebElement okSubmit = browserDriver.findElement(By.name("filter-form-submit"));

		okSubmit.click();
	}

	public void getAllResults() {
		// collects all the results from all results pages.
		getPageResults();

		while (canClickToNextResultsPage() == true) {
			nextPageArrow.click();
			System.out.println("Arrow clicked");
			helper.Wait(5000);
			getPageResults();

		}

	}

	private boolean canClickToNextResultsPage() {
		boolean ret = true;
		try {// Try to find the "click to next page" element
			nextPageArrow = browserDriver
					.findElement(By.xpath("//*[@id=\"load-more-items\"]/div[5]/div/div/div[3]/a/span"));

		} catch (NoSuchElementException e) {// can't be found
			nextPageArrow = null;
			ret = false;
		}
		return ret;
	}

	public void getPageResults() {
		// gets results for a single search page.
		List<WebElement> pageOfResults = browserDriver.findElements(By.cssSelector("div.ListingResults-item"));
		for (int i = 0; i < pageOfResults.size(); i++) {
			resultsList.add(pageOfResults.get(i));
			System.out.println(i);
			System.out.println(pageOfResults.get(i).getText());
		}

	}

}