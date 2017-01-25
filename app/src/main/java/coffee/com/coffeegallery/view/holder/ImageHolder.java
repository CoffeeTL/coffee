package coffee.com.coffeegallery.view.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;
import coffee.com.coffeegallery.R;
import coffee.com.coffeegallery.utils.DensityUtil;

/**
 * Created by Administrator on 2017/1/25.
 */

public class ImageHolder extends RecyclerView.ViewHolder {
    private Context context;
    @BindView(R.id.img_cell_iv)ImageView iv;
    public ImageHolder(Context context,View main){
        super(main);
        this.context = context;
    }
    public void setData(String path){
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) iv.getLayoutParams();
        params.width  = DensityUtil.getDeviceInfo(context)[0]/4;
        params.height = DensityUtil.getDeviceInfo(context)[0]/4;
        iv.setLayoutParams(params);
        Glide.with(context).load(path).
                diskCacheStrategy(DiskCacheStrategy.SOURCE).centerCrop().into(iv);
    }
}
