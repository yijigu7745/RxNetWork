package cn.com.yijigu.rxnetworkframework.utils;

import android.content.Context;
import android.graphics.Color;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class DialogFactoryUtils {
    SweetAlertDialog sweetAlertDialog;
    Context context;

    private DialogFactoryUtils(Context context){
        this.context = context;
    }

    public static DialogFactoryUtils builder(Context context){
        return new DialogFactoryUtils(context) ;
    }

    public void close(){
        if(this.sweetAlertDialog != null){
            if (this.sweetAlertDialog.isShowing()){
                sweetAlertDialog.cancel();
            }
            sweetAlertDialog = null;
        }
    }

    public DialogFactoryUtils progress(String title){
        close();
        sweetAlertDialog = new SweetAlertDialog(context,SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        sweetAlertDialog.setTitleText(title);
        sweetAlertDialog.setCancelable(false);
        return this ;
    }


    public DialogFactoryUtils waring(String title,boolean cancelable){
        close();
        sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
        sweetAlertDialog.setTitleText(title)
                .setConfirmText("确定")
//                .showCancelButton(false)
                .setCancelable(cancelable);
        return this ;
    }

    /**
     * @param title     标题
     * @param content   内容
     * @param cancelable     true 点击提示框外边能取消提示框
     * @param isCancelable   true 需要“取消”按钮
     * @return
     */
    public DialogFactoryUtils waring(String title,String content ,boolean cancelable,boolean isCancelable){
        close();
        sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
        if (!StringUtils.isEmpty(title)){
            sweetAlertDialog.setTitleText(title) ;
        }
        if (!StringUtils.isEmpty(content)){
            sweetAlertDialog.setContentText(content) ;
        }
        sweetAlertDialog.setTitleText(title)
                .setConfirmText("确定")
//                .showCancelButton(false)
                .setCancelable(cancelable);
        if (isCancelable){
            sweetAlertDialog.setCancelText("取消") ;
        }
        return this ;
    }

    public DialogFactoryUtils waring(String title,String content){
        close();
        sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
        sweetAlertDialog.setTitleText(title)
                .setCancelText("取消")
                .setConfirmText("确定")
                .showCancelButton(true);
        if (!StringUtils.isEmpty(content)){
            sweetAlertDialog.setContentText(content) ;
        }
        return this ;
    }

    public DialogFactoryUtils error(String title){
        close();
        sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
        sweetAlertDialog.setTitleText(title)
                .setConfirmText("确定");
        return this ;
    }

    public DialogFactoryUtils error(String title,String content){
        close();
        sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
        sweetAlertDialog.setTitleText(title)
                .setCancelText("取消")
                .setConfirmText("确定");
        if (!StringUtils.isEmpty(content)){
            sweetAlertDialog.setContentText(content) ;
        }
        return this ;
    }

    public DialogFactoryUtils success(String title){
        close();
        sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
        sweetAlertDialog.setTitleText(title)
                .setConfirmText("确定");
        return this ;
    }
    /**
     * @param title     标题
     * @param content   内容
     * @param cancelable     true 点击提示框外边能取消提示框
     * @param isCancelable   true 需要“取消”按钮
     * @return
     */
    public DialogFactoryUtils success(String title,String content ,boolean cancelable,boolean isCancelable){
        close();
        sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
        if (!StringUtils.isEmpty(title)){
            sweetAlertDialog.setTitleText(title) ;
        }
        if (!StringUtils.isEmpty(content)){
            sweetAlertDialog.setContentText(content) ;
        }
        sweetAlertDialog.setTitleText(title)
                .setConfirmText("确定")
//                .showCancelButton(false)
                .setCancelable(cancelable);
        if (isCancelable){
            sweetAlertDialog.setCancelText("取消") ;
        }
        return this ;
    }


    public DialogFactoryUtils switchError(String title){
        //转化为错误框
        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sDialog) {
                sDialog
                        .setTitleText(title)
                        .setConfirmText("确定")
                        .setConfirmClickListener(null)
                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
            }
        }) ;
        return this ;
    }

    public DialogFactoryUtils switchSuccess(String title){
        //转化为成功框
        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sDialog) {
                sDialog
                        .setTitleText(title)
                        .setConfirmText("确定")
                        .setConfirmClickListener(null)
                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
            }
        }) ;
        return this ;
    }
    public DialogFactoryUtils setConfirmClickListener(SweetAlertDialog.OnSweetClickListener confirmClickListener){
        this.sweetAlertDialog.setConfirmClickListener(confirmClickListener);
        return this ;
    }
    public DialogFactoryUtils setCancelClickListener(SweetAlertDialog.OnSweetClickListener cancelListener){
        this.sweetAlertDialog.setCancelClickListener(cancelListener) ;
        return this ;
    }

    public DialogFactoryUtils show(){
        if (this.sweetAlertDialog != null){
            if (!sweetAlertDialog.isShowing()){
                sweetAlertDialog.show();
            }
        }
        return this ;
    }

    public boolean isShowing(){
        if (this.sweetAlertDialog != null){
            return sweetAlertDialog.isShowing();
        }else {
            return false;
        }
    }

}
