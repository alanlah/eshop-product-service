package cn.eshop.eureka.service;

import java.util.List;

import cn.eshop.eureka.pojo.Brand;

public interface BrandService {
	
	public void add(Brand brand, String operationType);
	
	public void update(Brand brand, String operationType);
	
	public void delete(Long id, String operationType);
	
	public Brand findById(Long id);
	
	public List<Brand> findByIds(String ids);
	
}
