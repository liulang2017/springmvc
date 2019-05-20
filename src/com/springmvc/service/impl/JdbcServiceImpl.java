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
	 * ��ѯ�����������ݲ�����
	 * 
	 * @param
	 * @return list
	 */
	public List<WindowWindowInfo> queryAll() {

		return jdbcDao.queryAll();
	}

	/**
	 * �����Ƿ��ѯ����������
	 * 
	 * @param
	 * @return Integer
	 */
	public Integer getAll() {

		return jdbcDao.getAll();
	}

	/**
	 * SQL.query��ѯ�����ϰ
	 * 
	 * @param conds,suffix
	 * @return list
	 */
	public List<WindowWindowInfo> stuffQuery(Conditions conds, String suffix) {

		return jdbcDao.stuffQuery(conds, suffix);
	}

	/**
	 * SQL.execute��ѯ�����ϰ
	 * 
	 * @param nmOrganNodeId
	 * @return list
	 */
	public List<WindowWindowInfo> queryOrgan(String nmOrganNodeId) {
		return jdbcDao.queryOrgan(nmOrganNodeId);
	}

	/**
	 * ��ѯ�����ϰ
	 * 
	 * @param conds,suffix
	 * @return list
	 */
	public List<WindowWindowInfo> queryWindowId(Conditions conds, String suffix) {
		return jdbcDao.queryWindowId(conds, suffix);
	}

	/**
	 * ��ѯ�����ϰ
	 * 
	 * @param conds, suffix, pageSize, currentPage
	 * @return list
	 */
	public PaginationArrayList<WindowWindowInfo> windowInfo(Conditions conds,
			String suffix, int pageSize, int currentPage) {
		return jdbcDao.query(conds, suffix, pageSize, currentPage);
	}
	
	/**
	 * ���ݱ�����ѯҳ��
	 * @param tableName
	 * @return
	 */
	public int queryPage(String tableName) {

		return jdbcDao.queryPage(tableName);
	}
}
