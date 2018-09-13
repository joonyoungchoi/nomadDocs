package Wallet;


import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import org.web3j.crypto.CipherException;
import foundation.icon.icx.KeyWallet;

public class loadtoFile {
	public static void main(String[] args)throws IOException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CipherException{				

		String destinationDirectory = "./Key/";
		String password = "qwer1234%";
		File file = new File(destinationDirectory);
		String Firstfile = file.list()[0];
		System.out.println("첫번째 파일명 : "+Firstfile);
		
		//Key경로 내의 첫 번째 지갑 파일을 불러들인 후, 로드합니다. 
		KeyWallet keyStoreLoad = KeyWallet.load(password, new File(destinationDirectory+Firstfile));
		
		//위에서 불러들인 키스토어의 주소를 출력합니다. 
		System.out.println("keyStoreLoad address : " + 
		keyStoreLoad.getAddress());
		
		//위에서 불러들인 키스토어의 개안키를 출력합니다. 
		System.out.println("keyStoreLoad PrivateKey : " + 
		keyStoreLoad.getPrivateKey());

	
	}}

