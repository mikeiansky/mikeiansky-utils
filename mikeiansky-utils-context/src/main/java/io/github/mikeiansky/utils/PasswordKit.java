package io.github.mikeiansky.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * @author mike ian
 * @date 2024/8/5
 * @desc 密码工具
 **/
public class PasswordKit {

    public static String digestPassword(String password, String salt) {
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        return md5.digestHex(md5.digestHex(password) + salt);
    }

}
