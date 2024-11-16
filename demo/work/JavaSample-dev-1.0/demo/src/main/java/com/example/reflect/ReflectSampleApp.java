package com.example.reflect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.Configurator;
import com.example.PostNumber;

/**
 * Hello world!
 *
 */
public class ReflectSampleApp 
{
    private static Logger logger = LoggerFactory.getLogger(ReflectSampleApp.class);

    private String privateField = "";
    private static String privateStaticField = "";

    public void setPrivateField(String privateField) {
        this.privateField = privateField;
    }

    public static void setPrivateStaticField(String privateStaticField) {
        ReflectSampleApp.privateStaticField = privateStaticField;
    }

    public static void main( String[] args ) throws ClassNotFoundException
    {
        System.out.println( "Hello World!" );
        System.out.println(PostNumber.valueOf("012-3456-789").getValue());
        
        logger.info("〇test〇");
        logger.info(Configurator.getInstance().getValueByKey("key1"));

    }

    public void printValue() {
        System.out.println(privateField);
        System.out.println(privateStaticField);
        System.out.println(privateMethod());
        System.out.println(privateMethod("test"));
        System.out.println(privateStaticMethod());
    }

    public String publicMethod() {
        return "publicMethod param is none";
    }

    public String publicMethod(String param) {
        return "publicMethod param is " + param;
    }

    private String privateMethod() {
        return "privateMethod param is none";
    }

    private String privateMethod(String param) {
        return "privateMethod param is " + param;
    }

    private static String privateStaticMethod() {
        return "privateStaticMethod param is none";
    }
}
