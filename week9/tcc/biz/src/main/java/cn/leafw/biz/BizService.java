package cn.leafw.biz;

import cn.leafw.base.api.TransService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2022/6/29
 */
@Slf4j
@Service
public class BizService {

    @DubboReference
    private TransService transService;

    /**
     * 锁定资源
     */
    @HmilyTCC(confirmMethod = "sayConfirm", cancelMethod = "sayCancel")
    public void trans() {
        transService.frozeA();
        transService.frozeB();
    }

    /**
     * 真的修改资产数据，并解冻
     */
    public void sayConfirm() {
        log.info(" confirm hello world");
        transService.transA();
        transService.transB();
        transService.unFrozeA();
        transService.unFrozeB();
    }

    /**
     * 解冻回退
     */
    public void sayCancel() {
        log.info(" cancel hello world");
    }
}

