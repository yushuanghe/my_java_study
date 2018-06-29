package com.shuanghe.dynamicCompilation.java.demo3;


import org.apache.commons.io.IOUtils;

import javax.tools.JavaFileObject;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * {@link ClassLoader}的一个实现，它map类名和JavaFileObjectImpl的实例。本类在{@link CharSequenceCompiler}和{@link FileManagerImpl}中被使用。
 * Created by yushuanghe on 2017/08/25.
 */
final class ClassLoaderImpl extends ClassLoader {
    private final Map<String, JavaFileObjectImpl> classes = new HashMap<String, JavaFileObjectImpl>();

    ClassLoaderImpl(final ClassLoader parentClassLoader) {
        super(parentClassLoader);
    }

    @Override
    public InputStream getResourceAsStream(final String name) {
        if (name.endsWith(".class")) {
            String qualifiedClassName = name.substring(0, name.length() - ".class".length())
                    .replace('/', '.');
            JavaFileObjectImpl file = classes.get(qualifiedClassName);
            if (file != null) {
                try {
                    return new ByteArrayInputStream(IOUtils.toByteArray(file.openInputStream()));
                } catch (IOException ex) {
                }
            }
        }
        return super.getResourceAsStream(name);
    }

    protected void add(final String qualifiedClassName, final JavaFileObjectImpl javaFile) {
        classes.put(qualifiedClassName, javaFile);
    }

    /**
     * @return 返回不可变的Collection，含有所有持有的{@link JavaFileObject}对象
     */
    protected Collection<JavaFileObjectImpl> files() {
        return Collections.unmodifiableCollection(classes.values());
    }

    @Override
    protected Class<?> findClass(final String qualifiedClassName) throws ClassNotFoundException {
        JavaFileObject file = classes.get(qualifiedClassName);
        if (file != null) {
            try {
                byte[] bytes = IOUtils.toByteArray(file.openInputStream());
                return defineClass(qualifiedClassName, bytes, 0, bytes.length);
            } catch (IOException ex) {
            }
        }
        // Workaround in <a href="http://lib.csdn.net/base/java" class='replace_word' title="Java 知识库" target='_blank' style='color:#df3434; font-weight:bold;'>java </a>6. see http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6434149
        try {
            Class<?> c = Class.forName(qualifiedClassName);
            return c;
        } catch (ClassNotFoundException nf) {
        }
        return super.findClass(qualifiedClassName);
    }

    @Override
    protected synchronized Class<?> loadClass(final String name, final boolean resolve)
            throws ClassNotFoundException {
        return super.loadClass(name, resolve);
    }
}