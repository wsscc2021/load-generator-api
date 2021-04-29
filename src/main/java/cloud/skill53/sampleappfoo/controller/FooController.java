package cloud.skill53.sampleappfoo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cloud.skill53.sampleappfoo.dto.GreetDto;

@RestController
@RequestMapping("/foo")
public class FooController {
    
    @GetMapping("")
    public GreetDto getGreet() {
        return new GreetDto()
                    .setName("foo")
                    .setVersion("v1")
                    .setMessage("Bonjour!");
    }
}
