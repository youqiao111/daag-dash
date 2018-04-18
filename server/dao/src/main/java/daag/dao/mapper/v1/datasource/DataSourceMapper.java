package daag.dao.mapper.v1.datasource;

import daag.model.v1.datasource.DataSource;
import daag.model.v1.datasource.Vo.DetailDataSource;
import daag.model.v1.datasource.Vo.EditDataSource;
import daag.model.v1.datasource.Vo.ListDataSource;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by yq on 2018/4/4.
 */
@Mapper
public interface DataSourceMapper {

    @Select("select a.*,b.username from content_datasource a,sys_user b where a.id = #{id} and a.user_id = b.id")
    DetailDataSource findById(Integer id);

    @Select("select a.*,b.username from content_datasource a,sys_user b where a.user_id = b.id")
    List<ListDataSource> findAll();

    @Insert("insert into content_datasource(name,url,other,user_id,type,createtime) values(#{name},#{url},#{other},#{user_id},#{type},#{createtime})")
    @Options(useGeneratedKeys = true)
    int add(DataSource dataSource);

    @Update("update content_datasource set name = #{name},url = #{url},other = #{other},type = #{type} where id = #{id}")
    int update(EditDataSource editDataSource);

    @Delete("delete from content_datasource where id = #{id}")
    int deleteById(Integer id);

}
