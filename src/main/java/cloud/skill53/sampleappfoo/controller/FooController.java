package cloud.skill53.sampleappfoo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cloud.skill53.sampleappfoo.dto.BotDto;
import cloud.skill53.sampleappfoo.dto.GreetDto;
import cloud.skill53.sampleappfoo.dto.KeyDto;
import cloud.skill53.sampleappfoo.service.BotService;
import cloud.skill53.sampleappfoo.service.KeyService;

@RestController
@RequestMapping("/")
public class FooController {
    @Autowired
    private BotService botService = new BotService();
    private KeyService keyService = new KeyService();

    @GetMapping("")
    public GreetDto getGreet() {
        return new GreetDto()
                    .setName("foo")
                    .setVersion("v2")
                    .setMessage("Bonjour!");
    }

    @GetMapping("/bot")
    public BotDto getBot(@RequestParam(defaultValue = "sample") String name) {
        List<BotDto> bots = botService.cloneBot(name);
        return botService.getOriginBot(bots);
    }

    @GetMapping("/key")
    public KeyDto getKey(@RequestParam(defaultValue = "Empty") String name) {
        return keyService.generateKey(name);
    }
}
