package project5.jdbc;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class AddOoDB {

	public static void main(String[] args) {
		Document dom;
		JDBCPool pool = JDBCPool.getInstance();

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			// Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// parse using builder to get DOM representation of the XML file
			dom = db.parse("xml/mains243.xml");
			// get the root elememt
			Element docEle = dom.getDocumentElement();

			// get a nodelist of <employee> elements
			NodeList nl = docEle.getElementsByTagName("director");
			if (nl != null && nl.getLength() > 0) {
				for (int i = 0; i < nl.getLength(); i++) {

					// get the employee element
					//System.out.println(nl.item(i).getChildNodes().item(2).getNodeValue());

					// get the Employee object

				}
			}

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		pool.closePool();

	}
}