package io.kimmking.rpcfx.demo.provider;

import io.kimmking.rpcfx.api.RpcfxResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@Slf4j
public class DemoResolver implements RpcfxResolver, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object resolve(String serviceClass) {
        try {
            Class<?> aClass = Class.forName(serviceClass);
            if (aClass.isInterface()) {
                String[] split = serviceClass.split("\\.");
                String className = "io.kimmking.rpcfx.demo.provider." + split[split.length - 1] + "Impl";
                Class<?> implClass = Class.forName(className);
                return implClass.newInstance();
            }
            return aClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return this.applicationContext.getBean(serviceClass);
    }
}
