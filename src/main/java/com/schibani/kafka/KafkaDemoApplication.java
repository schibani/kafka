package com.schibani.kafka;

import com.schibani.kafka.consumer.Consumer;
import com.schibani.kafka.producer.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import static com.schibani.kafka.Constants.*;

@SpringBootApplication
@Slf4j
public class KafkaDemoApplication implements ApplicationRunner {


    @Autowired
    private TaskExecutor taskExecutor;

    public static void main(String[] args) {
        System.out.println("Current Directory = " + System.getProperty("user.dir"));
        SpringApplication.run(KafkaDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info(args.getOptionNames().toString());
        if (args.containsOption(Constants.OPTION_START_AS)) {
            String startAs = args.getOptionValues(OPTION_START_AS).get(0);
            log.info("Start as = {}", startAs);
            if (args.containsOption(Constants.OPTION_TOPIC)) {
                String topic = args.getOptionValues(OPTION_TOPIC).get(0);
                if (OPTION_CONSUMER.equalsIgnoreCase(startAs)) {
                    taskExecutor.execute(new Consumer(topic));
                } else if (OPTION_PRODUCER.equalsIgnoreCase(startAs)) {
                    String message = args.getOptionValues(OPTION_MESSAGE).get(0);
                    taskExecutor.execute(new Producer(topic, message));
                }
            }
        }
    }

    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }
}
