package org.aalto.anton.odf.airports;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.aalto.anton.odf.countries.Value;

public class CityC {
	String value ="val";
	@XmlAttribute(name="type")  
	String type ="https://iata.org/CityCode";
public CityC(String value)
{
	this.value = value;
}
@XmlElement(name="id")
public String getValue()
{
	return value;
}

}
