package cn.com.yijigu.rxnetworkframework.login.model;

public class UserModel implements IUser{

    private String userName;
    private String password;
    /**
     * parentId : 3504030101
     * phone : 15600000001
     * idCard : 111111111111111111
     * unitProperty : 0
     * unitType : 0
     * address : 1
     * unitName : 福建广汇信息技术有限公司
     * maxTaskDuration : 720
     * name : CJH技术
     * userId : 35040301010013
     * depotValidDistance : 1000
     * success : true
     * receiveValidTime : 60
     * roleId : 4
     */

    private String parentId;
    private String phone;
    private String idCard;
    private int unitProperty;
    private int unitType;
    private String address;
    private String unitName;
    private int maxTaskDuration;
    private String name;
    private String userId;
    private int depotValidDistance;
    private boolean success;
    private int receiveValidTime;
    private int roleId;

    public UserModel(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public int getUnitProperty() {
        return unitProperty;
    }

    public void setUnitProperty(int unitProperty) {
        this.unitProperty = unitProperty;
    }

    public int getUnitType() {
        return unitType;
    }

    public void setUnitType(int unitType) {
        this.unitType = unitType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public int getMaxTaskDuration() {
        return maxTaskDuration;
    }

    public void setMaxTaskDuration(int maxTaskDuration) {
        this.maxTaskDuration = maxTaskDuration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getDepotValidDistance() {
        return depotValidDistance;
    }

    public void setDepotValidDistance(int depotValidDistance) {
        this.depotValidDistance = depotValidDistance;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getReceiveValidTime() {
        return receiveValidTime;
    }

    public void setReceiveValidTime(int receiveValidTime) {
        this.receiveValidTime = receiveValidTime;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
