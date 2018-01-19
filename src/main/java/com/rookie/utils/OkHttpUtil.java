package com.rookie.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by rookie on 2018/1/18.
 */
@Slf4j
public class OkHttpUtil {

    /**
     * 连接超时时间
     */
    private static final int CONNECT_TIMEOUT = 60;

    /**
     * 读取超时时间
     */
    private static final int READ_TIMEOUT = 60;

    /**
     * 请求格式为form
     */
    private static MediaType FORM_CONTENT_TYPE = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");

    /**
     * 请求格式为json
     */
    private static MediaType JSON_CONTENT_TYPE = MediaType.parse("application/json; charset=utf-8");


    /**
     * 获取带有Cookie管理的okhttpclient
     *
     * @return OkHttpClient
     */
    public static OkHttpClient getClientWithCookie() {
        OkHttpClient client = new OkHttpClient().newBuilder().cookieJar(new LocalCookieJar())
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS).connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS).build();
        return client;
    }

    /**
     * 获取无Cookie管理的client
     *
     * @return OkHttpClient
     */
    public static OkHttpClient getClient() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS).connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS).build();
        return client;
    }


    /**
     * 使用okhttp登录目标系统
     *
     * @param client   获取的client
     * @param siteUrl  目标系统的url
     * @param username 用户名
     * @param password 密码(加密后的密码)
     * @throws IOException
     */
    public static boolean okhttpLogin(OkHttpClient client, String siteUrl, String username, String password) {
        // 标记登录是否成功
        boolean isSuccess = false;

        System.out.println("siteUrl----" + siteUrl);
        String[] ltAndChannel = getLtAndChannel(client, siteUrl);
        System.out.println(username);
        String backHtml = loginAgain(client, siteUrl, ltAndChannel[0], ltAndChannel[1], username, password, ltAndChannel[2]);
        System.out.println(backHtml);
        isSuccess = !backHtml.contains("密码");

        return isSuccess;

    }


    /**
     * 登录获取页面 lt 和 channel
     *
     * @param client  Okhttpclient
     * @param siteUrl 访问的url
     * @return lt 和 channel
     * @throws IOException
     */
    public static String[] getLtAndChannel(OkHttpClient client, String siteUrl) {
        Response response = null;
        String[] params = new String[3];
        try {
            Request request = new Request.Builder().url(siteUrl).header("Connection", "keep-alive").header("Referer", siteUrl).build();
            response = client.newCall(request).execute();
            String lt = "";
            String formChannel = "";
            String execution = "";

            if (response.isSuccessful()) {
                String res = response.body().string();
                Document document = Jsoup.parse(res);
                lt = document.select("input[name=lt]").val();
                System.out.println("LT===" + lt);
                formChannel = document.select("input[name=fromchannel]").val();
                System.out.println("formChannel===" + formChannel);
                execution = document.select("input[name=execution]").val();
            }

            params[0] = lt;
            params[1] = formChannel;
            params[2] = execution;
        } catch (IOException e) {
            log.error("获取LT和Channel异常", e);
        } finally {
            if (response != null) {
                response.close();
            }
        }

        return params;

    }


    /**
     * 进行http请求，请求方式post
     *
     * @param client
     * @param businessUrl 要请求的url
     * @param jsonStr     要携带的参数，json格式的String
     * @return 接口返回的字符串
     * @throws IOException
     */
    public static String postByJson(OkHttpClient client, String siteUrl, String accountName, String password, String businessUrl, String jsonStr) throws IOException {
        String responseStr = "";
        Response response = null;

        try {
            // 1.登录目标系统
            okhttpLogin(client, siteUrl, accountName, password);

            // 2.访问目标系统接口
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(JSON, jsonStr);

            Request request = new Request.Builder().url(businessUrl).post(body).build();
            Call call = client.newCall(request);
            response = call.execute();
            responseStr = response.body().string();

        } catch (IOException e) {
            log.error(String.valueOf(e));
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return responseStr;

    }


    /**
     * post请求，以json格式
     *
     * @param client      获取到的client
     * @param businessUrl 请求url
     * @param jsonStr     请求参数
     * @return 返回接口内容
     */
    public static String postByJson(OkHttpClient client, String businessUrl, String jsonStr) {
        String responseStr = "";
        Response response = null;
        try {
            // 1.访问目标系统接口

            RequestBody body = RequestBody.create(JSON_CONTENT_TYPE, jsonStr);

            Request request = new Request.Builder().url(businessUrl).post(body).build();
            Call call = client.newCall(request);
            response = call.execute();
            responseStr = response.body().string();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return responseStr;
    }


    /**
     * 进行http请求，请求方式form
     *
     * @param client     获取到的client
     * @param url        请求url
     * @param formParams 请求参数
     * @return 结果
     */
    public static String postByForm(OkHttpClient client, String url, String formParams) {
        String result = "";
        Response response = null;
        try {
            RequestBody requestBody = RequestBody.create(FORM_CONTENT_TYPE, formParams);
            Request request = new Request.Builder().url(url).post(requestBody).build();
            response = client.newCall(request).execute();
            log.info("调用接口返回状态码：" + response.code());
            if (response.isSuccessful()) {
                result = response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.close();
            }
        }

        return result;
    }


    public static String postByForm(OkHttpClient client, String url, HashMap<String, String> paramsMap) {
        //1. 将map转为String
        String params = getFormParams(paramsMap);

        //2. 进行post请求
        String result = postByForm(client, url, params);

        //3. 返回结果
        return result;
    }


    /**
     * 通过okhttp发送get请求
     *
     * @param client 获取到的client
     * @param url    请求url
     * @return 返回结果
     */
    public static String getByOkHttp(OkHttpClient client, String url) {
        String result = "";
        Response response = null;
        try {
            Request reuqest = new Request.Builder().url(url).build();
            response = client.newCall(reuqest).execute();
            if (response.isSuccessful()) {
                result = response.body().string();
            }
        } catch (IOException e) {
            log.error(String.valueOf(e));
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return result;
    }


    /**
     * 再次登录
     *
     * @param client      获取到的client
     * @param siteUrl     目标系统url
     * @param lt
     * @param formChannel
     * @throws IOException
     */
    public static String loginAgain(OkHttpClient client, String siteUrl, String lt, String formChannel, String username, String password, String execution) {
        Response response = null;
        String resultStr = "";
        try {
            System.out.println("password------" + password);
            FormBody.Builder builder = new FormBody.Builder();
            builder.add("username", username);
            builder.add("password", password);
            builder.add("fromchannel", formChannel);
            builder.add("lt", lt);
            //e3s1 e1s1
            builder.add("execution", execution);
            builder.add("_eventId", "submit");
            //%E7%99%BB%E5%BD%95
            builder.add("submit", "登录");

            FormBody formBody = builder.build();
            System.out.println("siteUrl------" + siteUrl);
            Request request = new Request.Builder().post(formBody).url(siteUrl).header("Connection", "keep-alive").header("Referer", siteUrl).build();
            response = client.newCall(request).execute();

            if (response.isSuccessful()) {

                resultStr = response.body().string();
            }
        } catch (IOException e) {
            log.error("再次登录异常", e);
        } finally {
            if (response != null) {
                response.close();
            }
        }

        return resultStr;


    }


    /**
     * 上传附件信息
     *
     * @param client    Okhttpclient
     * @param clueId    线索id
     * @param custId    客户id
     * @param custType  客户类型
     * @param fieldCode 上传区域
     * @throws IOException
     */
    public void uploadFileAsync(OkHttpClient client, String clueId, String custId, String custType, String fieldCode, String fileName) throws IOException {


        //登录成功后,更新客户附件信息
        String uploadUrl = "http://nginxd/nssp-web/sspUniteLoanController/sspUploadAttchmentFiles.do";
        File file = new File("D:\\image", fileName);
        System.out.println("---------------" + file);
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart("uploadedFile", file.getName(), fileBody);
        builder.addFormDataPart("clueIdThreadLocal", clueId);//线索id
        builder.addFormDataPart("fieldCode", fieldCode);//上传区域（如：身份证, 征信授权书等）
        builder.addFormDataPart("custId", custId);//客户id
        builder.addFormDataPart("custType", custType);//客户类型

        RequestBody requestBody = builder.build();
        Request request = new Request.Builder().url(uploadUrl).post(requestBody).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                log.info("上传附件失败");
                System.out.println("上传附件失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                log.info("附件长传完成");
                System.out.println("附件长传完成");
            }
        });
    }


    /**
     * 将参数以map键值对的形式传入，本方法将map的键值对组装为key1=value1&key2=value2的形式并返回
     *
     * @param params map格式的参数
     * @return key1=value1&key2=value2格式的字符串
     */
    public static String getFormParams(HashMap<String, String> params) {
        StringBuffer sb = new StringBuffer();
        for (String key : params.keySet()) {
            sb.append(key + "=" + params.get(key) + "&");
        }

        String formPamars = sb.toString();
        return formPamars;
    }


}
