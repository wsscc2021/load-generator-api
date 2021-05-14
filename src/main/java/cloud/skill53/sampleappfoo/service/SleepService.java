package cloud.skill53.sampleappfoo.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SleepService {

    public ResponseEntity<String> sleep() {
        try {
            Thread.sleep(2000);
            String result = "Success, you did sleep 2 seconds in busniess logic";
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch(InterruptedException error) {
            System.out.println(error.getMessage());
            String result = "Error Occur";
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
