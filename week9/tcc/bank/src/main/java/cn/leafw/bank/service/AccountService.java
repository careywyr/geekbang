package cn.leafw.bank.service;

import cn.leafw.bank.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * TODO
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2022/6/29
 */
public interface AccountService extends IService<Account> {

    /**
     * A交易
     */
    void transA();

    /**
     * B交易
     */
    void transB();
}

