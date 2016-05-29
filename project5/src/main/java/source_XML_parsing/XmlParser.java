package source_XML_parsing;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import fablix.Genres_Info_DAO;
import fablix.Genres;
import fablix.Genres_DAO;
import fablix.Movies;
import fablix.Movies_DAO;
import fablix.Stars_Info_DAO;
import fablix.Stars;
import fablix.Stars_DAO;
import fablix.JDBCConnection;

public class XmlParser {
		
	private static XmlParserInit xmlhandler;
	
	public static void initial(String xmlFile){
		try {

			SAXParser xmlparser = SAXParserFactory.newInstance().newSAXParser();
			xmlhandler = new XmlParserInit();

			InputSource input = new InputSource(xmlFile);
			
			input.setEncoding("ISO-8859-1");
			
			xmlparser.parse(input, xmlhandler);
			
			
		} catch (ParserConfigurationException | SAXException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		
		System.out.println("Start parsing actors63.xml");
		initial("source_XML_parsing/stanford-movies/actors63.xml");
		Stars_DAO sDAO = new Stars_DAO();
		HashSet<Stars> starList = xmlhandler.getStarsList(); 
		try {
			
			sDAO.insert(starList);
		
		} catch (SQLException e) {

			System.err.println(e.getMessage());
		}
		
		System.out.println("Finish parsing actors63.xml");
		
		System.out.println("Start parsing mains243.xml");
		initial("source_XML_parsing/stanford-movies/mains243.xml");
		Movies_DAO mDAO = new Movies_DAO();
		Genres_DAO gDAO = new Genres_DAO();
		Genres_Info_DAO gimDAO = new Genres_Info_DAO();
		
		HashSet<Movies> movieList = xmlhandler.getMovieList();
		HashSet<Genres> genresList = xmlhandler.getGenresList();
		HashMap<Movies,HashSet<Genres>> genreInMovie = xmlhandler.getGenreInMovie();
		
		
		try {
			mDAO.insert(movieList);
			gDAO.insert(genresList);
			gimDAO.insert(genreInMovie);
			
		} catch (SQLException e) {

			System.err.println(e.getMessage());
		}

		System.out.println("Finish parsing mains243.xml");
		
		
		System.out.println("Start parsing casts124.xml");
		initial("source_XML_parsing/stanford-movies/casts124.xml");
		Stars_Info_DAO simDAO = new Stars_Info_DAO(); 
		HashMap<Movies,HashSet<Stars>> starInMovie = xmlhandler.getStarInMovie();
		
		try {
			simDAO.insert(starInMovie);
		} catch (SQLException e) {

			System.err.println(e.getMessage());
		}
		
		System.out.println("Finish parsing casts124.xml");
		
		JDBCConnection.connectClose();
	}

	
	
}
