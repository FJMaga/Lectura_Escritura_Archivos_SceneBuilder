package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

public class StaXParser {
	
	static final String ITEM="item",DATE="date",NOMBRE="nombre",CIUDAD="ciudad",SALARIO="salario";
	
	static List<Item> items = new ArrayList<Item>();
	
	public List<Item> leer(String archivoXML) {
		
		
		
		try {
			
			
			XMLInputFactory factoria = XMLInputFactory.newInstance();
			
			InputStream entrada = new FileInputStream(archivoXML);
			
			XMLEventReader lector = factoria.createXMLEventReader(entrada);
			
			Item item = null;
            
			while (lector.hasNext()) {
				//Object object = (Object) lector.next();
				//System.out.println(object);
				
	            // Lee el documento XML document
				XMLEvent event = lector.nextEvent();
				
	            
				if (event.isStartElement()) {
                    StartElement startElement = (event.asStartElement());
                    // Si tenemos un elemento Item, crearemos un item nuevo
                    if (startElement.getName().getLocalPart().equals(ITEM)) {
                        item = new Item();
                        // leemos los atributos y añadimos date
                        // atributo a nuestro objeto
                       
						Iterator<Attribute> attributes = startElement.getAttributes();
                        
                        while (attributes.hasNext()) {
                            Attribute attribute = attributes.next();
                            if (attribute.getName().toString().equals(DATE)) {
                                item.setDate(attribute.getValue());
                            }

                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(NOMBRE)) {
                            event = lector.nextEvent();
                            item.setNombre(event.asCharacters().getData());
                            continue;
                        }
                    }

                    if (event.asStartElement().getName().getLocalPart().equals(CIUDAD)) {
                        event = lector.nextEvent();
                        item.setCiudad(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart().equals(SALARIO)) {
                        event = lector.nextEvent();
                        item.setSalario(event.asCharacters().getData());
                        continue;
                    }

                   
                }
                    
                //Leemos cada elemento y lo añadimos a la lista
            
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals(ITEM)) {
                        items.add(item);
                        
                    }
                }

            
			}//cierra while
			
			//System.out.println(items);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//cierra try catch
		
 		return items;
 		
		
	} // cierra método leer+

}

