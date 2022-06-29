package cn.leafw.bank.service.impl;

import cn.leafw.bank.service.AccountService;
import cn.leafw.bank.service.FrozenService;
import cn.leafw.base.api.TransService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * 对于数据库直接操作的这里包一层
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2022/6/29
 */
@DubboService
public class TransServiceImpl implements TransService {

    @Resource
    private AccountService accountService;
    @Resource
    private FrozenService frozenService;

    @Override
    public void transA() {
        accountService.transA();
    }

    @Override
    public void transB() {
        accountService.transB();
    }

    @Override
    public void frozeA() {
        frozenService.frozeA();
    }

    @Override
    public void frozeB() {
        frozenService.frozeB();
    }

    @Override
    public void unFrozeA() {
        frozenService.unFrozeA();
    }

    @Override
    public void unFrozeB() {
        frozenService.unFrozeB();
    }

}

