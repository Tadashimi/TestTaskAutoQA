package org.tempuri.soaptests;

public final class RequestBodies {
    public final static String ADD_BODY = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
            "  <soap:Body>\n" +
            "    <Add xmlns=\"http://tempuri.org/\">\n" +
            "      <intA>INT_A</intA>\n" +
            "      <intB>INT_B</intB>\n" +
            "    </Add>\n" +
            "  </soap:Body>\n" +
            "</soap:Envelope>";

    public final static String ADD_12_BODY = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
            "  <soap12:Body>\n" +
            "    <Add xmlns=\"http://tempuri.org/\">\n" +
            "      <intA>INT_A</intA>\n" +
            "      <intB>INT_B</intB>\n" +
            "    </Add>\n" +
            "  </soap12:Body>\n" +
            "</soap12:Envelope>";

    public final static String DIVIDE_BODY = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
            "  <soap:Body>\n" +
            "    <Divide xmlns=\"http://tempuri.org/\">\n" +
            "      <intA>INT_A</intA>\n" +
            "      <intB>INT_B</intB>\n" +
            "    </Divide>\n" +
            "  </soap:Body>\n" +
            "</soap:Envelope>";

    public final static String DIVIDE_12_BODY = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
            "  <soap12:Body>\n" +
            "    <Divide xmlns=\"http://tempuri.org/\">\n" +
            "      <intA>INT_A</intA>\n" +
            "      <intB>INT_B</intB>\n" +
            "    </Divide>\n" +
            "  </soap12:Body>\n" +
            "</soap12:Envelope>";

    private RequestBodies() {}
}
