package recall;


import java.io.IOException;
import java.util.List;
import foundation.icon.icx.*;
import foundation.icon.icx.data.Address;
import foundation.icon.icx.data.ScoreApi;
import foundation.icon.icx.transport.http.HttpProvider;
import okhttp3.OkHttpClient;

public class get_SCRE {

private static final String URL = "http://127.0.0.1:9000/api/v3";    
private static final String SCOREaddress = "cx92dc41a0997aa367df301174485bd6cd579a5ce9";

	public static void main(String[] args) throws IOException{
	OkHttpClient httpClient = new OkHttpClient.Builder()
											  .build();
	IconService iconService = new IconService(new HttpProvider(httpClient, URL));
	
	Address scoreAddress = new Address(SCOREaddress);
	List<ScoreApi> apis = iconService.getScoreApi(scoreAddress).execute();
	System.out.println("apis:" + apis);

}};
