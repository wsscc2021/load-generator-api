package cloud.skill53.sampleappfoo.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import cloud.skill53.sampleappfoo.dto.KeyDto;

@Service
public class KeyService {

    private String getRandomAlphaNumberic(int length) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
            .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
            .limit(length)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
        return generatedString;
    }

    public KeyDto generateKey(String name) {
        // Key 생성하는 로직 (연산 로직)
        // 서버에 부하를 주기 위해 고의적으로 비효율적인 코드를 작성합니다.
        // String연산에 + 연산자 이용하고, for-each로 1024번 반복하여 1024 길이의 key 생성.
        String keyString = "";
        for(int i=0; i<1024; i++) {
            keyString += getRandomAlphaNumberic(1);
        }
        KeyDto newKey = new KeyDto()
                            .setName(name)
                            .setKey(keyString);
        return newKey;
    }
}
