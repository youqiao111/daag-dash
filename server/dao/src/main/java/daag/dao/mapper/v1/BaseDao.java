package daag.dao.mapper.v1;

import com.fasterxml.jackson.databind.JsonNode;
import daag.model.v1.datasource.DataSource;
import daag.model.v1.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yq on 2018/4/9.
 */
public class BaseDao {

    private static final transient Logger log = LoggerFactory.getLogger(BaseDao.class);

    @Autowired
    private DaoUtil util;

    public DaoUtil getUtil() {
        return util;
    }

    protected JdbcTemplate getJdbcTemplate(String url, String driverClassName){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }

}
