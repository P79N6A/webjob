package webfood.model;

public class Process {
	private Integer id;

	private Integer projectId;

	private String pdate;

	private Double process;

	private Integer type;

	private String createTime;

	private Integer projectBuildId;

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

	public String getPdate() {
		return pdate;
	}

	public void setPdate(String pdate) {
		this.pdate = pdate;
	}

	public Double getProcess() {
		return process;
	}

	public void setProcess(Double process) {
		this.process = process;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getProjectBuildId() {
		return projectBuildId;
	}

	public void setProjectBuildId(Integer projectBuildId) {
		this.projectBuildId = projectBuildId;
	}
}