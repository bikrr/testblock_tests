import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class MainTest {

    ChromeDriver driver; //объявляем заранее драйвер бразуера. Делать нужно вне методов

    /*
    @BeforeMethod означает что будет выполянтся перед каждым методом, помеченным аннотацией Test
    alwaysRun означает что будет выпонятся всегда, даже если предыдущий тест упал
     */
    @BeforeMethod(alwaysRun = true)
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver","chromedriver.exe"); //обязательно указать путь до хромдрайвера
        driver = new ChromeDriver();
    }

    @Test
    public void testOpenPageGoogle() {
        driver.get("http://www.google.com");
        Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Поиск в Google']")).isDisplayed()); //Проверка на отображение элемента
        driver.quit();
    }

    @Test
    public void testOpenPageYandex() throws InterruptedException {
        driver.get("http://www.yandex.ru");
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