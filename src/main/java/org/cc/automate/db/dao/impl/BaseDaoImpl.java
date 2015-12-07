package org.cc.automate.db.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cc.automate.core.Pager;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 集合持久层的公用的增，删，改，查类 <T> 表示传入实体类
 * @param <T>
 */
public class BaseDaoImpl<T> extends SqlSessionDaoSupport {
	private static Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);
	
	@Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }
	/**
	 * 获取传过来的泛型类名字
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getClassName() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		Class<T> clazz = (Class) pt.getActualTypeArguments()[0];
		return clazz.getSimpleName().toString().toLowerCase();
	}
	
	// 查询，分页
	public List<T> query(Pager pager, T t) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("pager", pager);
		map.put("t", t);
		return getSqlSession().selectList(this.getClassName() + ".query", map);
	}

	//查询，不分页
	public List<T> queryForList(T t) {
		return getSqlSession().selectList(this.getClassName() + ".queryForList", t);
	}
	
	public T queryForOne(T t){
		List<T> list = getSqlSession().selectList(this.getClassName() + ".queryForList", t);
		if(list != null && !list.isEmpty()){
			if(list.size() == 1){	//size等于1
				return list.get(0);
			}else{					//size大于1
				logger.info("记录数大于1, 返回第一条记录。");
				return list.get(0);
			}
		}else{
			logger.info("记录数为0, 返回空。");
			return null;
		}
	}

	// 返回所有数据，没有查询条件
	public List<T> getAll() {
		return getSqlSession().selectList(this.getClassName() + ".getAll");
	}

	public int add(T t) {
		return getSqlSession().insert(this.getClassName() + ".add", t);
	}
	
	public int delete(String id) {
		return getSqlSession().delete(this.getClassName() + ".deleteById", id);
	}

	@SuppressWarnings("unchecked")
	public T getById(String id) {
		return (T) getSqlSession().selectOne(this.getClassName() + ".getById", id);
	}

	public int modify(T t) {
		return getSqlSession().update(this.getClassName() + ".update", t);
	}
	
	public int count(T t){
		return Integer.parseInt(getSqlSession().selectOne(this.getClassName() + ".count", t).toString());
	}
	
	public int insertBatch(List<T> list) {
		return getSqlSession().insert(this.getClassName() + ".insertBatch", list);
	}
	
	public int updateBatch(List<T> list) {
		return getSqlSession().update(this.getClassName() + ".updateBatch", list);
	}
	
	public int deleteBatch(List<T> list){
		return getSqlSession().delete(this.getClassName() + ".deleteBatch", list);
	}
}
