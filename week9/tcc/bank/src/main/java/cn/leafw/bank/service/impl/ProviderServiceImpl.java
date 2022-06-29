package cn.leafw.bank.service.impl;

import cn.leafw.base.api.ProviderService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * TODO
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2022/6/29
 */
@DubboService
public class ProviderServiceImpl implements ProviderService {
    @Override
    public String test() {
        return "---";
    }
}

