package cn.leafw.bank.service.impl;

import cn.leafw.bank.annotations.DbB;
import cn.leafw.bank.entity.Account;
import cn.leafw.bank.entity.Frozen;
import cn.leafw.bank.mapper.AccountMapper;
import cn.leafw.bank.mapper.FrozenMapper;
import cn.leafw.bank.service.AccountService;
import cn.leafw.bank.service.FrozenService;
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
public class FrozeServiceImpl extends ServiceImpl<FrozenMapper, Frozen> implements FrozenService {

    @Override
    public void frozeA() {

    }

    @DbB
    @Override
    public void frozeB() {

    }

    @Override
    public void unFrozeA() {

    }

    @DbB
    @Override
    public void unFrozeB() {

    }
}

