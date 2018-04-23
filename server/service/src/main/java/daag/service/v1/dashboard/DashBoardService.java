package daag.service.v1.dashboard;

import daag.model.v1.dashboard.DashBoard;
import daag.model.v1.dashboard.Vo.EditDashBoard;
import daag.model.v1.dashboard.Vo.ListDashBoard;
import daag.model.v1.dashboard.Vo.PublicDashBoard;

import java.util.List;

/**
 * Created by yq on 2018/4/12.
 */
public interface DashBoardService {

    int add(DashBoard dashBoard);

    int findBySlug(String slug);

    ListDashBoard findById(Integer id);

    List<ListDashBoard> findAll();

    List<PublicDashBoard> findPublic();

    int deleteById(Integer id);

    int edit(EditDashBoard editDashBoard);

    int update(Integer id,String setting);
}
