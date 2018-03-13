package software.appus.insta_fans.presentation.multithreading;

import android.os.Bundle;
import android.os.Message;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import software.appus.insta_fans.presentation.models.InsertMedialModel;
import software.appus.insta_fans.presentation.models.MediaModel;
import software.appus.insta_fans.presentation.models.ProgressModel;

/**
 * Created by Frank Tan on 10/04/2016.
 * <p>
 * A helper class with static properties and methods
 */
public class Util {
    public static final String LOG_TAG = "BackgroundThread";
    public static final int MESSAGE_ID = 1;
    public static final int MESSAGE_PROGRESS = 2;
    public static final int MESSAGE_INSERT_MEDIA = 3;
    public static final String MESSAGE_BODY = "MESSAGE_BODY";
    public static final String EMPTY_MESSAGE = "<EMPTY_MESSAGE>";

    public static String getReadableTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
        return sdf.format(new Date());
    }

    public static Message createMessage(int id, String dataString) {
        Bundle bundle = new Bundle();
        bundle.putString(Util.MESSAGE_BODY, dataString);
        Message message = new Message();
        message.what = id;
        message.setData(bundle);

        return message;
    }

    public static Message createProgress(int id, ProgressModel progress) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Util.MESSAGE_BODY, progress);
        Message message = new Message();
        message.what = id;
        message.setData(bundle);

        return message;
    }

    public static Message createInsertMediasMessage(int id, List<MediaModel> mediaList, int offset, String newMaxId) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Util.MESSAGE_BODY, new InsertMedialModel(mediaList, offset, newMaxId));
        Message message = new Message();
        message.what = id;
        message.setData(bundle);
        return message;
    }
}
