package com.open.tomcat.core;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.open.tomcat.utility.OpenXMLException;
import com.open.tomcat.xml.XmlParser;

/**
 * Server.XML Builder
 */
public class ServerXMLBuilder
{
    private String           fileName;

    private TomcatCongurator configurator;

    private XmlParser        xmlParser = new XmlParser();

    private Document         document;

    /**
     * Constructor ServerXMLBuilder
     * 
     * @throws OpenXMLException
     *
     */
    public ServerXMLBuilder(String fileName) throws OpenXMLException
    {
        this.fileName = fileName;

        init();

    }

    /**
     * Constructor ServerXMLBuilder
     * 
     * @throws OpenXMLException
     *
     */
    public ServerXMLBuilder(TomcatCongurator config) throws OpenXMLException
    {
        this.configurator = config;

        init();
    }

    private void init() throws OpenXMLException
    {
        if (this.configurator != null
                && this.configurator.getTomcatHome() != null)
        {
            try
            {
                this.fileName = TomcatCongurator.SERVER_XML_DEFAULT;
                document = xmlParser.parse(this.fileName);
            }
            catch (Exception e)
            {
                throw new OpenXMLException(
                        "some problem while initializing server.xml", e);
            }
        }
        else
        {
            try
            {
                document = xmlParser.parse(this.fileName);
            }
            catch (Exception e)
            {
                throw new OpenXMLException(
                        "some problem while initializing server.xml", e);
            }

        }
    }

    enum Protocol
    {
        HTTP("http"), HTTPS("org.apache.coyote.http11"), AJP("ajp");

        /**
         * Constructor ServerXMLBuilder.Protocol
         *
         */
        private Protocol(String protocol)
        {
            this.protocol = protocol;
        }

        private String protocol;

        public String getProtocol()
        {
            return this.protocol;
        }
    }

    private Element changeConnector(Protocol protocol)
    {
        NodeList nList = document.getElementsByTagName("Connector");

        for (int temp = 0; temp < nList.getLength(); temp++)
        {
            Node nNode = nList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE)
            {
                Element eElement = (Element) nNode;

                if (eElement.hasAttribute("protocol"))
                {
                    if (eElement.getAttribute("protocol").toLowerCase()
                            .startsWith(protocol.getProtocol()))
                    {
                        return eElement;

                    }
                }
            }
        }
        return null;
    }

    public ServerXMLBuilder newhttpPort(int port)
    {
        Element eElement = changeConnector(Protocol.HTTP);

        if (eElement == null)
        {
            return this;
        }

        eElement.setAttribute("port", port + "");

        return this;
    }

    public ServerXMLBuilder newhttpsPort(int port) throws OpenXMLException
    {
        this.enableHttps(true);

        Element eElement = changeConnector(Protocol.HTTPS);

        if (eElement == null)
        {
            return this;
        }

        eElement.setAttribute("port", port + "");
        return this;
    }

    public ServerXMLBuilder newAJPPort(int port)
    {
        Element eElement = changeConnector(Protocol.AJP);

        if (eElement == null)
        {
            return this;
        }

        eElement.setAttribute("port", port + "");
        return this;
    }

    public ServerXMLBuilder newhttpsKeyStore(String keystorepath)
            throws OpenXMLException
    {
        this.enableHttps(true);

        Element eElement = changeConnector(Protocol.HTTPS);

        if (eElement == null)
        {
            return this;
        }

        eElement.setAttribute("keystoreFile", keystorepath);
        return this;
    }

    public ServerXMLBuilder newhttpsKeyStorePassword(char[] password)
            throws OpenXMLException
    {
        this.enableHttps(true);

        Element eElement = changeConnector(Protocol.HTTPS);

        if (eElement == null)
        {
            return this;
        }

        eElement.setAttribute("keystorePass", String.copyValueOf(password));
        return this;
    }

    public ServerXMLBuilder newShutdownPort(int port)
    {

        NodeList nList = document.getElementsByTagName("Server");

        for (int temp = 0; temp < nList.getLength(); temp++)
        {
            Node nNode = nList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE)
            {
                Element eElement = (Element) nNode;

                if (eElement.hasAttribute("shutdown"))
                {
                    eElement.setAttribute("port", port + "");
                    break;
                }
            }
        }

        return this;
    }

    private ServerXMLBuilder enableHttps(boolean flag) throws OpenXMLException
    {

        Element eElement = changeConnector(Protocol.HTTP);

        Element fElement = changeConnector(Protocol.HTTPS);

        if (fElement == null)
        {
            throw new OpenXMLException("SSL connector not found in server.xml");
        }

        if (eElement == null)
        {
            return this;
        }

        eElement.setAttribute("redirectPort", fElement.getAttribute("port"));

        return this;
    }

    public ServerXMLBuilder enableHttp(boolean flag)
    {

        return this;
    }

    public ServerXMLBuilder enableClustering(boolean flag)
    {

        return this;
    }

    public void save()
    {
        this.xmlParser.saveXML(document, this.fileName);
        System.out.println(this.fileName + " Saved");
    }

    /**
     * Get fileName
     *
     * @return String
     */
    public String getFileName()
    {
        return fileName;
    }

    /**
     * Set fileName
     *
     * @param String
     */
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    /**
     * Get configurator
     *
     * @return TomcatCongurator
     */
    public TomcatCongurator getConfigurator()
    {
        return configurator;
    }

    /**
     * Set configurator
     *
     * @param TomcatCongurator
     */
    public void setConfigurator(TomcatCongurator configurator)
    {
        this.configurator = configurator;
    }
}
