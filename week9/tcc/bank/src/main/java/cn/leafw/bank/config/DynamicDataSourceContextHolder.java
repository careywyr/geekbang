package cn.leafw.bank.config;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * DynamicDataSourceContextHolder
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2022/6/15
 */
public class DynamicDataSourceContextHolder {

    public static List<Object> dataSourceKeys = new ArrayList<>();

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "master";
        }
    };

    /**
     * 切换数据源
     * @param key 数据源
     */
    public static void setDataSourceKey(String key) {
        if (StringUtils.isNotBlank(key)) {
            contextHolder.set(key);
        }
    }
    /**
     * 获取数据源
     * @return
     */
    public static String getDataSourceKey() {
        return contextHolder.get();
    }
    /**
     * 重置数据源
     */
    public static void clearDataSourceKey() {
        contextHolder.remove();
    }
    /**
     * 判断是否包含数据源
     * @param key 数据源
     * @return
     */
    public static boolean containDataSourceKey(String key) {
        return dataSourceKeys.contains(key);
    }
    /**
     * 添加数据源Keys
     * @param keys
     * @return
     */
    public static boolean addDataSourceKeys(Collection<? extends Object> keys) {
        return dataSourceKeys.addAll(keys);
    }
}

