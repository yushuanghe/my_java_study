package j2se.core.reflection.xml;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 将xml文件动态生成对象
 * Created by yobdc on 2017/01/18.
 */
public class Activty {

    private String xml;
    private Element root = null;
    private String classPath = "j2se.core.reflection.xml.";

    /**
     * 解析xml文件，得到根节点
     *
     * @param xml
     */
    public void setContentView(String xml) {
        this.xml = xml;
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(xml);
            root = document.getRootElement();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public Object findViewById(String id) {
        Object object = null;
        if (root != null) {
            //得到所有节点
            List<Element> elements = root.selectNodes("//*");

            Element element = null;

            if (elements != null && elements.size() > 0) {
                for (Element ele : elements) {
                    Attribute attribute = ele.attribute("id");
                    if (attribute != null) {
                        if (id.equals(attribute.getValue())) {
                            //得到要找的对象
                            element = ele;
                            break;
                        }
                    }
                }

                if (element != null) {
                    String className = this.classPath + element.getName();
                    try {
                        Class<?> clazz = Class.forName(className);
                        object = clazz.newInstance();

//                        if (object != null) {
//                            Field[] fields = clazz.getDeclaredFields();
//                            for (Field field : fields) {
//                                String fieldName = field.getName();
//                                Attribute attribute = element.attribute(fieldName);
//                                if (attribute != null) {
//                                    field.setAccessible(true);
//
//                                    String methodName = String.format("set%s%s",
//                                            fieldName.substring(0, 1).toUpperCase()
//                                            , fieldName.substring(1));
//
//                                    Method method = clazz.getMethod(methodName, field.getType());
//                                    method.invoke(object, attribute.getValue());
//                                }
//                            }
//                            return object;
//                        }
                        if (object != null) {
                            List<Attribute> attributes = element.attributes();
                            for (Attribute attribute : attributes) {
                                Field field = clazz.getDeclaredField(attribute.getName());
                                if (field != null) {
                                    field.setAccessible(true);
                                    field.set(object, attribute.getValue());
                                }
                            }
                            return object;
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }
}
