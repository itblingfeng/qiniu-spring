package cn.blingfeng.qiniu;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.util.StringMap;

import java.io.File;
import java.io.InputStream;

public interface Upload {

    Response put(File file, String key) throws QiniuException;

    Response put(byte[] data, String key) throws QiniuException;

    Response put(String filePath, String key) throws QiniuException;

    Response put(InputStream stream, String key, StringMap params, String mime) throws QiniuException;

    Response put(final byte[] data, String key, StringMap params,
                 String mime, boolean checkCrc) throws QiniuException;

    Response put(File file, String key, StringMap params, String mime, boolean checkCrc) throws QiniuException;

}
