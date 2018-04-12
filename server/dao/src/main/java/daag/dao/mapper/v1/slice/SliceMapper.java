package daag.dao.mapper.v1.slice;

import daag.dao.mapper.v1.slice.provider.SliceProvider;
import daag.model.v1.slice.Slice;
import daag.model.v1.slice.Vo.DetailSlice;
import daag.model.v1.slice.Vo.ListSlice;
import daag.model.v1.slice.Vo.UpdateSlice;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by yq on 2018/4/4.
 */
@Mapper
public interface SliceMapper {

    @Select("select count(1) from content_slice where datasource_id = #{datasource_id}")
    int findByDataSourceId(Integer datasource_id);

    @Select("select a.*,b.name datasource_name,c.username from content_slice a left join content_datasource b on a.datasource_id = b.id join sys_user c on a.user_id = c.id where a.id = #{id}")
    DetailSlice findById(Integer id);

    @Select("select a.*,b.name datasource_name,c.username from content_slice a left join content_datasource b on a.datasource_id = b.id join sys_user c on a.user_id = c.id")
    List<ListSlice> findAll();

    @Insert("insert into content_slice(name,createtime,user_id,description,type,slicesql,setting,datasource_id) values(#{name},#{createtime},#{user_id},#{description},'','','',0)")
    @Options(useGeneratedKeys = true)
    int add(Slice slice);

    @Select("select count(1) from content_dashboard_slice where slice_id = #{slice_id}")
    int findDashBySliceId(Integer slice_id);

    @Delete("delete from content_slice where id = #{id}")
    int deleteById(Integer id);

    @Update("update content_slice set name = #{name},description = #{description} where id = #{id}")
    int updateNameAndDesc(@Param("id") Integer id,@Param("name") String name,@Param("description") String description);

    @UpdateProvider(type = SliceProvider.class,method = "update")
    int update(UpdateSlice updateSlice);
}
