package daag.service.v1.datasource;

import daag.model.v1.datasource.DataSource;
import daag.model.v1.datasource.Vo.EditDataSource;
import daag.model.v1.datasource.Vo.ListDataSource;

import java.util.List;

/**
 * Created by yq on 2018/4/4.
 */
public interface DataSourceService {

    DataSource findById(Integer id);

    List<ListDataSource> findAll();

    int add(DataSource dataSource);

    int update(EditDataSource editDataSource);

    int deleteById(Integer id);
}
