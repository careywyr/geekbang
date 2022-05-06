package cn.leafw.xar;

import com.sprylab.xar.FileXarSource;
import com.sprylab.xar.XarEntry;
import com.sprylab.xar.XarException;
import com.sprylab.xar.XarSource;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 20-实现xlass打包的xar（类似class文件打包的jar）的加载：xar里是xlass
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2022/4/30
 */
public class XarClassLoader extends ClassLoader{

    public static void main(String[] args) {
        try {
            Object helloInstance = new XarClassLoader().findClass("Hello").newInstance();
            Class<?> helloClass = helloInstance.getClass();
            Method helloMethod = helloClass.getMethod("hello");
            helloMethod.invoke(helloInstance);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String path = Objects.requireNonNull(this.getClass().getClassLoader().getResource("hello.xar")).getPath();
        File file = new File(path);
        XarSource xar = new FileXarSource(file);
        try {
            List<XarEntry> entries = xar.getEntries();
            Optional<XarEntry> first = entries.stream().filter(item -> !item.isDirectory()).findFirst();
            if (!first.isPresent()) {
                return super.findClass(name);
            }
            byte[] bytes = first.get().getBytes();
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

