package org.aalto.anton.omi.lufthansa.camel;

import org.apache.camel.Exchange;

public class Transformer {
    
    public String process(Exchange exchange) {
        return exchange.getIn().getBody(String.class) + "<!-- World! -->";
    }

//    public String process(Exchange exchange) {
//        Message in = exchange.getIn();
//        Message out = exchange.getOut();
//        String str = in.getBody(String.class) + "<!-- World! -->";
//        return str;
//    }
    
//    public String processHttp(Exchange exchange) {
//	return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hel=\"http://com.porasto.emma.wmq/hello\">" +
//	   "<soapenv:Header/>" +
//	   "<soapenv:Body>" +
//	      "<hel:sayHello>" +
//	         "<txtIn>ni�p niekio allut isi�tip n�m�T</txtIn>" +
//	      "</hel:sayHello>" +
//	   "</soapenv:Body>" +
//	"</soapenv:Envelope>";
//    }

//    public String processHttp(Exchange exchange) {
//	return "<hel:sayHello xmlns:hel=\"http://com.porasto.emma.wmq/hello\">" +
//	         "<txtIn>ni�p niekio allut isi�tip n�m�T</txtIn>" +
//	       "</hel:sayHello>";
//    }
}
