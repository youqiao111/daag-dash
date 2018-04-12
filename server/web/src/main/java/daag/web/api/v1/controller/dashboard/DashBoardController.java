package daag.web.api.v1.controller.dashboard;

import daag.model.v1.ResultJson;
import daag.model.v1.dashboard.DashBoard;
import daag.model.v1.dashboard.Vo.AddDashBoard;
import daag.model.v1.dashboard.Vo.EditDashBoard;
import daag.model.v1.dashboard.Vo.ListDashBoard;
import daag.model.v1.user.User;
import daag.service.v1.dashboard.DashBoardService;
import daag.web.api.v1.BaseController;
import daag.web.api.v1.controller.dashboard.validator.DashBoardValidator;
import daag.web.utils.exception.DaagException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by yq on 2018/4/12.
 */
@Api(tags = "DashBoard相关")
@RestController
@RequestMapping("/api/v1/dashboard")
public class DashBoardController extends BaseController {

    private static final transient Logger log = LoggerFactory.getLogger(DashBoardController.class);

    @Autowired
    private DashBoardService dashBoardService;

    /**
     * DashBoard添加
     * @param addDashBoard
     * @return
     */
    @ApiOperation(value = "DashBoard添加")
    @PostMapping("/add")
    public ResultJson dashboard_add(AddDashBoard addDashBoard){
        log.info("^^^^^^^^^^^^^^^^^^^^DashBoard添加  begin");
        Integer status = -1;
        String msg = "";
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (user != null){
            try {
                if (DashBoardValidator.convert(addDashBoard)){
                    if (this.dashBoardService.findBySlug(addDashBoard.getSlug()) > 0){
                        return resultJson(status,"slug已存在");
                    }
                    DashBoard dashBoard = new DashBoard();
                    BeanUtils.copyProperties(addDashBoard,dashBoard);
                    dashBoard.setUser_id(user.getId());
                    dashBoard.setCreatetime(new Date());
                    if (this.dashBoardService.add(dashBoard) > 0){
                        log.info("^^^^^^^^^^^^^^^^^^^^DashBoard添加成功  end");
                        status = 0;
                        msg = "DashBoard添加成功";
                    }else {
                        msg = "DashBoard添加失败";
                    }
                }
            } catch (DaagException e) {
                e.printStackTrace();
                msg = "传入参数异常";
            }
        }
        return resultJson(status,msg);
    }

    /**
     * DashBoard列表查询
     * @return
     */
    @ApiOperation(value = "DashBoard列表查询")
    @GetMapping("/list")
    public ResultJson dashboard_list(){
        Integer status = -1;
        String msg = "";
        List<ListDashBoard> dashBoardList = this.dashBoardService.findAll();
        if (dashBoardList.size() > 0){
            status = 0;
        }else {
            msg = "暂无数据";
        }
        return resultJson(status,msg,dashBoardList);
    }

    /**
     * DashBoard详情
     * @param id
     * @return
     */
    @ApiOperation(value = "DashBoard详情")
    @GetMapping("/detail/{id}")
    public ResultJson dashboard_detail(@PathVariable("id") Integer id){
        Integer status = -1;
        String msg = "";
        ListDashBoard dashBoard = this.dashBoardService.findById(id);
        if (dashBoard != null){
            status = 0;
        }
        return resultJson(status,msg,dashBoard);
    }

    /**
     * DashBoard修改
     * @param editDashBoard
     * @return
     */
    @ApiOperation(value = "DashBoard基本信息修改")
    @PostMapping("/edit")
    public ResultJson dashboard_edit(EditDashBoard editDashBoard){
        Integer status = -1;
        String msg = "";
        try {
            if (DashBoardValidator.convert(editDashBoard)){
                if (this.dashBoardService.edit(editDashBoard) > 0){
                    status = 0;
                    msg = "修改成功";
                }else {
                    msg = "修改失败";
                }
            }
        } catch (DaagException e) {
            e.printStackTrace();
            msg = "传入参数异常";
        }
        return resultJson(status,msg);
    }

    /**
     * DashBoard删除
     * @param id
     * @return
     */
    @ApiOperation(value = "DashBoard删除")
    @PostMapping("/delete")
    public ResultJson dashboard_delete(Integer id){
        Integer status = -1;
        String msg = "";
        this.dashBoardService.deleteByDashId(id); // 清空Slice  DashBoard关联表数据
        if (this.dashBoardService.deleteById(id) > 0){
            status = 0;
            msg = "删除成功";
        }else {
            msg = "删除失败";
        }
        return resultJson(status,msg);
    }

}
