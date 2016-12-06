package com.open.tomcat.configurator;

import org.junit.Test;
import com.open.tomcat.core.ServerXMLBuilder;
import com.open.tomcat.core.TomcatCongurator;

public class ServerXMLTesting
{
    
    @Test
    public  void test1() 
    {
        try
        {
            ServerXMLBuilder parser = new ServerXMLBuilder(TomcatCongurator.SERVER_XML_DEFAULT);

            parser.newhttpPort(7070).save();
            
        }catch (Exception e) {
        }
    }

}
