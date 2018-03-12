package software.appus.insta_fans.ui.multithreading;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.ref.WeakReference;

import software.appus.insta_fans.ui.MainLoaderView;
import software.appus.insta_fans.ui.models.InsertMedialModel;
import software.appus.insta_fans.ui.models.ProgressModel;

// UI handler class, declared as static so it doesn't have implicit
// reference to activity context. This helps to avoid memory leak.
public class UiHandler extends Handler {
    private WeakReference<MainLoaderView> mView;

    public UiHandler(Looper looper, MainLoaderView view) {
        super(looper);
        this.mView = new WeakReference<>(view);
    }

    // This method will run on UI thread
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        Bundle bundle;
        switch (msg.what) {
            // Our communication protocol for passing a string to the UI thread
            case Util.MESSAGE_ID:
                bundle = msg.getData();
                String messsageText = bundle.getString(Util.MESSAGE_BODY, Util.EMPTY_MESSAGE);
                if (mView != null && mView.get() != null)
                    // mView.get().append(Util.getReadableTime() + " " + messsageText + "\n");
                    break;
            case Util.MESSAGE_PROGRESS:
                bundle = msg.getData();
                ProgressModel progress = bundle.getParcelable(Util.MESSAGE_BODY);
                if (mView != null && mView.get() != null)
                    mView.get().updateProgress(progress);
                break;

            case Util.MESSAGE_INSERT_MEDIA:
                bundle = msg.getData();
                InsertMedialModel data = bundle.getParcelable(Util.MESSAGE_BODY);
                if (mView != null && mView.get() != null)
                    mView.get().appendMedia(data);
                break;
            default:
                break;
        }
    }
}