package daag.web.utils.exception;

/**
 * Created by yq on 2018/4/2.
 */
public class CodeMsg {

    public final static int    UserParamErrCode = 401001;        //用户页面传参不可为空
    public final static String UserParamErrMsg  = "用户页面传参不可为空";

    public final static int    DataSourceParamErrCode = 401002;        //DataSource页面传参不可为空
    public final static String DataSourceParamErrMsg  = "DataSource页面传参不可为空";

    public final static int    SliceParamErrCode = 401003;        //Slice页面传参不可为空
    public final static String SliceParamErrMsg  = "Slice页面传参不可为空";

    public final static int    DashBoardParamErrCode = 401003;        //DashBoard页面传参不可为空
    public final static String DashBoardErrMsg  = "DashBoard页面传参不可为空";

}
