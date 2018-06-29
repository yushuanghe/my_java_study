package com.shuanghe.j2se.core.io.job.job01.biz;

import com.shuanghe.j2se.core.io.job.job01.dao.IGroup;
import com.shuanghe.j2se.core.io.job.job01.daoImpl.GroupService;
import com.shuanghe.j2se.core.io.job.job01.entity.Group;

import java.util.List;

/**
 * Created by yobdc on 2016/12/14.
 */
public class GroupManager {
    private IGroup dao = null;

    public GroupManager() {
        dao = new GroupService();
    }

    public Group getGroupById(int id) {
        return dao.getGroupById(id);
    }

    public List<Group> getAllGroups() {
        return dao.getAllGroups();
    }
}
