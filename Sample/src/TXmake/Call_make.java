package TXmake;

import foundation.icon.icx.Call;
import java.io.IOException;
import foundation.icon.icx.IconService;
import foundation.icon.icx.data.Address;
import foundation.icon.icx.transport.http.HttpProvider;
import foundation.icon.icx.transport.jsonrpc.RpcItem;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


public class Call_make {

    public final String URL = "http://localhost:9000/api/v3";
    private final Address scoreAddress = new Address("cx92dc41a0997aa367df301174485bd6cd579a5ce9");

    private IconService iconService;

    public Call_make() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
        iconService = new IconService(new HttpProvider(httpClient, URL));
    }

    public void getBalance() throws IOException {
        Address address = new Address("hxcc61e31ed6080926d6d6f7d0ac6e1b8b2ee5a9fa");
        Param params = new Param();
        params._owner = address;

        Call<RpcItem> call = new Call.Builder()
                .from(address)
                .to(scoreAddress)
                .method("balanceOf")
                .params(params)
                .build();

        RpcItem result = iconService.call(call).execute();
        System.out.println("balance:"+result);
    }

    class Param {
        public Address _owner;
    }

    public static void main(String[] args) throws IOException {
        new Call_make().getBalance();
    }
}





