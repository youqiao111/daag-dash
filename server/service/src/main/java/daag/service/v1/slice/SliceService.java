package daag.service.v1.slice;

import daag.model.v1.slice.Slice;
import daag.model.v1.slice.Vo.DetailSlice;
import daag.model.v1.slice.Vo.ListSlice;
import daag.model.v1.slice.Vo.UpdateSlice;

import java.util.List;

/**
 * Created by yq on 2018/4/4.
 */
public interface SliceService {

    int findByDataSourceId(Integer datasource_id);

    DetailSlice findById(Integer id);

    List<ListSlice> findAll();

    int add(Slice slice);

    int findDashBySliceId(Integer slice_id);

    int deleteById(Integer id);

    int updateNameAndDesc(Integer id,String name,String description);

    int update(UpdateSlice updateSlice);

    List query(String sql,String url,String type);
}
