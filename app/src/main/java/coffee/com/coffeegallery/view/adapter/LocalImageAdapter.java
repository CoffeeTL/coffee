package coffee.com.coffeegallery.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import coffee.com.coffeegallery.R;
import coffee.com.coffeegallery.view.holder.ImageHolder;

/**
 * Created by Administrator on 2017/1/25.
 */

public class LocalImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<String> pathList;
    private LayoutInflater inflater;
    public LocalImageAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
        pathList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_IMG){
            View imgView = inflater.inflate(R.layout.img_cell,null);
            ImageHolder holder = new ImageHolder(context,imgView);
            ButterKnife.bind(holder,imgView);
            return holder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ImageHolder){
            ((ImageHolder) holder).setData(pathList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return pathList.size();
    }
    private static int TYPE_IMG = 1;
    @Override
    public int getItemViewType(int position) {
        return TYPE_IMG;
    }
    public void setPathList(List<String> pathList){
        this.pathList = pathList;
    }
}
