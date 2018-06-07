package cn.blingfeng.qiniu;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.beans.factory.InitializingBean;

import java.io.File;
import java.io.InputStream;

/**
 * @author blingfeng
 */
public class UploadService implements InitializingBean, Upload {
    private UploadManager uploadManager;
    private String token;
    private String accessKey;
    private String secretKey;
    private String bucket;
    private Auth auth;
    private Integer engineRoom;

    UploadService() {

    }

    @Override
    public void afterPropertiesSet() {
        Zone zone = chooseEngineRoom();
        uploadManager = zone == null ? new UploadManager(new Configuration())
                : new UploadManager(new Configuration(zone));
        auth = Auth.create(accessKey, secretKey);
        token = auth.uploadToken(bucket);
    }


    private Zone chooseEngineRoom() {
        switch (this.engineRoom) {
            case 0:
                return Zone.zone0();
            case 1:
                return Zone.zone1();
            case 2:
                return Zone.zone2();
            case 3:
                return Zone.zoneNa0();
            default:
                return null;
        }
    }

    @Override
    public Response put(File file, String key) throws QiniuException {
        return uploadManager.put(file, key, token);
    }

    @Override
    public Response put(byte[] data, String key) throws QiniuException {
        return uploadManager.put(data, key, token);
    }

    @Override
    public Response put(String filePath, String key) throws QiniuException {
        return uploadManager.put(filePath, key, token);
    }

    @Override
    public Response put(InputStream stream, String key, StringMap params, String mime) throws QiniuException {
        return uploadManager.put(stream, key, token, params, mime);
    }

    @Override
    public Response put(final byte[] data, String key, StringMap params,
                        String mime, boolean checkCrc) throws QiniuException {
        return uploadManager.put(data, key, token, params, mime, checkCrc);
    }

    @Override
    public Response put(File file, String key, StringMap params, String mime, boolean checkCrc) throws QiniuException {
        return uploadManager.put(file, key, token, params, mime, checkCrc);
    }

    public void refresh(String bucket) {
        token = auth.uploadToken(bucket);
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }


    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public void refresh(String bucket, Integer engineRoom) {
        token = auth.uploadToken(bucket);
        this.engineRoom = engineRoom;
        Zone zone = chooseEngineRoom();
        uploadManager = zone == null ? new UploadManager(new Configuration())
                : new UploadManager(new Configuration());
    }

    public void setEngineRoom(Integer engineRoom) {
        this.engineRoom = engineRoom;
    }
}
