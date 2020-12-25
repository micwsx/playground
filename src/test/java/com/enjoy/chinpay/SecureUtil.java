// 
// Decompiled by Procyon v0.5.36
// 

package com.enjoy.chinpay;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.*;

public class SecureUtil {
    public static byte[] sign(final byte[] dataBytes, final PrivateKey priKey, final String sigAlgName) throws Exception {
        final Signature signature = Signature.getInstance(sigAlgName);
        signature.initSign(priKey);
        signature.update(dataBytes);
        final byte[] signByte = signature.sign();
        return signByte;
    }

    public static boolean verify(final byte[] dataBytes, final byte[] signBytes, final PublicKey pubKey, final String sigAlgName) throws Exception {
        final Signature signature = Signature.getInstance(sigAlgName);
        signature.initVerify(pubKey);
        String s = new String(Base64.getEncoder().encode(pubKey.getEncoded()));
        signature.update(dataBytes);
        return signature.verify(signBytes);
    }

    public static PublicKey getPublicKey(String publicFilePath) throws SecurityException {
        CertificateFactory cf = null;
        FileInputStream in = null;
        try {
            cf = CertificateFactory.getInstance("X.509");
            in = new FileInputStream(publicFilePath);
            X509Certificate verifyCert = (X509Certificate) cf.generateCertificate(in);
            PublicKey pubKey = verifyCert.getPublicKey();
            return pubKey;
        } catch (Exception e) {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                }
            }
            throw new SecurityException(SecssConstants.INIT_VERIFY_CERT_ERROR);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex2) {
                }
            }
        }
    }

    private static String getSignStr(final Map<String,String> map, final List invalidFields, final boolean isSort) throws SecurityException {
        StringBuffer param = null;
        List<String> msgList = null;
        if (map == null || map.size() == 0) {
            throw new SecurityException(SecssConstants.GET_SIGN_STRING_ERROR);
        }
        try {
            param = new StringBuffer();
            msgList = new ArrayList();
            for (final String key : map.keySet()) {
                if (invalidFields != null && invalidFields.contains(key)) {
                    continue;
                }
                final String value = map.get(key);
                msgList.add(String.valueOf(key) + SecssConstants.KEY_VALUE_CONNECT + value);
            }
            if (isSort) {
                Collections.sort(msgList);
            }
            for (int i = 0; i < msgList.size(); ++i) {
                final String msg = msgList.get(i);
                if (i > 0) {
                    param.append(SecssConstants.MESSAGE_CONNECT);
                }
                param.append(msg);
            }
            return param.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new SecurityException(SecssConstants.GET_SIGN_STRING_ERROR);
        }
    }


    public static void main(String[] args) {

        try {
            Map<String,String> map=new HashMap<>();
            map.put("AcqDate","20201117");
            map.put("AcqSeqId","0000000145807117");
            map.put("MerId","000001603011004");
            map.put("MerOrderNo","202011171122");
            map.put("MerResv","xutest|f79e82f3f6334cb7a55877df4b53b722");
            map.put("OrderAmt","00000000000000000001");
            map.put("OrderStatus","0001");
            map.put("RefundSumAmount","00000000000000000000");
            map.put("respCode","0000");
            map.put("respMsg","查询成功");
            map.put("TranDate","20201117");
            map.put("Version","20140728");
            String signStr = getSignStr(map,null,true);

            byte[] signBytes = Base64.getDecoder().decode("SbT8/DLYQVmiKf8eEdN3+YJqSgGn3F0gRYm0OwawcesAGvREwFeSqJajvGiefLrcfO+HPpheb3BBKiKRbn70JZiFPTnBlQ1wjGWS33azxCxuc6HUL4ddI3xzQfa/NdEHZrr49DDFfIS4IdlVuKQkxrvtXdK2HapE4emb04Vs/Pz8GrRst4zTmioFFlSIpYTOS8WhgI15bIlAcE0BYS9cs/YanRz0KSvWHlBKQIGXvQ6GvKDiRzOAZPo6VoLDM71a34xxfnA70L82Nsy5R7+3RINPneRrrSynQC0dEynaCKOP1TdrUU+U+jtOVqe8G4tnTGZrxAsvkvn1ISQL5F/nDA==".getBytes("UTF-8"));
            PublicKey pubKey = getPublicKey("C:\\WorkStation\\Michael\\Projects\\WechatAPI\\Demo\\Test\\bin\\Debug\\cp_test_new.cer");
            boolean result = SecureUtil.verify(signStr.getBytes("UTF-8"), signBytes, pubKey, SecssConstants.SIGN512_ALGNAME);
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
