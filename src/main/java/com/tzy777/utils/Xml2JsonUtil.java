package com.tzy777.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tzy777.resources.Resource;
import de.odysseus.staxon.json.JsonXMLConfig;
import de.odysseus.staxon.json.JsonXMLConfigBuilder;
import de.odysseus.staxon.json.JsonXMLInputFactory;
import de.odysseus.staxon.json.JsonXMLOutputFactory;
import de.odysseus.staxon.xml.util.PrettyXMLEventWriter;
import net.sf.json.xml.XMLSerializer;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import java.io.*;
import java.util.List;

/**
 * @author lipu@csii.com.cn
 * @date 2018/8/28 10:09
 */
public class Xml2JsonUtil {

    private static String xmlSting(Resource resource){
        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader br =new BufferedReader(new InputStreamReader(resource.getInputStream()));
            String sText;
            for (;;) {
                sText = br.readLine();
                if(sText == null){
                    break;
                }
                sb.append(sText);
            }
            return sb.toString();
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }

    /**
     * 这个方法有问题，转换的json无法判断元素的属性和子元素。
     * @param node
     * @return
     */
    private static JSONObject iterateElement(Element node) {
        JSONObject result = new JSONObject();
        // 当前节点的名称、文本内容和属性
        List<Attribute> listAttr = node.attributes();
        for (Attribute attr : listAttr) {
            result.put(attr.getName(), attr.getValue());
        }
        // 递归遍历当前节点所有的子节点
        List<Element> listElement = node.elements();
        if (!listElement.isEmpty()) {
            for (Element e : listElement) {
                if (e.attributes().isEmpty() && e.elements().isEmpty()) // 判断一级节点是否有属性和子节点
                    result.put(e.getName(), e.getTextTrim());// 沒有则将当前节点作为上级节点的属性对待
                else {
                    if (!result.containsKey(e.getName())) // 判断父节点是否存在该一级节点名称的属性
                        result.put(e.getName(), new JSONArray());// 没有则创建
                    ((JSONArray) result.get(e.getName())).add(iterateElement(e));// 将该一级节点放入该节点名称的属性对应的值中
                }
            }
        }
        return result;
    }

    public static JSONObject xml2Json_fastjson(Resource resource){
        try {
            String xml=xmlSting(resource);
            Document document = DocumentHelper.parseText(xml);
            Element element = document.getRootElement();
            return iterateElement(element);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }

    public static net.sf.json.JSONObject xml2Json_jsonlib(Resource resource)throws Exception{
        net.sf.json.JSONObject jsonObject = (net.sf.json.JSONObject) new XMLSerializer().readFromStream(resource.getInputStream());
        return  jsonObject;
    }

    /**
     * staxon--
     * @param resource
     * @return
     */
    public static String xml2JsonSting_staxon(Resource resource){
        String xml=xmlSting(resource);
        StringReader input = new StringReader(xml);
        StringWriter output = new StringWriter();
        JsonXMLConfig config = new JsonXMLConfigBuilder().autoArray(true).autoPrimitive(true).prettyPrint(true).build();
        try {
            XMLEventReader reader = XMLInputFactory.newInstance().createXMLEventReader(input);
            XMLEventWriter writer = new JsonXMLOutputFactory(config).createXMLEventWriter(output);
            writer.add(reader);
            reader.close();
            writer.close();
        } catch( Exception e){
            e.printStackTrace();
        } finally {
            try {
                output.close();
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return output.toString();
    }

    public static JSONObject xml2Json_staxon(Resource resource){
        return JSONObject.parseObject(xml2JsonSting_staxon(resource));
    }

    public static String json2xml_staxon(JSONObject jsonObject){
        StringReader input = new StringReader(jsonObject.toJSONString());
        StringWriter output = new StringWriter();
        JsonXMLConfig config = new JsonXMLConfigBuilder().multiplePI(false).repairingNamespaces(false).build();
        try {
            XMLEventReader reader = new JsonXMLInputFactory(config).createXMLEventReader(input);
            XMLEventWriter writer = XMLOutputFactory.newInstance().createXMLEventWriter(output);
            writer = new PrettyXMLEventWriter(writer);
            writer.add(reader);
            reader.close();
            writer.close();
        } catch( Exception e){
            e.printStackTrace();
        } finally {
            try {
                output.close();
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(output.toString().length()>=38){//remove <?xml version="1.0" encoding="UTF-8"?>
            return output.toString().substring(39);
        }
        return output.toString();
    }
}
