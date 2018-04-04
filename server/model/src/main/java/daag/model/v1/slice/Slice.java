package daag.model.v1.slice;

import java.util.Date;

/**
 * Created by yq on 2018/4/4.
 */
public class Slice {

    private Integer id;                 // 主键id
    private String name;                // 名称
    private String type;                // 类型
    private Date createtime;            // 创建时间
    private Integer user_id;            // 所属用户
    private String sql;                 // sql语句
    private String setting;             // 设置
    private Integer datasource_id;      // 数据源id

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

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }

    public Integer getDatasource_id() {
        return datasource_id;
    }

    public void setDatasource_id(Integer datasource_id) {
        this.datasource_id = datasource_id;
    }
}
