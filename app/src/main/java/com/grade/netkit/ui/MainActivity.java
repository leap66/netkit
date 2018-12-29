package com.grade.netkit.ui;

import com.grade.netkit.R;
import com.grade.netkit.Test;
import com.grade.netkit.client.ApiClient;
import com.grade.unit.callback.OnNetCallback;
import com.grade.unit.mgr.BaseMgr;
import com.grade.unit.model.base.BEntity;
import com.grade.unit.util.ToastUtil;

public class MainActivity extends RxActivity {
  @Override
  protected void initComponent() {
    setContentView(R.layout.activity_main);
    BaseMgr.init(context);
    ApiClient.init("http://172.22.1.224:8088/leap/");
    Test.register(context, new BEntity(), new OnNetCallback<BEntity>() {
      @Override
      public void onResult(boolean state, BEntity entity, String errorMsg) {
        ToastUtil.showFailure(errorMsg);
      }
    });
  }

  @Override
  protected void loadData() {

  }
}
