package com.example.demo;

import com.example.demo.DTO.ConductorDTO;
import com.example.demo.DTO.JwtAuthenticationResponse;
import com.example.demo.DTO.LoginDTO;
import com.example.demo.modelo.Conductor;
import com.example.demo.repositories.RepositorioConductor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration-testing")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class ConductorControllerTests {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private RepositorioConductor repositorioConductor;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void init() {
        repositorioConductor.save(new Conductor(null, "Karen", 132343L, 3005555L, "calle 500"));
        repositorioConductor.save(new Conductor(null, "Andrea", 135674L, 3006666L, "calle 600"));
        repositorioConductor.save(new Conductor(null, "Rafael", 139865L, 3007777L, "calle 700"));
    }

    @BeforeEach
    void setupWebTestClient() {
        // Datos del login
        LoginDTO loginRequest = new LoginDTO("coordinador@example.com", "password123");

        // Obtener el token JWT llamando al endpoint de login
        JwtAuthenticationResponse jwtResponse = webTestClient.post()
                .uri("/auth/login")
                .bodyValue(loginRequest)
                .exchange()
                .expectStatus().isOk()
                .expectBody(JwtAuthenticationResponse.class)
                .returnResult()
                .getResponseBody();

        assert jwtResponse != null; // Asegúrate de que el token fue generado correctamente
        String jwtToken = jwtResponse.getToken();

        // Configurar WebTestClient con el token JWT en el encabezado de autorización
        this.webTestClient = webTestClient.mutate()
                .baseUrl("http://localhost:8082")
                .defaultHeaders(httpHeaders -> httpHeaders.set(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken))
                .build();
    }
    @Test
    void testRecuperarConductor() {
        Long conductorId = 1L;
        webTestClient.get()
                .uri("/conductor/{idconductor}", conductorId)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(conductorId);
    }

    @Test
    void testRecuperarConductores() {
        webTestClient.get()
                .uri("/conductor")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ConductorDTO.class);
    }

    @Test
    void testCrearConductor() {
        ConductorDTO nuevoConductor = new ConductorDTO(null, "Juan", 142334L, 3008888L, "calle 800");
        webTestClient.post()
                .uri("/conductor")
                .bodyValue(nuevoConductor)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.nombre").isEqualTo("Juan")
                .jsonPath("$.cedula").isEqualTo(142334L);
    }

    @Test
    void testActualizarConductor() {
        Long conductorId = 1L;
        ConductorDTO conductorActualizado = new ConductorDTO(conductorId, "Carlos Actualizado", 132343L, 3005555L, "calle 500 actualizada");

        webTestClient.put()
                .uri("/conductor/{idConductor}", conductorId)
                .bodyValue(conductorActualizado)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.nombre").isEqualTo("Carlos Actualizado");
    }

    @Test
    void testEliminarConductor() {
        Long conductorId = 11L;

        webTestClient.delete()
                .uri("/conductor/{id}", conductorId)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isEqualTo("Conductor eliminado con éxito");
    }
}
