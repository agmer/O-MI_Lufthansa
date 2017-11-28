package org.aalto.anton.odf.airports;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

//@XmlRootElement(name="value")
public class Long {
//	@XmlElement(name="value")
	@XmlValue
	String value ="xs:string";
	@XmlAttribute(name="type")  
	String type ="longitude";
public Long(String value)
{
	this.value = value;
}
@XmlElement(name="value")
public Value getValue()
{
	return new Value(value);
}

}
