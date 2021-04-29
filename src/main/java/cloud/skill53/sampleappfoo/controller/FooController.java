package cloud.skill53.sampleappfoo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cloud.skill53.sampleappfoo.dto.BotDto;
import cloud.skill53.sampleappfoo.dto.GreetDto;
import cloud.skill53.sampleappfoo.service.BotService;

@RestController
@RequestMapping("/foo")
public class FooController {
    @Autowired
    private BotService botService = new BotService();

    @GetMapping("")
    public GreetDto getGreet() {
        return new GreetDto()
                    .setName("foo")
                    .setVersion("v1")
                    .setMessage("Bonjour!");
    }

    @GetMapping("/bot")
    public BotDto getBot(@RequestParam(defaultValue = "sample") String botName) {
        List<BotDto> bots = botService.cloneBot(botName);
        return botService.getOriginBot(bots);
    }
}
