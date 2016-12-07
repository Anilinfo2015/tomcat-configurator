package com.open.tomcat.utility;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 */
public class OpenXMLException extends Exception
{
    private static final long   serialVersionUID = -5415494793378917065L;

    private static final Logger LOG              = Logger
            .getLogger(OpenXMLException.class.getName());
    
    public OpenXMLException(String msg) {
        super(msg);
    }

    public OpenXMLException(String msg, Exception ex)
    {
        super("Tomcat Configurator Exception : " + msg, ex);

        LOG.log(Level.INFO, msg);
    }

}
