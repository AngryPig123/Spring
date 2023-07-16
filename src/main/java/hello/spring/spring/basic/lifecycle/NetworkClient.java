package hello.spring.spring.basic.lifecycle;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NetworkClient{

    private String url;

    public NetworkClient() {
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        log.info("connect : {}", url);
    }

    public void call(String massage) {
        log.info("call : {},  massage = {}", url, massage);
    }

    public void disconnect() {
        log.info("disconnect = {}", url);
    }


    public void init() throws Exception {
        log.info("생성자 호출, url = {}", url);
        connect();
        call("초기화 연결 메세지");
    }


    public void close() throws Exception {
        disconnect();
    }

}
