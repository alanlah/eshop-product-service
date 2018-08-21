package cn.eshop.eureka.service;

import cn.eshop.eureka.pojo.Product;

public interface ProductService {
	
	public void add(Product product, String operationType);
	
	public void update(Product product, String operationType);
	
	public void delete(Long id, String operationType);
	
	public Product findById(Long id);
	
}
