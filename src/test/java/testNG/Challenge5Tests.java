package testNG;

import org.testng.*;
import org.testng.annotations.*;
import seleniumLaunchers.SeleniumLauncher;
import skiUtahPageClasses.SkiUtahAllServicesPage;

public class Challenge5Tests {
	private SkiUtahAllServicesPage page;

	public Challenge5Tests() {
		
	}

	@BeforeSuite
	public void InitiatePage() {
		SeleniumLauncher launcher = new SeleniumLauncher();
		page = launcher.getPage();
	}


	@Test
	public void SendSearchStrings()
	{
		page.Search("ski school", "clinic", "all");
		page.getAllResults();
		Assert.assertTrue(page.getResultsListSize()>0);
		
	}
	
	@AfterSuite
	public void CloseTests()
	{
		page.SelfDestruct();
	}
}
