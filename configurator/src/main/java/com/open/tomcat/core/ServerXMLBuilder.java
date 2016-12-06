package com.open.tomcat.core;

import org.w3c.dom.Document;
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
                document = xmlParser.parse(TomcatCongurator.SERVER_XML_DEFAULT);
            }
            catch (Exception e)
            {
                throw new OpenXMLException(e);
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
                throw new OpenXMLException(e);
            }

        }
    }

    public ServerXMLBuilder newhttpPort(int port)
    {

        return this;
    }

    public ServerXMLBuilder newhttpsPort(int port)
    {

        return this;
    }

    public ServerXMLBuilder newAJPPort(int port)
    {

        return this;
    }

    public ServerXMLBuilder newhttpsKeyStore(String keystorepath)
    {

        return this;
    }

    public ServerXMLBuilder newhttpsKeyStorePassword(char[] password)
    {

        return this;
    }

    public ServerXMLBuilder newShutdownPort(int port)
    {

        return this;
    }

    public ServerXMLBuilder enableHttps(boolean flag)
    {

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
        this.xmlParser.saveXML(document);
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
