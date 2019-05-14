import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

public class MainTest {

    //***Windows***
   // ChromeDriver driver; //объявляем заранее драйвер бразуера. Делать нужно вне методов

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
        driver =  new RemoteWebDriver(new URL("http://0.0.0.0:4444/wd/hub"),options);


    }

    @Test
    public void testOpenPageGoogle() throws InterruptedException {
        driver.get("http://localhost:8172");
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='TestBlock test']")).isDisplayed()); //Проверка на отображение элемента
        driver.quit();
    }

    @Test
    public void testOpenPageYandex() throws InterruptedException {
        driver.get("http://localhost:8172");
        driver.quit();
    }

    /*
    @AfterMethod означает что будет выполянтся после каждого тестового метода, помеченным аннотацией Test
    */
    @AfterMethod(alwaysRun = true)
    public void afterTest(){
        if(driver!=null){
            driver.quit(); // закрыть браузер, если он не смог закрыться сам во время теста
        }
    }
}