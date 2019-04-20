package com.itheima.ssm.service;



import com.itheima.ssm.domian.SysLog;

import java.util.List;

public interface ISysLogService {

    public void save(SysLog sysLog) throws Exception;

    List<SysLog> findAll() throws Exception;
}
