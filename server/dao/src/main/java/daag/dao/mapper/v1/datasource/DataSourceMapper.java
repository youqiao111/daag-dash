package daag.dao.mapper.v1.datasource;

import daag.model.v1.datasource.DataSource;
import daag.model.v1.datasource.Vo.EditDataSource;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by yq on 2018/4/4.
 */
@Mapper
public interface DataSourceMapper {

    @Select("select id,name,url,other,user_id,type,createtime,updatetime from content_datasource where id = #{id}")
    DataSource findById(Integer id);

    @Select("select id,name,url,other,user_id,type,createtime,updatetime from content_datasource")
    List<DataSource> findAll();

    @Insert("insert into content_datasource(name,url,other,user_id,type,createtime) values(#{name},#{url},#{other},#{user_id},#{type},#{createtime})")
    @Options(useGeneratedKeys = true)
    int add(DataSource dataSource);

    @Update("update content_datasource set name = #{name},url = #{url},other = #{other},type = #{type} where id = #{id}")
    int update(EditDataSource editDataSource);

    @Delete("delete from content_datasource where id = #{id}")
    int deleteById(Integer id);
}
