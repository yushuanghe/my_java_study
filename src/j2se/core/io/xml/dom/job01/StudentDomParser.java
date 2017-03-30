package j2se.core.io.xml.dom.job01;

import j2se.core.io.xml.dom.job01.bean.Student;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yobdc on 2016/12/16.
 */
public class StudentDomParser {
    public static List<Student> parse(String filename) {
        List<Student> students = new ArrayList<>();

        DocumentBuilderFactory builderFactory = null;
        DocumentBuilder documentBuilder = null;
        Document document = null;
        try {
            builderFactory = DocumentBuilderFactory.newInstance();
            documentBuilder = builderFactory.newDocumentBuilder();
            document = documentBuilder.parse(new File(filename));

            Element root = document.getDocumentElement();

            NodeList studentList = root.getElementsByTagName("student");
            for (int i = 0; i < studentList.getLength(); i++) {
                Node studentNode = studentList.item(i);
                Student student = new Student();

                student.setId(Integer.parseInt(studentNode.getAttributes()
                        .getNamedItem("id").getTextContent()));

                NodeList childStudents = studentNode.getChildNodes();
                for (int j = 0; j < childStudents.getLength(); j++) {
                    Node childStudent = childStudents.item(j);
                    if (childStudent.getNodeName().equals("name"))
                        student.setName(childStudent.getTextContent());
                    else if (childStudent.getNodeName().equals("age"))
                        student.setAge(Integer.parseInt(childStudent.getTextContent()));
                    else if (childStudent.getNodeName().equals("sex"))
                        student.setSex(childStudent.getTextContent());
                }
                students.add(student);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }

    public static void main(String[] args) {
        List<Student> list = parse("xml/student.xml");
        for (Student s : list) {
            System.out.println(s.toString());
        }
    }
}
