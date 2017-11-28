package org.aalto.anton.odf.cities;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

//@XmlRootElement(name="value")
public class Lat {
//	@XmlElement(name="value")
	@XmlValue
	String value ="xs:string";
	@XmlAttribute(name="type")  
	String type ="latitude";
public Lat(String value)
{
	this.value = value;
}
@XmlElement(name="value")
public Value getValue()
{
	return new Value(value);
}

}
