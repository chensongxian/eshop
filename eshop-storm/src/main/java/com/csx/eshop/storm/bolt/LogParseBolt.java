package com.csx.eshop.storm.bolt;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志解析的bolt
 * @author Administrator
 *
 */
public class LogParseBolt extends BaseRichBolt {

	private static final long serialVersionUID = -8017609899644290359L;

	private OutputCollector collector;

	private static final Logger LOGGER = LoggerFactory.getLogger(LogParseBolt.class);
	
	@Override
	@SuppressWarnings("rawtypes")
	public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
	}
	
	@Override
	public void execute(Tuple tuple) {
		String message = tuple.getStringByField("message");

		LOGGER.info("【LogParseBolt接收到一条日志】message=" + message);

		JSONObject messageJSON = JSONObject.parseObject(message);
		JSONObject uriArgsJSON = messageJSON.getJSONObject("uri_args"); 
		Long productId = uriArgsJSON.getLong("productId"); 
		
		if(productId != null) {
			collector.emit(new Values(productId));
			LOGGER.info("【LogParseBolt发射出去一个商品id】productId=" + productId);
		}
	}
	
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("productId"));   
	}

}
