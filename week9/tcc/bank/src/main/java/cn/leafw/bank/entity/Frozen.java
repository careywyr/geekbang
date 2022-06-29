package cn.leafw.bank.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * TODO
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2022/6/29
 */
public class Frozen implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;

    private BigDecimal cny;

    private BigDecimal us;
}

