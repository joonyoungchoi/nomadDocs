
package TXmake;

import foundation.icon.icx.*;
import foundation.icon.icx.data.Address;
import foundation.icon.icx.data.Bytes;
import foundation.icon.icx.transport.http.HttpProvider;
import foundation.icon.icx.transport.jsonrpc.RpcObject;
import foundation.icon.icx.transport.jsonrpc.RpcValue;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;

public class DeploySampleToken {

    public final String URL = "http://localhost:9000/api/v3";
    public final String PRIVATE_KEY_STRING =
            "625de46fb951054330a58ab6f66c18849afc94797f0d37df6ff18cf8ed573981";

    private IconService iconService;
    private Wallet wallet;

    public DeploySampleToken() {

    	HttpLoggingInterceptor logging = new HttpLoggingInterceptor();  //[option]
        //httpProvider 의 url의 정보를 가로채기 위함입니다.
        logging.setLevel(HttpLoggingInterceptor.Level.BODY); //[option]
        OkHttpClient httpClient = new OkHttpClient.Builder() //로그 출력 
                .addInterceptor(logging) // [option]
                .build();

        // iconService를 쓰기위해 객체를 선언합니다. 
        iconService = new IconService(new HttpProvider(httpClient, URL));

        wallet = KeyWallet.load(new Bytes(PRIVATE_KEY_STRING));
    }

    public void sendTransaction() throws IOException {
        String contentType = "application/zip";
        byte[] content = readFile();
        BigInteger networkId = new BigInteger("3");
        Address fromAddress = wallet.getAddress();
        Address toAddress = new Address("cx0000000000000000000000000000000000000000");
        BigInteger stepLimit = new BigInteger("14685000");
        long timestamp = System.currentTimeMillis() * 1000L;
        BigInteger nonce = new BigInteger("1");

        BigInteger initialSupply = new BigInteger("10000");
        BigInteger decimals = new BigInteger("10");
        String tokenName = "NARA";
        String tokenSymbol = "NAS";

        RpcObject params = new RpcObject.Builder()
                .put("initialSupply", new RpcValue(initialSupply))
                .put("decimals", new RpcValue(decimals))
                //.put("name", new RpcValue(tokenName))
                //.put("symbol", new RpcValue(tokenSymbol))
                .build();

        Transaction transaction = TransactionBuilder.newBuilder()
                .nid(networkId)
                .from(fromAddress)
                .to(toAddress)
                .stepLimit(stepLimit)
                .timestamp(new BigInteger(Long.toString(timestamp)))
                .nonce(nonce)
                .deploy(contentType, content)
                .params(params)
                .build();

        SignedTransaction signedTransaction = new SignedTransaction(transaction, wallet);
        Bytes hash = iconService.sendTransaction(signedTransaction).execute();
        System.out.println("txHash:"+hash);
    }

    private byte[] readFile() throws IOException {                     
        File file = new File(getClass().getClassLoader().getResource("/Users/nc0201020/Dapp/init_test/test/nara.zip").getFile());
        return readBytes(file);
    }

    private byte[] readBytes(File file) throws IOException {
        int length = (int) file.length();
        if (length > Integer.MAX_VALUE) throw new OutOfMemoryError("File is too big!!");
        byte[] result = new byte[length];
        DataInputStream inputStream = new DataInputStream(new FileInputStream(file));
        inputStream.readFully(result);
        return result;
    }

    public static void main(String[] args) throws IOException {
        new DeploySampleToken().sendTransaction();
    }
}