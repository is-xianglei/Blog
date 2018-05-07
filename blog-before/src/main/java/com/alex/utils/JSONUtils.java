package com.alex.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.platform.commons.util.StringUtils;

/**
 * JSON工具类
 *
 * @Author: Alex isidea@outlook.com
 * @Date: 2018/5/7 0007 16:57
 */
public class JSONUtils {

    /**
     * Bean对象转JSON.采用Fastjson工具
     * 如果有时间日期格式需要转换。建议"yyyy-MM-dd HH:mm:ss"。
     * @param object
     * @param dataFormatString
     * @return
     */
    public static String beanToJsonByFastjson(Object object,String dataFormatString){

        if(object != null){
            if(StringUtils.isBlank(dataFormatString)){

                return JSONObject.toJSONString(object);
            }
            return JSON.toJSONStringWithDateFormat(object,dataFormatString);

        }else{
            return null;
        }
    }

    /**
     * 将json字符串转换成对象.采用Fastjson工具---自动识别日期时间类型转换
     *
     * @param json  json字符串
     * @param clazz 源对象
     * @return
     */
    public static Object jsonToBeanByFastjson(String json,Object clazz){

        if(StringUtils.isBlank(json) || clazz == null){
            return null;
        }
        return JSON.parseObject(json, clazz.getClass());
    }

}
