package recall;

import java.io.IOException;
import foundation.icon.icx.*;
import foundation.icon.icx.transport.http.HttpProvider;
import okhttp3.OkHttpClient;
import java.math.BigInteger;

public class totalsupply {
	public final static String URL = "http://127.0.0.1:9000/api/v3";    
	private static IconService iconService;
	public static String walletaddress = "hx4644c1e45240d0aea7e6f457049c088419957ffe";

	public static void main(String[] args) throws IOException {
		
	    OkHttpClient httpClient = new OkHttpClient.Builder()
	            .build();
	    iconService = new IconService(new HttpProvider(httpClient, URL));
        BigInteger gettotalSupply = iconService.getTotalSupply().execute();
        System.out.println("totalSupply : "+gettotalSupply);

	}}
