package freestar.vip.library;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.TextView;

import butterknife.BindView;
import lib.base.BaseActivity;
import vip.freestar.mylogger.Logger;

public class MainActivity extends BaseActivity {

    @BindView(R.id.te)
    TextView mTe;

    @Override
    protected void initView() {
        String[] permissions = null;
        try {
            PackageInfo info = getPackageManager().getPackageInfo("freestar.vip.library", PackageManager.GET_PERMISSIONS);
            permissions = info.requestedPermissions;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder builder = new StringBuilder();
        if (permissions != null) {
            for (String permission : permissions) {
                builder.append(permission).append("--");
            }
        }
        Logger.e(builder.toString());
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

}
