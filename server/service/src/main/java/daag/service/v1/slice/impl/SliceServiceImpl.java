package daag.service.v1.slice.impl;

import daag.dao.mapper.v1.slice.SliceMapper;
import daag.model.v1.slice.Slice;
import daag.service.v1.slice.SliceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yq on 2018/4/4.
 */
@Service
public class SliceServiceImpl implements SliceService {

    @Autowired
    private SliceMapper sliceMapper;

    @Override
    public List<Slice> findByDataSourceId(Integer datasource_id) {
        return this.sliceMapper.findByDataSourceId(datasource_id);
    }
}
