package com.ptutil.consumer;

import org.apache.commons.lang.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;

import com.ptutil.message.MessageTag;
import com.ptutil.message.MessageTopic;
import com.ptutil.ptbase.PtBaseConsumer;
import com.ptutil.ptbase.PtBaseService;

/**
 * @ClassName: ConsumerBuilder
 * @Description: 消费者建造工具
 * @author 谯雕
 * @date 2018年11月29日
 *
 */
public interface ConsumerBuilder {
	
	/**
	 * @Title: createConsumer
	 * @Description: 绑定各自的consumer
	 * @return
	 */
	DefaultMQPushConsumer bindConsumer();

	default ConsumerBuilder setConsumerGroup(String consumerGroup) {
		bindConsumer().setConsumerGroup(consumerGroup);
		return this;
	}

	default ConsumerBuilder setNamesrvAddr(String namesrvAddr) {
		bindConsumer().setNamesrvAddr(namesrvAddr);
		return this;
	}

	default ConsumerBuilder setConsumeFromWhere(ConsumeFromWhere fromWhere) {
		bindConsumer().setConsumeFromWhere(fromWhere);
		return this;
	}

	default ConsumerBuilder setSubscribe(MessageTopic messageTopic, MessageTag messageTag) {
		String topic = messageTopic.toString();
		String tag = messageTag.toString();
		tag = StringUtils.equalsIgnoreCase("all", tag) ? "*" : tag;
		try {
			bindConsumer().subscribe(topic, tag);
		} catch (MQClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}

	default ConsumerBuilder setConsumeThreadMax(Integer consumeThreadMax) {
		bindConsumer().setConsumeThreadMax(consumeThreadMax);
		return this;
	}

	default ConsumerBuilder setConsumeThreadMin(Integer consumeThreadMin) {
		bindConsumer().setConsumeThreadMin(consumeThreadMin);
		return this;
	}

	default ConsumerBuilder setConsumeMessageBatchMaxSize(Integer consumeMessageBatchMaxSize) {
		bindConsumer().setConsumeMessageBatchMaxSize(consumeMessageBatchMaxSize);
		return this;
	}

	
	/**
	 * @Title: build
	 * @Description: 传入service绑定到实例中。
	 */
	@SuppressWarnings("unchecked")
	public<T extends PtBaseService> PtBaseConsumer build(T...services);
}
