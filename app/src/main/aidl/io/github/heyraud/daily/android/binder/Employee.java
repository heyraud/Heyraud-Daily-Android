package io.github.heyraud.daily.android.binder;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 员工实体类
 *
 * @author aero.tang
 * @version 2018/9/7 16:27
 */
public class Employee implements Parcelable {

    /**
     * 姓名
     */
    public String name;

    /**
     * 工号
     */
    public String jobNum;

    /**
     * 职位
     */
    public String position;

    /**
     * 月薪
     */
    public int monthlySalary;

    protected Employee(Parcel in) {
        name = in.readString();
        jobNum = in.readString();
        position = in.readString();
        monthlySalary = in.readInt();
    }

    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(jobNum);
        dest.writeString(position);
        dest.writeInt(monthlySalary);
    }
}
