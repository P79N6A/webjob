package webfood.model;

public class Bdst {
    private Integer id;

    private Integer projectId;

    private Double budgetAmount;

    private Double bidprice;

    private String subType;

    private String lessprofitRate;

    private String proxyCompany;

    private String remark;

    private String createTime;

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

    public Double getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(Double budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    public Double getBidprice() {
        return bidprice;
    }

    public void setBidprice(Double bidprice) {
        this.bidprice = bidprice;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getLessprofitRate() {
        return lessprofitRate;
    }

    public void setLessprofitRate(String lessprofitRate) {
        this.lessprofitRate = lessprofitRate;
    }

    public String getProxyCompany() {
        return proxyCompany;
    }

    public void setProxyCompany(String proxyCompany) {
        this.proxyCompany = proxyCompany;
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
}