package daag.service.v1.impl;

import daag.dao.mapper.v1.SysRoleMapper;
import daag.service.v1.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yq on 2018/3/14.
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public int addAll(Integer user_id, String roleIds) {
        return this.sysRoleMapper.addAll(user_id,roleIds);
    }

    @Override
    public int deleteByUserId(Integer user_id) {
        return this.sysRoleMapper.deleteByUserId(user_id);
    }
}
