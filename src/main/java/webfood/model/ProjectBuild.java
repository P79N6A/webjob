package webfood.model;

import java.util.List;

public class ProjectBuild
{
	private Integer id;

	private Integer projectId;

	private Integer buildId;

	private String buildUnit;

	private String buildContractPic;

	private Double buildContractAmount;

	private String buildContractPeriod;

	private String payWay;

	private String subcontractor1;

	private String subcontractor2;

	private String subcontractor3;

	private String subcontractor4;

	private String subcontractor5;

	private String subType;

	private Double bidprice;

	private String lessprofitRate;

	private String proxyCompany;

	private String remark;

	private String createTime;

	private Double budgetAmount;

	private String supervisionUnit;

	private String supervisionMan;

	private String supervisionMobile;

	private String linkman1;

	private String linkman2;

	private String linkman3;

	private String linkman4;

	private String linkman5;

	private String mobile1;

	private String mobile2;

	private String mobile3;

	private String mobile4;

	private String mobile5;

	private Double deposit;

	private String qsdPic;

	private String bglxdPic;

	private String gcqzdPic;

	private Integer state;

	private String payType;

	private String finishProblem = "";

	private String finishAdvise = "";

	private String finishApplyDoc;

	private String finishRecordDoc;

	private Double auditAmount = 0.0;
	private List<PayInfo> plist;
	private List<Process> pclist;

	private List<String> qsdList;
	private List<String> bgdList;
	private List<String> qzdList;

	public List<String> getYsjlList()
	{
		return ysjlList;
	}

	public void setYsjlList(List<String> ysjlList)
	{
		this.ysjlList = ysjlList;
	}

	public List<String> getJgysList()
	{
		return jgysList;
	}

	public void setJgysList(List<String> jgysList)
	{
		this.jgysList = jgysList;
	}

	private List<String> ysjlList;
	private List<String> jgysList;

	public List<String> getQzdList()
	{
		return qzdList;
	}

	public void setQzdList(List<String> qzdList)
	{
		this.qzdList = qzdList;
	}

	public List<String> getQsdList()
	{
		return qsdList;
	}

	public void setQsdList(List<String> qsdList)
	{
		this.qsdList = qsdList;
	}

	public List<String> getBgdList()
	{
		return bgdList;
	}

	public void setBgdList(List<String> bgdList)
	{
		this.bgdList = bgdList;
	}

	private double totalPay;

	public double getTotalPay()
	{
		return totalPay;
	}

	public void setTotalPay(double totalPay)
	{
		this.totalPay = totalPay;
	}

	public List<PayInfo> getPlist()
	{
		return plist;
	}

	public void setPlist(List<PayInfo> plist)
	{
		this.plist = plist;
	}

	public List<Process> getPclist()
	{
		return pclist;
	}

	public void setPclist(List<Process> pclist)
	{
		this.pclist = pclist;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getProjectId()
	{
		return projectId;
	}

	public void setProjectId(Integer projectId)
	{
		this.projectId = projectId;
	}

	public Integer getBuildId()
	{
		return buildId;
	}

	public void setBuildId(Integer buildId)
	{
		this.buildId = buildId;
	}

	public String getBuildUnit()
	{
		return buildUnit;
	}

	public void setBuildUnit(String buildUnit)
	{
		this.buildUnit = buildUnit;
	}

	public String getBuildContractPic()
	{
		return buildContractPic;
	}

	public void setBuildContractPic(String buildContractPic)
	{
		this.buildContractPic = buildContractPic;
	}

	public Double getBuildContractAmount()
	{
		return buildContractAmount;
	}

	public void setBuildContractAmount(Double buildContractAmount)
	{
		this.buildContractAmount = buildContractAmount;
	}

	public String getBuildContractPeriod()
	{
		return buildContractPeriod;
	}

	public void setBuildContractPeriod(String buildContractPeriod)
	{
		this.buildContractPeriod = buildContractPeriod;
	}

	public String getPayWay()
	{
		return payWay;
	}

	public void setPayWay(String payWay)
	{
		this.payWay = payWay;
	}

	public String getSubcontractor1()
	{
		return subcontractor1;
	}

	public void setSubcontractor1(String subcontractor1)
	{
		this.subcontractor1 = subcontractor1;
	}

	public String getSubcontractor2()
	{
		return subcontractor2;
	}

	public void setSubcontractor2(String subcontractor2)
	{
		this.subcontractor2 = subcontractor2;
	}

	public String getSubcontractor3()
	{
		return subcontractor3;
	}

	public void setSubcontractor3(String subcontractor3)
	{
		this.subcontractor3 = subcontractor3;
	}

	public String getSubcontractor4()
	{
		return subcontractor4;
	}

	public void setSubcontractor4(String subcontractor4)
	{
		this.subcontractor4 = subcontractor4;
	}

	public String getSubcontractor5()
	{
		return subcontractor5;
	}

	public void setSubcontractor5(String subcontractor5)
	{
		this.subcontractor5 = subcontractor5;
	}

	public String getSubType()
	{
		return subType;
	}

	public void setSubType(String subType)
	{
		this.subType = subType;
	}

	public Double getBidprice()
	{
		return bidprice;
	}

	public void setBidprice(Double bidprice)
	{
		this.bidprice = bidprice;
	}

	public String getLessprofitRate()
	{
		return lessprofitRate;
	}

	public void setLessprofitRate(String lessprofitRate)
	{
		this.lessprofitRate = lessprofitRate;
	}

	public String getProxyCompany()
	{
		return proxyCompany;
	}

	public void setProxyCompany(String proxyCompany)
	{
		this.proxyCompany = proxyCompany;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}

	public Double getBudgetAmount()
	{
		return budgetAmount;
	}

	public void setBudgetAmount(Double budgetAmount)
	{
		this.budgetAmount = budgetAmount;
	}

	public String getSupervisionUnit()
	{
		return supervisionUnit;
	}

	public void setSupervisionUnit(String supervisionUnit)
	{
		this.supervisionUnit = supervisionUnit;
	}

	public String getSupervisionMan()
	{
		return supervisionMan;
	}

	public void setSupervisionMan(String supervisionMan)
	{
		this.supervisionMan = supervisionMan;
	}

	public String getSupervisionMobile()
	{
		return supervisionMobile;
	}

	public void setSupervisionMobile(String supervisionMobile)
	{
		this.supervisionMobile = supervisionMobile;
	}

	public String getLinkman1()
	{
		return linkman1;
	}

	public void setLinkman1(String linkman1)
	{
		this.linkman1 = linkman1;
	}

	public String getLinkman2()
	{
		return linkman2;
	}

	public void setLinkman2(String linkman2)
	{
		this.linkman2 = linkman2;
	}

	public String getLinkman3()
	{
		return linkman3;
	}

	public void setLinkman3(String linkman3)
	{
		this.linkman3 = linkman3;
	}

	public String getLinkman4()
	{
		return linkman4;
	}

	public void setLinkman4(String linkman4)
	{
		this.linkman4 = linkman4;
	}

	public String getLinkman5()
	{
		return linkman5;
	}

	public void setLinkman5(String linkman5)
	{
		this.linkman5 = linkman5;
	}

	public String getMobile1()
	{
		return mobile1;
	}

	public void setMobile1(String mobile1)
	{
		this.mobile1 = mobile1;
	}

	public String getMobile2()
	{
		return mobile2;
	}

	public void setMobile2(String mobile2)
	{
		this.mobile2 = mobile2;
	}

	public String getMobile3()
	{
		return mobile3;
	}

	public void setMobile3(String mobile3)
	{
		this.mobile3 = mobile3;
	}

	public String getMobile4()
	{
		return mobile4;
	}

	public void setMobile4(String mobile4)
	{
		this.mobile4 = mobile4;
	}

	public String getMobile5()
	{
		return mobile5;
	}

	public void setMobile5(String mobile5)
	{
		this.mobile5 = mobile5;
	}

	public Double getDeposit()
	{
		return deposit;
	}

	public void setDeposit(Double deposit)
	{
		this.deposit = deposit;
	}

	public String getQsdPic()
	{
		return qsdPic;
	}

	public void setQsdPic(String qsdPic)
	{
		this.qsdPic = qsdPic;
	}

	public String getBglxdPic()
	{
		return bglxdPic;
	}

	public void setBglxdPic(String bglxdPic)
	{
		this.bglxdPic = bglxdPic;
	}

	public String getGcqzdPic()
	{
		return gcqzdPic;
	}

	public void setGcqzdPic(String gcqzdPic)
	{
		this.gcqzdPic = gcqzdPic;
	}

	public Integer getState()
	{
		return state;
	}

	public void setState(Integer state)
	{
		this.state = state;
	}

	public String getPayType()
	{
		return payType;
	}

	public void setPayType(String payType)
	{
		this.payType = payType;
	}

	public String getFinishProblem()
	{
		return finishProblem;
	}

	public void setFinishProblem(String finishProblem)
	{
		this.finishProblem = finishProblem;
	}

	public String getFinishAdvise()
	{
		return finishAdvise;
	}

	public void setFinishAdvise(String finishAdvise)
	{
		this.finishAdvise = finishAdvise;
	}

	public String getFinishApplyDoc()
	{
		return finishApplyDoc;
	}

	public void setFinishApplyDoc(String finishApplyDoc)
	{
		this.finishApplyDoc = finishApplyDoc;
	}

	public String getFinishRecordDoc()
	{
		return finishRecordDoc;
	}

	public void setFinishRecordDoc(String finishRecordDoc)
	{
		this.finishRecordDoc = finishRecordDoc;
	}

	public Double getAuditAmount()
	{
		return auditAmount;
	}

	public void setAuditAmount(Double auditAmount)
	{
		this.auditAmount = auditAmount;
	}
}