package com.example.demo;

import com.example.demo.modelo.Conductor;
import com.example.demo.repositories.RepositorioConductor;
import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.WaitForSelectorState;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;



@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
@ActiveProfiles("default")
public class ConductorControllerSystemTests {

    @Autowired
    private RepositorioConductor repositorioConductor;


    private WebDriver driver;
    private WebDriverWait wait;
    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;
    private Page page;

    private String baseUrl = "http://localhost:4200";
    private String token;


    @BeforeEach
    private void init() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications--");
        chromeOptions.addArguments("--disable-extensions--");
        this.driver = new ChromeDriver(chromeOptions);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    @AfterEach
    public void tearDown(){
        //driver.quit();
    }

    @Test
    public void test() throws InterruptedException {
        driver.get(baseUrl);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtEmail")));
            WebElement correoLabel = driver.findElement(By.id("txtEmail"));
            correoLabel.sendKeys("coordinador@example.com");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtPassword")));
            WebElement passwordLabel = driver.findElement(By.id("txtPassword"));
            passwordLabel.sendKeys("password123");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnLogin")));
            WebElement btnLogin = driver.findElement(By.id("btnLogin"));
            btnLogin.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("navegarConductores")));
            WebElement navegarConductoresButton = driver.findElement(By.id("navegarConductores"));
            navegarConductoresButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("navegarACrearConductor")));
            WebElement navegarCrearConductorButton = driver.findElement(By.id("navegarACrearConductor"));
            navegarCrearConductorButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Nombre")));
            WebElement nombreInput = driver.findElement(By.id("Nombre"));
            nombreInput.sendKeys("Karen");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Cedula")));
            WebElement cedulaInput = driver.findElement(By.id("Cedula"));
            cedulaInput.sendKeys("132343");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Telefono")));
            WebElement telefonoInput = driver.findElement(By.id("Telefono"));
            telefonoInput.sendKeys("3005555");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Direccion")));
            WebElement direccionInput = driver.findElement(By.id("Direccion"));
            direccionInput.sendKeys("calle 500");

        Thread.sleep(1000);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("crearConductor")));
            WebElement crearConductorInput = driver.findElement(By.id("crearConductor"));
            crearConductorInput.click();
        ((JavascriptExecutor) driver).executeScript("location.reload();");
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(1000);


        WebElement verConductorButton = driver.findElement(By.xpath("//tbody/tr[1]/td[8]/i"));
        verConductorButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ConductorNombre")));
        WebElement nombreConductor = driver.findElement(By.id("ConductorNombre"));
        Assertions.assertTrue(nombreConductor.isDisplayed(), "El nombre del conductor no se muestra en la página de detalles");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Cedula")));
        WebElement cedula = driver.findElement(By.id("Cedula"));
        Assertions.assertTrue(cedula.isDisplayed(), "La cedula del conductor no se muestra en la página de detalles");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Telefono")));
        WebElement telefonoConductor = driver.findElement(By.id("Telefono"));
        Assertions.assertTrue(telefonoConductor.isDisplayed(), "El telefono del conductor no se muestra en la página de detalles");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Direccion")));
        WebElement direccionConductor = driver.findElement(By.id("Direccion"));
        Assertions.assertTrue(direccionConductor.isDisplayed(), "La direccion del conductor no se muestra en la página de detalles");

        Thread.sleep(1000);

        driver.navigate().back();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("table")));
        List<WebElement> filas = driver.findElements(By.xpath("//tbody/tr"));
        if (filas.size() >= 3) {
            // Verificar si la tercera fila está disponible y hacer clic en el botón de eliminar
            WebElement eliminarConductorButton = driver.findElement(By.xpath("//tbody/tr[3]/td[9]/i"));
            if (eliminarConductorButton.isDisplayed()) {
                eliminarConductorButton.click();
                Thread.sleep(1000); // Pausa de 1 segundo

                // Recargar la página para verificar si la eliminación se refleja
                ((JavascriptExecutor) driver).executeScript("location.reload();");
                Thread.sleep(1000); // Pausa para esperar la recarga
            } else {
                System.out.println("El botón de eliminar no está visible.");
            }
        } else {
            System.out.println("No hay suficientes filas en la tabla.");
        }

        WebElement EditarConductorButton = driver.findElement(By.xpath("//tbody/tr[1]/td[7]/i"));
        EditarConductorButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Nombre")));
        WebElement nombreInputEdit = driver.findElement(By.id("Nombre"));
        nombreInputEdit.clear();
        nombreInputEdit.sendKeys("Carlos");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Editar")));
        WebElement EditarConductorInput = driver.findElement(By.id("Editar"));
        EditarConductorInput.click();
    }
}
