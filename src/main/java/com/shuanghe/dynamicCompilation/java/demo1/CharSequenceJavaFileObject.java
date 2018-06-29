package com.shuanghe.dynamicCompilation.java.demo1;

import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import java.net.URI;

/**
 * Created by yushuanghe on 2017/08/24.
 */
public class CharSequenceJavaFileObject extends SimpleJavaFileObject {

    private CharSequence content;


    public CharSequenceJavaFileObject(String className,
                                      CharSequence content) {
        super(URI.create("string:///" + className.replace('.', '/')
                + JavaFileObject.Kind.SOURCE.extension), JavaFileObject.Kind.SOURCE);
        this.content = content;
    }

    @Override
    public CharSequence getCharContent(
            boolean ignoreEncodingErrors) {
        return content;
    }
}
