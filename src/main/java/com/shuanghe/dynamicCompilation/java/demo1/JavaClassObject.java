package com.shuanghe.dynamicCompilation.java.demo1;

import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

/**
 * Created by yushuanghe on 2017/08/24.
 */
public class JavaClassObject extends SimpleJavaFileObject {

    protected final ByteArrayOutputStream bos =
            new ByteArrayOutputStream();


    public JavaClassObject(String name, JavaFileObject.Kind kind) {
        super(URI.create("string:///" + name.replace('.', '/')
                + kind.extension), kind);
    }


    public byte[] getBytes() {
        return bos.toByteArray();
    }

    @Override
    public OutputStream openOutputStream() throws IOException {
        return bos;
    }
}
