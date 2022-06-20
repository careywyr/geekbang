package cn.leafw.trans.service;

import cn.leafw.trans.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author leafw
 * @since 2022-06-13
 */
public interface IOrderService {

    void success();

    void fail();

    void noTrans();
}
