package daag.dao.mapper.v1.slice.provider;

import daag.model.v1.slice.Vo.UpdateSlice;
//import daag.web.utils.StringUtil;

/**
 * Created by yq on 2018/4/11.
 */
public class SliceProvider {

    public String update(UpdateSlice updateSlice){
        StringBuffer sb = new StringBuffer();
        sb.append("update content_slice set ");
        if (updateSlice.getType() != null && updateSlice.getType() != ""){
            sb.append("type = #{type},");
        }
        if (updateSlice.getSlicesql() != null && updateSlice.getSlicesql() != ""){
            sb.append("slicesql = #{slicesql},");
        }
        if (updateSlice.getSetting() != null && updateSlice.getSetting() != ""){
            sb.append("setting = #{setting},");
        }
        if (updateSlice.getDatasource_id() != null){
            sb.append("datasource_id = #{datasource_id},");
        }
        String sql = sb.substring(0, sb.length() - 1);
        sql += " where id = #{id}";
        return sql;

    }
}
