package com.springmvc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Conditions;

import com.springmvc.bean.WindowWindowInfo;

@Service
public interface JdbcService {
	
	/**
	 * SQL.query
	 * @param 
	 * @return list
	 */
	public List<WindowWindowInfo> stuffQuery(Conditions conds,String suffix); 
	
	/**
	 * 查询所有数据（测试）
	 * 
	 * @param
	 * @return Integer
	 */
	public Integer getAll();
	
	/**
	 * 查询所有数据
	 * 
	 * @param 
	 * @return list
	 */
	public List<WindowWindowInfo> queryAll();
	
	/**
	 * 分页查询
	 * 
	 * @param conds
	 * @param suffix
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public PaginationArrayList<WindowWindowInfo> windowInfo(Conditions conds, String suffix,
			int pageSize, int currentPage);
	
	/**
	 * 根据表名查询总页数（每页20条）
	 * 
	 * @param tableName
	 * @return
	 */
	public int queryPage(String tableName);
	
	
	public List<WindowWindowInfo> queryOrgan(String nmOrganNodeId);
	
	public List<WindowWindowInfo> queryWindowId(Conditions conds,String suffix);
	
}
