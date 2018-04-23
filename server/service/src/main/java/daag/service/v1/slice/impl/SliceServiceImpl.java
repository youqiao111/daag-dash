package daag.service.v1.slice.impl;

import daag.dao.mapper.v1.slice.SliceMapper;
import daag.dao.mapper.v1.slice.SliceDao;
import daag.model.v1.slice.Slice;
import daag.model.v1.slice.Vo.DetailSlice;
import daag.model.v1.slice.Vo.ListSlice;
import daag.model.v1.slice.Vo.UpdateSlice;
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

    @Autowired
    private SliceDao sliceDao;


    @Override
    public int findByDataSourceId(Integer datasource_id) {
        return this.sliceMapper.findByDataSourceId(datasource_id);
    }

    @Override
    public DetailSlice findById(Integer id) {
        return this.sliceMapper.findById(id);
    }

    @Override
    public List<ListSlice> findAll() {
        return this.sliceMapper.findAll();
    }

    @Override
    public int add(Slice slice) {
        return this.sliceMapper.add(slice);
    }

    @Override
    public int countDashBySliceId(Integer slice_id) {
        return this.sliceMapper.countDashBySliceId(slice_id);
    }

    @Override
    public int deleteById(Integer id) {
        return this.sliceMapper.deleteById(id);
    }

    @Override
    public int updateNameAndDesc(Integer id, String name, String description) {
        return this.sliceMapper.updateNameAndDesc(id,name,description);
    }

    @Override
    public int update(UpdateSlice updateSlice) {
        return this.sliceMapper.update(updateSlice);
    }

    @Override
    public List query(String sql,String url,String type) throws Exception {
        return this.sliceDao.query(sql,url,type);
    }

    @Override
    public List<Slice> findByIds(String ids) {
        return this.sliceMapper.findByIds(ids);
    }
}
