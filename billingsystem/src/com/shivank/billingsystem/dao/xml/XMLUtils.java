package com.shivank.billingsystem.dao.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Utility class for XML operations.
 */
public class XMLUtils {

	/**
	 * Returns Document object of the xml located at the given path
	 * 
	 * @param path path to xml
	 * @return Document object
	 * @throws ParserConfigurationException If parsing error occurs
	 * @throws SAXException If parsing error occurs
	 * @throws IOException If IO error occurs.
	 */
	public static Document getXMLDocument(String path) throws ParserConfigurationException, SAXException, IOException{
		File file = new File(path);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(file);
		doc.getDocumentElement().normalize();
		return doc;
	}

}
