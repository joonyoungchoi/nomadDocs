package Wallet;


import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import org.web3j.crypto.CipherException;
import foundation.icon.icx.KeyWallet;
import foundation.icon.icx.Wallet;
import foundation.icon.icx.data.Bytes;

public class loadtoPK {
	public static void main(String[] args)throws IOException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CipherException{				

		// 개인키를 활용하여 지갑을 불러오고, 불러들인 지갑이 맞는지 확인합니다. 
		
		//개인키를 사용하여 지갑을 불러옵니다. 
		Bytes Private_Key = new Bytes("625de46fb951054330a58ab6f66c18849afc94797f0d37df6ff18cf8ed573981");
		Wallet Localwallet = KeyWallet.load(Private_Key);

		//불러온 지갑의 주소를 확인합니다.
		System.out.println("주소 = "+Localwallet.getAddress());

		//불러온 지갑의 개인키를 확인하여, 제대로 불러왔는지 확인합니다.
		System.out.println("개인키 = "+KeyWallet.load(Private_Key).getPrivateKey());

		
		
	}}

