package com.open.tomcat.utility;

public class OpenXMLException extends Exception
{
    private static final long serialVersionUID = -5415494793378917065L;

    /**
     * Constructor OpenXMLException
     *
     */
    public OpenXMLException(String msg)
    {
        super(msg);
    }
    
    public OpenXMLException(Throwable ex) {
        super(ex.getMessage());
    }

}
