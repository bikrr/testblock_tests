import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainTest {


    String app_url ="http://0.0.0.0:8172";

    //***Windows***
    // ChromeDriver driver; //объявляем заранее драйвер бразуера.

    //***Ubuntu***
    WebDriver driver;

    /*
    @BeforeMethod означает что будет выполянтся перед каждым методом, помеченным аннотацией Test
    alwaysRun означает что будет выпонятся всегда, даже если предыдущий тест упал
     */



    @BeforeMethod(alwaysRun = true)
    public void beforeTest() throws MalformedURLException {

        //***Windows***
        // System.setProperty("webdriver.chrome.driver","chromedriver74.exe"); //обязательно указать путь до хромдрайвера
        // driver = new ChromeDriver();

        //***Ubuntu***
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new RemoteWebDriver(new URL("http://0.0.0.0:4444/wd/hub"), options);


    }

    // Открыть приложение, убедиться, что заголовок TestBlock test присутствует на странице
    @Test
    public void isHeaderPresent() throws InterruptedException, IOException {
        driver.get(app_url);
        Thread.sleep(2000);
        // Скриншот для отладки теста
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("/home/bikrr/scr/isHeaderPresent.png"));
        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='TestBlock test']")).isDisplayed()); //Проверка на отображение элемента
        driver.quit();
    }

    // Открыть приложение, нажать кнопку JDBC connect, убедиться, что появляется сообщение успешного подключения
    @Test
    public void isDBconnected() throws InterruptedException, IOException {
        driver.get(app_url);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"dbconnectverify\"]")).click();
        Thread.sleep(10000);
        // Скриншот для отладки теста
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("/home/bikrr/scr/isDBconnected.png"));
        //Проверка на отображение элемента
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id='returnsoap'][text()='You successfully connected to database now']")).isDisplayed());
    }

    /*
    @AfterMethod означает что будет выполянтся после каждого тестового метода, помеченным аннотацией Test
    */
    @AfterMethod(alwaysRun = true)
    public void afterTest() {
        if (driver != null) {
            driver.quit(); // закрыть браузер, если он не смог закрыться сам во время теста
        }
    }
}