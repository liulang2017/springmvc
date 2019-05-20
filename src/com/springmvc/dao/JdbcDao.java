package com.springmvc.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Conditions;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

import com.springmvc.bean.WindowWindowInfo;
import com.springmvc.util.ConnectionMysql;

@Repository
public class JdbcDao {

	ConnectionMysql mysql = new ConnectionMysql();
	Connection conn = mysql.getConn();

	/**
	 * 查询所有数据
	 * @return list
	 */
	public List<WindowWindowInfo> queryAll() {
		String sql = "select * from window_window_info ";
		List<WindowWindowInfo> list = new ArrayList<WindowWindowInfo>();
		RecordSet rs = SQL.execute(conn, sql);
		while (rs.next()) {
			WindowWindowInfo info = new WindowWindowInfo();
			setProperties(info, rs);
			list.add(info);
		}
		return list;
	}

	/**
	 * NM_ORGAN_NODE_ID条件查询nmOrganNodeId
	 * 
	 * @param String
	 * @return list
	 */
	public List<WindowWindowInfo> queryOrgan(String nmOrganNodeId) {

		String sql = "SELECT * FROM WINDOW_WINDOW_INFO WHERE NM_ORGAN_NODE_ID = ? ";
		List<WindowWindowInfo> list = new ArrayList<WindowWindowInfo>();
		RecordSet rs = SQL.execute(conn, sql, new Object[] { nmOrganNodeId });

		while (rs.next()) {
			WindowWindowInfo info = new WindowWindowInfo();
			setProperties(info, rs);
			list.add(info);
		}
		return list;
	}

	// public int insert(Student student) {
	//
	// int i = 0;
	// String sql = "insert into students (Name,Sex,Age) values(?,?,?)";
	// PreparedStatement pstmt;
	// try {
	// pstmt = (PreparedStatement) conn.prepareStatement(sql);
	// pstmt.setString(1, student.getName());
	// pstmt.setString(2, student.getSex());
	// pstmt.setString(3, student.getAge());
	// i = pstmt.executeUpdate();
	// pstmt.close();
	// conn.close();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// return i;
	// }

	/**
	 * SQL.query
	 * 
	 * @param suffix
	 * @return list
	 */
	public List<WindowWindowInfo> stuffQuery(Conditions conds, String suffix) {
		ArrayList<WindowWindowInfo> al = new ArrayList<WindowWindowInfo>();
		try {
			RecordSet rs = SQL.query(conn, "WINDOW_WINDOW_INFO", null, conds,suffix);
			while (rs.next()) {
				WindowWindowInfo info = new WindowWindowInfo();
				setProperties(info, rs);
				al.add(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return al;
	}

	/**
	 * 分页查询
	 * @param conds
	 * @param suffix
	 * @param pageSize
	 * @param currentPage
	 * @return list
	 */
	public PaginationArrayList<WindowWindowInfo> query(Conditions conds,
			String suffix, int pageSize, int currentPage) {
		RecordSet rs;
		rs = SQL.query(conn, "WINDOW_WINDOW_INFO", "*", conds, suffix,pageSize, currentPage);

		PaginationArrayList<WindowWindowInfo> myList = new PaginationArrayList<WindowWindowInfo>(
				rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
		while (rs.next()) {
			WindowWindowInfo info = new WindowWindowInfo();
			setProperties(info, rs);
			myList.add(info);
		}
		return myList;
	}
	
	/**
	 * 根据表名查询总记录数
	 * @param tableName
	 * @return
	 */
	public int queryPage(String tableName) {
		//int page = Integer.parseInt(Config.get("history.page"));
		String sql = "select count(*) cou from " + tableName;
		RecordSet rs = SQL.execute(conn, sql);
		int cou = 0;
		while(rs.next()){
			 cou = Integer.parseInt(rs.getString("cou"));
		}
		return cou;
	}

	public List<WindowWindowInfo> queryWindowId(Conditions conds, String suffix) {
		ArrayList<WindowWindowInfo> al = new ArrayList<WindowWindowInfo>();
		try {
			RecordSet rs = SQL.query(conn, "WINDOW_WINDOW_INFO", null, conds,suffix);
			while (rs.next()) {
				WindowWindowInfo info = new WindowWindowInfo();
				setProperties(info, rs);
				al.add(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return al;
	}
	
	/**
	 * 查询练习
	 * 
	 * @param
	 * @return Integer
	 */
	public Integer getAll() {
		String sql = "select * from window_window_info ";
		RecordSet rs = SQL.execute(sql);
		while (rs.next()) {
			System.out.println(rs.getOriginalString("ST_WINDOW_ID"));
		}
		return 1;
	}
	
	public static void setProperties(WindowWindowInfo info, RecordSet rs){
		info.setStWindowId(rs.getOriginalString("ST_WINDOW_ID"));
		info.setStWindowNo(rs.getOriginalString("ST_WINDOW_NO"));
		info.setStUserId(rs.getOriginalString("ST_USER_ID"));
		info.setStWindowIp(rs.getOriginalString("ST_WINDOW_IP"));
		info.setNmReserVation(rs.getBigDecimal("NM_RESERVATION"));
		info.setNmOrganNodeId(rs.getBigDecimal("NM_ORGAN_NODE_ID"));
		info.setNmWindowStatus(rs.getBigDecimal("NM_WINDOW_STATUS"));
		info.setStInterActiveNo(rs.getOriginalString("ST_INTERACTIVE_NO"));
		info.setStInterActive(rs.getOriginalString("ST_INTERACTIVE_IP"));
		info.setStWindowName(rs.getOriginalString("ST_WINDOW_NAME"));
		info.setStVoiceChanne(rs.getOriginalString("ST_VOICE_CHANNE"));
		info.setStCall(rs.getOriginalString("ST_CALL"));
		info.setStIspay(rs.getOriginalString("ST_ISPAY"));
	}
	// public static void main(String[] args) {
	// ConnectionMysql mysql = new ConnectionMysql();
	// Connection conn = mysql.getConn();
	// JdbcDao jdbcDao = new JdbcDao();
	// Log.info("ddddd");
	// getAll();
	// String suffix = "order by ST_WINDOW_ID DESC";
	// String suffix = null;
	// Conditions conds = Conditions.newAndConditions();
	// conds.add(new Condition("ST_WINDOW_ID", Condition.OT_EQUAL, "D04"));
	// conds.add(new Condition("ST_WINDOW_NAME", Condition.OT_EQUAL, "12321"));
	// for (WindowWindowInfo temp : jdbcDao.stuffQuery(conds, suffix)) {
	// System.out.println(temp.getStWindowId());
	// }
	// for (WindowWindowInfo temp1 : jdbcDao.queryAll()) {
	// System.out.println(temp1.getStWindowIp());
	// }
	// }

}
