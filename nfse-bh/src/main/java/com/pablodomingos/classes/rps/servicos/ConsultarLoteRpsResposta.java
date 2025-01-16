package com.pablodomingos.classes.rps.servicos;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.pablodomingos.util.XStreamConfig;

@XStreamAlias("ConsultarLoteRpsResposta")
public class ConsultarLoteRpsResposta {
  @XStreamAlias("xmlns")
  @XStreamAsAttribute
  private String xmlns = "http://www.abrasf.org.br/nfse.xsd";

  @XStreamAlias("ListaMensagemRetornoLote")
  private ListaMensagemRetorno listaMensagemRetornoLote;

  @XStreamAlias("ListaMensagemRetorno")
  private ListaMensagemRetorno listaMensagemRetorno;

  @XStreamAlias("ListaNfse")
  private ListaNfse listaNfse;

  public static ConsultarLoteRpsResposta toPojo(String xml) {
    return XStreamConfig.fromXML(xml, ConsultarLoteRpsResposta.class);
  }

  public ListaMensagemRetorno getListaMensagemRetornoLote() {
    return listaMensagemRetornoLote;
  }

  public void setListaMensagemRetornoLote(ListaMensagemRetorno listaMensagemRetornoLote) {
    this.listaMensagemRetornoLote = listaMensagemRetornoLote;
  }

  public ListaNfse getListaNfse() {
    return listaNfse;
  }

  public void setListaNfse(ListaNfse listaNfse) {
    this.listaNfse = listaNfse;
  }

}
