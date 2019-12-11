package application;

import java.io.FileOutputStream;


import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import application.*;


public class StaXEscritura {
	
	private String configFile;
	
    public void setFile(String configFile) {
        this.configFile = configFile;
    }

    public void saveConfig() throws Exception {
        // crea un XMLOutputFactory
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        // crea XMLEventWriter
        XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(new FileOutputStream(configFile));
        // crea un EventFactory
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        // crea y escribe una etiqueta de empiece
        StartDocument startDocument = eventFactory.createStartDocument();
        eventWriter.add(startDocument);

        // crea la etiqueta config
        StartElement configStartElement = eventFactory.createStartElement("",
                "", "config");
        eventWriter.add(configStartElement);
        eventWriter.add(end);
        StartElement configItem = eventFactory.createStartElement("","", "item");
        System.out.println(XMLController.fechaString);
        Attribute ItemAtributo = eventFactory.createAttribute("date", XMLController.fechaString);
		eventWriter.add(configItem);
		eventWriter.add(ItemAtributo);
        eventWriter.add(end);
        // escribe los diferentes nodos
        createNode(eventWriter, "nombre",  XMLController.nombreString);
        createNode(eventWriter, "ciudad",  XMLController.ciudadString);
        createNode(eventWriter, "salario",  XMLController.salarioString);
        eventWriter.add(eventFactory.createEndElement("", "date", "item"));
        eventWriter.add(end);
        // cierra la etiqueta config
        eventWriter.add(eventFactory.createEndElement("", "", "config"));
        eventWriter.add(end);
        eventWriter.add(eventFactory.createEndDocument());
        eventWriter.close();
    }

    private void createNode(XMLEventWriter eventWriter, String name,
            String value) throws XMLStreamException {

        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t");
        // crea el nodo de empiece
        StartElement sElement = eventFactory.createStartElement("", "", name);
        eventWriter.add(tab);
        eventWriter.add(sElement);
        // crea contenido
        Characters characters = eventFactory.createCharacters(value);
        eventWriter.add(characters);
        // crea el final del nodo
        EndElement eElement = eventFactory.createEndElement("", "", name);
        eventWriter.add(eElement);
        eventWriter.add(end);

    }
}
