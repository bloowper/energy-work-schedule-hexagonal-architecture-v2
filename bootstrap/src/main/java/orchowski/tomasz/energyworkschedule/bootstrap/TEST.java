package orchowski.tomasz.energyworkschedule.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
class TEST implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("DZIAŁAJ KURWO Z JPMS 1");
    }
}
