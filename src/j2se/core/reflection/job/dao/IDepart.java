package j2se.core.reflection.job.dao;

import j2se.core.reflection.job.entity.Depart;

import java.util.List;

/**
 * Created by yobdc on 2017/01/16.
 */
public interface IDepart {
    public Depart getDepartById(int id);

    public List<Depart> getAllDepart();
}
