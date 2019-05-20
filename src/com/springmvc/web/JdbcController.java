package com.springmvc.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.json.JSONArray;

import net.sf.json.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.config.Config;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;

import com.springmvc.bean.WindowWindowInfo;
import com.springmvc.service.JdbcService;

@Controller
public class JdbcController {
	
	@Autowired
	private JdbcService jdbcService;
	
	private static int page = Integer.parseInt(Config.get("history.page"));
	
	/**
	 * 主页
	 * @param req
	 * @param res
	 * @throws IOException 
	 */
	@RequestMapping("my.do")
	public ModelAndView myIndex(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		return new ModelAndView("index.jsp");
	}
	
	/**
	 * Ajax方式加载分页导航栏
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("turnAjax.do")
	public void turnAjax(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		String nowpageString = req.getParameter("nowpage");
		int pageInt = Integer.parseInt(req.getParameter("pageInt"));
		String stWindowId = req.getParameter("ST_WINDOW_ID");
		
		int nowpage = 1;
		if (nowpageString != null && nowpageString != "" && !"NaN".equals(nowpageString)) {
			nowpage = Integer.parseInt(nowpageString);
		}else {
			return;
		}
		
		Conditions conds = Conditions.newAndConditions();
		String suffix = null;
		if (!"".equals(stWindowId) && stWindowId != null) {
			conds.add(new Condition("ST_WINDOW_ID", Condition.OT_EQUAL,stWindowId));
		}
		
		PaginationArrayList<WindowWindowInfo> myList = jdbcService.windowInfo(conds, suffix, pageInt, nowpage);
		JSONObject myJson = new JSONObject();
		JSONArray arr = JSONArray.fromObject(myList);
		try {
			//把存有数据的Json数组放入一个Json对象
			myJson.put("rows", arr);
			//把总记录数放入Json对象
			myJson.put("total", myList.getTotalItemCount());
		} catch (JSONException e) {
			e.printStackTrace();
		}		
		res.setContentType("application/json; charset=UTF-8");
		res.getWriter().print(myJson);
		//原生写法
//		JSONObject myJson1 = new JSONObject();
//		JSONArray arr = new JSONArray();
//		for (WindowWindowInfo temp : myList) {
//			myJson1 = new JSONObject();
//			try {
//				myJson1.put("ST_INTERACTIVE_NO",temp.getStInterActiveNo());
//				myJson1.put("ST_WINDOW_ID",temp.getStWindowId());
//				myJson1.put("ST_WINDOW_NO",temp.getStWindowNo());
//				myJson1.put("ST_USER_ID",temp.getStUserId());
//				myJson1.put("ST_WINDOW_IP",temp.getStWindowIp());
//				myJson1.put("NM_RESERVATION",temp.getNmReserVation());
//				myJson1.put("NM_ORGAN_NODE_ID",temp.getNmOrganNodeId());
//				myJson1.put("NM_WINDOW_STATUS",temp.getNmWindowStatus());
//				myJson1.put("ST_INTERACTIVE_IP",temp.getStInterActive());
//				myJson1.put("ST_WINDOW_NAME",temp.getStWindowName());
//				myJson1.put("ST_VOICE_CHANNEL",temp.getStVoiceChanne());
//				myJson1.put("ST_CALL",temp.getStCall());
//				myJson1.put("ST_ISPAY",temp.getStIspay());
//				arr.put(myJson1);
//				} catch (JSONException e) {
//				e.printStackTrace();
//				}
//		}
		
	}
	
	/**
	 * submit提交表单
	 * @param req
	 * @param res
	 * @throws IOException 
	 */
	@RequestMapping("window.do")
	public ModelAndView windowWindowInfo(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		String stWindowId = (String)req.getSession().getAttribute("stWindowId");
		Conditions conds = Conditions.newAndConditions();
		String suffix = null;
		if (!"".equals(stWindowId) && stWindowId != null) {
			conds.add(new Condition("ST_WINDOW_ID", Condition.OT_EQUAL,stWindowId));
		}
		
		PaginationArrayList<WindowWindowInfo> myList = jdbcService.windowInfo(conds, suffix,page,1);
		int cou = jdbcService.queryPage("WINDOW_WINDOW_INFO");
		req.setAttribute("windowWindowInfoList",myList);
		req.setAttribute("cou", cou);
		return new ModelAndView("index.jsp");
	}
	
	/**
	 * 条件查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("conditionsSelect1.do")
	public void conditionsSelect1(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String stWindowId = req.getParameter("stWindowId");
		req.getSession().setAttribute("stWindowId", stWindowId);
		
		res.sendRedirect("window.do");
	}
	
	/*
	*//**
	 * Ajax条件查询
	 * @param req
	 * @param res
	 * @throws IOException 
	 *//*
	@RequestMapping("myAjax.do")
	public void selectAjax(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		String stWindowId = req.getParameter("stWindowId");
		Conditions conds = Conditions.newAndConditions();
		String suffix = null;
		if (!"".equals(stWindowId) && stWindowId != null) {
			conds.add(new Condition("ST_WINDOW_ID", Condition.OT_EQUAL,stWindowId));
		}
		PaginationArrayList<WindowWindowInfo> myList = jdbcService.windowInfo(conds, suffix, page, 1);
		res.setContentType("text/html; charset=UTF-8");
		//res.getWriter().write(JSONArray.fromObject(myList).toString());
		
		
		JSONObject myJson = new JSONObject();
		JSONArray jsonarray = JSONArray.fromObject(myList);
		try {
			myJson.put("data", jsonarray);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		res.setContentType("application/json; charset=UTF-8");
		res.getWriter().write(myJson.toString());
		Json
		for (WindowWindowInfo temp : myList) {
			try {
				myJson.put("ST_WINDOW_ID",temp.getStWindowId());
				myJson.put("ST_WINDOW_NO",temp.getStWindowNo());
				myJson.put("ST_USER_ID",temp.getStUserId());
				myJson.put("ST_WINDOW_IP",temp.getStWindowIp());
				myJson.put("NM_RESERVATION",temp.getNmReserVation());
				myJson.put("NM_ORGAN_NODE_ID",temp.getNmOrganNodeId());
				myJson.put("NM_WINDOW_STATUS",temp.getNmWindowStatus());
				myJson.put("ST_INTERACTIVE_NO",temp.getStInterActiveNo());
				myJson.put("ST_INTERACTIVE_IP",temp.getStInterActive());
				myJson.put("ST_WINDOW_NAME",temp.getStWindowName());
				myJson.put("ST_VOICE_CHANNEL",temp.getStVoiceChanne());
				myJson.put("ST_CALL",temp.getStCall());
				myJson.put("ST_ISPAY",temp.getStIspay());
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}			
	}*/
	
	/**
	 * execute条件查询
	 * @param req
	 * @param res
	 * @throws IOException 
	 */
	/*@RequestMapping("hello.do")
	public ModelAndView selectJdbc(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		String NM_ORGAN_NODE_ID = req.getParameter("NM_ORGAN_NODE_ID");
		
		List<WindowWindowInfo> myList = jdbcService.queryOrgan(NM_ORGAN_NODE_ID);
		req.setAttribute("windowWindowInfoList",myList);
		return new ModelAndView("index.jsp");
	}*/
	
	/**
	 * query查询
	 * @param req
	 * @param res
	 * @throws IOException 
	 */
	/*
	@RequestMapping("today.do")
	public ModelAndView conSelect(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		String stWindowId = (String)req.getSession().getAttribute("stWindowId");
		Conditions conds = Conditions.newAndConditions();
		String suffix = null;
		if (!"".equals(stWindowId) && stWindowId != null) {
			conds.add(new Condition("ST_WINDOW_ID", Condition.OT_EQUAL,stWindowId));
		}
			
		List<WindowWindowInfo> myList = jdbcService.stuffQuery(conds, suffix );
		req.setAttribute("windowWindowInfoList",myList);
		return new ModelAndView("index.jsp");
	}*/
}
