package com.pablodomingos.classes.rps.servicos;

import java.util.List;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("Nfse")
public class Nfse {
    
    @XStreamAlias("InfNfse")
    private InfNFSe infNfse;
    
    @XStreamAlias("Signature")
    private Signature signature;

    public InfNFSe getInfNfse() {
        return infNfse;
    }

    public void setInfNfse(InfNFSe infNfse) {
        this.infNfse = infNfse;
    }
    
    public Signature getSignature() {
        return signature;
    }

    public void setSignature(Signature signature) {
        this.signature = signature;
    }

    @XStreamAlias("Signature")
    public static class Signature {
        @XStreamAlias("SignedInfo")
        private SignedInfo signedInfo;
        
        @XStreamAlias("SignatureValue")
        private String signatureValue;
        
        @XStreamAlias("KeyInfo")
        private KeyInfo keyInfo;
    }

    @XStreamAlias("SignedInfo")
    public static class SignedInfo {
        @XStreamAlias("CanonicalizationMethod")
        private CanonicalizationMethod canonicalizationMethod;
        
        @XStreamAlias("SignatureMethod")
        private SignatureMethod signatureMethod;
        
        @XStreamAlias("Reference")
        private Reference reference;
    }

    @XStreamAlias("CanonicalizationMethod")
    public static class CanonicalizationMethod {
        @XStreamAlias("Algorithm")
        @XStreamAsAttribute
        private String algorithm;
    }

    @XStreamAlias("SignatureMethod")
    public static class SignatureMethod {
        @XStreamAlias("Algorithm")
        @XStreamAsAttribute
        private String algorithm;
    }

    @XStreamAlias("Reference")
    public static class Reference {
        @XStreamAlias("Transforms")
        private Transforms transforms;
        
        @XStreamAlias("DigestMethod")
        private DigestMethod digestMethod;
        
        @XStreamAlias("DigestValue")
        private String digestValue;
    }

    @XStreamAlias("Transforms")
    public static class Transforms {
        @XStreamImplicit(itemFieldName = "Transform")
        private List<Transform> transform;
    }

    @XStreamAlias("Transform")
    public static class Transform {
        @XStreamAlias("Algorithm")
        @XStreamAsAttribute
        private String algorithm;
    }

    @XStreamAlias("DigestMethod")
    public static class DigestMethod {
        @XStreamAlias("Algorithm")
        @XStreamAsAttribute
        private String algorithm;
    }

    @XStreamAlias("KeyInfo")
    public static class KeyInfo {
        @XStreamAlias("X509Data")
        private X509Data x509Data;
    }

    @XStreamAlias("X509Data")
    public static class X509Data {
        @XStreamAlias("X509Certificate")
        private String x509Certificate;
    }
}
