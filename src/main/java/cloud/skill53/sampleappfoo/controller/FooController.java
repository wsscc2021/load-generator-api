package cloud.skill53.sampleappfoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cloud.skill53.sampleappfoo.dto.GreetDto;
import cloud.skill53.sampleappfoo.service.BotService;
import cloud.skill53.sampleappfoo.service.KeyService;
import cloud.skill53.sampleappfoo.service.SleepService;

@RestController
@RequestMapping("/")
public class FooController {
    @Autowired
    private BotService botService = new BotService();
    private KeyService keyService = new KeyService();
    private SleepService sleepService = new SleepService();

    @GetMapping("")
    public ResponseEntity<GreetDto> getGreet() {
        GreetDto greet = new GreetDto()
                            .setName("foo")
                            .setVersion("v2")
                            .setMessage("Bonjour!");
        return new ResponseEntity<>(greet, HttpStatus.OK);
    }

    @GetMapping("/sleep")
    public ResponseEntity<String> getSleep() {
        return sleepService.sleep();
    }

    @GetMapping("/bot")
    public ResponseEntity<?> getBot(@RequestParam(defaultValue = "sample") String name) {
        try {
            return botService.cloneBot("siri");
        }
        catch(Error error) {
            return new ResponseEntity<>("Error Occur!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/key")
    public ResponseEntity<?> getKey(@RequestParam(defaultValue = "Empty") String name) {
        try {
            return keyService.generateKey(name);
        }
        catch(Error error) {
            return new ResponseEntity<>("Error Occur!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/error")
    public ResponseEntity<?> getError(@RequestParam(defaultValue = "0") Integer code) {
        switch(code) {
            case 400:
                return new ResponseEntity<>("400, Bad Request", HttpStatus.BAD_REQUEST);
            case 401:
                return new ResponseEntity<>("401, Unauthorized", HttpStatus.UNAUTHORIZED);
            case 403:
                return new ResponseEntity<>("403, Forbidden", HttpStatus.FORBIDDEN);
            case 404:
                return new ResponseEntity<>("404, Not Found", HttpStatus.NOT_FOUND);
            case 500:
                return new ResponseEntity<>("500, Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
            case 501:
                return new ResponseEntity<>("501, Not Implement", HttpStatus.NOT_IMPLEMENTED);
            case 502:
                return new ResponseEntity<>("502, Bad Gateway", HttpStatus.BAD_GATEWAY);
            case 503:
                return new ResponseEntity<>("503, Unavailable", HttpStatus.SERVICE_UNAVAILABLE);
            case 504:
                return new ResponseEntity<>("504, Gateway Timeout", HttpStatus.GATEWAY_TIMEOUT);
            default:
                return new ResponseEntity<>("200, This code is not found", HttpStatus.OK);
        }
    }
}
