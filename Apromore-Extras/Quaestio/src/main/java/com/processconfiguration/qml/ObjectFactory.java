/*
 * Copyright © 2009-2015 The Apromore Initiative.
 *
 * This file is part of "Apromore".
 *
 * "Apromore" is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of the
 * License, or (at your option) any later version.
 *
 * "Apromore" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program.
 * If not, see <http://www.gnu.org/licenses/lgpl-3.0.html>.
 */

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.08.01 at 05:03:55 PM EST 
//


package com.processconfiguration.qml;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.processconfiguration.qml package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _QML_QNAME = new QName("http://www.processconfiguration.com/QML", "QML");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.processconfiguration.qml
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link QuestionType }
     * 
     */
    public QuestionType createQuestionType() {
        return new QuestionType();
    }

    /**
     * Create an instance of {@link QMLType }
     * 
     */
    public QMLType createQMLType() {
        return new QMLType();
    }

    /**
     * Create an instance of {@link FactType }
     * 
     */
    public FactType createFactType() {
        return new FactType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QMLType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.processconfiguration.com/QML", name = "QML")
    public JAXBElement<QMLType> createQML(QMLType value) {
        return new JAXBElement<QMLType>(_QML_QNAME, QMLType.class, null, value);
    }

}
