package daag.web.utils.exception;

/**
 * Created by yq on 2018/4/2.
 */
public class CodeMsg {

    public final static int    ErrCode = 105000; //服务器异常
    public final static String Error   = "服务器异常";

    public final static int    SuccessCode = 102000; //成功
    public final static String Success     = "返回成功";

    public final static int    CDateErrCode = 103000; //sql异常
    public final static String CDateErr     = "sql异常";

    public final static int    UserParamErrCode = 401001;        //用户修改页面传参不可为空
    public final static String UserParamErrMsg  = "用户修改页面传参不可为空";

}
