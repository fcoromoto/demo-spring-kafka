package com.example.demospringkafka;

import java.util.concurrent.Executors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

  public static void main(String[] args) {
    SpringApplication.run(DemoSpringKafkaApplication.class, args);
  }

  @GetMapping
  @RequestMapping("/")
  public String run() {
    System.out.println("Hello");
    Executors.newCachedThreadPool().submit(() -> kafkaTemplate.send("pendentes", "teste"));
    return "Runing";
  }
}
