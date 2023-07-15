package hello.spring.spring.basic;

import static hello.spring.spring.basic.TextColor.RESET;


public class CommonUtil {

    public static String getFormat(TextColor textColor, String logFormat) {
        return textColor.getColor() + logFormat + RESET.getColor();
    }

}
