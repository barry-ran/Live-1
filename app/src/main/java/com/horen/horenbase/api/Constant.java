package com.horen.horenbase.api;

import android.os.Environment;

import java.util.ArrayList;
import java.util.Arrays;

public class Constant {
    public static String ADS_PATH = null;
    public static final String AES_IV = "5efd3f6060eavr2r";
    public static final String AES_PWD = "625202f9149avr2r";
    public static final String API_ADSCOUNT = "/api/ads/adsCount";
    public static final String API_APP_DOWNLOAD = "/api/apps/download";
    public static final String API_APP_LIST = "/api/apps/listall";
    public static final String API_APP_SHOW = "/api/apps/show";
    public static final String API_COLLECT_VIDEO = "/api/videos/addFav";
    public static final String API_FAV_LIST = "/api/videos/myFav";
    public static final String API_GET_ACTRESS = "/api/actress/listAll";
    public static final String API_GET_ACTRESSINFO = "/api/actress/show";
    public static final String API_GET_CLASSIFY = "/api/genre/genreVideos";
    public static final String API_GET_CLASSIFYLIST = "/api/genre/listAll";
    public static final String API_GET_LASTNOTICE = "/api/messages/getThreeMsg";
    public static final String API_GET_NEW_LIST = "/api/videos/listAll";
    public static final String API_GET_NOTICELIST = "/api/messages/listAll";
    public static final String API_LOGIN = "/api/users/login";
    public static final String API_REGISTER = "/api/users/register";
    public static final String API_SEARCH = "/api/videos/search";
    public static final String API_SYSTEM = "/api/system/getInfo";
    public static final String API_VERSION = "/api/version/getVersion";
    public static final String API_VIDEO = "/api/videos/show";
    public static String BACKUP_DOMAIN = "a9.bbspapi.com";
    public static ArrayList<String> DOMAINLIST = new ArrayList(Arrays.asList(new String[]{"api.maomisp.com", "api.apimaomisp.com", "ssr.mimiapisp.com", "a8.kkspapi.com"}));
    public static final String DOWNLOAD = (Environment.getExternalStorageDirectory().getPath() + "/download/");
    public static String HOST_HTTP = "http://";
    public static String HOST_HTTPS = "https://";
    public static String HOST_PORT = ":8099";
    public static final String SDCARD = (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/MaomiAV");
    public static String SHARE_CONTENT = null;
    public static final String URL_SIG_KEY = "avr2r_pass_xyz";
}
