package com.open.tomcat.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XmlParser
{

    public InputStream getXML(String fileName)
    {
        return XmlParser.class.getResourceAsStream(fileName);
    }

    public String getXMLasString(String fileName) throws IOException
    {
        StringBuilder sb = new StringBuilder();
        InputStream stream = null;
        BufferedReader buf = null;

        try
        {
            stream = this.getXML(fileName);
            buf = new BufferedReader(new InputStreamReader(stream));
            String line = buf.readLine();
            while (line != null)
            {
                sb.append(line).append("\n");
                line = buf.readLine();
            }
        }
        finally
        {
            buf.close();
            stream.close();
        }
        String fileAsString = sb.toString();
        return fileAsString;
    }

    public Document parse(String fileName)
            throws ParserConfigurationException, SAXException, IOException
    {

        File fXmlFile = new File(fileName);

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();

        return doc;
    }

    public void saveXML(Document document,String fileName)
    {
        try
        {
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new FileWriter(new File(fileName)));
            
            transformer.transform(source, result);
        }
        catch (TransformerConfigurationException tce)
        {
            tce.printStackTrace();
        }
        catch (TransformerException te)
        {
            te.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
