package coffee.com.coffeegallery.base;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import coffee.com.coffeegallery.utils.ImagePipelineConfigUtils;

/**
 * Created by Administrator on 2017/1/25.
 */

public class App extends Application{
    private static App app;
    public static App getApp() {
        return app;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        ImagePipelineConfig pipelineConfig = ImagePipelineConfigUtils.
                getDefaultImagePipelineConfig(this);
        Fresco.initialize(this, pipelineConfig);
    }
}
