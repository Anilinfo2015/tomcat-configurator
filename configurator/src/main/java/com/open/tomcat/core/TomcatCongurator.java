package com.open.tomcat.core;

public class TomcatCongurator
{
    private String             tomcatHome;

    public static final String SERVER_XML_DEFAULT      = "server.xml";

    public static final String WEB_XML_DEFAULT         = "server.xml";

    public static final String TOMCAT_USER_XML_DEFAULT = "server.xml";

    public static final String CONTEXT_XML_DEFAULT     = "server.xml";

    /**
     * Get tomcatHome
     *
     * @return String
     */
    public String getTomcatHome()
    {
        return tomcatHome;
    }

    /**
     * Set tomcatHome
     *
     * @param String
     */
    public void setTomcatHome(String tomcatHome)
    {
        this.tomcatHome = tomcatHome;
    }
}
