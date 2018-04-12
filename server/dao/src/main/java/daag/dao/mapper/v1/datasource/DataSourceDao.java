package daag.dao.mapper.v1.datasource;

import daag.dao.mapper.v1.BaseDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by yq on 2018/4/10.
 */
@Repository
public class DataSourceDao extends BaseDao {

    private JdbcTemplate jdbcTemplate;

    public int test(String url, String type) throws Exception {
        Integer result = -1;
        String driverClassName = getUtil().getDriverClassName(type);
        if (driverClassName != null) {
            jdbcTemplate = getJdbcTemplate(url, driverClassName);
            result = jdbcTemplate.queryForObject("select 1", Integer.class);
        }
        return result;
    }

}
