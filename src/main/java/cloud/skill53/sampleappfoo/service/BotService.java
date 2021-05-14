package cloud.skill53.sampleappfoo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import cloud.skill53.sampleappfoo.dto.BotDto;

@Service
public class BotService {

    public ResponseEntity<?> cloneBot(String botName) {
        // 메모리 사용량을 높이기 위한 로직
        // 1,000,000 개 인스턴스를 생성하여 Heap Size를 늘리기
        try {
            List<BotDto> bots = new ArrayList<BotDto>();
            for (int i=0; i<100000; i++) {
                bots.add(new BotDto().setId(i).setName(botName));
            }
            return new ResponseEntity<>(bots.get(0), HttpStatus.OK);
        }
        catch(Error error) {
            System.out.println(error);
            String result = "Error Occur!";
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
