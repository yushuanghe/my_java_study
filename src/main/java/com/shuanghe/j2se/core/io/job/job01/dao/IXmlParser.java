package com.shuanghe.j2se.core.io.job.job01.dao;

import com.shuanghe.j2se.core.io.job.job01.entity.Contact;

import java.util.List;

/**
 * Created by yobdc on 2016/12/16.
 */
public interface IXmlParser {
    public List<Contact> parse(String filename);
}
