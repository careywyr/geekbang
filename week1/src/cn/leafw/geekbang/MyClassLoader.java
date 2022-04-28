package cn.leafw.geekbang;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 自定义一个 Classloader，加载一个 Hello.xlass 文件，
 * 执行 Hello 方法，此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2022/4/28
 */
public class MyClassLoader extends ClassLoader{

    public static void main(String[] args) {
        try {
            Object helloInstance = new MyClassLoader().findClass("Hello").newInstance();
            Class<?> helloClass = helloInstance.getClass();
            Method helloMethod = helloClass.getMethod("hello");
            helloMethod.invoke(helloInstance);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String rootPath= System.getProperty("user.dir");
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(rootPath + "/attachments/Hello.xlass"));
            byte[] origin = new byte[bytes.length];
            for (int i = 0; i < bytes.length; i++) {
                byte aByte = bytes[i];
                origin[i] = (byte) ((byte) 255 - aByte);
            }
            return defineClass(name, origin, 0, origin.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }
}

