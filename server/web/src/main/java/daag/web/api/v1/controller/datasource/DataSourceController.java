package daag.web.api.v1.controller.datasource;

import daag.model.v1.ResultJson;
import daag.model.v1.datasource.DataSource;
import daag.model.v1.datasource.Vo.AddDataSource;
import daag.model.v1.datasource.Vo.DetailDataSource;
import daag.model.v1.datasource.Vo.EditDataSource;
import daag.model.v1.datasource.Vo.ListDataSource;
import daag.model.v1.slice.Slice;
import daag.model.v1.user.User;
import daag.service.v1.datasource.DataSourceService;
import daag.service.v1.slice.SliceService;
import daag.web.api.v1.BaseController;
import daag.web.api.v1.controller.datasource.validator.DataSourceValidator;
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
 * Created by yq on 2018/4/4.
 */
@Api(tags = "DataSource相关")
@RestController
@RequestMapping("/api/v1/datasource")
public class DataSourceController extends BaseController {

    private static final transient Logger log = LoggerFactory.getLogger(DataSourceController.class);

    @Autowired
    private DataSourceService dataSourceService;

    @Autowired
    private SliceService sliceService;

    /**
     * DataSource添加
     * @param addDataSource
     * @return
     */
    @ApiOperation(value = "DataSource添加")
    @PostMapping("/add")
    public ResultJson datasource_add(AddDataSource addDataSource){
        log.info("^^^^^^^^^^^^^^^^^^^^DataSource添加  begin");
        Integer status = -1;
        String msg = "";
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (user != null){
            try {
                if (DataSourceValidator.convert(addDataSource)){
                    DataSource dataSource = new DataSource();
                    BeanUtils.copyProperties(addDataSource,dataSource);
                    dataSource.setUser_id(user.getId());
                    dataSource.setCreatetime(new Date());
                    if (this.dataSourceService.add(dataSource) > 0){
                        log.info("^^^^^^^^^^^^^^^^^^^^DataSource添加成功  end");
                        status = 0;
                        msg = "DataSource添加成功";
                    }else {
                        msg = "DataSource添加失败";
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
     * DataSource列表查询
     * @return
     */
    @ApiOperation(value = "DataSource列表查询")
    @GetMapping("/list")
    public ResultJson datasource_list(){
        Integer status = -1;
        String msg = "";
        List<ListDataSource> dataSourceList = this.dataSourceService.findAll();
        if (dataSourceList.size() > 0){
            status = 0;
        }else {
            msg = "暂无数据";
        }
        return resultJson(status,msg,dataSourceList);
    }

    /**
     * DataSource详情
     * @param id
     * @return
     */
    @ApiOperation(value = "DataSource详情")
    @GetMapping("/detail/{id}")
    public ResultJson datasource_detail(@PathVariable("id") Integer id){
        Integer status = -1;
        String msg = "";
        DetailDataSource dataSource = this.dataSourceService.findById(id);
        if (dataSource != null){
            status = 0;
        }
        return resultJson(status,msg,dataSource);
    }

    /**
     * DataSource修改
     * @param editDataSource
     * @return
     */
    @ApiOperation(value = "DataSource修改")
    @PostMapping("/edit")
    public ResultJson datasource_edit(EditDataSource editDataSource){
        Integer status = -1;
        String msg = "";
        try {
            if (DataSourceValidator.convert(editDataSource)){
                if (this.dataSourceService.update(editDataSource) > 0){
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
     * DataSource删除
     * @param id
     * @return
     */
    @ApiOperation(value = "DataSource删除")
    @PostMapping("/delete")
    public ResultJson datasource_delete(Integer id){
        Integer status = -1;
        String msg = "";
        if (this.sliceService.findByDataSourceId(id) > 0){
            msg = "删除失败，该DataSource下存在Slice";
        }else {
            if (this.dataSourceService.deleteById(id) > 0){
                status = 0;
                msg = "删除成功";
            }else {
                msg = "删除失败";
            }
        }
        return resultJson(status,msg);
    }

    @ApiOperation(value = "DataSource连接测试")
    @PostMapping("/test")
    public ResultJson datasource_test(String url,String type){
        Integer status = -1;
        String msg = "";
        if (this.dataSourceService.test(url, type) == 1){
            status = 1;
        }else {
            msg = "url或type错误";
        }
        return resultJson(status,msg);
    }
}
