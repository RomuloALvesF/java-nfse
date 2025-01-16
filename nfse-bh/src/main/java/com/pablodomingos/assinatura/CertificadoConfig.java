package com.pablodomingos.assinatura;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.security.cert.CertificateException;
import java.util.Arrays;
import com.pablodomingos.classes.rps.enums.NFSeAmbiente;

public class CertificadoConfig {

  private TipoCertificado tipoCertificado;
  private String senhaCertificado;
  private String aliasCertificado;
  private String caminhoParaCertificado;
  private String caminhoParaCadeiaCertificado;
  private KeyStore keyStoreCertificado;
  private NFSeAmbiente ambiente;
  private InputStream inputStream;

  public CertificadoConfig(CertificadoConfigBuilder builder) {
    super();
    this.senhaCertificado = builder.senhaCertificado;
    this.aliasCertificado = builder.aliasCertificado;
    this.tipoCertificado = builder.tipoCertificado;
    this.caminhoParaCertificado = builder.caminhoParaCertificado;
    this.caminhoParaCadeiaCertificado = builder.caminhoParaCadeiaCertificado;
    this.ambiente = builder.ambiente;

  }

  public KeyStore getCertificadoKeyStore() throws KeyStoreException {
    try {
      if (keyStoreCertificado == null) {
        if (Arrays.asList(TipoCertificado.A3_TOKEN, TipoCertificado.A3_CARD)
            .contains(tipoCertificado)) {
          this.inputStream = getClass().getClassLoader()
              .getResourceAsStream(tipoCertificado.getArquivoConfiguracao());
          this.carregarModuloPKCS11();

        } else if (tipoCertificado.equals(TipoCertificado.A1)) {
          this.inputStream = new FileInputStream(caminhoParaCertificado);
          this.keyStoreCertificado = KeyStore.getInstance("pkcs12");
        }

        this.keyStoreCertificado.load(inputStream, this.getSenhaCertificado().toCharArray());

      }

      return this.keyStoreCertificado;

    } catch (NoSuchAlgorithmException | CertificateException | IOException e) {
      throw new KeyStoreException("Ocorreu um erro ao montar a KeyStore com o certificado", e);
    }
  }

  @SuppressWarnings("restriction")
  private void carregarModuloPKCS11() throws KeyStoreException {
    try {
        KeyStore keystoreInstance = KeyStore.getInstance("pkcs11");
        if (keystoreInstance != null && keystoreInstance.getProvider() != null) {
            this.keyStoreCertificado = keystoreInstance;
        }
    } catch (KeyStoreException ke) {
        // Create a configuration file for PKCS11
        String configString = String.format("name=Provedor\nlibrary=%s", this.inputStream);
        try {
            // Use the newer PKCS11 configuration approach
            Provider provider = Security.getProvider("SunPKCS11");
            provider = provider.configure(configString);
            Security.addProvider(provider);
            this.keyStoreCertificado = KeyStore.getInstance("pkcs11", provider);
        } catch (Exception e) {
            throw new KeyStoreException("Erro ao configurar PKCS11", e);
        }
    }
  }

  @SuppressWarnings("restriction")
  public void carregarCertificados() {
    try {
      this.getCertificadoKeyStore();
    } catch (KeyStoreException e) {
      e.printStackTrace();
    }
    System.clearProperty("javax.net.ssl.keyStore");
    System.clearProperty("javax.net.ssl.keyStorePassword");
    System.clearProperty("javax.net.ssl.trustStore");

    if (this.getTipoCertificado().equals(TipoCertificado.A1)) {
        // Replace deprecated SSL provider with modern TLS
        System.setProperty("https.protocols", "TLSv1.2");
        Security.addProvider(Security.getProvider("SunJSSE"));

        System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
        System.setProperty("javax.net.ssl.keyStore", this.getCaminhoParaCertificado());
        System.setProperty("javax.net.ssl.keyStorePassword", this.getSenhaCertificado());
    } else if (this.getTipoCertificado().equals(TipoCertificado.A3_CARD)) {

      System.setProperty("javax.net.ssl.keyStore", "NONE");
      System.setProperty("javax.net.ssl.keyStoreType", "PKCS11");
      System.setProperty("javax.net.ssl.keyStoreProvider", "SunPKCS11-SmartCard");

    } else if (this.getTipoCertificado().equals(TipoCertificado.A3_TOKEN)) {

      System.setProperty("javax.net.ssl.keyStore", "NONE");
      System.setProperty("javax.net.ssl.keyStoreType", "PKCS11");
      System.setProperty("javax.net.ssl.keyStoreProvider", "SunPKCS11-eToken");

    }

    System.setProperty("javax.net.ssl.trustStoreType", "JKS");
    System.setProperty("javax.net.ssl.trustStore", this.getCaminhoParaCadeiaCertificado());
  }

  public TipoCertificado getTipoCertificado() {
    return tipoCertificado;
  }

  public String getSenhaCertificado() {
    return senhaCertificado;
  }

  public String getAliasCertificado() {
    return aliasCertificado;
  }

  public String getCaminhoParaCertificado() {
    return caminhoParaCertificado;
  }

  public String getCaminhoParaCadeiaCertificado() {
    return caminhoParaCadeiaCertificado;
  }

  public KeyStore getKeyStoreCertificado() {
    return keyStoreCertificado;
  }

  public NFSeAmbiente getAmbiente() {
    return ambiente;
  }

  public static class CertificadoConfigBuilder {

    private TipoCertificado tipoCertificado;
    private String senhaCertificado;
    private String aliasCertificado = null;
    private String caminhoParaCertificado =
        Paths.get("/certificado/certificado.pfx").toAbsolutePath().toString();
    private String caminhoParaCadeiaCertificado =
        Paths.get("/certificado/nfse-bh.cacerts").toAbsolutePath().toString();
    private NFSeAmbiente ambiente = NFSeAmbiente.HOMOLOGACAO;

    public CertificadoConfigBuilder(TipoCertificado tipoCertificado, String senhaCertificado) {

      if (tipoCertificado == null || senhaCertificado == null) {
        throw new IllegalArgumentException("senha ou tipoCertificado não podem ser null");
      }
      this.tipoCertificado = tipoCertificado;
      this.senhaCertificado = senhaCertificado;
    }

    public CertificadoConfigBuilder comAlias(String aliasCertificado) {
      this.aliasCertificado = aliasCertificado;
      return this;
    }

    public CertificadoConfigBuilder comCaminhoCertificadoCliente(String caminhoParaCertificado) {
      this.caminhoParaCertificado = caminhoParaCertificado;
      return this;
    }

    public CertificadoConfigBuilder comCaminhoCadeiaDeCertificados(
        String caminhoParaCadeiaCertificado) {
      this.caminhoParaCadeiaCertificado = caminhoParaCadeiaCertificado;
      return this;
    }

    public CertificadoConfigBuilder comAmbiente(NFSeAmbiente ambiente) {
      this.ambiente = ambiente;
      return this;
    }

    public CertificadoConfig build() {
      return new CertificadoConfig(this);
    }

  }


  public void fechar() {
    try {
      this.inputStream.close();
    } catch (Exception e) {
      /* ignore */
    }
  }
}
