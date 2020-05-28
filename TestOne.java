// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) braces fieldsfirst splitstr(80) 
// Source File Name:   TestOne.java

package com.fiat.sapiens.xi.transform;

import com.sap.aii.mapping.api.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class TestOne
    implements StreamTransformation
{

    private Map parametri;
    private AbstractTrace trace;

    public void execute(InputStream in, OutputStream out)
        throws StreamTransformationException
    {
        try
        {
            trace = (AbstractTrace)parametri.get("MappingTrace");
            String messId = (String)parametri.get("MessageId");
            trace.addWarning("START TEST Validate");
            trace.addWarning("test Validate:1");
            org.w3c.dom.Document doc = null;
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(in);
            trace.addWarning("test Validate:2");
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            StringWriter sw = new StringWriter();
            StreamResult result = new StreamResult(sw);
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);
            String nodeString = sw.getBuffer().toString();
            trace.addWarning("test Validate:3");
            DocumentBuilderFactory factory1 = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder1 = factory1.newDocumentBuilder();
            org.w3c.dom.Document doc1 = builder1.parse(new StringBufferInputStream(nodeString));
            trace.addWarning("test Validate:4");
            TransformerFactory tFactory2 = TransformerFactory.newInstance();
            Transformer transformer2 = tFactory2.newTransformer();
            StringWriter sw2 = new StringWriter();
            StreamResult result2 = new StreamResult(sw2);
            DOMSource source2 = new DOMSource(doc1);
            transformer2.transform(source2, result2);
            String nodeString2 = sw2.getBuffer().toString();
            trace.addWarning("test Validate:5");
            out.write(nodeString2.getBytes());
            out.flush();
            trace.addWarning("END TEST Validate");
        }
        catch(Exception e)
        {
            trace.addWarning("test Error :\n" + e.toString());
            throw new StreamTransformationException("test Error :" + e.toString());
        }
    }

    public void setParameter(Map parametri)
    {
        this.parametri = parametri;
        if(parametri == null)
        {
            this.parametri = new HashMap();
        }
    }

    public TestOne()
    {
        parametri = null;
        trace = null;
    }
}
