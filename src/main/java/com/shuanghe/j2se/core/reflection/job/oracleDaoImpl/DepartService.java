package com.shuanghe.j2se.core.reflection.job.oracleDaoImpl;

import com.shuanghe.j2se.core.reflection.job.dao.IDepart;
import com.shuanghe.j2se.core.reflection.job.entity.Depart;

import java.util.List;

/**
 * Created by yobdc on 2017/01/16.
 */
public class DepartService implements IDepart {
    @Override
    public Depart getDepartById(int id) {
        System.out.println("ORACLE:获取id为：" + id + "的部门信息");
        return null;
    }

    @Override
    public List<Depart> getAllDepart() {
        System.out.println("ORACLE:获取部门信息");
        return null;
    }
}
