package daag.model.v1.slice.Vo;

import java.util.Date;

/**
 * Created by yq on 2018/4/4.
 */
public class ListSlice {

    private Integer id;                 // 主键id
    private String name;                // 名称
    private String username;        // 创建用户
    private String type;                // 类型
    private Date createtime;            // 创建时间
    private Date updatetime;            // 修改时间
    private Integer datasource_name;      // 数据源名称
    private String description;                // 描述

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getDatasource_name() {
        return datasource_name;
    }

    public void setDatasource_name(Integer datasource_name) {
        this.datasource_name = datasource_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
