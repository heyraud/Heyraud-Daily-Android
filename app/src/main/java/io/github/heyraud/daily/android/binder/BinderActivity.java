package io.github.heyraud.daily.android.binder;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import io.github.heyraud.daily.android.BasicActivity;
import io.github.heyraud.daily.android.R;

/**
 * Binder Sample
 *
 * @author aero.tang
 * @version 2018/8/3 22:06
 */
@SuppressLint("SetTextI18n")
public class BinderActivity extends BasicActivity implements View.OnClickListener {

    private TextView mTvState;
    private TextView mTvMessage;
    private TextView mBtnBind;
    private TextView mBtnUnbind;
    private TextView mBtnAdd;
    private TextView mBtnQuery;

    @Override
    protected int getContent() {
        return R.layout.activity_binder;
    }

    @Override
    protected void initComponent() {
        mBtnBind = findViewById(R.id.btn_bind);
        mBtnUnbind = findViewById(R.id.btn_unbind);
        mBtnAdd = findViewById(R.id.btn_add);
        mBtnQuery = findViewById(R.id.btn_query);

        mTvState = findViewById(R.id.tv_state);
        mTvMessage = findViewById(R.id.tv_message);

        mBtnBind.setOnClickListener(this);
        mBtnUnbind.setOnClickListener(this);
        mBtnAdd.setOnClickListener(this);
        mBtnQuery.setOnClickListener(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private IRemoteService mBinder;
    private boolean mIsBound;

    private final ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder = IRemoteService.Stub.asInterface(service);
            mTvState.setTextColor(ContextCompat.getColor(BinderActivity.this, R.color.colorAccent));
            mTvState.setText("remote service is connected");
            mBtnAdd.setEnabled(true);
            mBtnQuery.setEnabled(true);
            mBtnUnbind.setEnabled(true);
            mBtnBind.setEnabled(false);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBinder = null;
            mTvState.setTextColor(Color.GRAY);
            mTvState.setText("remote service is disconnected");
            mBtnAdd.setEnabled(false);
            mBtnQuery.setEnabled(false);
            mBtnUnbind.setEnabled(false);
        }
    };

    private void bindRemote() {
        Intent intent = new Intent(this, RemoteService.class);
        mIsBound = bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        mTvState.setTextColor(Color.DKGRAY);
        mTvState.setText("remote service is binding......");
    }

    private void unbindRemote() {
        if (mIsBound) {
            unbindService(mConnection);
            mIsBound = false;
            mTvState.setTextColor(Color.GRAY);
            mTvState.setText("remote service is unbound");
            mBtnAdd.setEnabled(false);
            mBtnQuery.setEnabled(false);
            mBtnUnbind.setEnabled(false);
            mBtnBind.setEnabled(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bind:
                bindRemote();
                break;
            case R.id.btn_unbind:
                unbindRemote();
                break;
            case R.id.btn_add:
                addEmployee();
                break;
            case R.id.btn_query:
                queryEmployee();
                break;
        }
    }

    private void queryEmployee() {
        if (mIsBound) {
            try {
                if (mBinder != null) {
                    Employee employee = mBinder.queryEmployee("123456");
                    mTvMessage.setText("query result: " + employee);
                }
            } catch (RemoteException | SecurityException e) {
                e.printStackTrace();
            }
        }
    }

    private void addEmployee() {
        if (mIsBound) {
            Employee employee = new Employee();
            employee.jobNum = "123456";
            employee.name = "Heyraud";
            employee.monthlySalary = 15;
            try {
                if (mBinder != null) {
                    boolean result = mBinder.addEmployee(employee);
                    mTvMessage.setText("add result: " + result);
                }
            } catch (RemoteException | SecurityException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mIsBound) {
            unbindService(mConnection);
            mIsBound = false;
            mBinder = null;
        }
    }
}
