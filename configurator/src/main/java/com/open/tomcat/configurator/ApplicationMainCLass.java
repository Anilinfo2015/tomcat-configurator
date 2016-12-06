package com.open.tomcat.configurator;

import com.open.tomcat.core.ServerXMLBuilder;
import com.open.tomcat.core.TomcatCongurator;
import com.open.tomcat.utility.OpenXMLException;

/**
 * Hello world!
 *
 */
public class ApplicationMainCLass
{
    public static void main(String[] args) throws OpenXMLException
    {

        ServerXMLBuilder parser = new ServerXMLBuilder(
                TomcatCongurator.SERVER_XML_DEFAULT);
        
        parser.newhttpPort(7070).save();
    }
}
