package cn.blingfeng.qiniu;


import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.util.StringMap;

import java.io.File;
import java.io.InputStream;
@SuppressWarnings("unused")
public class Uploader implements Upload {

    private UploadService uploadService;

    @Override
    public Response put(File file, String key) throws QiniuException {
        return uploadService.put(file, key);
    }

    @Override
    public Response put(byte[] data, String key) throws QiniuException {
        return uploadService.put(data, key);
    }

    @Override
    public Response put(String filePath, String key) throws QiniuException {
        return uploadService.put(filePath, key);
    }

    @Override
    public Response put(InputStream stream, String key, StringMap params, String mime) throws QiniuException {
        return uploadService.put(stream, key, params, mime);
    }

    @Override
    public Response put(byte[] data, String key, StringMap params, String mime, boolean checkCrc) throws QiniuException {
        return uploadService.put(data, key, params, mime, checkCrc);
    }

    @Override
    public Response put(File file, String key, StringMap params, String mime, boolean checkCrc) throws QiniuException {
        return uploadService.put(file, key, params, mime, checkCrc);
    }

    public void refresh(String bucket) {
        uploadService.refresh(bucket);
    }

    public void setUploadService(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    public void refresh(String bucket, Integer engineRoom) {
        uploadService.refresh(bucket, engineRoom);
    }
}
