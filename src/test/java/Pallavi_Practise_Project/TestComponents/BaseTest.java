package Pallavi_Practise_Project.TestComponents;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.Dimension;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;

import Pallavi_Practise_Project.PageObjectModel.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingpage;

	public WebDriver initalizedriver() throws IOException {
		// Properties class
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\Pallavi_Practise_Project\\Resource\\GlobalData.properties"); // package path
		prop.load(fis);

		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
		// java ternary operator
		// If first argument is fail then it goes to second if second fails then it goes
		// to third
		// prop.getProperty("browser");

		if (browserName.contains("chrome")) {

			ChromeOptions option = new ChromeOptions();

			WebDriverManager.chromedriver().setup();
			if (browserName.contains("headless")) {
				option.addArguments("headless");
			}
			driver = new ChromeDriver(option);
			driver.manage().window().setSize(new Dimension(1440, 900)); //full screen
			
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Selenium\\geckodriver-v0.35.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		else if (browserName.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "edge.exe");
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// String to hashmap Jackson Databin,

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

	public String getScreenshot(String TestCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "\\reports\\" + TestCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "\\reports\\" + TestCaseName + ".png";
	}
	// Extent Reports

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {

		driver = initalizedriver();
		landingpage = new LandingPage(driver);
		landingpage.goTo();
		return landingpage;

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}

}
