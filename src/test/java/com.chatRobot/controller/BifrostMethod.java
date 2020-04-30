package com.chatRobot.controller;

import com.alibaba.fastjson.JSON;
import com.chatRobot.utils.HttpConnectionUtil;
import com.ebanma.cloud.gateway.client.BifrostClient;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class BifrostMethod {
    private static Logger log = LoggerFactory.getLogger(BifrostMethod.class);
    String urlHeader = "https://oapi-qa.ebanma.com:20016";
    String appkey = "coupon";
    String appSecret = "2d223e65fb3a4273a7ab3ffc15a44ed4";
    String method = "/gw/spCoupon/couponInfoBack/1.0";
    String sign = "";
    @Test
    public void test() throws Exception {
        String appkey = "coupon";
        String appSecret = "2d223e65fb3a4273a7ab3ffc15a44ed4";
        String method = "/gw/spCoupon/couponInfoBack/1.0";
        String sign = "";
        Map<Object, Object> hashMap = new HashMap<>();
        hashMap.put("mobileNo","17621910063");
        hashMap.put("couponCode","12302932093");
        String json = JSON.toJSONString(hashMap);
        ArrayList<String> list = new ArrayList();
        list.add(method);
        list.add(String.valueOf(System.currentTimeMillis()));
        list.add(json);
        /*Collections.sort(list);
        StringBuilder str = new StringBuilder();
        for (Object o : list) {
            str.append(o);
        }
        String s = str.toString();
        System.out.println(s);
        byte[] bytes = HMACSHA1.HmacSHA1Encrypt(s, appSecret);
        BASE64Encoder encoder = new BASE64Encoder();
        String encode = encoder.encode(bytes);*/
        String encode = hmacSign(list, appkey, appSecret);
        sign = encode;
        Map<String,String> header = new HashMap<>();
        header.put("gw-user-key",appkey);
        header.put("gw-timestamp",String.valueOf(System.currentTimeMillis()));
        header.put("gw-sign",sign);
        String url = urlHeader+method;
        String post = HttpConnectionUtil.post(url, json, header);
        System.out.println(post);

    }

    @Test
    public void BiforestClient() throws IOException {
        Map<Object, Object> hashMap = new HashMap<>();
        hashMap.put("mobileNo","17621910063");
        hashMap.put("couponCode","12302932093");
        String json = JSON.toJSONString(hashMap);
        BifrostClient bifrostClient = new BifrostClient(urlHeader, appkey, appSecret);
        String s = bifrostClient.get(method, json);
        System.out.println(s);
    }
    //测试HUB领券接口
    @Test
    public void ZBBiforestClientRW() throws IOException {
        String appkey = "living";
        String appSecret = "224954b25aa04e31a0424ba7ba8e0553";
        String method = "/gw/spCoupon/fetchHubCoupon/1.0";
        Map<Object, Object> hashMap = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        hashMap.put("userId","CKYZIwGechcOeu8m5wSt0g");
        hashMap.put("couponId",15L);
        hashMap.put("platform","1");
        hashMap.put("accessToken","2_1296b9191610ad6eef7687757242d41855pgivi5y05g9bzn6mbo78c3zyy1b726");
        hashMap.put("activaityId","1");
        hashMap.put("activityName","测试");
        hashMap.put("nickName","臭弟弟");
        hashMap.put("orgCode","SQ2106A");
        map.put("fetchDto",hashMap);
        String json = JSON.toJSONString(map);
        BifrostClient bifrostClient = new BifrostClient(urlHeader, appkey, appSecret);
        String s = bifrostClient.get(method, json);
        System.out.println(s);
    }

    @Test
    public void fetchCoupon() throws IOException {
        int brandCode = 1;
        List<Long> list = new ArrayList<>();
        if(brandCode == 1 ){
            list.add(12L);
            list.add(13L);
            list.add(14L);
            list.add(15L);
            for (Long couponId : list) {
//                ZBBiforestClientRW(couponId);
            }
        }else {
            list.add(16L);
            list.add(17L);
            list.add(18L);
            list.add(19L);
            for (Long couponId : list) {
                ZBBiforestClientMG(couponId);
            }
        }
    }
    public void ZBBiforestClientMG(Long couponId) throws IOException {
        String appkey = "living";
        String appSecret = "224954b25aa04e31a0424ba7ba8e0553";
        String method = "/gw/spCoupon/fetchHubCoupon/1.0";
        Map<Object, Object> hashMap = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        hashMap.put("userId","CKYZIwGechcOeu8m5wSt0g");
        hashMap.put("couponId",couponId);
        hashMap.put("platform","1");
        hashMap.put("accessToken","2_1296b9191610ad6eef7687757242d41855pgivi5y05g9bzn6mbo78c3zyy1b726");
        hashMap.put("activaityId","1");
        hashMap.put("activityName","测试");
        hashMap.put("nickName","臭弟弟");
        hashMap.put("orgCode","SQ2303B");
        map.put("fetchDto",hashMap);
        String json = JSON.toJSONString(map);
        BifrostClient bifrostClient = new BifrostClient(urlHeader, appkey, appSecret);
        String s = bifrostClient.get(method, json);
        System.out.println(s);
    }

    //hmacSha1加密算法
    public static String hmacSign(ArrayList<String> params, String appkey, String appsecret) {
        params.add(appkey);
        Collections.sort(params);
        StringBuilder sb = new StringBuilder();
        Iterator var4 = params.iterator();

        while(var4.hasNext()) {
            String param = (String)var4.next();
            sb.append(param);
        }

        byte[] data = hmacSha1(sb.toString().getBytes(StandardCharsets.UTF_8), appsecret.getBytes(StandardCharsets.UTF_8));
        return Base64.encodeBase64URLSafeString(data);
    }

    private static byte[] hmac(byte[] inDataBytes, byte[] keyBytes, String algStr) {
        if (inDataBytes != null && inDataBytes.length > 0) {
            if (keyBytes != null && keyBytes.length > 0) {
                try {
                    SecretKeySpec signingKey = new SecretKeySpec(keyBytes, algStr);
                    Mac mac = Mac.getInstance(algStr);
                    mac.init(signingKey);
                    byte[] hashBytes = mac.doFinal(inDataBytes);
                    return hashBytes;
                } catch (Exception var6) {
                    throw new RuntimeException(var6.getMessage());
                }
            } else {
                throw new RuntimeException("param keyBytes can't be null");
            }
        } else {
            throw new RuntimeException("param inDataBytes can't be null");
        }
    }

    private static byte[] hmacSha1(byte[] inDataBytes, byte[] keyBytes) {
        return hmac(inDataBytes, keyBytes, "HmacSHA1");
    }

}
