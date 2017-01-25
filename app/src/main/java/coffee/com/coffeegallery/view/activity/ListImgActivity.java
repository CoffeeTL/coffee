package coffee.com.coffeegallery.view.activity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import coffee.com.coffeegallery.R;
import coffee.com.coffeegallery.base.BaseActivity;
import coffee.com.coffeegallery.view.adapter.LocalImageAdapter;
import coffee.com.coffeegallery.view.ui.RVDecoration;

/**
 * Created by Administrator on 2017/1/25.
 */

public class ListImgActivity extends BaseActivity {
    @BindView(R.id.list_img_activity_recycle)RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    private List<String> pathList;
    private LocalImageAdapter imageAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        layoutManager = new GridLayoutManager(this,4);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new RVDecoration(this,RVDecoration.VERTICAL_LIST));
        getImageViewFromLocal();

    }

    private void getImageViewFromLocal() {
        pathList = new ArrayList<>();
        imageAdapter = new LocalImageAdapter(this);
        recyclerView.setAdapter(imageAdapter);
        imageAdapter.setPathList(pathList);
        imageAdapter.notifyDataSetChanged();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver mContentResolver = ListImgActivity.this
                        .getContentResolver();
                // 只查询jpeg和png的图片
                Cursor mCursor = mContentResolver.query(mImageUri, null,
                        MediaStore.Images.Media.MIME_TYPE + "=? or "
                                + MediaStore.Images.Media.MIME_TYPE + "=? or "+MediaStore.Images.Media.MIME_TYPE + "=?",
                        new String[]{"image/jpeg", "image/png","image/wepg"},
                        MediaStore.Images.Media.DATE_MODIFIED);
                Log.i("TAG", mCursor.getCount() + "");
                while (mCursor.moveToNext()) {
                    // 获取图片的路径
                    String path = mCursor.getString(mCursor
                            .getColumnIndex(MediaStore.Images.Media.DATA));
                    Log.i("TAG",path);
                    pathList.add(path);
                    imageAdapter.notifyItemChanged(pathList.size()-1);
                }
                mCursor.close();
            }
        }).start();
    }

    @Override
    public int getLayoutId() {
        return R.layout.list_img_activity;
    }


}
