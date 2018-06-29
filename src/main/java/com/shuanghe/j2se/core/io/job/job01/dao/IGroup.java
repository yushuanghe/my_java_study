package com.shuanghe.j2se.core.io.job.job01.dao;

import java.util.List;

import com.shuanghe.j2se.core.io.job.job01.entity.Group;

public interface IGroup {
    public Group getGroupById(int id);

    public List<Group> getAllGroups();
}
