package cloud.skill53.sampleappfoo.service;

import org.springframework.stereotype.Service;

@Service
public class SleepService {

    public String sleep() {
        try {
            Thread.sleep(2000);
            return "Success, you did sleep 2 seconds in busniess logic";
        } catch(InterruptedException error) {
            System.out.println(error.getMessage());
            return "Error occur!";
        }
    }
}
