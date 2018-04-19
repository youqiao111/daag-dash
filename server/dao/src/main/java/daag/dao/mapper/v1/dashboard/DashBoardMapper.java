package daag.dao.mapper.v1.dashboard;

import daag.dao.mapper.v1.dashboard.provider.DashBoardProvider;
import daag.model.v1.dashboard.DashBoard;
import daag.model.v1.dashboard.Vo.EditDashBoard;
import daag.model.v1.dashboard.Vo.ListDashBoard;
import daag.model.v1.dashboard.Vo.PublicDashBoard;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by yq on 2018/4/10.
 */
@Mapper
public interface DashBoardMapper {

    @Insert("insert into content_dashboard(name,slug,user_id,ispublic,setting,description,createtime) values(#{name},#{slug},#{user_id},#{ispublic},#{setting},#{description},#{createtime})")
    int add(DashBoard dashBoard);

    @Select("select a.*,b.username from content_dashboard a,sys_user b where a.id = #{id} and a.user_id = b.id")
    ListDashBoard findById(Integer id);

    @Select("select count(1) from content_dashboard where slug = #{slug}")
    int findBySlug(String slug);

    @Select("select a.*,b.username from content_dashboard a,sys_user b where a.user_id = b.id")
    List<ListDashBoard> findAll();

    @Select("select a.*,b.username from content_dashboard a,sys_user b where a.user_id = b.id and a.ispublic = 1")
    List<PublicDashBoard> findPublic();

    @Delete("delete from content_dashboard_slice where dashboard_id = #{dashboard_id}")
    int deleteByDashId(Integer dashboard_id);

    @Delete("delete from content_dashboard where id = #{id}")
    int deleteById(Integer id);

    @UpdateProvider(type = DashBoardProvider.class,method = "edit")
    int edit(EditDashBoard editDashBoard);

    @Update("update content_dashboard set setting = #{setting} where id = #{id}")
    int update(@Param("id") Integer id,@Param("setting") String setting);
}
