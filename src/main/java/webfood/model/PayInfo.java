package webfood.model;

import java.util.List;

public class PayInfo
{
	private Integer id;

	private Integer processId;

	private Integer processBuildId;

	private Integer projectId;

	private String pindex;

	private Double process;

	private Double payedAmount;

	private String payTime;

	private String payWay;

	private String remark;

	private String createTime;

	private String doc;

	private Double auditAmount;

	private Double deposit;

	private List<String> pstrlist;

	public List<String> getPstrlist()
	{
		return pstrlist;
	}

	public void setPstrlist(List<String> pstrlist)
	{
		this.pstrlist = pstrlist;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getProcessId()
	{
		return processId;
	}

	public void setProcessId(Integer processId)
	{
		this.processId = processId;
	}

	public Integer getProcessBuildId()
	{
		return processBuildId;
	}

	public void setProcessBuildId(Integer processBuildId)
	{
		this.processBuildId = processBuildId;
	}

	public Integer getProjectId()
	{
		return projectId;
	}

	public void setProjectId(Integer projectId)
	{
		this.projectId = projectId;
	}

	public String getPindex()
	{
		return pindex;
	}

	public void setPindex(String pindex)
	{
		this.pindex = pindex;
	}

	public Double getProcess()
	{
		return process;
	}

	public void setProcess(Double process)
	{
		this.process = process;
	}

	public Double getPayedAmount()
	{
		return payedAmount;
	}

	public void setPayedAmount(Double payedAmount)
	{
		this.payedAmount = payedAmount;
	}

	public String getPayTime()
	{
		return payTime;
	}

	public void setPayTime(String payTime)
	{
		this.payTime = payTime;
	}

	public String getPayWay()
	{
		return payWay;
	}

	public void setPayWay(String payWay)
	{
		this.payWay = payWay;
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

	public String getDoc()
	{
		return doc;
	}

	public void setDoc(String doc)
	{
		this.doc = doc;
	}

	public Double getAuditAmount()
	{
		return auditAmount;
	}

	public void setAuditAmount(Double auditAmount)
	{
		this.auditAmount = auditAmount;
	}

	public Double getDeposit()
	{
		return deposit;
	}

	public void setDeposit(Double deposit)
	{
		this.deposit = deposit;
	}
}