package com.pablodomingos.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

public class XStreamConfig {
    
    public static XStream createXStream() {
        // Remover a sobrescrição de shouldEncodeReference
        XStream xstream = new XStream(new DomDriver("UTF-8"));
        
        // Allow any type to be deserialized
        xstream.addPermission(AnyTypePermission.ANY);
        
        // Configure XML processing
        xstream.setMode(XStream.NO_REFERENCES);
        xstream.autodetectAnnotations(true);
        
        // Register all classes that need XML serialization
        xstream.allowTypes(new Class[] {
            com.pablodomingos.classes.rps.servicos.GerarNFSeResposta.class,
            com.pablodomingos.classes.rps.servicos.EnviarLoteRpsResposta.class,
            com.pablodomingos.classes.rps.servicos.ConsultarLoteRpsResposta.class,
            com.pablodomingos.classes.rps.servicos.LoteRpsConsultaSituacao.class,
            com.pablodomingos.classes.rps.servicos.LoteRpsConsulta.class,
            com.pablodomingos.classes.rps.servicos.NFSeCancelamento.class,
            com.pablodomingos.classes.rps.servicos.NFSeConsulta.class,
            com.pablodomingos.classes.rps.servicos.RpsConsulta.class,
            com.pablodomingos.classes.rps.RpsPrestador.class,
            com.pablodomingos.classes.rps.NFSePedidoCancelamento.class,
            com.pablodomingos.classes.rps.PeriodoEmissao.class,
            com.pablodomingos.classes.rps.RpsIdentificacao.class,
            // Add all response classes and their dependencies
            com.pablodomingos.classes.rps.servicos.ListaMensagemRetorno.class,
            com.pablodomingos.classes.rps.servicos.ListaNfse.class,
            com.pablodomingos.classes.rps.servicos.CompNFSe.class,
            com.pablodomingos.classes.rps.servicos.Nfse.class,
            com.pablodomingos.classes.rps.servicos.InfNFSe.class,
            com.pablodomingos.classes.rps.servicos.ConstrucaoCivil.class
        });
        
        processAnnotations(xstream);
        
        return xstream;
    }
    
    public static <T> T fromXML(String xml, Class<T> type) {
        XStream xstream = createXStream();
        xstream.processAnnotations(type);
        return (T) xstream.fromXML(xml);
    }
    
    private static void processAnnotations(XStream xstream) {
        xstream.processAnnotations(new Class[] {
            com.pablodomingos.classes.rps.servicos.LoteRpsConsultaSituacao.class,
            com.pablodomingos.classes.rps.servicos.LoteRpsConsulta.class,
            com.pablodomingos.classes.rps.servicos.NFSeCancelamento.class,
            com.pablodomingos.classes.rps.servicos.NFSeConsulta.class,
            com.pablodomingos.classes.rps.servicos.RpsConsulta.class,
            com.pablodomingos.classes.rps.servicos.GerarNFSeResposta.class,
            com.pablodomingos.classes.rps.servicos.EnviarLoteRpsResposta.class,
            com.pablodomingos.classes.rps.servicos.ConsultarLoteRpsResposta.class
        });
    }
}
