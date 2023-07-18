package hello.spring.spring.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

import static hello.spring.spring.basic.CommonUtil.getFormat;

@Slf4j
@Component
@Scope(value = "request")
public class MyLogger {

    private String uuid;
    private String requestUrl;

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public void log(String massage) {
        log.info(
                getFormat(TextColor.GREEN, "[{}]") +
                        getFormat(TextColor.YELLOW, "[{}]") +
                        getFormat(TextColor.BLUE, "[{}]")
                , uuid, requestUrl, massage);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        log.info(getFormat(TextColor.GREEN, "[{}] request scope bean create : {}"), uuid, this);
    }

    @PreDestroy
    public void destroy() {
        log.info(getFormat(TextColor.GRAY, "[{}] request scope bean destroy : {}"), uuid, this);
    }

}
