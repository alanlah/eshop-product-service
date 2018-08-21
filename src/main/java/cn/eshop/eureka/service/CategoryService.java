package cn.eshop.eureka.service;

import cn.eshop.eureka.pojo.Category;

public interface CategoryService {
	
	public void add(Category category, String operationType);
	
	public void update(Category category, String operationType);
	
	public void delete(Long id, String operationType);
	
	public Category findById(Long id);
	
}
