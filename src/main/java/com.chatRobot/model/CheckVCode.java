package com.chatRobot.model;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;

public class CheckVCode {
    public static void main(String[] args) {
        sendPostReq("9bf804b38df4489bb1cf95901e6f7679","HSCXXGV6xw6.X86xy76VyiXq.xwvoLM__xTlaJgPd4nQd581MGAMMW_oxhQodhFu57ftj5rSxD2ToaivNuOdE6nd6iJBfdIiI.EKmUSrzXqgUc4GjQVmebPwEW6E99CTGP7iPjlRRZ2T_phru.QImf147wQosV4IVj9XGNPVnvbbcnVeOqcwzZ2aMgpS9UWVZ_ZsPHRfyGlR_LybOuRXh8AsnBV0lbJbOsInNqsIyGCrE790p-LG4te2jRLg7q5NswwycxywxWOjCOJ.9qr2dgWascr-zY99kR0mvD6e5IgILWCHrA8ayAxd5EVrJ5EqkknEm8XYbmk7T0f5LV.xWD4NNM4Q4tc0MR4LV2bmXfb11pbUqMUEcHUmS-CvJdQxrlXI7IKQky2MviD6-AZz_8YvLHRj5eUxU6VcLjoqy.6gDAksdUuX1YMdC.BxjQZk6jjE4IXqJzC9WQuWfAD4XCKT8.jUHg79vHJImz6OymFRFa2phiEdYniweUF3");
    }
    public static String genSignature(String secretKey, Map<String, String> params) throws  Exception{
        // 1. 参数名按照ASCII码表升序排序
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);
        // 2. 按照排序拼接参数名与参数值
        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            sb.append(key).append(params.get(key));
        }
        // 3. 将secretKey拼接到最后
        sb.append(secretKey);
        // 4. MD5是128位长度的摘要算法，转换为十六进制之后长度为32字符
        return DigestUtils.md5Hex(sb.toString().getBytes("UTF-8"));
    }

    public static boolean sendPostReq(String captchaId, String NECaptchaValidate) {

        String url ="http://c.dun.163yun.com/api/v2/verify";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(url);
        try {
            System.out.println("开始时间：" + new Date());
            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("captchaId", captchaId);
            paramMap.put("validate", NECaptchaValidate);
            paramMap.put("user", null);
            paramMap.put("secretId", "34e69e006f26d4d87bea31024f9b1a7b");
            paramMap.put("version", "v2");
            paramMap.put("timestamp", System.currentTimeMillis() + "");
            paramMap.put("nonce", new Random().nextInt(1000) + "");
            String signature = genSignature("68abebc8345b061dd314258e8fe12dd6", paramMap);
            // paramMap.put("signature", signature);

            List<NameValuePair> pairs = new ArrayList<NameValuePair>();

            Iterator<Map.Entry<String, String>> iterator = paramMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> elem = iterator.next();
                pairs.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
            }

            pairs.add(new BasicNameValuePair("signature", signature));


            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(pairs, "utf-8");
//            httppost.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            httppost.setHeader("Content-type","application/x-www-form-urlencoded");
            httppost.setEntity(formEntity);


            //httppost.addHeader("content-type", "application/x-www-form-urlencoded");

            CloseableHttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();

            /*
             * HttpRequester request = new HttpRequester();
             *
             * Map<String, String> headMap = new HashMap<String, String>();
             * headMap.put("Content-type", "application/x-www-form-urlencoded"); HttpRespons
             * response =
             * request.sendPost(url,paramMap,headMap);
             * System.out.println(response.getContent());
             */
            if (entity != null) {
                // jo = JSONObject.fromObject(EntityUtils.toString(entity));
                System.out.println(EntityUtils.toString(entity));
                // EntityUtils.consume(entity);
            }
            System.out.println("结束时间：" + new Date());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

}
