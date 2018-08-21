package cn.eshop.eureka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.eshop.eureka.mapper.ProductIntroMapper;
import cn.eshop.eureka.pojo.ProductIntro;
import cn.eshop.eureka.rabbit.RabbitMQSender;
import cn.eshop.eureka.rabbit.RabbitQueue;
import cn.eshop.eureka.service.ProductIntroService;

@Service
public class ProductIntroServiceImpl implements ProductIntroService {

	@Autowired
	private ProductIntroMapper productIntroMapper;
	@Autowired
	private RabbitMQSender rabbitMQSender;
	
	public void add(ProductIntro productIntro, String operationType) {
		productIntroMapper.add(productIntro); 
		
		String queue = null;
		
		if(operationType == null || "".equals(operationType)) {
			queue = RabbitQueue.DATA_CHANGE_QUEUE;
		} else if("refresh".equals(operationType)) {
			queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
		} else if("high".equals(operationType)) {
			queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
		}
		
		rabbitMQSender.send(queue, "{\"event_type\": \"add\", \"data_type\": \"product_intro\", \"id\": " + productIntro.getId() + ", \"product_id\": " + productIntro.getProductId() + "}");
	}

	public void update(ProductIntro productIntro, String operationType) {
		productIntroMapper.update(productIntro); 
		
		String queue = null;
		
		if(operationType == null || "".equals(operationType)) {
			queue = RabbitQueue.DATA_CHANGE_QUEUE;
		} else if("refresh".equals(operationType)) {
			queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
		} else if("high".equals(operationType)) {
			queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
		}
		
		rabbitMQSender.send(queue, "{\"event_type\": \"update\", \"data_type\": \"product_intro\", \"id\": " + productIntro.getId() + ", \"product_id\": " + productIntro.getProductId() + "}");
	}

	public void delete(Long id, String operationType) {
		ProductIntro productIntro = findById(id);
		productIntroMapper.delete(id); 
		
		String queue = null;
		
		if(operationType == null || "".equals(operationType)) {
			queue = RabbitQueue.DATA_CHANGE_QUEUE;
		} else if("refresh".equals(operationType)) {
			queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
		} else if("high".equals(operationType)) {
			queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
		}
		
		rabbitMQSender.send(queue, "{\"event_type\": \"delete\", \"data_type\": \"product_intro\", \"id\": " + id + ", \"product_id\": " + productIntro.getProductId() + "}");
	}

	public ProductIntro findById(Long id) {
		return productIntroMapper.findById(id);
	}

}