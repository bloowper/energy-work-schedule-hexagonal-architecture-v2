package orchowski.tomasz.energyworkschedule.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(
        scanBasePackages = {
                "orchowski.tomasz.energyworkschedule.application",
                "orchowski.tomasz.energyworkschedule.framework",
                "orchowski.tomasz.energyworkschedule.bootstrap"
        }
)
@EnableMongoRepositories(
        basePackages = {
                "orchowski.tomasz.energyworkschedule.framework.adapter.output.mongodb"
        }
)
@EnableScheduling
public class ApplicationBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationBootstrap.class, args);
    }
}
