package cn.leafw.bank.mapper;

import cn.leafw.bank.entity.Account;
import cn.leafw.bank.entity.Frozen;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author leafw
 */
@Mapper
public interface FrozenMapper extends BaseMapper<Frozen> {

}
