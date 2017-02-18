package coffee.com.coffeegallery.view.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import coffee.com.coffeegallery.R;
import coffee.com.coffeegallery.base.BaseActivity;
import coffee.com.coffeegallery.view.ui.FilterDialog;
import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImageGrayscaleFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSaturationFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSepiaFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSharpenFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSobelEdgeDetection;
import jp.co.cyberagent.android.gpuimage.GPUImageView;

/**
 * Created by Administrator on 2017/2/15.
 */

public class GpuImageActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.activity_gpuimage)GPUImageView gpuImage;
    @BindView(R.id.activity_seekBar)SeekBar seekBar;
    @BindView(R.id.activity_button_choose_filter)Button filter_btn;
    @BindView(R.id.activity_button_save)Button save_btn;
    private FilterDialog filterDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        gpuImage.setImage(new File(getIntent().getStringExtra("path")));
        filter_btn.setOnClickListener(this);
        save_btn.setOnClickListener(this);
        seekBar.setMax(100);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                GPUImageSaturationFilter filter = new GPUImageSaturationFilter();
                filter.setSaturation(i);
                gpuImage.setFilter(filter);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_gpu_img;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.activity_button_choose_filter:
                showFilterDialog();
                break;
            case R.id.activity_button_save:
                gpuImage.saveToPictures("Coffee_GPU", System.currentTimeMillis() + ".jpg ", new GPUImageView.OnPictureSavedListener() {
                    @Override
                    public void onPictureSaved(Uri uri) {
                        if(uri != null){
                            Toast.makeText(GpuImageActivity.this,"picture saved",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
                break;
        }
    }

    private void showFilterDialog() {
        filterDialog = new FilterDialog(this).builder();
        filterDialog.setCancelable(true);
        filterDialog.show();
        filterDialog.setOnFilterInterface(new FilterDialog.OnFilterBtnInterface() {
            @Override
            public void clickCancel() {
                filterDialog.dismiss();
            }

            @Override
            public void clickOriginal() {
                gpuImage.setFilter(null);
            }

            @Override
            public void clickSepia() {
                gpuImage.setFilter(new GPUImageSepiaFilter());
            }

            @Override
            public void clickSharp() {
                gpuImage.setFilter(new GPUImageSharpenFilter());
            }

            @Override
            public void clickGray() {
                gpuImage.setFilter(new GPUImageGrayscaleFilter());
            }

            @Override
            public void clickEdge() {
                gpuImage.setFilter(new GPUImageSobelEdgeDetection());
            }
        });
    }
}
