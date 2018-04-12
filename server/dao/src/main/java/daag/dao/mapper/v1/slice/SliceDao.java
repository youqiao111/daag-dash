package daag.dao.mapper.v1.slice;

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

    public List query(String sql,String url,String type) throws Exception{
        List list = new ArrayList<>();
        String driverClassName = getUtil().getDriverClassName(type);
        if (driverClassName != null) {
            jdbcTemplate = getJdbcTemplate(url,driverClassName);
            list = jdbcTemplate.queryForList(sql);
        }
        return list;
    }
}
