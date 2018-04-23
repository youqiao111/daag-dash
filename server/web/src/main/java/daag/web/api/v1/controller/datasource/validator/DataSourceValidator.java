package daag.web.api.v1.controller.datasource.validator;

import daag.model.v1.datasource.Vo.AddDataSource;
import daag.model.v1.datasource.Vo.EditDataSource;
import daag.util.StringUtil;
import daag.util.exception.CodeMsg;
import daag.util.exception.DaagException;

/**
 * Created by yq on 2018/4/4.
 */
public class DataSourceValidator {

    public static boolean convert(AddDataSource addDataSource) throws DaagException{
        if (StringUtil.isEmpty(addDataSource.getName()) || StringUtil.isEmpty(addDataSource.getOther())
                || StringUtil.isEmpty(addDataSource.getType()) || StringUtil.isEmpty(addDataSource.getUrl())){
            throw new DaagException(CodeMsg.DataSourceParamErrMsg,CodeMsg.DataSourceParamErrCode);
        }
        return true;
    }

    public static boolean convert(EditDataSource editDataSource) throws DaagException{
        if (StringUtil.isEmpty(editDataSource.getName()) || StringUtil.isEmpty(editDataSource.getOther())
                || StringUtil.isEmpty(editDataSource.getType()) || StringUtil.isEmpty(editDataSource.getUrl())
                || StringUtil.isEmpty(editDataSource.getId())){
            throw new DaagException(CodeMsg.DataSourceParamErrMsg,CodeMsg.DataSourceParamErrCode);
        }
        return true;
    }
}
