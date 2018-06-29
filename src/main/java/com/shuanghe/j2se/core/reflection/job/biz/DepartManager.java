package com.shuanghe.j2se.core.reflection.job.biz;

import com.shuanghe.j2se.core.reflection.job.constrant.AppType;
import com.shuanghe.j2se.core.reflection.job.dao.IDepart;
import com.shuanghe.j2se.core.reflection.job.entity.Depart;
import com.shuanghe.j2se.core.reflection.job.factory.Factory;

import java.util.List;

/**
 * Created by yobdc on 2017/01/16.
 */
public class DepartManager {
    private IDepart dao;
    private Factory factory;

    public DepartManager() {
        this.factory = Factory.getInstance();
        dao = factory.createIDepart();
    }

    public DepartManager(AppType appType) {
        this.factory = Factory.getInstance();
        this.dao = factory.createIDepart(appType);
    }

    public Depart getDepartById(int id) {
        return dao.getDepartById(id);
    }

    public List<Depart> getAllDepart() {
        return dao.getAllDepart();
    }
}
