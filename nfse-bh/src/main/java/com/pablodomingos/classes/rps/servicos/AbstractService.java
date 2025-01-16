package com.pablodomingos.classes.rps.servicos;

import com.pablodomingos.util.XStreamConfig;
import com.thoughtworks.xstream.XStream;

public abstract class AbstractService {
    
    public String converterParaXml() {
        return converterParaXml(XStreamConfig.createXStream());
    }
    
    public String converterParaXml(XStream xstream) {
        String xml = xstream.toXML(this);
        return xml.replaceAll("\\s+", "").trim();
    }
}
