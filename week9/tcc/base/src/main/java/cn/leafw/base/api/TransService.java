package cn.leafw.base.api;

/**
 * TODO
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2022/6/29
 */
public interface TransService {

    /**
     * A交易
     */
    void transA();

    /**
     * B交易
     */
    void transB();

    /**
     * A冻结
     */
    void frozeA();

    /**
     * B冻结
     */
    void frozeB();

    /**
     * A冻结
     */
    void unFrozeA();

    /**
     * B冻结
     */
    void unFrozeB();
}

