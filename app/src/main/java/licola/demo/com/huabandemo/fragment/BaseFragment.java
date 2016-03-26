package licola.demo.com.huabandemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonSyntaxException;

import java.net.UnknownHostException;

import butterknife.BindString;
import butterknife.ButterKnife;
import licola.demo.com.huabandemo.R;
import licola.demo.com.huabandemo.Util.Logger;
import licola.demo.com.huabandemo.Util.NetUtils;


/**
 * Created by LiCola on  2015/12/19  20:19
 */
public abstract class BaseFragment extends Fragment {

    @BindString(R.string.snack_message_net_error)
    protected String snack_message_net_error;
    @BindString(R.string.snack_action_to_setting)
    protected String snack_action_to_setting;
    @BindString(R.string.snack_message_unknown_error)
    protected String snack_message_unknown_error;
    @BindString(R.string.snack_message_data_error)
    protected String snack_message_data_error;

    protected String TAG = getTAG();

    protected abstract String getTAG();

    protected View mRootView;

    protected abstract int getLayoutId();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d(TAG);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Logger.d(TAG);
        if (null == mRootView) {
            mRootView = inflater.inflate(getLayoutId(), null);
        }
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.d(TAG);
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.d(TAG);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Logger.d(TAG);
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.d(TAG);

    }

    /**
     * 异常类型判断 检查网络访问的异常 类型
     * 根据类型 弹出SnackBar提示
     * @param e
     */
    protected void checkException(Throwable e) {
        if ((e instanceof UnknownHostException)) {
            NetUtils.showNetworkError(getActivity(), mRootView, snack_message_net_error, snack_action_to_setting);
        }
        if (e instanceof JsonSyntaxException) {
            NetUtils.showNetworkError(getActivity(), mRootView, snack_message_data_error, snack_action_to_setting);
        } else {
            Snackbar.make(mRootView, snack_message_unknown_error, Snackbar.LENGTH_LONG);
        }
    }

}
