package demo;

import org.openqa.selenium.WebDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.print.attribute.SupportedValuesAttribute;

import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
//Selenium Imports
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import com.github.dockerjava.api.command.StopContainerCmd;

public class windowhandle1 {
    WebDriver driver;
    public windowhandle1(){
        System.out.println("Constructor: TestCases");
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


    }
    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void handlingWindow() throws InterruptedException{
        //Navigate to url
        driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_win_open");
        Thread.sleep(3000);

        //click on try button using xpath //button[text()='Try it']
        // driver.switchTo().frame("iframeResult");
        WebElement iframe = driver.findElement(By.id("iframeResult"));
        // switch to the iframe by webElement driver.switchTo().iframe(iframeResult);
        driver.switchTo().frame(iframe);


        Boolean button = driver.findElement(By.xpath("//button[@onclick='myFunction()']")).isEnabled();
        System.out.println("Button Enabled: " + button);

        driver.findElement(By.xpath("//button[text()='Try it']")).click();

        //Get window handles object
        String mainWindowHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
            break;
            }
        }

        //Get the URL, Title, and take the screenshot. Close the new window.
        String Url = driver.getCurrentUrl();
        System.out.println("Current URL: " + Url);

        String Title = driver.findElement(By.xpath("//h1[@class='learntocodeh1']")).getText();
        System.out.println("Title: " + Title);
        driver.close();

        driver.switchTo().window(mainWindowHandle);
        

        System.out.println("Switched to the Main Window");


    }
    
}