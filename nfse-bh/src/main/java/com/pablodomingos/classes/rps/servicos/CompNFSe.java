package com.pablodomingos.classes.rps.servicos;



import com.thoughtworks.xstream.annotations.XStreamAlias;



@XStreamAlias("CompNFSe")

public class CompNFSe {

    

    @XStreamAlias("Nfse")

    private Nfse nfse;



    public Nfse getNfse() {

        return nfse;

    }



    public void setNfse(Nfse nfse) {

        this.nfse = nfse;

    }

}



