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
}
