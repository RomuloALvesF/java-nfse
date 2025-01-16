package com.pablodomingos.classes.rps.servicos;

import com.pablodomingos.classes.rps.LoteRps;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("GerarNfseEnvio")
public class RpsEnvio extends AbstractService{

  @XStreamAlias("xmlns")
  @XStreamAsAttribute
  private String xmlns = "http://www.abrasf.org.br/nfse.xsd";

  @XStreamAlias("LoteRps")
  private LoteRps loteRps;

  public RpsEnvio(LoteRps loteRps) {
    this.loteRps = loteRps;
    }
  
}
