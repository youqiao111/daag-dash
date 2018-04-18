package daag.service.v1.dashboard.impl;

import daag.dao.mapper.v1.dashboard.DashBoardMapper;
import daag.model.v1.dashboard.DashBoard;
import daag.model.v1.dashboard.Vo.EditDashBoard;
import daag.model.v1.dashboard.Vo.ListDashBoard;
import daag.model.v1.dashboard.Vo.PublicDashBoard;
import daag.service.v1.dashboard.DashBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yq on 2018/4/12.
 */
@Service
public class DashBoardServiceImpl implements DashBoardService {

    @Autowired
    private DashBoardMapper dashBoardMapper;

    @Override
    public int add(DashBoard dashBoard) {
        return this.dashBoardMapper.add(dashBoard);
    }

    @Override
    public int findBySlug(String slug) {
        return this.dashBoardMapper.findBySlug(slug);
    }

    @Override
    public ListDashBoard findById(Integer id) {
        return this.dashBoardMapper.findById(id);
    }

    @Override
    public List<ListDashBoard> findAll() {
        return this.dashBoardMapper.findAll();
    }

    @Override
    public List<PublicDashBoard> findPublic() {
        return this.dashBoardMapper.findPublic();
    }

    @Override
    public int deleteByDashId(Integer dashboard_id) {
        return this.dashBoardMapper.deleteByDashId(dashboard_id);
    }

    @Override
    public int deleteById(Integer id) {
        return this.dashBoardMapper.deleteById(id);
    }

    @Override
    public int edit(EditDashBoard editDashBoard) {
        return this.dashBoardMapper.edit(editDashBoard);
    }
}
