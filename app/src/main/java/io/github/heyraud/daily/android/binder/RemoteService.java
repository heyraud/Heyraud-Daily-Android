package io.github.heyraud.daily.android.binder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;


/**
 * 描述
 *
 * @author aero.tang
 * @version 2018/9/6 21:54
 */
public class RemoteService extends Service {
    private final static String TAG = "RemoteService";

    private Map<String, Employee> employeeMap;

    private final IRemoteService.Stub mBinder = new IRemoteService.Stub() {

        @Override
        public Employee queryEmployee(String jobNum) throws RemoteException {
            Log.d(TAG, "client request-> fetch monthly salary jobNum=" + jobNum);

            Employee employee = null;
            if (employeeMap != null || TextUtils.isEmpty(jobNum)) {
                employee = employeeMap.get(jobNum);
            }

            return employee;
        }

        @Override
        public boolean addEmployee(Employee employee) throws RemoteException {
            Log.d(TAG, "client request-> add employee=" + employee.toString());
            if (employeeMap == null) {
                employeeMap = new HashMap<>();
            }

            boolean result = false;
            if (checkValid(employee)) {
                //设置修改时间
                employee.timestamp = System.currentTimeMillis();
                employeeMap.put(employee.jobNum, employee);
                result = true;
            }
            return result;
        }
    };

    /**
     * check employee info valid
     */
    private boolean checkValid(Employee employee) {
        return employee != null && !TextUtils.isEmpty(employee.jobNum);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

}
