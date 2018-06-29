package com.shuanghe.dynamicCompilation.java.demo3;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

/**
 * Created by yushuanghe on 2017/08/25.
 */
public abstract class Utils {
    public static final String ENCODING = Charset.defaultCharset().name();

    /**
     * 把String转换成URI，如果转换异常不抛出URISyntaxException，而直接抛出RuntimeException。
     */
    static URI toURI(String name) {
        try {
            return new URI(name);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
