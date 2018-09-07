package io.github.heyraud.daily.android;
//包名指定，必须跟aidl配置文件相同，否则会报finished with non-zero exit value 1

import io.github.heyraud.daily.android.binder.Employee;

//定义远程员工薪酬服务
interface IRemoteService {
    /**
     * 获取指定员工月薪，单位千/月
     *
     * @param jobNum 工号
     */
    int fetchMonthlySalary(String jobNum);

    /**
     * 录入新员工信息
     *
     * @param employee 新员工
     */
    Employee addEmployee(inout Employee employee);

}