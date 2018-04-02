package daag.web.api.v1.controller.user.validator;

import daag.model.v1.request.AddUser;
import daag.model.v1.request.ReqUser;
import daag.web.utils.StringUtil;
import daag.web.utils.exception.CodeMsg;
import daag.web.utils.exception.DaagException;

/**
 * 用户参数验证
 * Created by yq on 2018/4/2.
 */
public class UserValidator {

    public static boolean convert(ReqUser reqUser) throws DaagException{

        if (StringUtil.isEmpty(reqUser.getUsername()) || StringUtil.isEmpty(reqUser.getName())
                ||StringUtil.isEmpty(reqUser.getEmail()) || StringUtil.isEmpty(reqUser.getPlainpassword())){
            throw new DaagException(CodeMsg.UserParamErrMsg,CodeMsg.UserParamErrCode);
        }
        return true;
    }

    public static boolean convert(AddUser addUser) throws DaagException{

        if (StringUtil.isEmpty(addUser.getUsername()) || StringUtil.isEmpty(addUser.getName())
                ||StringUtil.isEmpty(addUser.getEmail()) || StringUtil.isEmpty(addUser.getPlainpassword())){
            throw new DaagException(CodeMsg.UserParamErrMsg,CodeMsg.UserParamErrCode);
        }
        return true;
    }

}
