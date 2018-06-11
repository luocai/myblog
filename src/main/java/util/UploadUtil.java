package util;

import java.io.UnsupportedEncodingException;

import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

public class UploadUtil {

	
	 /**
     * 上传文件到七牛云, 并返回网络地址
     *
     * @param file
     * @return
     */

    public static String uploadImgToQiuniu(MultipartFile file) throws Exception {

        String imgUrl = "";

        Configuration cfg = new Configuration(Zone.zone0());

        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = Constant.ACCESS_KEY;
        String secretKey = Constant.SECRET_KEY;
        String bucket = Constant.BUCKET;

        // 如果不指定 key 值，以文件内容的 hash 值作为文件名
        // 获取原文件名的后缀
        String originalFilename = file.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
        String key = UuidUtil.getUuid() + extName;
        
        try {
            byte[] uploadBytes = file.getBytes();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(uploadBytes, key, upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
                imgUrl = Constant.QIUNIU_DOMAIN + "/" + putRet.key;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (UnsupportedEncodingException ex) {
            //ignore
        }
        return imgUrl;
    }
    
   
}
