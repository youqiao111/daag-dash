package daag.model.v1.slice.Vo;

/**
 * Created by yq on 2018/4/11.
 */
public class UpdateSlice {

    private Integer id;                 // 主键id
    private String type;                // 类型
    private String slicesql;            // sql语句
    private String setting;             // 设置
    private Integer datasource_id;      // 数据源id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSlicesql() {
        return slicesql;
    }

    public void setSlicesql(String slicesql) {
        this.slicesql = slicesql;
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
