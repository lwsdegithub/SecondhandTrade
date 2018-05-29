package com.wanghongyun.secondhandtrade.activity.Mine;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wanghongyun.secondhandtrade.R;
import com.wanghongyun.secondhandtrade.activity.AddNewGoodsActivity;
import com.wanghongyun.secondhandtrade.activity.UpdatePasswordActivity;
import com.wanghongyun.secondhandtrade.bean.User;
import com.wanghongyun.secondhandtrade.constant.NetConstant;
import com.wanghongyun.secondhandtrade.helper.gsonBeans.Common;
import com.wanghongyun.secondhandtrade.helper.retrofitInterfaces.GoodsHelper;
import com.wanghongyun.secondhandtrade.helper.retrofitInterfaces.UserHelper;
import com.wanghongyun.secondhandtrade.utils.GlideUtils;
import com.wanghongyun.secondhandtrade.utils.IntentUtils;
import com.wanghongyun.secondhandtrade.utils.RetrofitUtils;
import com.wanghongyun.secondhandtrade.utils.SharedPreferencesUtils;
import com.wanghongyun.secondhandtrade.utils.StringUtils;
import com.wanghongyun.secondhandtrade.utils.ToastUtils;
import com.wanghongyun.secondhandtrade.utils.UserUtils;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by 李维升 on 2018/5/16.
 */

public class MyDetailsActivity extends AppCompatActivity {

    private static final int SELECT_HEAD_ICON=0;

    private ArrayList<Uri> headIconUriList;

    @BindView(R.id.civ_head_icon)
    CircleImageView civHeadIcon;
    @BindView(R.id.ckv_phone)
    RelativeLayout relLayPhone;
    @BindView(R.id.ckv_user_name)
    RelativeLayout relLayUserName;
    @BindView(R.id.ckv_credit_score)
    RelativeLayout relLayCreditScore;

    TextView tvKeyPhone;
    TextView tvValuePhone;
    TextView tvKeyUserName;
    TextView tvValueUserName;
    TextView tvKeyCreditScore;
    TextView tvValueCreditScore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_details);
        ButterKnife.bind(this);
        this.initView();
    }

    private void initView() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("个人资料");
        actionBar.setDisplayHomeAsUpEnabled(true);

        tvKeyPhone=relLayPhone.findViewById(R.id.tv_key);
        tvValuePhone=relLayPhone.findViewById(R.id.tv_value);
        tvKeyPhone.setText("手机号");

        tvKeyUserName=relLayUserName.findViewById(R.id.tv_key);
        tvValueUserName=relLayUserName.findViewById(R.id.tv_value);
        tvKeyUserName.setText("用户名");

        tvKeyCreditScore=relLayCreditScore.findViewById(R.id.tv_key);
        tvValueCreditScore=relLayCreditScore.findViewById(R.id.tv_value);
        tvKeyCreditScore.setText("信誉分");
        initData();
    }

    private void initData(){
        RetrofitUtils.getRetrofit(NetConstant.BASE_URL).create(UserHelper.class).getUserByIdCall(0, UserUtils.getUserId(this)).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    User user=response.body();
                    GlideUtils.loadImage(getApplicationContext(),NetConstant.BASE_HEAD_ICON_URL+user.getHeadIcon(),civHeadIcon);
                    tvValuePhone.setText(user.getPhone());
                    tvValueUserName.setText(user.getUserName());
                    tvValueCreditScore.setText(user.getCreditScore()+"");
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                ToastUtils.showMsg(getApplicationContext(),"请求失败");
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_exit_login:
                SharedPreferencesUtils.putData(this, SharedPreferencesUtils.USER, SharedPreferencesUtils.IS_LOGIN, false);
                this.finish();
                break;
            case R.id.item_update_password:
                updatePassword();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_my_details_menu, menu);
        return true;
    }

    @OnClick({R.id.civ_head_icon,R.id.ckv_phone, R.id.ckv_user_name, R.id.ckv_credit_score})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.civ_head_icon:
                selectHeadIcon();
                break;
            case R.id.ckv_phone:
                //showUpdateDialog(tvValuePhone);
                ToastUtils.showMsg(this,"不可修改");
                break;
            case R.id.ckv_user_name:
                showUpdateDialog("USER_NAME",tvValueUserName,"昵称");
                break;
            case R.id.ckv_credit_score:
                ToastUtils.showMsg(this,"不可修改");
                break;
        }
    }
    //修改密码
    private void updatePassword(){
        View view=View.inflate(this,R.layout.common_edittext,null);
        final EditText editText=view.findViewById(R.id.et_common);
        editText.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD);
        new AlertDialog.Builder(this).setTitle("请输入原密码").setView(view).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (editText.getText().toString().equals(UserUtils.getUserPassword(getApplicationContext()))){
                    IntentUtils.startActivity(MyDetailsActivity.this, UpdatePasswordActivity.class);
                }else {
                    ToastUtils.showMsg(getApplicationContext(),"密码不正确");
                }
            }
        }).setNegativeButton("取消",null).show();
    }
    //选择头像图片
    private void selectHeadIcon(){
        Matisse.from(this)
                .choose(MimeType.allOf())
                .countable(true)
                .maxSelectable(1)
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .imageEngine(new GlideEngine())
                .thumbnailScale(0.85f)
                .forResult(SELECT_HEAD_ICON);
    }
    //修改头像
    private void updateHeadIcon(){
        MultipartBody.Builder builder=new MultipartBody.Builder().setType(MultipartBody.FORM);
        //首先把得到的Uri转成绝对路径
        String path="";
        if (!headIconUriList.isEmpty()){
            path=StringUtils.getFilePathFromContentUri(headIconUriList.get(0),getContentResolver());
            }
        File file = new File(path);//图片地址
        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        builder.addFormDataPart("image", file.getName(), imageBody);//"imgfile"+i 后台接收图片流的参数名

        builder.addFormDataPart("USER_ID",""+UserUtils.getUserId(this));
        List<MultipartBody.Part> parts = builder.build().parts();
        RetrofitUtils.getRetrofit(NetConstant.BASE_URL).create(UserHelper.class).getUpdateHeadIconCall(parts).enqueue(new Callback<Common>() {
            @Override
            public void onResponse(Call<Common> call, Response<Common> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus()==NetConstant.OK){
                        ToastUtils.showMsg(getApplicationContext(),"上传成功");
                        Glide.with(getApplicationContext()).load(headIconUriList.get(0)).into(civHeadIcon);
                        UserUtils.updateSpData(getApplicationContext());
                    }
                }else {
                    ToastUtils.showMsg(getApplicationContext(),"上传失败");
                }
            }
            @Override
            public void onFailure(Call<Common> call, Throwable t) {
                ToastUtils.showMsg(getApplicationContext(),"上传失败");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==SELECT_HEAD_ICON&&resultCode==RESULT_OK){
            headIconUriList= (ArrayList<Uri>) Matisse.obtainResult(data);
            if (!headIconUriList.isEmpty()){
                ToastUtils.showMsg(this,"正在上传");
                updateHeadIcon();
            }
        }
    }

    //自定义修改信息的dialog
    private void showUpdateDialog(final String updateWhat, final View updateView, String title){
        View view=View.inflate(this,R.layout.common_edittext,null);
        final EditText editText=view.findViewById(R.id.et_common);
        new AlertDialog.Builder(this).setTitle("修改"+title).setView(view).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(!editText.getText().toString().isEmpty()){
                    Map<String,String> queryMap=new HashMap<>();
                    queryMap.put("TYPE",5+"");
                    queryMap.put("USER_ID",UserUtils.getUserId(getApplicationContext())+"");
                    queryMap.put(updateWhat,editText.getText().toString());
                    RetrofitUtils.getRetrofit(NetConstant.BASE_URL).create(UserHelper.class).getUpdateUserCall(queryMap).enqueue(new Callback<Common>() {
                        @Override
                        public void onResponse(Call<Common> call, Response<Common> response) {
                            if (response.isSuccessful()){
                                if (response.body().getStatus()==NetConstant.OK){
                                    ToastUtils.showMsg(getApplicationContext(),"修改成功");
                                    initData();
                                }
                            }else {
                                ToastUtils.showMsg(getApplicationContext(),"修改失败");
                            }
                        }
                        @Override
                        public void onFailure(Call<Common> call, Throwable t) {
                            ToastUtils.showMsg(getApplicationContext(),"修改失败");
                        }
                    });
                }else {
                    ToastUtils.showMsg(getApplicationContext(),"输入为空");
                }
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).show();
    }
}
