package daag.dao.mapper.v1.slice;

import daag.model.v1.slice.Slice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by yq on 2018/4/4.
 */
@Mapper
public interface SliceMapper {

    @Select("select id,name,type,createtime,user_id,sql,setting,datasource_id from content_silce where datasource_id = #{datasource_id}")
    List<Slice> findByDataSourceId(Integer datasource_id);
}
