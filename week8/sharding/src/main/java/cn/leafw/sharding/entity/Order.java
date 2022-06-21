package cn.leafw.sharding.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author leafw
 * @since 2022-06-13
 */
@Data
@TableName("t_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long orderId;

    private Long userId;

    private Long commodityId;

    private String commodityName;

    private BigDecimal unitPrice;

    private Integer quantity;

    private BigDecimal totalAmount;

    private BigDecimal realAmount;

    private Long createTime;

    private Long creator;

    private Long updateTime;

    private Long updater;


    @Override
    public String toString() {
        return "Order{" +
            "orderId=" + orderId +
            ", userId=" + userId +
            ", commodityId=" + commodityId +
            ", commodityName=" + commodityName +
            ", unitPrice=" + unitPrice +
            ", quantity=" + quantity +
            ", totalAmount=" + totalAmount +
            ", realAmount=" + realAmount +
            ", createTime=" + createTime +
            ", creator=" + creator +
            ", updateTime=" + updateTime +
            ", updater=" + updater +
        "}";
    }
}
