package webfood.model;

public class DicItem {
    private Integer id;

    private Integer classId;

    private String itemName;

    private String itemValue;

    private String itemOrder;

    private Integer itemState;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public String getItemOrder() {
        return itemOrder;
    }

    public void setItemOrder(String itemOrder) {
        this.itemOrder = itemOrder;
    }

    public Integer getItemState() {
        return itemState;
    }

    public void setItemState(Integer itemState) {
        this.itemState = itemState;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}