package com.alex.utils;

import java.util.Random;
import java.util.UUID;

/**
 * @author Alex isidea@outlook.com
 * @create 2018-04-30 15:46
 **/
public class UUIDUtils {

     public static String getUUID(){

        return UUID.randomUUID().toString().replaceAll("-", "").toString();

    }

}
