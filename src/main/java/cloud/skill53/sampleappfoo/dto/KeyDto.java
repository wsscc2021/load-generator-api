package cloud.skill53.sampleappfoo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain=true)
public class KeyDto {
    private String name;
    private String key;
}
