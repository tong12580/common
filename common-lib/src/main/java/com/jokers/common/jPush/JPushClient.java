package com.jokers.common.jPush;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import com.google.gson.JsonObject;
import com.jokers.common.http.HttpUtil;
import com.jokers.common.jPush.bo.Android;
import com.jokers.common.jPush.bo.Audience;
import com.jokers.common.jPush.bo.Ios;
import com.jokers.common.jPush.bo.JPush;
import com.jokers.common.jPush.bo.JPushResult;
import com.jokers.common.jPush.bo.Message;
import com.jokers.common.jPush.bo.Notification;
import com.jokers.common.jPush.bo.Options;
import com.jokers.common.jPush.bo.PushMessage;
import com.jokers.common.json.JsonUtil;
import com.jokers.common.other.Files.Base64Util;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.util.Set;

import static com.jokers.common.jPush.Constant.J_PUSH_URL;

/**
 * <p>极光推送客户端</p>
 *
 * @author yuton
 * @version 1.0
 * @since 2018/7/11 16:21
 */
@Slf4j
public class JPushClient {

    /**
     * 推送通知
     *
     * @param appKey          String
     * @param masterSecret    String
     * @param apns_production Boolean
     * @param pushMessage     PushMessage
     * @return JPushResult
     */
    public static JPushResult pushNotification(String appKey, String masterSecret,
                                               Boolean apns_production, PushMessage pushMessage) {
        String authorization = "Basic " + Base64Util.encodeBase64(appKey + ":" + masterSecret, Charset.defaultCharset().name());
        Options options = new Options();
        options.setTime_to_live(60);
        options.setApns_production(apns_production);

        JsonObject jsonObject = JsonUtil.beanToJsonObject(ImmutableMap.of("msg", pushMessage));

        Notification notification = new Notification();
        Ios ios = new Ios();
        ios.setAlert(pushMessage.getContent());
        ios.setSound(pushMessage.getSound());
        ios.setBadge("+1");
        ios.setExtras(jsonObject);
        Android android = new Android();
        android.setAlert(pushMessage.getContent());
        android.setExtras(jsonObject);
        notification.setIos(ios);

        Audience audience = new Audience();
        Set<String> stringSet = Sets.newHashSet();
        stringSet.add(pushMessage.getToken());
        audience.setRegistration_id(stringSet);

        JPush jPush = new JPush();
        jPush.setOptions(options);
        jPush.setNotification(notification);
        jPush.setAudience(audience);
        jPush.setPlatform("all");
        String result = HttpUtil.httpPostWithJSONToSMS(J_PUSH_URL, JsonUtil.objectToJson(jPush), authorization);
        log.info(result);
        return JsonUtil.jsonToBean(result, JPushResult.class);
    }

    /**
     * 推送自定义消息
     *
     * @param appKey          String
     * @param masterSecret    String
     * @param apns_production Boolean
     * @param pushMessage     PushMessage
     * @return JPushResult
     */
    public static JPushResult pushMessage(String appKey, String masterSecret,
                                          Boolean apns_production, PushMessage pushMessage) {
        String authorization = "Basic " + Base64Util.encodeBase64(appKey + ":" + masterSecret, Charset.defaultCharset().name());
        Options options = new Options();
        options.setTime_to_live(60);
        options.setApns_production(apns_production);

        Message message = new Message();
        message.setTitle(pushMessage.getContent());
        message.setExtras(JsonUtil.beanToJsonObject(pushMessage));

        Audience audience = new Audience();
        Set<String> stringSet = Sets.newHashSet();
        stringSet.add(pushMessage.getToken());
        audience.setRegistration_id(stringSet);

        JPush jPush = new JPush();
        jPush.setOptions(options);
        jPush.setAudience(audience);
        jPush.setPlatform("all");
        jPush.setMessage(message);
        String result = HttpUtil.httpPostWithJSONToSMS(J_PUSH_URL, JsonUtil.objectToJson(jPush), authorization);
        log.info(result);
        return JsonUtil.jsonToBean(result, JPushResult.class);
    }
}
