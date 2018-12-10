package br.com.palves.pbd.model.complemento;

import org.apache.commons.codec.binary.Base64;


public class EncriptaDecriptaApacheCodec {
  
       /**
     * Codifica string na base 64 (Encoder)
     */
    public static String codificaBase64Encoder(String msg) {
        return new Base64().encodeToString(msg.getBytes());
    }
  
    /**
     * Decodifica string na base 64 (Decoder)
     */
    public static String decodificaBase64Decoder(String msg) {
        return new String(new Base64().decode(msg));
    }
  
    public static void main(String[] args) {
  
        String msgCodificada = codificaBase64Encoder("12345678");
  
        String msgDecodificada = decodificaBase64Decoder(msgCodificada);
  
        System.out.println("Mensagem Codificada: " + msgCodificada);
        System.out.println("Mensagem Decodificada: " + msgDecodificada);
        System.out.println("De novo Codificada: " + codificaBase64Encoder("P Alves"));
        System.out.println("De novo: "+decodificaBase64Decoder("UCBBbHZlcw=="));
  
    }
       
}
