package com.example.demospringkafka;

import java.util.Objects;
import java.util.concurrent.Executors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableKafka
public class DemoSpringKafkaApplication {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  private Environment env;

  public static void main(String[] args) {
    SpringApplication.run(DemoSpringKafkaApplication.class, args);
  }

  @GetMapping
  @RequestMapping("/")
  public String run() {

    String topico = Objects.requireNonNull(env.getProperty("KAFKA_TOPICO"));
    String mensagem = "teste";

    Executors.newCachedThreadPool().submit(() -> kafkaTemplate.send(topico, mensagem));

    return "Runing";
  }
}
