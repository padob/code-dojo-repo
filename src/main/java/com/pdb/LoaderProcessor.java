package com.pdb;

import com.pdb.utils.FileUtils;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.Objects.isNull;

public class LoaderProcessor {
    private static final String SUBJECT_DATAS = "SubjectDatas";
    private static final String SUBJECT_DATA = "SubjectData";
    private static final String CONTENT = "content";
    private final FileWritter fileWritter;
    private final VeryRobustService veryRobustService;

    public LoaderProcessor(FileWritter fileWritter,
                           VeryRobustService veryRobustService) {
        this.fileWritter = fileWritter;
        this.veryRobustService = veryRobustService;
    }

    public void load(String filename) throws XMLStreamException {
        XMLEventReader reader = null;
        veryRobustService.call("used for mocking it only");
        try {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            reader = xmlInputFactory.createXMLEventReader(new FileInputStream(FileUtils.getFilenamePath(filename)));
            while (reader.hasNext()) {
                XMLEvent nextEvent = reader.nextEvent();
                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();
                    switch (startElement.getName().getLocalPart()) {
                        case SUBJECT_DATAS -> //initialize file writters in real life
                                System.out.println("SubjectDatas found");
                        case SUBJECT_DATA -> {
                            System.out.println("Single SubjectData found");

                            Attribute content = startElement.getAttributeByName(new QName(CONTENT));
                            String fileContent = Optional.ofNullable(content).map(Attribute::getValue).orElse(null);
                            fileWritter.write(fileContent);
                        }
                    }
                }
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            Logger.getLogger(LoaderProcessor.class.getName()).log(Level.SEVERE, "Important error occured: ", e);
        } catch (Exception e) {
            Logger.getLogger(LoaderProcessor.class.getName()).log(Level.WARNING, null, e);
            e.printStackTrace();
        } finally {
            //XMLEventReader doesn't implement autoclosable
            if (!isNull(reader)) {
                reader.close();
            }
        }
    }


}
