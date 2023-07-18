package hello.spring.spring.basic.web;

import hello.spring.spring.basic.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class LoggerDemoController {

    private final LogDemoService logDemoService;
    private final ObjectProvider<MyLogger> myLoggerObjectProvider;

    @GetMapping
    public String loggerTest(HttpServletRequest httpServletRequest) {
        MyLogger myLogger = myLoggerObjectProvider.getObject();
        StringBuffer requestURL = httpServletRequest.getRequestURL();
        myLogger.setRequestUrl(requestURL.toString());
        myLogger.log("controller test");
        logDemoService.logic("test id");
        return "OK!";
    }

}
