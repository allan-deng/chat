/**
 * @Title: ClientMap.java
 * @Package cn.allandeng.server.model
 * @Description: TODO
 * Copyright: Copyright (c) 2019
 * 
 * @author 邓依伦
 * @date 2019年10月31日 下午1:31:02
 * @version V1.0
 */
package cn.allandeng.server.model;

import java.sql.ResultSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
  * @ClassName: ClientMap
  * @Description: 使用HashMap对象实现ClientsMap
  * @author 邓依伦-Allan
  * @date 2019年10月31日 下午1:31:02
  *
  */
public class ClientsMap<K, V>  {
	//线程安全的HashMap,不指定KV的类型，以后好改
	public Map<K, V> map = Collections.synchronizedMap(new HashMap<K, V>());
	
	//hashmap中已经实现了使用key对元素的一系列操作，这里添加使用value的一系列操作
	//根据value删除项
	public synchronized void removeByValue(Object value) {
		for(Object key:map.keySet()) {
			if(map.get(key) == value || map.get(key).equals(value)) {
				map.remove(key);
				break;
			}
		}
	}
	//创建value的set集合
	public synchronized Set<V> valueSet(){
		Set<V> resultSet = new HashSet<>();
		for(Object key:map.keySet()) {
			resultSet.add(map.get(key));
		}		
		return resultSet;
	}
	//根据value查找key
	public synchronized K getKeyByValue(V value) {
		for(K key:map.keySet()) {
			if (map.get(key) == value || map.get(key).equals(value)) {
				return key ; 
			}
		}
		return null ;
	}
	//put方法 ，不允许value重复
	public synchronized V put(K key ,V value) {
		//遍历value的集合
		for(V val:valueSet()) {
			if (val.equals(value) && val == value) {
				throw new RuntimeException("ClientsMap中value不可重复");
			}
		}
		return map.put(key, value);
		
	}
}
