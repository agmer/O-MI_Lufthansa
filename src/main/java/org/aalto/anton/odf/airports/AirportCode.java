package org.aalto.anton.odf.airports;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.aalto.anton.odf.countries.Value;

public class AirportCode {
	String value ="val";
	@XmlAttribute(name="type")  
	String type ="https://iata.org/AirportCode";
public AirportCode(String value)
{
	this.value = value;
}
@XmlElement(name="id")
public String getValue()
{
	return value;
}

}
