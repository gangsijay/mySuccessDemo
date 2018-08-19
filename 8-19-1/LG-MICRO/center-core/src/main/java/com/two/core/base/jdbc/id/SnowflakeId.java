package com.two.core.base.jdbc.id;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import com.two.core.base.common.util.Sequence;

/**
 * 功能描述: 集成jpa自增策略
 *
 * @Auther: 田杰
 * @Date: 2018/5/9 22:30
 */
public class SnowflakeId implements IdentifierGenerator {
    Sequence sequence = new Sequence(0, 0);

	@Override
	public Serializable generate(SharedSessionContractImplementor arg0, Object arg1) throws HibernateException {
		return sequence.nextId();
	}
}
