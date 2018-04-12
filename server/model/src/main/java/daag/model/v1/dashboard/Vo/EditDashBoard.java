package daag.model.v1.dashboard.Vo;

/**
 * Created by yq on 2018/4/12.
 */
public class EditDashBoard {

    private Integer id;                 // 主键id
    private String name;                // 名称
    private Boolean ispublic;           // 是否公开
    private String setting;             // 设置
    private String description;         // 描述

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIspublic() {
        return ispublic;
    }

    public void setIspublic(Boolean ispublic) {
        this.ispublic = ispublic;
    }

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
