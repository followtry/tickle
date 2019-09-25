package cn.followtry.mybatis;

import org.w3c.dom.Attr;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * 测试解析xml文档的api
 * @author jingzhongzhi
 * @since 2019-02-03
 */
public class ParseXmlDocTest {
    /**
     * main.
     */
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        docFactory.setValidating(true);

        docFactory.setNamespaceAware(false);
        docFactory.setIgnoringComments(true);
        docFactory.setIgnoringElementContentWhitespace(false);
        docFactory.setCoalescing(false);
        docFactory.setExpandEntityReferences(true);
        System.out.println(docFactory.getClass().getName());

        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        docBuilder.setErrorHandler(new ErrorHandler() {
            @Override
            public void warning(SAXParseException exception) throws SAXException {
                System.err.println("warning");
                throw exception;
            }

            @Override
            public void error(SAXParseException exception) throws SAXException {
                System.err.println("error");
                throw exception;
            }

            @Override
            public void fatalError(SAXParseException exception) throws SAXException {
                System.err.println("fatal-error");
                throw exception;
            }
        });
        System.out.println(docBuilder.getClass().getName());
        Document document = docBuilder.parse(new File("brief-mybatis/src/main/resources/cn/followtry/mybatis/mybatis-config.xml"));
        Element root = document.getDocumentElement();
        System.out.println(root.getTagName());
        System.out.println(root.getTextContent());
        System.out.println(root.getSchemaTypeInfo());
        NodeList childNodes = root.getChildNodes();
        String indent = "|  ";
        getChildNode(childNodes,indent);
    }

    private static void getChildNode(NodeList childNodes,String indent) {
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            String attrIndent = indent + "";
            switch (node.getNodeType()) {
                case Node.ELEMENT_NODE:
                    System.out.println(indent + "元素:" + node.getNodeName());
                    printArrt(node.getAttributes(),attrIndent);
                    break;
                case Node.TEXT_NODE:
                    System.out.println(indent + "文本" + node.getNodeName());
                    printArrt(node.getAttributes(),attrIndent);
                    break;
                case Node.COMMENT_NODE:
                    System.out.println(indent + "注释:" + node.getNodeName());
                    Comment comment = (Comment) node;
                    System.out.println(indent + "内容:" + comment.getData());
                    printArrt(node.getAttributes(),attrIndent);
                    break;
                    default:
                        System.out.println(indent + "不匹配类型");
                        break;
            }
            String subIndent = indent + "|  ";
            NodeList subChildNodes = node.getChildNodes();
            getChildNode(subChildNodes,subIndent);
        }
    }

    private static void printArrt(NamedNodeMap attributes,String attrIndent) {
        if (attributes == null || attributes.getLength() <= 0) {
            return;
        }
        for (int j = 0; j < attributes.getLength(); j++) {
            Attr attr =(Attr) attributes.item(j);
            System.out.println(attrIndent + "-属性名:"+attr.getName()+"，属性值："+attr.getValue());
        }
    }
}
