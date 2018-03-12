package software.appus.insta_fans.ui;

import software.appus.insta_fans.ui.models.InsertMedialModel;
import software.appus.insta_fans.ui.models.ProgressModel;

/**
 * Created by anatolii.pozniak on 3/12/18.
 */

public interface MainLoaderView {

    void updateProgress(ProgressModel progress);

    void appendMedia(InsertMedialModel mediaList);
}
