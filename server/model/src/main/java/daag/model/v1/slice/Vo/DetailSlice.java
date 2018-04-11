package daag.model.v1.slice.Vo;

import java.util.Date;

/**
 * Created by yq on 2018/4/11.
 */
public class DetailSlice {

    private Integer id;                 // 主键id
    private String name;                // 名称
    private String type;                // 类型
    private Date createtime;            // 创建时间
    private Date updatetime;            // 修改时间
    private Integer user_id;            // 创建用户id
    private String username;            // 创建用户
    private String slicesql;            // sql语句
    private String setting;             // 设置
    private Integer datasource_id;      // 数据源id
    private Integer datasource_name;    // 数据源名称
    private String description;         // 描述
}
