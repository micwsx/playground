// 
// Decompiled by Procyon v0.5.36
// 

package com.enjoy.chinpay;

import java.util.HashMap;

public class SecssConstants
{
    public static String KEY_VALUE_CONNECT;
    public static String MESSAGE_CONNECT;
    public static String SIGN1_ALGNAME;
    public static String SIGN512_ALGNAME;
    public static String SIGN512;
    public static String ENC_ALG_PREFIX;
    public static String DEFAULT_PROVIDER;
    public static String CHARSET_COMM;
    public static String SIGN_FILE;
    public static String SIGN_FILE_ALIAS;
    public static String SIGN_FILE_PW;
    public static String VERIFY_FILE;
    public static String SIGN_CERT_TYPE;
    public static String SIGNATURE_FIELD;
    public static String SIGN_INVALID_FIELDS;
    public static String RETURN_LINE;
    public static String SUCCESS;
    public static String LOAD_CONFIG_ERROR;
    public static String SIGN_CERT_ERROR;
    public static String SIGN_CERT_PWD_ERROR;
    public static String SIGN_CERT_TYPE_ERROR;
    public static String INIT_SIGN_CERT_ERROR;
    public static String VERIFY_CERT_ERROR;
    public static String INIT_VERIFY_CERT_ERROR;
    public static String GET_PRI_KEY_ERROR;
    public static String GET_CERT_ID_ERROR;
    public static String GET_SIGN_STRING_ERROR;
    public static String SIGN_GOES_WRONG;
    public static String VERIFY_GOES_WRONG;
    public static String VERIFY_FAILED;
    public static String SIGN_FIELD_NULL;
    public static String SIGN_VALUE_NULL;
    public static String UNKNOWN_WRONG;
    public static String ENCPIN_GOES_WRONG;
    public static String ENCDATA_GOES_WRONG;
    public static String DECDATA_GOES_WRONG;
    public static String DEFAULTINIT_GOES_WRONG;
    public static String SPECIFYINIT_GOES_WRONG;
    public static String RELOADSC_GOES_WRONG;
    public static String NO_INIT;
    public static String CONFIG_WRONG;
    public static String INIT_CONFIG_WRONG;
    public static final HashMap ERRMAP;
    
    static {
        SecssConstants.KEY_VALUE_CONNECT = "=";
        SecssConstants.MESSAGE_CONNECT = "&";
        SecssConstants.SIGN1_ALGNAME = "SHA1WithRSA";
        SecssConstants.SIGN512_ALGNAME = "SHA512WithRSA";
        SecssConstants.SIGN512 = "SHA-512";
        SecssConstants.ENC_ALG_PREFIX = "RSA";
        SecssConstants.DEFAULT_PROVIDER = "BC";
        SecssConstants.CHARSET_COMM = "UTF-8";
        SecssConstants.SIGN_FILE = "sign.file";
        SecssConstants.SIGN_FILE_ALIAS = "sign.file.alias";
        SecssConstants.SIGN_FILE_PW = "sign.file.password";
        SecssConstants.VERIFY_FILE = "verify.file";
        SecssConstants.SIGN_CERT_TYPE = "sign.cert.type";
        SecssConstants.SIGNATURE_FIELD = "signature.field";
        SecssConstants.SIGN_INVALID_FIELDS = "sign.invalid.fields";
        SecssConstants.RETURN_LINE = "\r\n";
        SecssConstants.SUCCESS = "00";
        SecssConstants.LOAD_CONFIG_ERROR = "01";
        SecssConstants.SIGN_CERT_ERROR = "02";
        SecssConstants.SIGN_CERT_PWD_ERROR = "03";
        SecssConstants.SIGN_CERT_TYPE_ERROR = "04";
        SecssConstants.INIT_SIGN_CERT_ERROR = "05";
        SecssConstants.VERIFY_CERT_ERROR = "06";
        SecssConstants.INIT_VERIFY_CERT_ERROR = "07";
        SecssConstants.GET_PRI_KEY_ERROR = "08";
        SecssConstants.GET_CERT_ID_ERROR = "09";
        SecssConstants.GET_SIGN_STRING_ERROR = "10";
        SecssConstants.SIGN_GOES_WRONG = "11";
        SecssConstants.VERIFY_GOES_WRONG = "12";
        SecssConstants.VERIFY_FAILED = "13";
        SecssConstants.SIGN_FIELD_NULL = "14";
        SecssConstants.SIGN_VALUE_NULL = "15";
        SecssConstants.UNKNOWN_WRONG = "16";
        SecssConstants.ENCPIN_GOES_WRONG = "17";
        SecssConstants.ENCDATA_GOES_WRONG = "18";
        SecssConstants.DECDATA_GOES_WRONG = "19";
        SecssConstants.DEFAULTINIT_GOES_WRONG = "20";
        SecssConstants.SPECIFYINIT_GOES_WRONG = "21";
        SecssConstants.RELOADSC_GOES_WRONG = "22";
        SecssConstants.NO_INIT = "23";
        SecssConstants.CONFIG_WRONG = "24";
        SecssConstants.INIT_CONFIG_WRONG = "25";
        (ERRMAP = new HashMap()).put(SecssConstants.SUCCESS, "\u64cd\u4f5c\u6210\u529f");
        SecssConstants.ERRMAP.put(SecssConstants.LOAD_CONFIG_ERROR, "\u52a0\u8f7dsecurity.properties\u914d\u7f6e\u6587\u4ef6\u51fa\u9519\uff0c\u8bf7\u68c0\u67e5\u6587\u4ef6\u8def\u5f84\uff01");
        SecssConstants.ERRMAP.put(SecssConstants.SIGN_CERT_ERROR, "\u7b7e\u540d\u6587\u4ef6\u8def\u5f84\u914d\u7f6e\u9519\u8bef\uff01");
        SecssConstants.ERRMAP.put(SecssConstants.SIGN_CERT_PWD_ERROR, "\u7b7e\u540d\u6587\u4ef6\u8bbf\u95ee\u5bc6\u7801\u914d\u7f6e\u9519\u8bef\uff01");
        SecssConstants.ERRMAP.put(SecssConstants.SIGN_CERT_TYPE_ERROR, "\u7b7e\u540d\u6587\u4ef6\u5bc6\u94a5\u5bb9\u5668\u7c7b\u578b\u914d\u7f6e\u9519\u8bef\uff0c\u9700\u4e3aPKCS12\uff01");
        SecssConstants.ERRMAP.put(SecssConstants.INIT_SIGN_CERT_ERROR, "\u521d\u59cb\u5316\u7b7e\u540d\u6587\u4ef6\u51fa\u9519\uff01");
        SecssConstants.ERRMAP.put(SecssConstants.VERIFY_CERT_ERROR, "\u9a8c\u7b7e\u8bc1\u4e66\u8def\u5f84\u914d\u7f6e\u9519\u8bef\uff01");
        SecssConstants.ERRMAP.put(SecssConstants.INIT_VERIFY_CERT_ERROR, "\u521d\u59cb\u5316\u9a8c\u7b7e\u8bc1\u4e66\u51fa\u9519\uff01");
        SecssConstants.ERRMAP.put(SecssConstants.GET_PRI_KEY_ERROR, "\u83b7\u53d6\u7b7e\u540d\u79c1\u94a5\u51fa\u9519\uff01");
        SecssConstants.ERRMAP.put(SecssConstants.GET_CERT_ID_ERROR, "\u83b7\u53d6\u7b7e\u540d\u8bc1\u4e66ID\u51fa\u9519\uff01");
        SecssConstants.ERRMAP.put(SecssConstants.GET_SIGN_STRING_ERROR, "\u83b7\u53d6\u7b7e\u540d\u5b57\u7b26\u4e32\u51fa\u9519\uff01");
        SecssConstants.ERRMAP.put(SecssConstants.SIGN_GOES_WRONG, "\u7b7e\u540d\u8fc7\u7a0b\u53d1\u751f\u9519\u8bef\uff01");
        SecssConstants.ERRMAP.put(SecssConstants.VERIFY_GOES_WRONG, "\u9a8c\u7b7e\u8fc7\u7a0b\u53d1\u751f\u9519\u8bef\uff01");
        SecssConstants.ERRMAP.put(SecssConstants.VERIFY_FAILED, "\u9a8c\u7b7e\u5931\u8d25\uff01");
        SecssConstants.ERRMAP.put(SecssConstants.SIGN_FIELD_NULL, "\u914d\u7f6e\u6587\u4ef6\u4e2d\u7b7e\u540d\u5b57\u6bb5\u540d\u79f0\u4e3a\u7a7a\uff01");
        SecssConstants.ERRMAP.put(SecssConstants.SIGN_VALUE_NULL, "\u62a5\u6587\u4e2d\u7b7e\u540d\u4e3a\u7a7a\uff01");
        SecssConstants.ERRMAP.put(SecssConstants.UNKNOWN_WRONG, "\u672a\u77e5\u9519\u8bef\uff01");
        SecssConstants.ERRMAP.put(SecssConstants.ENCPIN_GOES_WRONG, "Pin\u52a0\u5bc6\u8fc7\u7a0b\u53d1\u751f\u9519\u8bef\uff01");
        SecssConstants.ERRMAP.put(SecssConstants.ENCDATA_GOES_WRONG, "\u6570\u636e\u52a0\u5bc6\u8fc7\u7a0b\u53d1\u751f\u9519\u8bef\uff01");
        SecssConstants.ERRMAP.put(SecssConstants.DECDATA_GOES_WRONG, "\u6570\u636e\u89e3\u5bc6\u8fc7\u7a0b\u53d1\u751f\u9519\u8bef\uff01");
        SecssConstants.ERRMAP.put(SecssConstants.DEFAULTINIT_GOES_WRONG, "\u4ece\u9ed8\u8ba4\u914d\u7f6e\u6587\u4ef6\u521d\u59cb\u5316\u5b89\u5168\u63a7\u4ef6\u53d1\u751f\u9519\u8bef\uff01");
        SecssConstants.ERRMAP.put(SecssConstants.SPECIFYINIT_GOES_WRONG, "\u4ece\u6307\u5b9a\u5c5e\u6027\u96c6\u521d\u59cb\u5316\u5b89\u5168\u63a7\u4ef6\u53d1\u751f\u9519\u8bef\uff01");
        SecssConstants.ERRMAP.put(SecssConstants.RELOADSC_GOES_WRONG, "\u91cd\u65b0\u52a0\u8f7d\u7b7e\u540d\u8bc1\u4e66\u53d1\u751f\u9519\u8bef\uff01");
        SecssConstants.ERRMAP.put(SecssConstants.NO_INIT, "\u672a\u521d\u5316\u5b89\u5168\u63a7\u4ef6\uff01");
        SecssConstants.ERRMAP.put(SecssConstants.CONFIG_WRONG, "\u63a7\u4ef6\u521d\u59cb\u5316\u4fe1\u606f\u672a\u6b63\u786e\u914d\u7f6e\uff0c\u8bf7\u68c0\u67e5\uff01");
        SecssConstants.ERRMAP.put(SecssConstants.INIT_CONFIG_WRONG, "\u521d\u59cb\u5316\u914d\u7f6e\u4fe1\u606f\u53d1\u751f\u9519\u8bef\uff01");
    }
}
