package com.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Conditions;

import com.springmvc.bean.WindowWindowInfo;
import com.springmvc.dao.JdbcDao;
import com.springmvc.service.JdbcService;

@Service
public class JdbcServiceImpl implements JdbcService {

	@Autowired
	JdbcDao jdbcDao;

	/**
	 * 查询表中所有数据并返回
	 * 
	 * @param
	 * @return list
	 */
	public List<WindowWindowInfo> queryAll() {

		return jdbcDao.queryAll();
	}

	/**
	 * 测试是否查询到表中数据
	 * 
	 * @param
	 * @return Integer
	 */
	public Integer getAll() {

		return jdbcDao.getAll();
	}

	/**
	 * SQL.query查询语句练习
	 * 
	 * @param conds,suffix
	 * @return list
	 */
	public List<WindowWindowInfo> stuffQuery(Conditions conds, String suffix) {

		return jdbcDao.stuffQuery(conds, suffix);
	}

	/**
	 * SQL.execute查询语句练习
	 * 
	 * @param nmOrganNodeId
	 * @return list
	 */
	public List<WindowWindowInfo> queryOrgan(String nmOrganNodeId) {
		return jdbcDao.queryOrgan(nmOrganNodeId);
	}

	/**
	 * 查询语句练习
	 * 
	 * @param conds,suffix
	 * @return list
	 */
	public List<WindowWindowInfo> queryWindowId(Conditions conds, String suffix) {
		return jdbcDao.queryWindowId(conds, suffix);
	}

	/**
	 * 查询语句练习
	 * 
	 * @param conds, suffix, pageSize, currentPage
	 * @return list
	 */
	public PaginationArrayList<WindowWindowInfo> windowInfo(Conditions conds,
			String suffix, int pageSize, int currentPage) {
		return jdbcDao.query(conds, suffix, pageSize, currentPage);
	}
	
	/**
	 * 根据表名查询页数
	 * @param tableName
	 * @return
	 */
	public int queryPage(String tableName) {

		return jdbcDao.queryPage(tableName);
	}
}
