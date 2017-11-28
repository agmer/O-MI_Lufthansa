package org.aalto.anton.odf.airports;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

//@XmlRootElement(name="value")
public class Value {
//	@XmlElement(name="value")
	@XmlValue
	String value ="xs:string";
	@XmlAttribute(name="type")  
	String type ="xs:string";
public Value(String value)
{
	this.value = value;
}

}
