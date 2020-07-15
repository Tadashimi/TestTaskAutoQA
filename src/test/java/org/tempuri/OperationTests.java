package org.tempuri;

import io.restassured.http.Header;

public abstract class OperationTests {
    protected final String CALCULATOR_URL = "http://www.dneonline.com/calculator.asmx";
    protected final int OK_HTTP_STATUS = 200;
    protected final int SERVER_ERROR_HTTP_STATUS = 500;
    protected final Header ADD_SOAP_ACTION_HEADER = new Header("SOAPAction", "http://tempuri.org/Add");
    protected final Header DIVIDE_SOAP_ACTION_HEADER = new Header("SOAPAction", "http://tempuri.org/Divide");
    protected final String TEXT_XML_CONTENT_TYPE = "text/xml; charset=utf-8";
    protected final String SOAP_XML_CONTENT_TYPE = "application/soap+xml; charset=utf-8";
}
