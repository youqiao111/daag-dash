package daag.web.api.v1.controller.dashboard.validator;

import daag.model.v1.dashboard.Vo.AddDashBoard;
import daag.model.v1.dashboard.Vo.EditDashBoard;
import daag.util.StringUtil;
import daag.util.exception.CodeMsg;
import daag.util.exception.DaagException;

/**
 * Created by yq on 2018/4/12.
 */
public class DashBoardValidator {

    public static boolean convert(AddDashBoard addDashBoard) throws DaagException {
        if (StringUtil.isEmpty(addDashBoard.getName()) || StringUtil.isEmpty(addDashBoard.getSlug())
                ||StringUtil.isEmpty(addDashBoard.getSetting()) || StringUtil.isEmpty(addDashBoard.getDescription())
                || addDashBoard.getIspublic() == null){
            throw new DaagException(CodeMsg.DashBoardErrMsg,CodeMsg.DashBoardParamErrCode);
        }
        return true;
    }

    public static boolean convert(EditDashBoard editDashBoard) throws DaagException {
        if (StringUtil.isEmpty(editDashBoard.getName()) || StringUtil.isEmpty(editDashBoard.getDescription())
                ||StringUtil.isEmpty(editDashBoard.getId()) || editDashBoard.getIspublic() == null){
            throw new DaagException(CodeMsg.DashBoardErrMsg,CodeMsg.DashBoardParamErrCode);
        }
        return true;
    }
}
