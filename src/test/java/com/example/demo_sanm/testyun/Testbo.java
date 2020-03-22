package com.example.demo_sanm.testyun;

import com.example.demo_sanm.contro.excel.ErorerBo;
import com.example.demo_sanm.interfacesvc.ColumnConfig;
import com.example.demo_sanm.interfacesvc.TableConFiG;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2020/3/10.
 * @by: DELL)
 */

public class Testbo {



    Logger  logger = Logger.getLogger(getClass());



    @Test
    public  void md5test() throws NoSuchFieldException {
        ErorerBo erorerBo1  = new   ErorerBo("code1","name1");
        ErorerBo erorerBo2  = new   ErorerBo("code2","name2");
        List<ErorerBo> erorerBoList = new ArrayList <>();



        List<List<Object>> rows = new ArrayList();
        erorerBoList.add(erorerBo1);
        erorerBoList.add(erorerBo2);
        List <Object> objectList =new ArrayList <>();


        toSheet2(erorerBo1,objectList);


        rows.add(objectList);

        toSheet(erorerBoList,rows);




    }


    public <T> void toSheet(List<T> beans,List<List<Object>> rows) {


        for(T bean : beans) {
            Class<?> beanType = bean.getClass();
            Field[] fields = beanType.getDeclaredFields();
            logger.debug("fields"+fields);
            Field field = null;

            List<Object> objectList = new ArrayList <>();
            for (int i = 0; i < fields.length; i++) {

                field = fields[i];


                if( beanType.isMemberClass()  && "this$0".equals(field.getName())) continue;
                // 外部类的this属性判断
                if(field.getType().getSimpleName().equals(bean.getClass().getSimpleName())) {
                    // 如果是this属性就取下一个字段； 此类判断对内部类无法生效
                    continue;
                }
                // 如果是static字段，取下一个字段

                if(Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                field.setAccessible(true);
                try {
                    objectList.add(field.get(bean));
                 //   rows.add(objectList);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
          rows.add(objectList);
        }
        System.out.println("====》"+rows);
    }




    public <T> void toSheet2(T beans,List <Object> objectList ) {
        // List <Object> objectList = new ArrayList <>();
        Class <?> beanType = beans.getClass();
        Field[] fields = beanType.getDeclaredFields();
        logger.debug("fields" + fields);
        Field field = null;
        for (int i = 0; i < fields.length; i++) {
            //   List <Object> objectList = new ArrayList <>();
            field = fields[i];
            if (field.isAnnotationPresent(ColumnConfig.class)) {
                for (Annotation anno : field.getDeclaredAnnotations()) {
                    logger.info("所有注解" + anno);
                    if (anno.annotationType().equals(ColumnConfig.class)) {
                        String str = ((ColumnConfig) anno).descriptionyun();
                        logger.info("====>" + str);
                        objectList.add(str);
                    }
                }

            }

        }
    }



    public   <T> String  toSheet29999(T beans ) {

        Class <?> beanType = beans.getClass();
        TableConFiG tableConFiG = beanType.getAnnotation(TableConFiG.class);
        String str= tableConFiG.value();
        logger.info("===>"+str);

//        Field[] fields = beanType.getFields();
//        logger.info("fields" + fields);
//        Field field = null;
//        for (int i = 0; i < fields.length; i++) {
//            //   List <Object> objectList = new ArrayList <>();
//            field = fields[i];
//            if (field.isAnnotationPresent(TableConFiG.class)) {
//                for (Annotation anno : field.getDeclaredAnnotations()) {
//                    logger.info("所有注解" + anno);
//                    if (anno.annotationType().equals(TableConFiG.class)) {
//                         str = ((TableConFiG) anno).value();
//                        logger.info("====>" + str);
//
//                    }
//                }
//
//            }
//
//        }

        return str;

    }



    @Test
    public  void Test1(){
        ErorerBo erorerBo1  = new   ErorerBo();

        // 反射获得 class


        String  str= toSheet29999(erorerBo1);
        System.out.println("str"+str);
    }




}
