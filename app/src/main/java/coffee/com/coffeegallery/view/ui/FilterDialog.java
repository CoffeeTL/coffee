package coffee.com.coffeegallery.view.ui;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.text.DecimalFormat;

import coffee.com.coffeegallery.R;

/**
 * Created by Administrator on 2017/2/15.
 */

public class FilterDialog {
    private Context context;
    private Dialog dialog;
    private Display display;
    private LayoutInflater inflater;
    private TextView original_btn;
    private TextView cancel_btn;
    private TextView sepia_btn;
    private TextView gray_btn;
    private TextView sharp_btn;
    private TextView edge_btn;
    public interface OnFilterBtnInterface{
        void clickCancel();
        void clickOriginal();
        void clickSepia();
        void clickSharp();
        void clickGray();
        void clickEdge();
    }
    private OnFilterBtnInterface listener;
    public void setOnFilterInterface(OnFilterBtnInterface listener){
        this.listener = listener;
    }
    public FilterDialog(Context context){
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
        inflater = LayoutInflater.from(context);
    }
    public FilterDialog builder(){
        View view = LayoutInflater.from(context).inflate(
                R.layout.filter_sheet_dialog, null);
        original_btn = (TextView) view.findViewById(R.id.filter_sheet_dialog_origin_btn);
        cancel_btn = (TextView) view.findViewById(R.id.filter_sheet_dialog_btn);
        sepia_btn = (TextView) view.findViewById(R.id.filter_sheet_dialog_sepia_btn);
        gray_btn = (TextView) view.findViewById(R.id.filter_sheet_dialog_gray_btn);
        sharp_btn = (TextView) view.findViewById(R.id.filter_sheet_dialog_sharp_btn);
        edge_btn = (TextView) view.findViewById(R.id.filter_sheet_dialog_edge_btn);
        view.setMinimumWidth(display.getWidth());
        dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.LEFT | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        dialogWindow.setAttributes(lp);

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.clickCancel();
            }
        });
        original_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.clickOriginal();
            }
        });
        sepia_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.clickSepia();
                dismiss();
            }
        });
        sharp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.clickSharp();
                dismiss();
            }
        });
        gray_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.clickGray();
                dismiss();
            }
        });
        edge_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.clickEdge();
                dismiss();
            }
        });
        return this;
    }
    public FilterDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }
    public FilterDialog show() {
        dialog.show();
        return this;
    }
    public FilterDialog dismiss(){
        dialog.dismiss();
        return this;
    }
}
