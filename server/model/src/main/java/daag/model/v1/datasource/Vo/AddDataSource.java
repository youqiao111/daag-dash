package daag.model.v1.datasource.Vo;

import java.util.Date;

/**
 * Created by yq on 2018/4/2.
 */
public class AddDataSource {

    private String name;            // 名称
    private String url;             // jdbc链接
    private String other;           // 其他
    private String type;            // 数据库类型

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
