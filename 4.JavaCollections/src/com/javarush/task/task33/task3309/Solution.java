package com.javarush.task.task33.task3309;

import org.w3c.dom.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.StringWriter;

/*
Комментарий внутри xml
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) throws Exception {

        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        StringWriter stringWriter = new StringWriter();
        //marshaller.marshal(obj, stringWriter);

        // Получение фабрики, чтобы после получить билдер документов.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // Получили из фабрики билдер, который парсит XML, создает структуру Document в виде иерархического дерева.
        DocumentBuilder builder = factory.newDocumentBuilder();
        // Запарсили XML, создав структуру Document. Теперь у нас есть доступ ко всем элементам, каким нам нужно.
        Document document = builder.newDocument();
        marshaller.marshal(obj,document);

        NodeList nodeList = document.getElementsByTagName("*");

        // Перебор всех элементов employee
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeName().equals(tagName)) {
                Comment commentNode = document.createComment(comment);
                node.getParentNode().insertBefore(commentNode, node);
            }
            if (node.hasChildNodes()) {
                Node firstChild = node.getFirstChild();
                if ((firstChild.getNodeType() == Node.TEXT_NODE) && (firstChild.getTextContent().matches(".*[<>&\'\"].*"))) {
                    CDATASection cdataSection = document.createCDATASection(firstChild.getTextContent());
                    node.replaceChild(cdataSection, firstChild);
                }
            }
        }

        // Coхpaнить тekcтoвoe пpeдcтaвлeниe XML дokymeнтa в фaйл            
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");  // this allows you make \n
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(stringWriter);
        transformer.transform(source, result);

        return stringWriter.toString();
    }

    public static void main(String[] args) throws Exception {
        First obj = new First();
        String newxml = toXmlWithComment(obj,"second","it's a comment");
        System.out.println(newxml);
    }
}
