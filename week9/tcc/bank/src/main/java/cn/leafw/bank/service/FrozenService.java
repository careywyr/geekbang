package cn.leafw.bank.service;

import cn.leafw.bank.entity.Account;
import cn.leafw.bank.entity.Frozen;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2022/6/29
 */
@Service
public interface FrozenService extends IService<Frozen> {

    /**
     * A冻结
     */
    void frozeA();

    /**
     * B冻结
     */
    void frozeB();

    /**
     * A解冻
     */
    void unFrozeA();

    /**
     * B解冻
     */
    void unFrozeB();
}

