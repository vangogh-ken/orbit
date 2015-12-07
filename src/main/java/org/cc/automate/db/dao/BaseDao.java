package org.cc.automate.db.dao;

import java.util.List;

import org.cc.automate.core.Pager;


/**
 * 集合持久层的公用的增，删，改，查接口 <T> 表示传入实体类
 * 
 * @param <T>
 */
public interface BaseDao<T> {
	public List<T> query(Pager pager, T t);

	public List<T> queryForList(T t);
	
	public T queryForOne(T t);

	public List<T> getAll();

	public int delete(String id);

	public int modify(T t);

	public T getById(String id);

	public int add(T t);
	
	public int count(T t);
	
	public int insertBatch(List<T> list);
	
	public int updateBatch(List<T> list);
	
	public int deleteBatch(List<T> list);
}
