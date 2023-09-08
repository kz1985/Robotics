package com.Tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Base.TestBase;
import com.Pages.DeshBoardHome;
import com.Pages.HomePage;
import com.Pages.LoginPage;
import com.Utility.TestUtils;

public class DeshBoardHomeTest extends TestBase {
	LoginPage loginPage;
	HomePage homePage;
	DeshBoardHome deshBoardHome;
	TestUtils testUtils;
	static String sheetName = "Sheet1";

	@BeforeMethod(groups = "Initializations")
	public void tearUp() {
		initialization();

		deshBoardHome = new DeshBoardHome();
		testUtils = new TestUtils();
		homePage = new HomePage();
		loginPage = new LoginPage();
		TestUtils.implicitelyWaitTime();

	}

	@Test(groups = "Desh Board Page Functions", priority = 1)
	public void pageTitleTest() {
		String ti = deshBoardHome.validatePageTitle();
		Assert.assertEquals(ti, "RobotSpareBin Industries Inc. - Intranet", "Title is missing!!");
		System.out.println("Page Title is : " + ti);
	}

	@DataProvider
	public Object[][] getNewData() {
		Object[][] data = TestUtils.getTestData(sheetName);
		return data;
	}

	@Test(groups = "Desh Board  Functions", dataProvider = "getNewData", priority = 2)
	public void salseDataTest(String firstname, String lastname, String amounts) {
		loginPage.enterCredentials(prop.getProperty("userName"), prop.getProperty("password"));
		//TestUtils.implicitelyWaitTime();
		deshBoardHome.enterSalseData(firstname, lastname, amounts);
		//TestUtils.implicitelyWaitTime();
		deshBoardHome.displatResult();
		//TestUtils.implicitelyWaitTime();
		deshBoardHome.bossMessages();
		
	}
/*
	@Test(groups = "Desh Board Functions", priority=3)
	public void desplayResults() {
		deshBoardHome.displatResult();
	}

	@Test(groups = "Desh Board  Functions", priority = 3, dependsOnMethods ="salseDataTest" )
	public void bossMessageValidation(String firstname, String lastname, String amounts) {
		loginPage.enterCredentials(prop.getProperty("userName"), prop.getProperty("password"));
		TestUtils.implicitelyWaitTime();
		deshBoardHome.enterSalseData(firstname, lastname, amounts);
		TestUtils.implicitelyWaitTime();
		deshBoardHome.bossMessages();
	}
*/
	@AfterMethod(groups = "Initializations")
	public void tearDown() {
		driver.manage().deleteAllCookies();
		driver.close();
		driver.quit();
	}

}
