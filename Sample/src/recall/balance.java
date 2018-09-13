package recall;



import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import org.web3j.crypto.CipherException;
import foundation.icon.icx.*;
import foundation.icon.icx.data.Address;
import foundation.icon.icx.transport.http.HttpProvider;
import okhttp3.OkHttpClient;
import java.math.BigInteger;

public class balance {
public final static String URL = "http://127.0.0.1:9000/api/v3";    
private static IconService iconService;
public static String walletaddress = "hx4644c1e45240d0aea7e6f457049c088419957ffe";


public static void BasicGetMethods() throws IOException {
    OkHttpClient httpClient = new OkHttpClient.Builder()
            .build();
    iconService = new IconService(new HttpProvider(httpClient, URL));
    Address address = new Address(walletaddress);
    BigInteger balance = iconService.getBalance(address).execute();
    System.out.println("The Wallet address "+walletaddress+"'s balance : " + balance);
}

public static void main(String[] args)throws IOException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CipherException{				
	balance.BasicGetMethods();
}}