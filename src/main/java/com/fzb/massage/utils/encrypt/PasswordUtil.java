package com.fzb.massage.utils.encrypt;


import com.fzb.massage.utils.GlobalConst;

public class PasswordUtil {

    /**
     * 加密
     * @param password
     * @return
     */
    public static String encrypt(String password) {
        return Md5Util.MD5(password, GlobalConst.PASS_SALT);
    }
}
