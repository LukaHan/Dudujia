package com.ddj.dudujia.app;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * Created by hanshaobo on 15/10/2017.
 */

public class SampleApplication extends TinkerApplication {
    public SampleApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL,"com.ddj.dudujia.app.SampleApplicationLike","com.tencent.tinker.loader.TinkerLoader",false);
    }
}
