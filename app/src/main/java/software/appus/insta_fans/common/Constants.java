package software.appus.insta_fans.common;

public interface Constants {

    String CLIENT_ID = "1123882b917a4edc88b5be843a1a9514";
    String CLIENT_SECRET = "675c5c2cef9f4ef4bc221baaf7e4bf4a";
    String REDIRECT_URI = "https://api.instagram.com/oauth/authorize/?client_id=CLIENT-ID%26redirect_uri=REDIRECT-URI%26response_type=token";
    String FAILURE_URL = "http://bwuit.com/auth/failure";
    String OAUTH_URI = "https://api.instagram.com/oauth/authorize/?client_id="
            + CLIENT_ID + "&redirect_uri=" + REDIRECT_URI + "&response_type=token&scope=likes+follower_list";
    String TOKEN_URL = "https://api.instagram.com/oauth/access_token";


  //   String OAUTH_URI = "https://www.instagram.com/oauth/authorize/?client_id=1123882b917a4edc88b5be843a1a9514&redirect_uri=https://api.instagram.com/oauth/authorize/?client_id=CLIENT-ID%26redirect_uri=REDIRECT-URI%26response_type=token&response_type=token&scope=likes+follower_list";

     String REG_EXP_ACCESS_TOKEN = "access_token=.*";

    String LOG_TAG = "LIKULATOR INSTA";

    //Billing
    public static final String SKU_PRO_VERSION = "pro_version";
    public static final String BASE_64_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA65C38wic+Wh7Y7L4bVqU2zyFd4+mHeqgGuxaGbQTeIm3y3KDPq6kHNUbo4/+JR9nKXrr0E6muVmt52BivgBHdS706G+FSyVRpEktOG2fpZxFUmcdUw02XyLYCT63WdmyUiaFK8hhFQsWGxgXjrGui50j/bPccSjFSdK3E8bfbmmX79t0RICYQwda/wCdXlpzfkI12r6c5n1ot0f+5yXDoz4s51jxWquEB1fA8SYyYg0VvOjyy9pshmycLgD4O55sfPaROgZCE0uavrbkUweO1PxSPtOI7YolNFVaMsaT8VkFoYSar03UYr3vtbddv5nnVcZ6RHSbWZuikvBWg/AvvwIDAQAB";;



}
