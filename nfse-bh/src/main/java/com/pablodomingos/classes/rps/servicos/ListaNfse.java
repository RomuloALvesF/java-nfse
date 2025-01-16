package com.pablodomingos.classes.rps.servicos;

import java.util.List;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("ListaNfse")
public class ListaNfse {
    
    @XStreamImplicit(itemFieldName = "CompNfse")
    private List<CompNFSe> compNfse;

    public List<CompNFSe> getCompNfse() {
        return compNfse;
    }

    public void setCompNfse(List<CompNFSe> compNfse) {
        this.compNfse = compNfse;
    }
}
