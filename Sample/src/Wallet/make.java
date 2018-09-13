package Wallet;


import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import org.web3j.crypto.CipherException;
import foundation.icon.icx.KeyWallet;

public class make {
	public static void main(String[] args)throws IOException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CipherException{				

	// 지갑을 만들고, 주소를 출력합니다.	
	KeyWallet KeyWallet = foundation.icon.icx.KeyWallet.create()  ;
	System.out.println("지갑 주소 : " +  KeyWallet.getAddress());
	
	
}
}