package design.controller;

import design.remoteapi.RemoteInterface;

import java.rmi.RemoteException;

/***********************************************************
 * 版权所有 (C)2020, hekai
 *
 * 文件名称：StudentInfoList.java
 * 文件标识：无
 * 内容摘要：该类得到数据库中的学生信息，并且将其放在ArrayList中返回
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201215
 **********************************************************/
public class StudentInfoList {
//    RepositoryImp<StudentInfo> repositoryImp=new RepositoryImp<>();
//    StudentInfoFactory studentInfoFactory=new StudentInfoFactory();
//    public List<StudentInfo> StudentRes(){
    RemoteFunction remoteFunction=new RemoteFunction();
    public Object[][] StudentRes(){
        String sql= "select * from student_info";
//        return repositoryImp.getResult(studentInfoFactory,sql);
        Object[][] res=null;
        try {
            res=remoteFunction.getResult(RemoteInterface.MYOBJECT.Student,sql);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return res;
    }
}
