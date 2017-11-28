package org.aalto.anton.odf.airports;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.aalto.anton.odf.countries.Value;
@XmlType(name = "", propOrder = {
	    "id",
	    "InfoItem",
	})

public class Timezone {
	@XmlAttribute(name="type")  
	String type ="https://iso.org/ISO8601";
	@XmlElement(name="id")
	String id ="timezone";
	String zone ="zone";
	String offset ="off";
public Timezone(String zone, String offset)
{
	this.zone = zone;
	this.offset = offset;
}
//@XmlElement(name = "InfoItem")
//public Value getZone()
//{
//	return new Value(zone);
//}
//public Value getOffset()
//{
//	return new Value(zone);
//}
@XmlElement(name = "InfoItem")
public TimezoneName getLat() {
    return new TimezoneName(zone);
}
@XmlElement(name = "InfoItem2")
public Offset getLong() {
    return new Offset(offset);
}

}
