package webfood.model;

import java.util.List;

public class CompactPayinfo {
	private Integer id;

	private Integer projectId;

	private String pindex;

	private Double payedAmount;

	private String payTime;

	private String remark;

	private String createTime;

	private Double deposit;

	private List<String> picList;

	public List<String> getPicList() {
		return picList;
	}

	public void setPicList(List<String> picList) {
		this.picList = picList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getPindex() {
		return pindex;
	}

	public void setPindex(String pindex) {
		this.pindex = pindex;
	}

	public Double getPayedAmount() {
		return payedAmount;
	}

	public void setPayedAmount(Double payedAmount) {
		this.payedAmount = payedAmount;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Double getDeposit() {
		return deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}
}