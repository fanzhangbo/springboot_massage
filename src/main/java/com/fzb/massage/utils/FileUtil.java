package com.fzb.massage.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
public class FileUtil {



    /**
     * 文件保存目录，物理路径
     */
    public final String rootPath = "F:\\java_study\\IdeaProjects\\SpringBootPro\\massage\\src\\main\\resources\\static";

    public final String allowSuffix = ".bmp.jpg.jpeg.png.gif.pdf.doc.zip.rar.gz";

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    public GlobalResult  uploadFile(MultipartFile file) {

        //1.文件后缀过滤，只允许部分后缀
        //文件的完整名称,如spring.jpeg
        String originalFilename = file.getOriginalFilename();

        // 文件名,如spring
        // String name = originalFilename.substring(0, filename.indexOf("."));
        // 文件后缀,如.jpeg
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        if (allowSuffix.indexOf(suffix) == -1) {
            return GlobalResult.build(0, "不允许上传该后缀的文件");
        }

        String filename = getRandomString(8) + new Date().getTime() + suffix;

        //2.创建文件目录
        //创建年月文件夹
//        Calendar date = Calendar.getInstance();
//        File dateDirs = new File(date.get(Calendar.YEAR)
//                + "/" + (date.get(Calendar.MONTH) + 1));

        //目标文件
        File descFile = new File(rootPath + "/uploads/" + filename);
//        int i = 1;
        //若文件存在重命名
//        String newFilename = filename;
//        while (descFile.exists()) {
//            newFilename = name + "(" + i + ")" + suffix;
//            String parentPath = descFile.getParent();
//            descFile = new File(parentPath + "/" + newFilename);
//            i++;
//        }
        //判断目标文件所在的目录是否存在
//        if (!descFile.getParentFile().exists()) {
//            //如果目标文件所在的目录不存在，则创建父目录
//            descFile.getParentFile().mkdirs();
//        }

        //3.存储文件
        //将内存中的数据写入磁盘
        try {
            file.transferTo(descFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //完整的url
        String fileUrl = "/uploads/" + filename;

        //4.返回URL
        Map<String, String> ret = new HashMap<>();
        ret.put("filename", filename);
        ret.put("fileUrl", fileUrl);
        return GlobalResult.build(1, "上传成功", ret);
    }

    //length用户要求产生字符串的长度
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public void deleteFile(String filePath) {
        File file = new File(rootPath + filePath);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }

}
