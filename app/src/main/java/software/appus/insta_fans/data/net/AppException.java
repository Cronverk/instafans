package software.appus.insta_fans.data.net;

/**
 * Created by anatolii.pozniak on 10/6/17.
 */

public class AppException extends RuntimeException {
    private String message;
    private int messageId;
    private Type mType;

    private AppException() {
    }

    private AppException(Builder builder) {
        mType = builder.mType;
        message = builder.message;
        messageId = builder.messageId;
    }
//
//    private String removeQuotes(final String message) {
//        String m = message;
//        return m.replace("\"", "");
//    }

    @Override
    public String getMessage() {
        return message;
    }


    public enum Type {
        INTERNET, BAD_REQUEST, UNAUTHORIZED, TIMEOUT, WRONG_DATA, DEFAULT

    }

    public static class Builder {
        private String message;
        private Type mType;
        private int messageId;

        public Builder(Type type) {
            mType = type;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setMessageId(int messageId) {
            this.messageId = messageId;
            return this;
        }


        public AppException build() {
            return new AppException(this);
        }
    }

}
