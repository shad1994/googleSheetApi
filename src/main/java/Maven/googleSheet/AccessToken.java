package Maven.googleSheet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class AccessToken {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver","/home/qainfotech/Downloads/chromedriver");
		
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://google.com");
	}

	public void A() {
		System.setProperty("webdriver.gecko.driver", "resource/geckodriver");

		WebDriver driver = new ChromeDriver();
		driver.get("http://google.com");

	}

}
