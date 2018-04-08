package daag.service.v1.datasource.impl;

import daag.dao.mapper.v1.datasource.DataSourceMapper;
import daag.model.v1.datasource.DataSource;
import daag.model.v1.datasource.Vo.EditDataSource;
import daag.model.v1.datasource.Vo.ListDataSource;
import daag.service.v1.datasource.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yq on 2018/4/4.
 */
@Service
public class DataSourceServiceImpl implements DataSourceService {

    @Autowired
    private DataSourceMapper dataSourceMapper;

    @Override
    public DataSource findById(Integer id) {
        return this.dataSourceMapper.findById(id);
    }

    @Override
    public List<ListDataSource> findAll() {
        return this.dataSourceMapper.findAll();
    }

    @Override
    public int add(DataSource dataSource){
        return this.dataSourceMapper.add(dataSource);
    }

    @Override
    public int update(EditDataSource editDataSource) {
        return this.dataSourceMapper.update(editDataSource);
    }

    @Override
    public int deleteById(Integer id) {
        return this.dataSourceMapper.deleteById(id);
    }
}
