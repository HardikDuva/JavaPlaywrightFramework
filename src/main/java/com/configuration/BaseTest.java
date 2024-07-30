package com.configuration;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.utilities.*;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static com.utilities.TestDataConstants.*;

public class BaseTest {

	Playwright playwright;
	//Browser browser;
	BrowserContext context;
	protected Page page;

    protected static String clientName = null;
	private static String platform = null;
	private static String device = null;
	private static String browserVal = null;
	protected static String outputDirPath = null;
	private String finalOutputReport = null;
	public static ExtentReports extentReports;
	protected ExtentTest extentTest;

    @BeforeSuite
	@Parameters({"Platform","Device","Browser","Client"})
	public void setConfiguration(String pForm,String dwc,String brw,String client) {
		platform = pForm;
		device = dwc;
		browserVal = brw;
		clientName = client;

		outputDirPath = System.getProperty("user.dir") + File.separator + "TestResult" + File.separator + client
				+ File.separator + DateTimeConnector.getTimeStamp() + File.separator + platform +
				File.separator + device + File.separator + browserVal ;

		try {
			FrameworkConfig.init(System.getProperty("user.dir")
					+ "/src/test/java/resource/configuration/FW_Config.properties");

			UserDetailsConfig.init(System.getProperty("user.dir")
					+ "/clients/" + clientName + "/TestData/TestData.properties");

		} catch (Exception e) {
			Assert.fail("Framework configuration file initialization error" + e.getMessage());
		}

		try {
			FileConnector.createDir(outputDirPath);
			finalOutputReport = outputDirPath
					+ File.separator + REPORT_NAME + ".html";
			setExtentHtmlReportProperty();

		} catch (Exception e) {
			Assert.fail("Error while creating directory for extent report" + e.getMessage());
		}
	}

	@BeforeMethod
	public void launchBrowser(ITestResult result) {
        String testName = result.getMethod().getMethodName();
		extentTest = extentReports.createTest(testName);

		// Open browser
		try {
			playwright = Playwright.create();
			BrowserSetup browserSetup = new BrowserSetup();
			Browser browser = browserSetup.setBrowser(browserVal,playwright);
			context = browser.newContext();
			page = context.newPage();
			page.navigate(PRODUCT_URL);

		} catch (Exception e) {
			extentTest.log(Status.FAIL,"Open browser failed!" + e.getMessage());
		}
	}

	@AfterMethod
	public void closeBrowser() {
		context.close();
	}

	@AfterSuite
	public void generateReport() throws IOException {
		extentReports.flush();
		playwright.close();
		if(SEND_EMAIL) {
			sendEmail();
		}
	}

	protected void infoLog(String msg) {
		printMsgOnConsole(msg);
		extentTest.log(Status.INFO, msg);
	}

	protected void errorLog(String msg) {
		// Capture a screenshot
		String screenshotPath = outputDirPath +
				File.separator + "ScreenShot" + File.separator + FileConnector.getRandomInt(1,100)
				+ "_" + DateTimeConnector.getTimeStamp() + "_.png";
		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath)));
		extentTest.addScreenCaptureFromPath(screenshotPath);
		extentTest.log(Status.FAIL,msg);
		printMsgOnConsole(msg);
		Assert.fail(msg);
	}

	private void sendEmail() {
		//Send Report
		EmailConnector emailConnector = new EmailConnector();
		String subject = "Execution Report for the client " + clientName;
		String bodyContent = "Please Find Attached Execution Report with" +
				"\n Browser : " + browserVal +
				"\n Time    : " + DateTimeConnector.getTimeStampWithLocaleEnglish();
		File tempFile = new File(finalOutputReport);

		emailConnector.sendEmailWithAttachment(EMAIL_TO,subject,bodyContent,tempFile);
	}

	public static void printMsgOnConsole(String msg) {
		System.out.println(msg);
	}

	private void setExtentHtmlReportProperty() {
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(finalOutputReport);
		htmlReporter.config().setDocumentTitle(REPORT_NAME);
		htmlReporter.config().setReportName(REPORT_NAME);
		extentReports = new ExtentReports();
		extentReports.attachReporter(htmlReporter);
		extentReports.setSystemInfo("Platform",platform);
		extentReports.setSystemInfo("Device",device);
		extentReports.setSystemInfo("Browser",browserVal);
		extentReports.setSystemInfo("Client",clientName);
	}
}
	
	