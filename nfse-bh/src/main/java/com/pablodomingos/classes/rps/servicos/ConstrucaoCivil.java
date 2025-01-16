package com.pablodomingos.classes.rps.servicos;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class ConstrucaoCivil {
    
    @XStreamAlias("CodigoObra")
    private String codigoObra;
    
    @XStreamAlias("Art")
    private String art;

    public String getCodigoObra() {
        return codigoObra;
    }

    public void setCodigoObra(String codigoObra) {
        this.codigoObra = codigoObra;
    }

    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }
} 