package com.fcprovin.chukcheck.common;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    public class USER {
        public static final String USER_UUID = "user_uuid";
        public static final String USER_SNS_ID = "user_sns_id";
        public static final String USER_ID = "user_id";
        public static final String USER_NAME = "user_name";
        public static final String USER_EMAIL = "user_email";
        public static final String USER_NUMBER = "user_number";
        public static final String USER_BIRTH = "user_birth";
        public static final String ACCESS_TOKEN = "user_token";
    }

    public class SNS{
        public static final String SNS_TYPE = "sns_type";
    }


    private static SharedPreferences getSharedPreferences() {
        return ChukCheckApp.getAppContext().getSharedPreferences("sp", Context.MODE_PRIVATE);
    }

    public static void setChukCheckUserUuId(String uuid) {
        SharedPreferences sp = getSharedPreferences();
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(USER.USER_UUID, uuid);
    }

    public static String getChukCheckUserUuId() {
        SharedPreferences sp = getSharedPreferences();
        return sp.getString(USER.USER_UUID,"");
    }

    public static void setChukCheckSnsType(String type){
        SharedPreferences sp = getSharedPreferences();
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SNS.SNS_TYPE, type);
    }

    public static String setChukCheckSnsType() {
        SharedPreferences sp = getSharedPreferences();
        return sp.getString(SNS.SNS_TYPE,"");
    }
}
