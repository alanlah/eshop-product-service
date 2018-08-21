package cn.eshop.eureka.service;

import cn.eshop.eureka.pojo.ProductSpecification;

public interface ProductSpecificationService {
	
	public void add(ProductSpecification productSpecification, String operationType);
	
	public void update(ProductSpecification productSpecification, String operationType);
	
	public void delete(Long id, String operationType);
	
	public ProductSpecification findById(Long id);
	
	public ProductSpecification findByProductId(Long productId);
	
}
