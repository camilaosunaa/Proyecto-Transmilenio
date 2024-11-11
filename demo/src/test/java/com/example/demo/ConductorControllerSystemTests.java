package com.example.demo;

import com.example.demo.modelo.Conductor;
import com.example.demo.repositories.RepositorioConductor;
import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@ActiveProfiles("system-test")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ConductorControllerSystemTests {

    @Autowired
    private RepositorioConductor repositorioConductor;

    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;
    private Page page;

    private String baseUrl = "http://localhost:4200";
    private String token;

    @BeforeEach
    void init() {
        // Guardar datos de prueba en la base de datos
        //repositorioConductor.save(new Conductor(null, "Karen", 132343L, 3005555L, "calle 500"));
        //repositorioConductor.save(new Conductor(null, "Andrea", 135674L, 3006666L, "calle 600"));
        //repositorioConductor.save(new Conductor(null, "Rafael", 139865L, 3007777L, "calle 700"));

        // Configuración de Playwright
        this.playwright = Playwright.create();
        this.browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        this.browserContext = browser.newContext();
        this.page = browserContext.newPage();

        // Autenticación para obtener el token
        obtenerTokenDeAutenticacion();
    }

    private void obtenerTokenDeAutenticacion() {
        // Realizar login y obtener el token
        page.navigate(baseUrl + "/login");

        // Completar el formulario de login
        page.fill("input[name='email']", "coordinador@example.com");
        page.fill("input[name='password']", "password123");
        page.click("button[type='submit']");

        // Esperar hasta que el token esté en localStorage
        page.waitForFunction("localStorage.getItem('auth_token') !== null");

        // Obtener el token desde localStorage
        token = (String) page.evaluate("() => localStorage.getItem('auth_token')");
        assert token != null : "El token JWT no fue generado.";

        // Configurar el contexto para incluir el token en todas las solicitudes
        browserContext = browser.newContext(new Browser.NewContextOptions()
                .setExtraHTTPHeaders(Map.of("Authorization", "Bearer " + token)));
        page = browserContext.newPage();
    }

    /*@Test
    void testRecuperarConductorFront() {
        Long conductorId = 1L; // Reemplaza con el ID del conductor que quieras ver
        page.navigate(baseUrl + "/conductor/view/" + conductorId);

        // Verificar que el conductor se haya mostrado en el frontend
        Locator conductorNombre = page.locator("text= Karen ");
        assertThat(conductorNombre).isVisible();
    }*/
    @AfterEach
    void end() {
        browser.close();
        playwright.close();
    }


}
