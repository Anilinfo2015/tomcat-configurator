#Apache Tomcat Server Configuration Builder
server.xml,context.xml,web.xml and tomcat-users.xml file builder and all the apache tomcat server configurations e.g. SSL enable, connectors,port,load balancing etc.

Example : 

   ServerXMLBuilder parser = new ServerXMLBuilder(
                TomcatCongurator.SERVER_XML_DEFAULT);
        
   parser.newhttpPort(7070).save();
