package daag.dao.mapper.v1.slice.provider;

import daag.dao.mapper.v1.BaseDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yq on 2018/4/11.
 */
@Repository
public class SliceDao extends BaseDao {

    private JdbcTemplate jdbcTemplate;

    public List query(String sql,String url,String type){
        List list = new ArrayList<>();
        String driverClassName = getUtil().getDriverClassName(type);
        if (driverClassName != null) {
            try {
                jdbcTemplate = getJdbcTemplate(url,driverClassName);
                list = jdbcTemplate.queryForList(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
