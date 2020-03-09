package tech.kaijie.factory;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 一个创建Bean对象的工厂
 *
 * Bean：在计算机英语中，有可重用组件的含义。
 * JavaBean： 用java语言编写的可重用组件。
 *      Javabean的范围远大于实体类
 * 它就是创建service和dao对象的。
 *
 * 第一个，需要一个配置文件来配置service和Dao
 *      配置的内容：唯一标识=全限定类名（key=value）
 * 第二个，通过读取配置文件中配置的内容，用于反射创建对象
 *
 * 配置文件可以是xml，也可以是properties
 */
public class BeanFactory {

    // 定义个Properties对象
    private static Properties props;

    // 定义一个Map，用于存放我们要创建的对象，我们称之为容器。
    private static Map<String, Object> beans;

    // 使用静态代码块为Properties对象赋值
    // 静态代码块，只在类加载的时候执行一次
    static {
        try {
            // 实例化对象
            props = new Properties();
            // 获取properties对象的文件的流对象
            InputStream in = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            props.load(in);
            // 实例化容器
            beans = new HashMap<String, Object>();
            // 取出配置文件中所有的key
            Enumeration keys = props.keys();
            // 遍历枚举
            while (keys.hasMoreElements()){
                // 取出每个key
                String key = keys.nextElement().toString();
                // 根据key获取value
                String beanPath = props.getProperty(key);
                // 反射创建对象
                Object value = Class.forName(beanPath).newInstance();
                // 把key和value存入容器中
                beans.put(key, value);
            }
        }
        catch (Exception e){
            throw new ExceptionInInitializerError("初始化properties失败");
        }
    }

    /**
     * 根据Bean的名称获取bean对象1
     * @param beanName
     * @return
     */
//    public static Object getBean(String beanName) {
//        Object bean = null;
//        try {
//            String beanPath = props.getProperty(beanName);
//            // 用反射的方式创建对象
//            bean = Class.forName(beanPath).newInstance(); // 每次都会调用默认构造函数创建对象
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        return bean;
//    }

    /**
     * 由于业务层或者持久层，有很少的类成员会修改
     * 根据Bean的名称获取bean对象2（单例），执行效率更高
     * 把业务层或持久层的参数定义到方法内部，这样就达到了单例模式下也可以修改该参数的目的
     * @param beanName
     * @return
     */
    public static Object getBean(String  beanName)
    {
        return beans.get(beanName);
    }
}
