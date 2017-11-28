package org.aalto.anton.odf.cities;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.aalto.anton.odf.countries.Value;

public class CountryC {
	String value ="val";
	@XmlAttribute(name="type")  
	String type ="https://iata.org/CountryCode";
public CountryC(String value)
{
	this.value = value;
}
@XmlElement(name="id")
public String getValue()
{
	return value;
}

}
