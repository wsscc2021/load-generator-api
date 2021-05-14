package cloud.skill53.sampleappfoo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class BotDto {
    private Integer id;
    private String name;
}
