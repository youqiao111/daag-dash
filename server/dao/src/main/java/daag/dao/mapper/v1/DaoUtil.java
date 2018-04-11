package daag.dao.mapper.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yq on 2018/4/11.
 */
@Component
public class DaoUtil {

    private static final transient Logger log = LoggerFactory.getLogger(DaoUtil.class);

    private Map<String,String> driverClassNameMap = new HashMap<>();

    @PostConstruct
    public void initDriverClassName(){
        log.info("^^^^^^^^^^^^^^^^^初始化DriverClassName");
        driverClassNameMap.put("mysql","com.mysql.jdbc.Driver");
    }

    public String getDriverClassName(String type){
        return driverClassNameMap.get(type);
    }

}
