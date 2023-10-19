package trasladorXmlBaseDatos;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Consultas {
	
	HashMap<String,Profesor> profesores = new HashMap<>();
	HashMap<String,Curso> cursos = new HashMap<>();
	
	public void rellenar() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();		
		try {
			
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File("C:\\Users\\DAW_M\\Documents\\xml\\curso.xml"));
			document.getDocumentElement().normalize();
			
			System.out.println("El elemento raiz: " + document.getDocumentElement().getNodeName());
			NodeList profesor = document.getElementsByTagName("profesor");
			NodeList Listacursos = document.getElementsByTagName("curso");
			
			//tenemos los profesores en un hashMap
			for (int i = 0; i < profesor.getLength(); i++) {
				Node prof = profesor.item(i);
				if(prof.getNodeType() == Node.ELEMENT_NODE) {
					Element elemento = (Element) prof;
					var nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent();
					if(!profesores.containsKey(nombre)) {
						var apellido1 = elemento.getElementsByTagName("apellido1").item(0).getTextContent();
						var apellido2 = elemento.getElementsByTagName("apellido2").item(0).getTextContent();
						HashMap<String, Curso> cursosProfesor = new HashMap<>();
						
						profesores.put(nombre, new Profesor(nombre,apellido1,apellido2,cursosProfesor));
					}					
				}
			}	
			
			//tenemos los cursos en un hashMap
			for (int i = 0; i < Listacursos.getLength(); i++) {
				Node cur = Listacursos.item(i);
				if(cur.getNodeType() == Node.ELEMENT_NODE) {
					Element elemento = (Element) cur;
					var titulo = elemento.getElementsByTagName("titulo").item(0).getTextContent();
					var fecha = elemento.getElementsByTagName("fecha").item(0).getTextContent();
					var universidad = elemento.getElementsByTagName("universidad").item(0).getTextContent();	
					String key = titulo + universidad;
					NodeList nombresProfesores = elemento.getElementsByTagName("profesor");
					Element profesorprueba = (Element) nombresProfesores.item(0);					
	                String nombre = profesorprueba.getElementsByTagName("nombre").item(0).getTextContent();
	                //rellenamos ahora los cursos a los profesores
	                Profesor asignarCursoProfesor = profesores.get(nombre);
	                HashMap<String,Curso> cursosProfesor = asignarCursoProfesor.getCurso();
	                cursosProfesor.put(key, new Curso(key,fecha,universidad,asignarCursoProfesor));
	                asignarCursoProfesor.setCurso(cursosProfesor);
					//fin rellenar
					Profesor asignarProfesor = profesores.get(nombre);
					HashMap<String, Curso> curso = new HashMap<>();	
					
					cursos.put(key,new Curso(key,fecha,universidad,asignarProfesor));										
				}
			}							
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void cursoProfesor(String nombreProgreso) {
		Profesor p = profesores.get(nombreProgreso);
		var cursosProfesor = p.getCurso();
		System.out.println("Profesor: " + p.getNombre() + " " + p.getApellido1() + " " + p.getApellido2() );
		System.out.println("Sus cursos:"+ cursosProfesor.size());
		System.out.println("------------------------");		   
	    System.out.println("");	
		for (Curso x : cursosProfesor.values()) {
			 System.out.println("------------------------");
		    System.out.println("Universidad: " + x.universidad);
		    System.out.println("Asignatura: " + x.titulo);
		    System.out.println("Fecha: " + x.fecha);	
		}
	}
	
	public void cursoProfesorUniversidad(String nombreProgreso) {
		Profesor p = profesores.get(nombreProgreso);
		var cursosProfesor = p.getCurso();		
	    List<String> universidadesProfesor = new ArrayList<>();
		for (Curso x : cursosProfesor.values()) {
			universidadesProfesor.add(x.universidad);
		}
		Set<String> set = new HashSet<>(universidadesProfesor);
        List<String> listWithoutDuplicates = new ArrayList<>(set);
        System.out.println("Profesor: " + p.getNombre() + " " + p.getApellido1() + " " + p.getApellido2() );
		System.out.println("Universidades donde ha dado clase:"+ set.size());
		System.out.println("------------------------");
        for (String elemento : set) {
            System.out.println(elemento);
        }
	}

}
