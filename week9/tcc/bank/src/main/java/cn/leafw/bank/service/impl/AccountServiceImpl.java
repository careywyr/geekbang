package cn.leafw.bank.service.impl;

import cn.leafw.bank.annotations.DbB;
import cn.leafw.bank.entity.Account;
import cn.leafw.bank.mapper.AccountMapper;
import cn.leafw.bank.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2022/6/29
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Override
    public void transA() {

    }

    @DbB
    @Override
    public void transB() {

    }
}

