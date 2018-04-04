package daag.service.v1.datasource;

import daag.model.v1.datasource.DataSource;
import daag.model.v1.datasource.Vo.EditDataSource;

import java.util.List;

/**
 * Created by yq on 2018/4/4.
 */
public interface DataSourceService {

    DataSource findById(Integer id);

    List<DataSource> findAll();

    int add(DataSource dataSource);

    int update(EditDataSource editDataSource);

    int deleteById(Integer id);
}
