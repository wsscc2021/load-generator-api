## REST API
요청을 보냈을 때 서버에 CPU 사용률이 급격하게 증가하는 간단한 API 서버입니다.
HPA, 모니터링 등의 CPU 사용률 변화에 따른 동작이 필요할 때 유용하게 사용할 수 있습니다.

### GET /
Response
```
{
    "name": "foo",
    "version": "<version>",
    "message": "Bonjour!"
}
```

### GET /error
Error를 발생시키는 API입니다. Error Rate를 뽑을 때 유용하게 사용할 수 있습니다.

/error
- response status code: 200

/error?code=400
- response status code: 400

/error?code=401
- response status code: 401

/error?code=403
- response status code: 403

/error?code=404
- response status code: 404

/error?code=500
- response status code: 500

/error?code=501
- response status code: 501

/error?code=502
- response status code: 502

/error?code=503
- response status code: 503

/error?code=504
- response status code: 504

### GET /sleep
Response (String)
해당 API는 2초의 지연시간을 가지고 있습니다.
```
Success, you did sleep 2 seconds in businiess logic
```

### GET /key
Query String
| key  | type   | description          |
| ---- | ------ | -------------------- |
| name | string | 단순 식별 *생략 가능  |

Response
```
{
    "name": <name>,
    "key": "<1024 size random key>
}
```

#### CPU 사용률이 급격하게 증가하는 원리
Ref: https://javacan.tistory.com/entry/41

Java에서 String을 합치는 연산을 수행할 때 + 연산자를 사용하면 상당한 비효율을 발생시킨다.
이 점을 이용하여 1024길이의 랜덤 문자열을 생성할 때 `+` 연산자를 1024번 이용하도록 구현했고, 해당 로직에서 연산처리가 급격하게 늘어난다.

```
for(int i=0; i<1024; i++) {
    keyString += getRandomAlphaNumberic(1);
}
```



### GET /bot
Query String
| key  | type   | description          |
| ---- | ------ | -------------------- |
| name | string | 단순 식별 *생략 가능  |

Response
```
{
    "id": "0",
    "name": <name>
}
```


#### Memory 사용률이 급격하게 증가하는 원리

한번 요청이 들어오면 bot인스턴스를 1000만개 만들도록 구현했다.
요청이 처리될 때까지 해당 인스턴스들이 Heap 메모리 영역에 잔존하여 메모리 사용률이 증가하게 된다.

```
public List<BotDto> cloneBotMillion(String botName) {
    List<BotDto> bots = new ArrayList<BotDto>();
    for (int i=0; i<1000000; i++) {
        bots.add(new BotDto().setId(i).setName(botName));
    }
    return bots;
}
```



### 부하 테스트 주는 방법
apache benchmark를 통해 간단하게 생성할 수 있다.

`CPU`
```
ab -c 20 -n 10000 http://{server-ip}:8080/key
```

`Memory`
```
ab -c 20 -n 10000 http://{server-ip}:8080/bot
```
