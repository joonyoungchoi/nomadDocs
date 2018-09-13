package TXmake;


import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import org.web3j.crypto.CipherException;
import foundation.icon.icx.IconService;
import foundation.icon.icx.KeyWallet;
import foundation.icon.icx.SignedTransaction;
import foundation.icon.icx.Transaction;
import foundation.icon.icx.TransactionBuilder;
import foundation.icon.icx.data.Address;
import foundation.icon.icx.data.Bytes;
import foundation.icon.icx.data.IconAmount;
import foundation.icon.icx.transport.http.HttpProvider;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class Message {
	public static void main(String[] args)throws IOException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CipherException{				
		
	// T-Bears사용시 
	final String URL = "http://127.0.0.1:9000/api/v3"; 

	// icx를 전송할때 필요한 객체를 선언합니다.
	IconService iconService;
	KeyWallet keyStoreLoad;

	
	Bytes Private_Key = new Bytes("625de46fb951054330a58ab6f66c18849afc94797f0d37df6ff18cf8ed573981");
    keyStoreLoad = KeyWallet.load(Private_Key);
    Address fromAddress = keyStoreLoad.getAddress(); 
    
    
	
	HttpLoggingInterceptor logging = new HttpLoggingInterceptor();  //[option]
    //httpProvider 의 url의 정보를 가로채기 위함입니다.
    logging.setLevel(HttpLoggingInterceptor.Level.BODY); //[option]
    OkHttpClient httpClient = new OkHttpClient.Builder() //로그 출력 
            .addInterceptor(logging) // [option]
            .build();

    // iconService를 쓰기위해 객체를 선언합니다. 
    iconService = new IconService(new HttpProvider(httpClient, URL));

    
    

    //돈을 보낼 주소 (keystore 파일로 불러들인 지갑의 주소를 말합니다.)
    Address toAddress = new Address("hx4644c1e45240d0aea7e6f457049c088419957ffe"); 
    //돈을 받을 주소
    
    //보낼 ICX의 양은 "1"이며, ICX를 Loop로 계산해 주는 코드입니다. 
    //1ICX를 보냅니다. 
    BigInteger value = IconAmount.of("1", 18).toLoop(); 
    
    
    BigInteger stepLimit = new BigInteger("7500000"); 
    long timestamp = System.currentTimeMillis() * 1000L;

 
////////////////////////////////////////////////////////////////////////////////////
////////////아래 블럭부분이 달라집니다. //////////////////////////////////////////////////
    
    String message = "Hello World";

    Transaction transaction = TransactionBuilder.newBuilder() 
    		.from(fromAddress)
    		.to(toAddress)
    		.stepLimit(stepLimit)
    		.timestamp(new BigInteger(Long.toString(timestamp)))        
    		//timestamp는 unix time을 삽입합니다. 시간은 1/1000000초 까지 기록됩니다. 
    		.message(message)
    		.build();

////////////////////////////////////////////////////////////////////////////////////
    
    //빌드된 트랜젝션을 보내는사람 주소로 sign 합니다. 
    SignedTransaction signedTransaction =new SignedTransaction(transaction, keyStoreLoad ); 
    
    // 트랜젝션을 보낸 후, 결과인 TX hash 값을 받아옵니다. 
    Bytes hash = iconService.sendTransaction(signedTransaction).execute();
    
    
    // 결과 종합 출력 
    System.out.println( "from Addres : "+fromAddress);
    System.out.println( "to Addres : "+toAddress);
    System.out.println( "Amount : "+value);
    System.out.println( "stepLimit : "+stepLimit);
    //TX hash 값을 출력합니다. 
    System.out.println("TX hash : "+hash);
    
    
    
    
    
}

}
