package daag.model.v1.dashboard.Vo;

import java.util.Date;

/**
 * Created by yq on 2018/4/12.
 */
public class PublicDashBoard {

    private Integer id;                 // 主键id
    private String name;                // 名称
    private String username;            // 创建用户
    private String description;         // 描述
    private Date createtime;            // 创建时间
    private Date updatetime;            // 修改时间

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
