package com.two.core.base.jdbc.id;

import org.springframework.stereotype.Component;

import com.two.core.base.common.util.Sequence;


/**
 * 功能描述:
 *
 * @Auther: 田杰
 * @Date: 2018/4/14 02:04
 */
@Component
public class IdWorker  {
    Sequence sequence = new Sequence(0, 0);
    public Long nextId() {
        return sequence.nextId();
    }
}
