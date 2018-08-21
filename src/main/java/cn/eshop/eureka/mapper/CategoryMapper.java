package cn.eshop.eureka.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.eshop.eureka.pojo.Category;

@Mapper
public interface CategoryMapper {
	
	@Insert("INSERT INTO category(name,description) VALUES(#{name},#{description})")  
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	public void add(Category category);
	
	@Update("UPDATE category SET name=#{name},description=#{description} WHERE id=#{id}")  
	public void update(Category category);
	
	@Delete("DELETE FROM category WHERE id=#{id}")  
	public void delete(Long id);
	
	@Select("SELECT * FROM category WHERE id=#{id}")  
	@Results({ @Result(column = "product_id", property = "productId") })
	public Category findById(Long id);
	
}
