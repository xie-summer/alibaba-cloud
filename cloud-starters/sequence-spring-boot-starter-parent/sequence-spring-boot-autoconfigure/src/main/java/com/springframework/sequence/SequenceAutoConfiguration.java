package com.springframework.sequence;

import com.springframework.sequence.support.IWorker;
import com.springframework.sequence.support.Sequence;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fuwei.deng
 * @date 2018年1月25日 下午4:21:10
 * @version 1.0.0
 */
@Configuration
public class SequenceAutoConfiguration {

	@Bean
	@ConditionalOnBean(IWorker.class)
	public Sequence sequence(IWorker worker) {
		return new Sequence(worker);
	}
}
