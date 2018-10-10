package io.github.thismj.android.binder;
//包名指定，必须跟aidl配置文件相同，否则会报finished with non-zero exit value 1

import io.github.thismj.android.binder.Employee;

//定义远程员工薪酬服务
interface IRemoteService {
    /**
     * 查询指定员工
     *
     * @param jobNum 工号
     */
    Employee queryEmployee(String jobNum);

    /**
     * 添加新员工
     *
     * @param employee 新员工
     */
    boolean addEmployee(in Employee employee);

}