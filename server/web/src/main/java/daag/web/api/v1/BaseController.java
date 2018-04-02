package daag.web.api.v1;

import daag.model.v1.ResultJson;

/**
 * Created by yq on 2018/3/20.
 */
public abstract class BaseController {

    protected ResultJson resultJson(Integer status, String msg){
        return ResultJson.build(status,msg);
    }

    protected ResultJson resultJson(Integer status,String msg,Object data){
        return  ResultJson.build(status,msg,data);
    }

}
