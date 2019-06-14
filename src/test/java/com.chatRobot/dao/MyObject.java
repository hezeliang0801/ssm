package com.chatRobot.dao;

import com.chatRobot.model.HttpConnectionUtil;
import net.sf.json.JSONObject;

import java.util.*;

public class MyObject {

    public static void main(String[] argn) {
        HashMap<String, Object> requestMap = new HashMap<>();
        //参数map
        HashMap<String, String> paramMap = new HashMap<>(8);
            //创建请求头
            Map<String, String> headers = new HashMap(1);
            headers.put("Content-Type", "application/x-www-form-urlencoded");
            //从xconfig获取网易请求Url
//            String verificationUrl = ossProps.getProperty("sp-is.graphicVerification.url");
            //封装生成签名的参数
            paramMap.put("captchaId", "03d242fdc4fe4c4da156ec042b9820a8");
            paramMap.put("validate", "YKX-qeOG0xaw4tBGWc7aFMBhk7fTw.Sn9.A1BO4xvWEW_ipUQkv6gFddv4s6uD.EEe89AjJrgG4.HcrobZ2aXvGjY8eTjEzjFI-9pHNV_ePIV-ctcnTbM5PIGSrMQh8lK.D-ekk502mhpe-QTCetA2A9cIdQSl_uQtg9.mbivgQjuaEME.FpJ__WkHnl5i6pGUC0c5Vt00lawBVIKYo_XMNBcAGpiQmYcBffEgjefs4sMr8HCTceibQdstizmbH4yE_WQB7kwfv2DZq9-yO.OhHgO9JSefVNQi.x7Nf.uzXtg6snxRRHb9k-yosocRPA6D9spOp-0xOH_v8fil4WE8Mywp.a.flaxcouiB28uzGs6woHL7WkkDO.AOPKHqa_zEI8aJKtawO5GjIKue.rhSO5TMENZXn1uzxgQCLHGRFAtIqoaoTyGIA4CmjCkEJfjPhQYacQR.TS8GNnN.vsWWBtAhsYvYgqoxI2ykmO_6zc7jRaNc7FW4FgWFj3");
            paramMap.put("user", "");
            paramMap.put("secretId", "6ab8d764baf7599577d39a9e03ff7b18");
            paramMap.put("version", "v2");
            paramMap.put("timestamp", "1560246147240");
            paramMap.put("nonce", "524");
            //最终请求的参数
//            String signature = ApiUtils.genSignature("a722c5464fa79131d244929d92f2a5c0", paramMap);
            paramMap.put("signature", "b5e0dba6e0cf2586b6a26cba5063d474");
        //map转字符串
            JSONObject jsonObject = JSONObject.fromObject(paramMap);
        String post = HttpConnectionUtil.post(" http://c.dun.163yun.com/api/v2/verify", jsonObject.toString(), headers);
        System.out.println(post);


    }

}
