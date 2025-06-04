package br.ifsp.reservas_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication(scanBasePackages = "br.ifsp.reservas_api")
public class ReservasApiApplication {
 public static void main(String[] args) {
     SpringApplication.run(ReservasApiApplication.class, args);
 }
}
