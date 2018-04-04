package daag.service.v1.slice;

import daag.model.v1.slice.Slice;

import java.util.List;

/**
 * Created by yq on 2018/4/4.
 */
public interface SliceService {

    List<Slice> findByDataSourceId(Integer datasource_id);
}
