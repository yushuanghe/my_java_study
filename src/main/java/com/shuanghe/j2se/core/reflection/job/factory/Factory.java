package com.shuanghe.j2se.core.reflection.job.factory;

import com.shuanghe.j2se.core.reflection.job.constrant.AppType;
import com.shuanghe.j2se.core.reflection.job.dao.IDepart;
import com.shuanghe.j2se.core.reflection.job.dao.IEmployee;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by yobdc on 2017/01/16.
 */
public class Factory {

    private static Factory factory;

    private String myPackage;

    private Factory() {
        myPackage = getConfig();
    }

    public static Factory getInstance() {
        if (factory == null)
            factory = new Factory();

        return factory;
    }

    public IDepart createIDepart(AppType appType) {
        IDepart dao = null;
        switch (appType) {
            case mysql:
                dao = new com.shuanghe.j2se.core.reflection.job.mysqlDaoImpl.DepartService();
                break;
            case mssql:
                dao = new com.shuanghe.j2se.core.reflection.job.mssqlDaoImpl.DepartService();
                break;
            default:
                break;
        }

        return dao;
    }

    public IDepart createIDepart() {
        IDepart dao = null;

        String path = myPackage + ".DepartService";
        try {
            Class<IDepart> clazz = (Class<IDepart>) Class.forName(path);
            dao = clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return dao;
    }

    public IEmployee createEmployee() {
        IEmployee dao = null;

        String path = myPackage + ".EmployeeService";
        try {
            Class<IEmployee> clazz = (Class<IEmployee>) Class.forName(path);
            try {
                dao = clazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return dao;
    }

    private String getConfig() {
        String path = null;
        Properties properties = new Properties();
        File file = new File("src/com.shuanghe.j2se/core/reflection/job/config.xml");

        try {
            properties.loadFromXML(new FileInputStream(file));

            path = properties.getProperty("package");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }
}
