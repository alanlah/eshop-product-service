package cn.eshop.eureka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.eshop.eureka.mapper.BrandMapper;
import cn.eshop.eureka.pojo.Brand;
import cn.eshop.eureka.rabbit.RabbitMQSender;
import cn.eshop.eureka.rabbit.RabbitQueue;
import cn.eshop.eureka.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandMapper brandMapper;
	@Autowired
	private RabbitMQSender rabbitMQSender;
	
	public void add(Brand brand, String operationType) {
		brandMapper.add(brand); 
		
		String queue = null;
		
		if(operationType == null || "".equals(operationType)) {
			queue = RabbitQueue.DATA_CHANGE_QUEUE;
		} else if("refresh".equals(operationType)) {
			queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
		} else if("high".equals(operationType)) {
			queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
		}
		
		rabbitMQSender.send(queue, "{\"event_type\": \"add\", \"data_type\": \"brand\", \"id\": " + brand.getId() + "}");
	}
	
	public void update(Brand brand, String operationType) {
		brandMapper.update(brand); 
		
		String queue = null;
		
		if(operationType == null || "".equals(operationType)) {
			queue = RabbitQueue.DATA_CHANGE_QUEUE;
		} else if("refresh".equals(operationType)) {
			queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
		} else if("high".equals(operationType)) {
			queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
		}
		
		rabbitMQSender.send(queue, "{\"event_type\": \"update\", \"data_type\": \"brand\", \"id\": " + brand.getId() + "}");
	}

	public void delete(Long id, String operationType) {
		brandMapper.delete(id); 
		
		String queue = null;
		
		if(operationType == null || "".equals(operationType)) {
			queue = RabbitQueue.DATA_CHANGE_QUEUE;
		} else if("refresh".equals(operationType)) {
			queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
		} else if("high".equals(operationType)) {
			queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
		}
		
		rabbitMQSender.send(queue, "{\"event_type\": \"delete\", \"data_type\": \"brand\", \"id\": " + id + "}");
	}

	public Brand findById(Long id) {
		return brandMapper.findById(id);
	}

	public List<Brand> findByIds(String ids) {
		return brandMapper.findByIds(ids);
	}

}
