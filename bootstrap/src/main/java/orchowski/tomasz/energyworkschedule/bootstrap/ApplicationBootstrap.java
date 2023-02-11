package orchowski.tomasz.energyworkschedule.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
                "orchowski.tomasz.energyworkschedule.application",
                "orchowski.tomasz.energyworkschedule.framework",
                "orchowski.tomasz.energyworkschedule.bootstrap"
        }
)
public class ApplicationBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationBootstrap.class, args);
    }
}
