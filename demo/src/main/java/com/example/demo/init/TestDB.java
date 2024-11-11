package com.example.demo.init;

import com.example.demo.modelo.Conductor;
import com.example.demo.repositories.RepositorioConductor;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"internal-test-server"})
public class TestDB implements CommandLineRunner{
    @Autowired
    private RepositorioConductor repositorioConductor ;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        repositorioConductor.save(new Conductor(null, "Karen", 132343L, 3005555L, "calle 500"));
        repositorioConductor.save(new Conductor(null, "Andrea", 135674L, 3006666L, "calle 600"));
        repositorioConductor.save(new Conductor(null, "Rafael", 139865L, 3007777L, "calle 700"));
    }
}
