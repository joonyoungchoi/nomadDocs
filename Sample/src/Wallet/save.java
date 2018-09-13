package Wallet;


import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import org.web3j.crypto.CipherException;
import foundation.icon.icx.KeyWallet;


public class save {
	public static void main(String[] args)throws IOException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CipherException{			

	//keyStore 파일 저장할 경로를 지정합니다. 
	File destinationDirectory = new File("./"); 

	// keysotre 파일의 password
	String password = "qwer1234%";  
	
	// KeyWallet.create() 를 사용하여, 새로 만들어낸 지갑을 저장하였습니다.
	// 불러들인 지갑도 저장할 수 있습니다. 
	String store = KeyWallet.store(KeyWallet.create(), password, destinationDirectory);	
	//parameter : 로드한 지갑객체, 비밀번호 , 저장할 경로 
	//성공시 저장된 key store file 의 파일명이 리턴됩니다. 
	
	System.out.println("Located in = " + destinationDirectory + "/" + store);
}}
