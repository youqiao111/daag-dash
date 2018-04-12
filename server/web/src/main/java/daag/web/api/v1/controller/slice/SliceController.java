package daag.web.api.v1.controller.slice;

import daag.model.v1.ResultJson;
import daag.model.v1.datasource.Vo.DetailDataSource;
import daag.model.v1.slice.Slice;
import daag.model.v1.slice.Vo.DetailSlice;
import daag.model.v1.slice.Vo.ListSlice;
import daag.model.v1.slice.Vo.UpdateSlice;
import daag.model.v1.user.User;
import daag.service.v1.datasource.DataSourceService;
import daag.service.v1.slice.SliceService;
import daag.web.api.v1.BaseController;
import daag.web.api.v1.controller.slice.validator.SliceValidator;
import daag.web.utils.StringUtil;
import daag.web.utils.exception.DaagException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yq on 2018/4/10.
 */
@Api(tags = "Slice相关")
@RestController
@RequestMapping("/api/v1/slice")
public class SliceController extends BaseController {

    private static final transient Logger log = LoggerFactory.getLogger(SliceController.class);

    @Autowired
    private SliceService sliceService;

    @Autowired
    private DataSourceService dataSourceService;

    /**
     * Slice添加
     * @param name
     * @param description
     * @return
     */
    @ApiOperation(value = "Slice添加")
    @PostMapping("/add")
    public ResultJson slice_add(String name,String description){
        log.info("^^^^^^^^^^^^^^^^^^^^Slice添加  begin");
        Integer status = -1;
        String msg = "";
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if(StringUtil.isEmpty(name) || StringUtil.isEmpty(description)){
            return resultJson(status,"传入参数异常");
        }
        if (user != null){
            Slice slice = new Slice();
            slice.setName(name);
            slice.setUser_id(user.getId());
            slice.setCreatetime(new Date());
            slice.setDescription(description);
            if (this.sliceService.add(slice) > 0){
                log.info("^^^^^^^^^^^^^^^^^^^^Slice添加成功  end");
                status = 0;
                msg = "Slice添加成功";
            }else {
                msg = "Slice添加失败";
            }
        }
        return resultJson(status,msg);
    }

    /**
     * Slice列表查询
     * @return
     */
    @ApiOperation(value = "Slice列表查询")
    @GetMapping("/list")
    public ResultJson datasource_list(){
        Integer status = -1;
        String msg = "";
        List<ListSlice> sliceList = this.sliceService.findAll();
        if (sliceList.size() > 0){
            status = 0;
        }else {
            msg = "暂无数据";
        }
        return resultJson(status,msg,sliceList);
    }

    /**
     * Slice详情
     * @param id
     * @return
     */
    @ApiOperation(value = "Slice详情")
    @GetMapping("/detail/{id}")
    public ResultJson slice_detail(@PathVariable("id") Integer id){
        Integer status = -1;
        String msg = "";
        DetailSlice slice = this.sliceService.findById(id);
        if (slice != null){
            status = 0;
        }
        return resultJson(status,msg,slice);
    }

    /**
     * Slice名称描述修改
     * @param id
     * @param name
     * @param description
     * @return
     */
    @ApiOperation(value = "Slice名称描述修改")
    @PostMapping("/edit")
    public ResultJson slice_edit(Integer id,String name,String description){
        Integer status = -1;
        String msg = "";
        if (id == null || StringUtil.isEmpty(name) || StringUtil.isEmpty(description)){
            return resultJson(status,"传入参数异常");
        }
        if (this.sliceService.updateNameAndDesc(id,name,description) > 0){
            status = 0;
            msg = "修改成功";
        }else {
            msg = "修改失败";
        }
        return resultJson(status,msg);
    }

    /**
     * Slice类型sql等信息修改
     * @param updateSlice
     * @return
     */
    @ApiOperation(value = "Slice类型sql等信息修改")
    @PostMapping("/update")
    public ResultJson slice_update(UpdateSlice updateSlice){
        Integer status = -1;
        String msg = "";
        try {
            if (SliceValidator.convert(updateSlice)){
                if (this.sliceService.update(updateSlice) > 0){
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
     * Slice删除
     * @param id
     * @return
     */
    @ApiOperation(value = "Slice删除")
    @PostMapping("/delete")
    public ResultJson slice_delete(Integer id){
        Integer status = -1;
        String msg = "";
        if(this.sliceService.findDashBySliceId(id) > 0){
            msg = "删除失败，该Slice存在DashBoard";
        }else {
            if (this.sliceService.deleteById(id) > 0){
                status = 0;
                msg = "删除成功";
            }else {
                msg = "删除失败";
            }
        }
        return resultJson(status,msg);
    }

    /**
     * Slice的sql语句预执行
     * @param sql
     * @param datasource_id
     * @return
     */
    @ApiOperation(value = "Slice的sql语句预执行")
    @PostMapping("/query")
    public ResultJson slice_query(String sql,Integer datasource_id){
        Integer status = -1;
        String msg = "";
        List list = new ArrayList<>();
        DetailDataSource dataSource = this.dataSourceService.findById(datasource_id);
        if (dataSource != null){
            try {
                list = this.sliceService.query(sql, dataSource.getUrl(), dataSource.getType());
                status = 0;
            } catch (Exception e) {
                e.printStackTrace();
                msg = e.getMessage();
            }
        }else {
            msg = "无对应DataSource";
        }
        return resultJson(status,msg,list);
    }
}
