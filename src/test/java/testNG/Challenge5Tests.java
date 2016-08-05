package testNG;

import org.eclipse.jetty.io.ClientConnectionFactory.Helper;
import org.testng.*;
import org.testng.annotations.*;
import org.testng.asserts.*;

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
	public void CheckTitle()
	{
		Assert.assertEquals(page.getPageTitle(), "All Services - Ski Utah");
	}
	
	@Test
	public void SendStrings()
	{
		//	page.navigateToDefault();
		page.Search("Ski school", "clinics", "all resorts");
		page.getAllResults();
	}
	
	
	@Test
	public void GetResults()
	{
	//	page.getPageResults();
		//Assert.assertTrue(page.getResultsSize()>0);
	}
	
	
	@AfterSuite
	public void CloseTests()
	{
		page.SelfDestruct();
	}
}
