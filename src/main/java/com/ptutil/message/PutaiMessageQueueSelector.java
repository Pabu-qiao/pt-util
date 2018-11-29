package com.ptutil.message;

import java.util.List;

import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

/**
 * @ClassName: PutaiMessageQueueSelector
 * @Description: 消息queue的选择器，相同Id的对象放到一个queue里面
 * @author 谯雕
 * @date 2018年11月5日
 *
 */
public class PutaiMessageQueueSelector implements MessageQueueSelector {

	public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
		long id = Long.parseLong(arg.toString());
		int size = mqs.size();
        int index = (int) (id % size);
        return mqs.get(index);
	}

}
