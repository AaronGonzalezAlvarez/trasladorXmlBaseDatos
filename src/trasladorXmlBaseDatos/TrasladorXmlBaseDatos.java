package trasladorXmlBaseDatos;

import java.io.File;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TrasladorXmlBaseDatos {
	

	public static void main(String[] args) {
		Consultas consulta = new Consultas();
		consulta.rellenar();
		//consulta.cursoProfesor("Pablo");
		consulta.cursoProfesorUniversidad("Pablo");
	}			

}
