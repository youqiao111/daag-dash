package daag.model.v1.user.Vo;

/**
 * Created by yq on 2018/3/26.
 */
public class ReqRole {
    private Integer id;
    private String role; 		// 角色
    private String remark; // 角色描述
    private Boolean available = Boolean.FALSE; // 状态,默认不可用
}
