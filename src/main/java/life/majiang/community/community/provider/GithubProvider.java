package life.majiang.community.community.provider;

import com.alibaba.fastjson.JSON;
import life.majiang.community.community.dto.AccessTokenDTO;
import life.majiang.community.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author lihailong
 * @date 2019/9/16 23:13
 */
@Service
//@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

            RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
            Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String string= response.body().string();
                //access_token=d678308f4aa4103b589aa514edbf525f5d2c5e1b&scope=user&token_type=bearerstring
                //获取 access_token 的值   Java基础  剪切的方式
                String[] split = string.split("&");
                String s = split[0];
                String token= s.split("=")[1];
                System.out.println(token+"token");
                //直接返回去
                return token;

                //System.out.println(string+"string");
                //return  string;  出现 error 是因为这个没有返回
            } catch (Exception e) {
                e.printStackTrace();
            }
        return  null;
        }

   public GithubUser gitUser(String accessToken){

       OkHttpClient client = new OkHttpClient();

       //RequestBody body = RequestBody.create(JSON,json);
       Request request = new Request.Builder()
               //https://github.com/login/oauth/access_token  地址错了，所以出现错误  json   与  null 空指针
               .url("https://api.github.com/user?access_token="+accessToken)
               .build();
       try (Response response = client.newCall(request).execute()) {
           String string= response.body().string();
           GithubUser githubUser = JSON.parseObject(string,GithubUser.class);
         return  githubUser;
       } catch (Exception e) {
           e.printStackTrace();
       }
       return  null;
   }
}
