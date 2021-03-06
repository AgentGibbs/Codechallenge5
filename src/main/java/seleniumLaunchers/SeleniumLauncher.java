package seleniumLaunchers;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import skiUtahPageClasses.*;

public class SeleniumLauncher {
	public WebDriver driver;
	private SkiUtahAllServicesPage page;

	public SeleniumLauncher() {
		// TODO Auto-generated constructor stub
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		page = new SkiUtahAllServicesPage(driver);
	}

	public SkiUtahAllServicesPage getPage() {
		String unused = "This is a new string that can be used to show the difference in Github"
		return page;
	}
}
