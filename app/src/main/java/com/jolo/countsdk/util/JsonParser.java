package com.jolo.countsdk.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.JsonReader;
import android.util.Log;

import com.jolo.countsdk.net.bean.UserAgent;
import com.jolo.countsdk.net.request.BaseReq;
import com.jolo.countsdk.net.request.GetAdSdkConfigReq;
import com.jolo.countsdk.net.request.UploadUserInsApplistReq;
import com.jolo.countsdk.net.response.BaseResp;
import com.jolo.countsdk.net.response.GetAdSdkConfigResp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.StringReader;

/**
 * Description:
 * Created by duzhiqi on 2016/12/19.
 */

public class JsonParser {
    

    private static final String TAG = JsonParser.class.getSimpleName();

    @Nullable
    public static String toJson(BaseReq request) {
        if (request instanceof GetAdSdkConfigReq) {
            GetAdSdkConfigReq req = (GetAdSdkConfigReq) request;
            return toJson(req);
        } else if (request instanceof UploadUserInsApplistReq) {
            UploadUserInsApplistReq req = (UploadUserInsApplistReq) request;
            return toJson(req);
        }
        return null;
    }

    @Nullable
    private static String toJson(GetAdSdkConfigReq request) {
        JSONObject jsonObj = new JSONObject();
        try {
            setJsonUA(jsonObj, request);
            jsonObj.put("businessCode", request.getBusinessCode());
            jsonObj.put("uuid", request.getUuid());
            jsonObj.put("installTime", request.getInstallTime().toString());
            jsonObj.put("adSdkVer", request.getAdSdkVer().toString());
            jsonObj.put("blackPkgsVer", request.getBlackPkgsVer().toString());
            return jsonObj.toString();
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }

    @Nullable
    private static String toJson(UploadUserInsApplistReq request) {
        JSONObject jsonObj = new JSONObject();
        try {
            setJsonUA(jsonObj, request);
            jsonObj.put("businessCode", request.getBusinessCode());
            jsonObj.put("uuid", request.getUuid());
            jsonObj.put("userApks", request.getUserApks());
            return jsonObj.toString();
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }

    private static void setJsonUA(JSONObject jsonObj, BaseReq request) throws JSONException {
        UserAgent ua = request.getUserAgent();

        JSONObject uaJsonObj = new JSONObject();

        uaJsonObj.put("imsi", ua.getImsi());
        uaJsonObj.put("imei", ua.getImei());
        uaJsonObj.put("androidSystemVer", ua.getAndroidSystemVer());
        uaJsonObj.put("screenSize", ua.getScreenSize());
        uaJsonObj.put("ramSize", ua.getRamSize().toString());
        uaJsonObj.put("romSize", ua.getRomSize().toString());
        uaJsonObj.put("cpu", ua.getCpu());
        uaJsonObj.put("hsman", ua.getHsman());
        uaJsonObj.put("hstype", ua.getHstype());
        uaJsonObj.put("networkType", ua.getNetworkType().toString());
        uaJsonObj.put("provider", ua.getProvider());
        uaJsonObj.put("packageName", ua.getPackegeName());
        uaJsonObj.put("apkVer", ua.getApkVer());
        uaJsonObj.put("dpi", ua.getDpi().toString());
        uaJsonObj.put("apkVerInt", ua.getApkverInt().toString());
        uaJsonObj.put("mac", ua.getMac());
        uaJsonObj.put("installTime", ua.getInstallTime().toString());
        uaJsonObj.put("terminalId", ua.getTerminalId());
        uaJsonObj.put("channelCode", ua.getChannelCode());
        uaJsonObj.put("lat", ua.getLat());
        uaJsonObj.put("lng", ua.getLng());

        jsonObj.put("userAgent", uaJsonObj);
    }

    @NonNull
    public static BaseResp fromJson(String jsonStr, Class clazz) throws IOException {
        BaseResp response;
        if (clazz.getSimpleName().equals(GetAdSdkConfigResp.class.getSimpleName())) {
            response = fromJson_SdkConfig(jsonStr);
        } else{
            response = fromJson_Base(jsonStr);
        }
        return response;
    }

    private static BaseResp fromJson_Base (String jsonStr) throws IOException {
        BaseResp response = new BaseResp();
        if (!TextUtils.isEmpty(jsonStr)){
            JsonReader reader = new JsonReader(new StringReader(jsonStr));
            reader.beginObject();
            while (reader.hasNext()){
                String tag = reader.nextName();
                switch (tag) {
                    case "responseCode":
                        response.setResponseCode(reader.nextInt());
                        break;
                    case "responseMsg":
                        response.setResponseMsg(reader.nextString());
                        break;
                    default:
                        reader.skipValue();
                        break;
                }
            }
            reader.endObject();
        }
        return response;
    }

    private static GetAdSdkConfigResp fromJson_SdkConfig(String jsonStr) throws IOException {
        GetAdSdkConfigResp response = new GetAdSdkConfigResp();
        if (!TextUtils.isEmpty(jsonStr)) {
            JsonReader reader = new JsonReader(new StringReader(jsonStr));
            reader.beginObject();
            while (reader.hasNext()) {
                String tag = reader.nextName();
                switch (tag) {
                    case "responseCode":
                        response.setResponseCode(reader.nextInt());
                        break;
                    case "responseMsg":
                        response.setResponseMsg(reader.nextString());
                        break;
                    case "terminalId":
                        response.setTerminalId(reader.nextString());
                        break;
                    case "serverTime":
                        response.setServerTime(reader.nextLong());
                        break;
                    case "adSdkConfigTimeInterval":
                        response.setAdSdkConfigTimeInterval(reader.nextLong());
                        break;
                    case "adSdkVer":
                        response.setAdSdkVer(reader.nextInt());
                        break;
                    case "adSdkUpgradeUrl":
                        response.setAdSdkUpgradeUrl(reader.nextString());
                        break;
                    case "blackPkgsVer":
                        response.setBlackPkgsVer(reader.nextInt());
                        break;
                    case "blackPkgs":
                        response.setBlackPkgs(reader.nextString());
                        break;
                    case "userApksTimeInterval":
                        response.setUserApksTimeInterval(reader.nextLong());
                        break;
                    case "adMsgListTimeInterval":
                        response.setAdMsgListTimeInterval(reader.nextLong());
                        break;
                    case "userAdEventTimeInterval":
                        response.setUserAdEventTimeInterval(reader.nextLong());
                        break;
                    default:
                        reader.skipValue();
                        break;
                }
            }
            reader.endObject();
        }

        return response;
    }
}
