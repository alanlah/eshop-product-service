package cn.eshop.eureka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.eshop.eureka.mapper.CategoryMapper;
import cn.eshop.eureka.pojo.Category;
import cn.eshop.eureka.rabbit.RabbitMQSender;
import cn.eshop.eureka.rabbit.RabbitQueue;
import cn.eshop.eureka.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;
	@Autowired
	private RabbitMQSender rabbitMQSender;
	
	public void add(Category category, String operationType) {
		categoryMapper.add(category); 
		
		String queue = null;
		
		if(operationType == null || "".equals(operationType)) {
			queue = RabbitQueue.DATA_CHANGE_QUEUE;
		} else if("refresh".equals(operationType)) {
			queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
		} else if("high".equals(operationType)) {
			queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
		}
		
		rabbitMQSender.send(queue, "{\"event_type\": \"add\", \"data_type\": \"category\", \"id\": " + category.getId() + "}");
	}

	public void update(Category category, String operationType) {
		categoryMapper.update(category); 
		
		String queue = null;
		
		if(operationType == null || "".equals(operationType)) {
			queue = RabbitQueue.DATA_CHANGE_QUEUE;
		} else if("refresh".equals(operationType)) {
			queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
		} else if("high".equals(operationType)) {
			queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
		}
		
		rabbitMQSender.send(queue, "{\"event_type\": \"update\", \"data_type\": \"category\", \"id\": " + category.getId() + "}");
	}

	public void delete(Long id, String operationType) {
		categoryMapper.delete(id);
		
		String queue = null;
		
		if(operationType == null || "".equals(operationType)) {
			queue = RabbitQueue.DATA_CHANGE_QUEUE;
		} else if("refresh".equals(operationType)) {
			queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
		} else if("high".equals(operationType)) {
			queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
		}
		
		rabbitMQSender.send(queue, "{\"event_type\": \"delete\", \"data_type\": \"category\", \"id\": " + id + "}");
	}

	public Category findById(Long id) {
		return categoryMapper.findById(id);
	}

}
