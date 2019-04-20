package com.itheima.ssm.controller;

import com.itheima.ssm.domian.SysLog;
import com.itheima.ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component  //把普通pojo实例化到spring容器中，相当于配置文件中的
@Aspect
public class LogAOP {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    private Date visitTime; //开始时间
    private Class clazz; //访问的类
    private Method method;//访问的方法

    //前置通知  主要是获取开始时间，执行的类是哪一个，执行的是哪一个方法
    @Before("execution(* com.itheima.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException { //切入点
        //当前时间就是开始访问的时间
        visitTime = new Date();
        //具体要访问的类
        clazz = jp.getTarget().getClass();
        //获取访问的方法的名称
        String methodName = jp.getSignature().getName();//(signature是信号,标识的意思):jp.getSignature()获取被增强的方法相关信息
        //获取访问的方法的参数
        Object[] args = jp.getArgs();

        //获取具体执行的方法的Method对象
        if (args == null || args.length == 0){  //无参的方法
            //只能获取无参数的方法
            method = clazz.getMethod(methodName);
        }else {
            //通过Class对象遍历获得方法的参数
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                //在args加载进内存时获得方法的参数
                classArgs[i] = args[i].getClass();
                /*
                    Class 类的实例表示正在运行的 Java 应用程序中的类和接口。枚举是一种类，注释是一种接口。
                    每个数组属于被映射为 Class 对象的一个类，所有具有相同元素类型和维数的数组都共享该 Class 对象。
                    基本的 Java 类型（boolean、byte、char、short、int、long、float 和 double）和关键字 void 也表示为 Class 对象。
                 */
            }
            clazz.getMethod(methodName,classArgs);
        }
    }

    //后置通知
    @After("execution(* com.itheima.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        long time = new Date().getTime() - visitTime.getTime(); //获取访问的时长

        String url = "";
        //获取url
        if (clazz != null && method != null && clazz != LogAOP.class) {
            //1.获取类上的@RequestMapping("/orders")
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                String[] classValue = classAnnotation.value();
                //2.获取方法上的@RequestMapping(xxx)
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0] + methodValue[0];

                    //获取访问的ip
                    String ip = request.getRemoteAddr();

                    //获取当前操作的用户
                    SecurityContext context = SecurityContextHolder.getContext();//从上下文中获了当前登录的用户
                    User user = (User) context.getAuthentication().getPrincipal();
                    String username = user.getUsername();

                    //将日志相关信息封装到SysLog对象
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(time); //执行时长
                    sysLog.setIp(ip);
                    sysLog.setMethod("[类名] " + clazz.getName() + "[方法名] " + method.getName());
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);

                    //调用Service完成操作
                    sysLogService.save(sysLog);
                }
            }
        }

    }
}
