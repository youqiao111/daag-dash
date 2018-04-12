package daag.dao.mapper.v1.dashboard.provider;

import daag.model.v1.dashboard.Vo.EditDashBoard;

/**
 * Created by yq on 2018/4/12.
 */
public class DashBoardProvider {

    public String edit(EditDashBoard editDashBoard){
        StringBuffer sb = new StringBuffer();
        sb.append("update content_dashboard set name = " + editDashBoard.getName());
        sb.append(",ispublic = " + editDashBoard.getIspublic());
        if (editDashBoard.getSetting() != null && editDashBoard.getSetting() != "") {
            sb.append(",setting = " + editDashBoard.getSetting());
        }
        sb.append(",description = " + editDashBoard.getDescription());
        sb.append(" where id = " + editDashBoard.getId());
        return sb.toString();
    }
}
