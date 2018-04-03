package daag.web.api.v1.controller.user.validator;

import daag.model.v1.request.AddUser;
import daag.model.v1.request.EditUser;
import daag.model.v1.request.UpdateUser;
import daag.web.utils.StringUtil;
import daag.web.utils.exception.CodeMsg;
import daag.web.utils.exception.DaagException;

/**
 * 用户参数验证
 * Created by yq on 2018/4/2.
 */
public class UserValidator {

    public static boolean convert(EditUser editUser) throws DaagException{

        if (StringUtil.isEmpty(editUser.getUsername()) || StringUtil.isEmpty(editUser.getName())
                ||StringUtil.isEmpty(editUser.getEmail())){
            throw new DaagException(CodeMsg.UserParamErrMsg,CodeMsg.UserParamErrCode);
        }
        return true;
    }

    public static boolean convert(UpdateUser updateUser) throws DaagException{

        if (StringUtil.isEmpty(updateUser.getName()) ||StringUtil.isEmpty(updateUser.getEmail())
                || StringUtil.isEmpty(updateUser.getPlainpassword())){
            throw new DaagException(CodeMsg.UserParamErrMsg,CodeMsg.UserParamErrCode);
        }
        return true;
    }

    public static boolean convert(AddUser addUser) throws DaagException{

        if (StringUtil.isEmpty(addUser.getUsername()) || StringUtil.isEmpty(addUser.getName())
                || StringUtil.isEmpty(addUser.getEmail()) || StringUtil.isEmpty(addUser.getPlainpassword())
                || StringUtil.isEmpty(addUser.getRoles())){
            throw new DaagException(CodeMsg.UserParamErrMsg,CodeMsg.UserParamErrCode);
        }
        return true;
    }

}
