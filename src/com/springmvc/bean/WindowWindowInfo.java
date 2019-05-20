package com.springmvc.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "WINDOW_WINDOW_INFO")
public class WindowWindowInfo implements Serializable{
	public static final String WINDOW_WINDOW_INFO = "WINDOW_WINDOW_INFO";
	public static final String ST_WINDOW_ID = "ST_WINDOW_ID";
	public static final String ST_WINDOW_NO = "ST_WINDOW_NO";
	public static final String ST_USER_ID = "ST_USER_ID";
	public static final String ST_WINDOW_IP = "ST_WINDOW_IP";
	public static final String NM_RESERVATION = "NM_RESERVATION";
	public static final String NM_ORGAN_NODE_ID = "NM_ORGAN_NODE_ID";
	public static final String NM_WINDOW_STATUS = "NM_WINDOW_STATUS";
	public static final String ST_INTERACTIVE_NO = "ST_INTERACTIVE_NO";
	public static final String ST_INTERACTIVE_IP = "ST_INTERACTIVE_IP";
	public static final String ST_WINDOW_NAME = "ST_WINDOW_NAME";
	public static final String ST_VOICE_CHANNEL = "ST_VOICE_CHANNEL";
	public static final String ST_CALL = "ST_CALL";
	public static final String ST_ISPAY = "ST_ISPAY";
	
	@Id
    @Column(name = "WINDOW_WINDOW_INFO")
    private String windowWindowInfo;
	
	@Column(name = "ST_WINDOW_ID")
    private String stWindowId;
	
	@Column(name = "ST_WINDOW_NO")
    private String stWindowNo;
	
	@Column(name = "ST_USER_ID")
    private String stUserId;
	
	@Column(name = "ST_WINDOW_IP")
    private String stWindowIp;
	
	@Column(name = "NM_RESERVATION")
    private BigDecimal nmReserVation;
	
	@Column(name = "NM_ORGAN_NODE_ID")
    private BigDecimal nmOrganNodeId;
	
	@Column(name = "NM_WINDOW_STATUS")
    private BigDecimal nmWindowStatus;
	
	@Column(name = "ST_INTERACTIVE_NO")
    private String stInterActiveNo;
	
	@Column(name = "ST_INTERACTIVE_IP")
    private String stInterActive;
	
	@Column(name = "ST_WINDOW_NAME")
    private String stWindowName;
	
	@Column(name = "ST_VOICE_CHANNE")
    private String stVoiceChanne;
	
	@Column(name = "ST_CALL")
    private String stCall;
	
	@Column(name = "ST_ISPAY")
    private String stIspay;

	
	public String getWindowWindowInfo() {
		return windowWindowInfo;
	}

	public void setWindowWindowInfo(String windowWindowInfo) {
		this.windowWindowInfo = windowWindowInfo;
	}

	public String getStWindowId() {
		return stWindowId;
	}

	public void setStWindowId(String stWindowId) {
		this.stWindowId = stWindowId;
	}

	public String getStWindowNo() {
		return stWindowNo;
	}

	public void setStWindowNo(String stWindowNo) {
		this.stWindowNo = stWindowNo;
	}

	public String getStUserId() {
		return stUserId;
	}

	public void setStUserId(String stUserId) {
		this.stUserId = stUserId;
	}

	public String getStWindowIp() {
		return stWindowIp;
	}

	public void setStWindowIp(String stWindowIp) {
		this.stWindowIp = stWindowIp;
	}

	public BigDecimal getNmReserVation() {
		return nmReserVation;
	}

	public void setNmReserVation(BigDecimal nmReserVation) {
		this.nmReserVation = nmReserVation;
	}

	public BigDecimal getNmOrganNodeId() {
		return nmOrganNodeId;
	}

	public void setNmOrganNodeId(BigDecimal nmOrganNodeId) {
		this.nmOrganNodeId = nmOrganNodeId;
	}

	public BigDecimal getNmWindowStatus() {
		return nmWindowStatus;
	}

	public void setNmWindowStatus(BigDecimal nmWindowStatus) {
		this.nmWindowStatus = nmWindowStatus;
	}

	public String getStInterActiveNo() {
		return stInterActiveNo;
	}

	public void setStInterActiveNo(String stInterActiveNo) {
		this.stInterActiveNo = stInterActiveNo;
	}

	
	public String getStWindowName() {
		return stWindowName;
	}

	public void setStWindowName(String stWindowName) {
		this.stWindowName = stWindowName;
	}

	public String getStVoiceChanne() {
		return stVoiceChanne;
	}

	public void setStVoiceChanne(String stVoiceChanne) {
		this.stVoiceChanne = stVoiceChanne;
	}

	public String getStCall() {
		return stCall;
	}

	public void setStCall(String stCall) {
		this.stCall = stCall;
	}

	public String getStIspay() {
		return stIspay;
	}

	public void setStIspay(String stIspay) {
		this.stIspay = stIspay;
	}

	public String getStInterActive() {
		return stInterActive;
	}

	public void setStInterActive(String stInterActive) {
		this.stInterActive = stInterActive;
	}
	
}
