package org.aalto.anton.odf.countries;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

//@XmlRootElement(name="value")
public class Zone {
//	@XmlElement(name="value")
//	@XmlValue
	String value ="val";
	@XmlAttribute(name="name")  
	String type ="Zone";
public Zone(String value)
{
	this.value = value;
}
@XmlElement(name="value")
public Value getValue()
{
	return new Value(value);
}

}
