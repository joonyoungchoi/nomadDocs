Icon Dev Guide
==============
아이콘은 아이콘 재단의 블록체인이다. 
<p></p>본 문서에서는 아이콘의 개발자용 툴인 T-bears 와, 아이콘에서 제공하는 파이썬 SDK, 자바 SDK를 활용하여 기초적인 개발을 도와주는 Guide다. 
따라하기 쉬운 Sample 코드를 활용하여 주어진 상황별로 튜토리얼을 진행해 봅니다. 본가이드는 아래의 아이콘 github의 내용을 기반으로 작성되었습니다.

<br></br>
아이콘 홈페이지 : <https://icon.foundation/?lang=ko>

아이콘 T-bears : <https://github.com/icon-project/t-bears>

아이콘 파이썬 SDK : <https://github.com/icon-project/icon-sdk-python>

아이콘 자바 SDK : <https://github.com/icon-project/icon-sdk-java>

<br></br>


## 순서
1. T-bears를 사용해서 아이콘 로컬네트워크 구축하기
2. aws에서 아이콘 네트워크 구축하기
3. [개발환경 구축하기](#개발환경 구축하기)
	* [파이썬](## 파이썬 개발 환경 구축하기)
		* [파이썬설치](#### 파이썬 설치) 
		* [Git설치](#### Git 설치) 
		* [파이썬SDK 설치하기](### 파이썬 SDK 설치하기)
		* [1. Git의 코드를 다운로드하여 설치하는 방법](#### 1. Git의 코드를 다운로드하여 설치하는 방법)
		* [2. pip install 을 활용한 방법](#### 2. pip install 을 활용한 방법)

	* [자바](## 자바 개발 환경 구축하기)

		* [자바설치](#### Java 설치) 
		* [이클립스설치](#### Eclipse 설치) 
		* [Git설치](#### Git 설치 )

		* [1. Git의 코드를 다운로드하여 설치하는 방법](#### 1. Git의 코드를 다운로드하여 설치하는 방법)
		* [2. Maven dependency setting](#### 2. Maven dependency setting)
			* [Maven dependency 적용 확인](#### Maven dependency 적용 확인)

		* [3. Gradle dependency setting](#### 3. Gradle dependency setting)
			* [Gradle dependency 적용 확인](#### Gradle dependency 적용 확인)

	
4. SDK 활용하기
	* 지갑사용하기
	* 네트워크와 연결하기
	* 트랜젝션 만들어보기
	* 블록에서 원하는 정보 불러보기

<br></br>


# 개발환경 구축하기
현재 아이콘의 개발자 배포용 SDK는 파이썬, 자바 두 가지 종류가 있다. 개발자들을 위해서 Git을 통해 배포되고 있으며, 각각 언어들을 활용해 개발자들은 지갑을 만들거나, Dapp을 만들어볼 수 있다. 

## 파이썬 개발 환경 구축하기
----------------------

	OS X
	
	Python 3.6.x
	
	Git
	
<br></br>



>파이썬이 설치되어있지 않다면?

#### 파이썬 설치 
파이썬이 설치되어있지 않다면, Home Brew를 통하여 설치해 줍니다. 
Home Brew[<https://brew.sh>] 를 통하여 설치를 진행합니다.
	
	brew install python3


<br></br>

>Git이 설치되어있지 않다면?

#### Git 설치 
Git이 설치되어있지 않다면, Home Brew를 통하여 설치해 줍니다. 
Home Brew[<https://brew.sh>] 를 통하여 설치를 진행합니다.

	brew install git

<br></br>

### 파이썬 SDK 설치하기
파이썬을 활용하여 개발할 수 있도록 아이콘재단에서 공개한 파이썬 SDK를 다운로드 받는 방법은 아래와 같이 두 가지 입니다.  

	1. Git의 코드를 다운로드하여 설치하는 방법
	2. pip install 을 활용한 방법




<br></br>

#### 1. Git의 코드를 다운로드하여 설치하는 방법

Git의 코드를 다운로드하여 설치하는 방법은 아이콘이 지원하는 SDK의 가장 최신버전을 사용할 수 있는 방법입니다. 


* git을 통하여 원격 레포지토리에 있는 SDK를 원하는 위치에 clone 합니다.

		git clone https://github.com/icon-project/icon-sdk-python

* SDK 파일이 clone 된 위치에서 아래와같이 입력합니다.

		python setup.py install 


<br></br>


#### 2. pip install 을 활용한 방법
제일 간단한 방법입니다. pip는 파이썬을 설치하게 되면 같이 설치되는 파이썬으로 작성된 패키지 라이브러리 관리 시스템으로서, 사용자에게 간단한 명령어로 pypl(파이썬 패키지 인덱스)에 등록되어있는 라이브러리를 다운로드받도록 지원해 줍니다.

* pip명령어를 통하여 iconSDK를 설치합니다. 

		pip install iconsdk
	

<br></br>



## 자바 개발 환경 구축하기
---------------------

	OS X
	
	Java(TM) SE Runtime Environment 18.3
	
	Eclipse Version: 2.2.200.v20180611-0500
	
	Maven 
	OR
	Gradle(Optional)
	
<br></br>


> 자바가 설치되어있지 않다면?

#### Java 설치 
아래의 자바 홈페이지 설치 경로에서 자바를 다운로드 받습니다. 

* 홈페이지 : <https://java.com/ko/download/>


<br></br>

>IDE가 없다면?

#### Eclipse 설치 
아래의 이클립스 홈페이지 주소에서 이클립스를 다운로드 받습니다. 

* 홈페이지 : <https://www.eclipse.org/downloads/>

<br></br>


>Git이 설치되어있지 않다면?

#### Git 설치 
Git이 설치되어있지 않다면, Home Brew를 통하여 설치해 줍니다. 
Home Brew[<https://brew.sh>] 를 통하여 설치를 진행합니다.
	
	brew install git

<br></br>

### 자바 SDK 설치하기
자바를 활용하여 개발할 수 있도록 아이콘재단에서 공개한 자바SDK를 다운로드 받는 방법은 아래와 같이 두 가지 입니다.  

	1. Git의 코드를 다운로드하여 설치하는 방법
	2. Maven dependency 설정을 통한 다운로드 방법 (권장)
	3. Gradle dependency 설정을 통한 다운로드 방법 (권장)

<br></br>


#### 1. Git의 코드를 다운로드하여 설치하는 방법

Git의 코드를 다운로드하여 설치하는 방법은 아이콘이 지원하는 SDK의 가장 최신버전을 사용할 수 있는 방법입니다. 


* git을 통하여 원격 레포지토리에 있는 SDK를 원하는 위치에 clone 합니다.

		git clone https://github.com/icon-project/icon-sdk-java

* 다운로드받은 SDK 라이브러리를 import 하여 사용합니다. 

<br></br>


#### 2. Maven dependency setting
* 만들어진 Maven 패키지 안의 POM.xml 파일에 아래와 같이 삽입한다. 

1. icon-sdk를 위한 Dependencies 설정

	+ Maven을 사용할 경우

```
<dependencies>
	<!-- icon-sdk를 위한 dependency -->
  <dependency>
  <groupId>foundation.icon</groupId>
  <artifactId>icon-sdk</artifactId>
  <version>0.9.1</version>
  </dependency>
  
	<!-- okhttp3를 위한 dependency -->
 <dependency>
  <groupId>com.squareup.okhttp3</groupId>
  <artifactId>logging-interceptor</artifactId>
  <version>3.11.0</version>
</dependency>
  </dependencies>
```

 ``` <dependencies> ``` 부터 ``` </dependencies> ``` 까지 내용을 붙여 넣는다. 
 
* 본 Sample Code에서는 
logging-interceptor를 활용한 Log를 위하여 dependencies에 logging-interceptor도 추가하였다.(필수아님)

* 예시 (POM.xml) // 현재 버전 0.9.4

```   
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>java_icon</groupId>
  <artifactId>java_icon</artifactId>
  <version>0.0.1-SNAPSHOT</version>


<dependencies>
	<!-- icon-sdk를 위한 dependency -->
  <dependency>
  <groupId>foundation.icon</groupId>
  <artifactId>icon-sdk</artifactId>
  <version>0.9.4</version>
  </dependency>
  
	<!-- logging-interceptor를 위한 dependency -->
 <dependency>
  <groupId>com.squareup.okhttp3</groupId>
  <artifactId>logging-interceptor</artifactId>
  <version>3.11.0</version>
</dependency>
  </dependencies>
  
  <build>
    <sourceDirectory>src</sourceDirectory>
    <plugins>
      <plugin>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.7.0</version>
        <configuration>
          <release>10</release>
        </configuration>
      </plugin>
    </plugins>
  </build>
</project>

```

위와 같이 dependency 설정을 마치면,  refreash를 해 주어야 원격 아카이브에서 jar 파일을 다운받는다. 

```[생성된 패키지 우클릭 --> Maven --> UpdateProject]```

#### Maven dependency 적용 확인


정상적으로 다운로드가 완료 되면, 프로젝트 내에 있는 
```[Maven Dependencies]```에서 
```icon-sdk-버전.jar``` 
```logging-interceptor-버전.jar``` 가 다운로드 되어있는것을 확인 할 수 있다. 


#### 3. Gradle dependency setting

	
	만들어진 Gradle 패키지 안의 build.Gradle 파일에 아래와 같이 삽입한다. 
	

```
    implementation 'foundation.icon:icon-sdk:0.9.3'
```


* 예시 (build.Gradle) // 현재 버전 0.9.4
	

```
/*
 * This build file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java Library project to get you started.
 * For more details take a look at the Java Libraries chapter in the Gradle
 * user guide available at https://docs.gradle.org/4.3/userguide/java_library_plugin.html
 */

// Apply the java-library plugin to add support for Java Library
apply plugin: 'java-library'

// In this section you declare where to find the dependencies of your project
repositories {
    // Use jcenter for resolving your dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
}

dependencies {
    // This dependency is exported to consumers, that is to say found on their compile classpath.
    api 'org.apache.commons:commons-math3:3.6.1'

    // This dependency is used internally, and not exposed to consumers on their own compile classpath.
    implementation 'com.google.guava:guava:23.0'

    // Use JUnit test framework
    testImplementation 'junit:junit:4.12'
    
    implementation 'foundation.icon:icon-sdk:0.9.4'
    
}
```

위와 같이 dependency 설정을 마치면,  Refreash를 해 주어야 원격 아카이브에서 jar 파일을 다운받는다. 

```[생성된 패키지 우클릭 --> Gradle --> Refreash Gradle Project]```

#### Gradle dependency 적용 확인

정상적으로 다운로드가 완료 되면, 프로젝트 내에 있는 
```[Project and External Dependencies]```에서 
```icon-sdk-버전.jar```  가 다운로드 되어있는것을 확인 할 수 있다. 



<br></br>


# SDK 활용하기
현재 아이콘의 개발자 배포용 SDK는 파이썬, 자바 두 가지 종류가 있습니다. 개발자들을 위해서 Git을 통해 배포되고 있으며, 각각 언어들을 활용해 개발자들은 지갑을 만들거나, 트랜젝션을 보내거나, 내 지갑의 토큰 balnce 를 조회할 수 있습니다.


## 지갑만들기
아이콘의 지갑은 여러 방법을 통하여 만들 수 있습니다. KeyWallet은 Keystore 파일을 통하여 백업할 수 있고, Keystore 파일을 통하여 다시 불러들일 수 있습니다. 가장 간단하게 아이콘의 지갑을 만나볼 수 있는 방법중 하나는 크롬의 확장프로그램(<https://chrome.google.com/webstore/detail/iconex/flpiciilemghbmfalicajoolhkkenfel>)을 다운로드받아 사용하는 것입니다.

지갑에 관련된 작업은 크게 3가지로 분류됩니다.

#### wallet create (지갑생성)
>지갑을 임의로 생성.

#### wallet load (지갑로드)
> Private Key를 통하여 지갑을 로드.

> 비밀번호와 Keystore 파일을 통하여 로드.

#### wallet store (지갑저장)
> Keystore파일을 경로와 비밀번호를 지정하여 저장.

Keystore파일을 저장할 경우, 비밀번호를 통해서 Keystore파일 내의 정보를 암호화합니다. 따라서, Keystore파일을 불러올 경우에는 저장할 때 입력했던 비밀번호를 통해서 복호화를 통하여 불러오게 됨으로, 저장할 경우에 사용하였던 비밀번호가 필수적으로 필요합니다. 지갑을 만들경우 생성되는 개인키는 토큰 또는 ICX를 전송할 때 사용됨으로, 반드시 본인이 별도로 보안이 유지되는 곳에 기록해 두어야 합니다. 각 언어별 예제는 아래 순서대로 안내되어 있습니다.

1. [자바 SDK로 지갑 만들기](### 1. 자바 SDK로 지갑 만들기)
2. [파이썬 SDK로 지갑 만들기](### 2. 파이썬 SDK로 지갑 만들기)

<br></br>


**wallet create (지갑생성)**

 SDK에 내장된 함수를 통하여 지갑 인스턴스를 생성합니다.

##### 자바 SDK로 실행

	wallet = KeyWallet.create()
    create.getAddress();
     
    System.out.println("privateKey로 로드한 지갑 주소 : " +  
    load.getAddress());
    
출력

    hx4873b94352c8c1f3b2f09aaeccea31ce9e90bd31
    // 지갑 주소를 출력합니다
    
*위 출력값은 예시입니다. 실행하실 때 마다 출력값은 달라집니다.*

##### 파이썬 SDK로 실행

	from iconsdk.wallet.wallet import KeyWallet

	wallet = KeyWallet.create()
	wallet.get_address()

출력

	wallet.get_address()
	Out[6]: 'hxfb87932482914ff8ecc750767242e1cbe8b8c41b'
	## 지갑 주소를 출력합니다.
*위 출력값은 예시입니다. 실행하실 때 마다 출력값은 달라집니다.*





<br></br>

**wallet load (지갑로드)**

##### 1. private key 변수를 이용해 지갑을 로드합니다. 
> JavaSDK 에서는 privateKey를 출력하는 기능이 구현되어있지 않습니다.(2018/09/07기준) iconex 크롬 익스텐션 또는 모바일 앱에서도 프라이빗 키를 확인할 수 있습니다. 

* ##### 자바 SDK로 실행

		public static final String 
		PRIVATE_KEY_STRING = "PrivateKey";
    	KeyWallet loadedKey = KeyWallet.load(PRIVATE_KEY_STRING);  
     	//privateKey 로  keywallet 로드 

* ##### 파이썬 SDK로 실행
		key= 'PrivateKey'
		wallet = KeyWallet.load(key)
		//PrivateKey를 통하여 KeyWallet 로드
		
	<br></br>

##### 2. 기존에 저장되어있는 keystore 파일로 지갑을 로드합니다. 

* ##### 자바 SDK로 실행
		File file = new File(destinationDirectory, store);
    	//keystore file로 load, import한다.
     	KeyWallet keyStoreLoad = KeyWallet.load(password, file);
    	//위에서 생성한 keystore 파일과 비밀번호로 다시 로드 

		System.out.println("keyStoreLoad address : " + 
		keyStoreLoad.getAddress());
		
	출력

		hx4873b94352c8c1f3b2f09aaeccea31ce9e90bd31
		// 지갑주소를 리턴합니다.
*위 출력값은 예시입니다. 실행하실 때 마다 출력값은 달라집니다.*

* ##### 파이썬 SDK로 실행
		##password와 keystore의 위치를 통해 KeyWallet.load을 불러들입니다. 
		wallet = KeyWallet.load("./keystore", "password")
		print(wallet.get_address())
	출력
	
		hx4873b94352c8c1f3b2f09aaeccea31ce9e90bd31
		## 지갑주소를 리턴합니다.
				
*위 출력값은 예시입니다. 실행하실 때 마다 출력값은 달라집니다.*

<br></br>


**wallet store (지갑저장)**	
#####keyStore 파일이 저장될 경로를 지정하고 위에서 선언한 key의 password로 지갑을 저장합니다. 
keyStore 파일을 생성할 때는 비밀번호가 필요합니다. 현재 아이콘 크롬 확장프로그램 [지갑](<https://chrome.google.com/webstore/detail/iconex/flpiciilemghbmfalicajoolhkkenfel>)에서는, 숫자, 문자, 특수문자를 모두 포함하여 9자 이상입니다. 현재 SDK상에서는 규약이 강제되어있지 않습니다. 보안성을 위해서는 아이콘 크롬 확장 프로그램에서와 같은 강제성이 부여되는것이 좋습니다.
 
지갑을 저장하기 위해서는, 지갑 인스턴스를 불러들어야 합니다. 
불러들인 지갑 인스턴스를 지정된 위치와 지정된 비밀번호로 암호화 하여 keyStore파일로 저장하여, T-bears, aws, SDK, [크롬확장프로그램](<https://chrome.google.com/webstore/detail/iconex/flpiciilemghbmfalicajoolhkkenfel>)을 통해서 사용할 수 있습니다.

* ##### 자바 SDK로 실행
		File destinationDirectory = new File("./keystore"); 
		//keyStore 파일 저장할 경로를 지정합니다. 
		
		String password = "qwer1234%"; // keysotre 파일의 password 
		String store = KeyWallet.store(loadedKey, password, destinationDirectory);
		//parameter : 로드한 지갑객체, 비밀번호 , 저장할 경로 
		//성공시 저장된 key store file 의 파일명이 리턴됩니다. 
		
* ##### 파이썬 SDK로 실행

		wallet.store("./keystore", "password") 

<br></br>


### 네트워크와 연결하기
트랜젝션을 보내기 이전에, 사용자는 아이콘 블록체인 네트워크와 연결이 되어야 한다. 네트워크는 아래 3가지가 존재합니다.

		1. 메인넷
		2. 테스트넷
		3. 로컬넷
		4. aws 네트워크

 실제 트랜젝션을 발생시키고 거래를 블록에 기입하기 위해서는 네트워크에 연결이 되어 있어야 합니다. 위의 4가지 네트워크의 유형중, 메인넷은 아이콘블록체인 네트워크의 메인 네트워크를 말하며, 테스트넷은 개발자들을 위해 오픈된 테스트 넷을 말합니다. 둘 다 현재는 오픈되어있지 않습니다. (2018/09/07기준) SDK 테스트는 앞선 1번에서 T-bears 를 활용하여 구축한 로컬 네트워크와 연결하여 진행하겠습니다. 네트워크와의 연결은 트랜젝션을 작성하고 노드에 보낼때 반드시 필요한 부분 이기 때문에, 아래의 [트랜젝션 보내기](### 트랜젝션 보내기)에 포함하겠습니다. 

<br></br>


### 트랜젝션 보내기

트랜젝션은 4가지 종류가 있습니다. 

		1. ICX를 전송하는 트랜젝션
		2. 토큰을 전송하는 트랜젝션
		3. 메세지를 전송하는 트랜젝션
		4. SCORE를 Deploy 하는 트랜젝션

각기 트랜젝션은 서버에 json 형식으로 전송이 되며, 서버는 해당 트랜젝션을 받아 블록에 트랜젝션 해시값을 기입함 으로서, 사용자가 전송한 트랜젝션이 블록에 기입됩니다. 

	1. 지갑만들기에서 만든 지갑을 불러와서 다른 지갑으로 ICX를 보내보고 결과 확인하기
	2. 지갑만들기에서 만든 지갑을 불러와서 다른 지갑으로 토큰을 보내보고 결과 확인하기
	3. 메세지를 트랜젝션에 넣고 전송해 보기
	4. 만들어진 SCORE를 Deploy 해 보기

위의 4가지 트랜젝션은 **Transaction 메세지 생성** 부분만이 다릅니다. 아래의 **1. 지갑만들기에서 만든 지갑을 불러와서 다른 지갑으로 ICX를 보내보고 결과 확인하기** 의 실습 코드에서 전체적인 내용을 살펴 보고, **Transaction 메세지 생성** 부분만을 변경함 으로서, 다른 트랜젝션을 보내는 내용을 기록하겠습니다. 

<br></br>

### 1. 지갑만들기에서 만든 지갑을 불러와서 다른 지갑으로 ICX를 보내보고 결과 확인하기

아이콘 rpc 서버의 노드 URL은 아래와 같이 규정됩니다. 

	ip_address:9000/api/v3

* ##### 자바 SDK로 실행

	    public final String URL = "http://[node ip]/api/v3"; 
    
		// icx를 전송할때 필요한 객체를 선언합니다.
	    private IconService iconService;
	    private KeyWallet wallet;
	    private KeyWallet keyStoreLoad;

	지갑을 로드할때 필요한 변수를 선언합니다.
 
	    public static final String 
	    PRIVATE_KEY_STRING =  "----------";
	    
	    //프라이빗 키에 해당하는 지갑 Address 주소
	    public static final String ADDRESS = "hx------------------";
	    public static final String PASSWORD = "Password";

	노드와의 연결 및 상세한 로그를 표출하기 위한 logger를 선언합니다. logger는 옵션이기 때문에 생략하셔도 됩니다.
	**[option]이라고 주석처리된 코드는 생략하셔도 됩니다.**

		HttpLoggingInterceptor logging = new HttpLoggingInterceptor();  //[option]
		//httpProvider 의 url의 정보를 가로채기위함입니다.
	    logging.setLevel(HttpLoggingInterceptor.Level.BODY); //[option]
   		OkHttpClient httpClient = new OkHttpClient.Builder() //로그  출력 
    			.addInterceptor(logging) // [option]
    			.build();


		// iconService를 쓰기위해 객체를 선언합니다. 
     	iconService = new IconService(new HttpProvider(httpClient, URL)); 
     	

	keyStore 파일을 저장한 경로를 정하고  그 경로에 저장되어있는 기존의 keyStore파일을 이용해 지갑을 로드 합니다. 

   		File destinationDirectory = new File("./iconSDKfile"); 
    	File file = new File(destinationDirectory, "keystore.json"); 
    	keyStoreLoad = KeyWallet.load(PASSWORD, file);  
    

	IXC 송금 하기
	
	
	접속하려는 노드의 networkId값은 해당 네트워크의 제네시스 블록에 기록된 값을 기입해 줍니다. networkId는 네트워크를 구분 해 주는 확인자 중 하나이기 때문에 네트워크마다 다르지만, 1은 메인넷, 2는 테스트넷, 3번부터는 Custom Network 가 할당할 수 있습니다. [T-bears의 networkId는 기본 3번으로 할당되어 있습니다. ]
	
		BigInteger networkId = new BigInteger("3"); 
		
		Address fromAddress = keyStoreLoad.getAddress(); 
		//돈을 보낼 주소 (keystore 파일로 불러들인 지갑의 주소를 말합니다.)
		
		Address toAddress = new Address("hx----------------"); 
		//돈을 받을 주소


	송금할금액(ICX)을 정하고, stepLimit 과 timestamp, nonce 를 정해줍니다. 아이콘 네트워크의 전송은 내부적으로 Loop단위로 전송이 되게 됩니다. 따라서 전송될 때 에는 ICX를 Loop 로 계산하여 보내게 됩니다. ICX와 Loop의 비율은 아래와 같습니다. 
	
											1 ICX = 1*10^(18) Loop 
					
		
		//보낼 ICX의 양은 "1"이며, ICX를 Loop로 계산해 주는 코드입니다. 
		BigInteger value = IconAmount.of("1", 18).toLoop(); 
	   	
	stepLimit은 수수료의 한계치 입니다. 스마트컨트렉트를 사용할 경우, 잘못된 스마트컨트렉트의 결과로 모든 잔고가 소진되는것을 막아주기 위함 입니다. 
	ICX 전송시, 권장하는 stepLimit은 10,000입니다. 또한 IRC 2 기반 토큰을 전송할 경우, 권장 step limit 은 20,000 입니다. 
	
		BigInteger stepLimit = new BigInteger("75000"); 
	
	동일 트랜잭션을 막기 위해 타임스탬프 값을 사용합니다. timestamp는 꼭 현재 시간을 넣어야 합니다. 현재시간과 큰 격차가 있을 경우, 서버에서 트랜젝션을 거부합니다. 
	
		long timestamp = System.currentTimeMillis() * 1000L;
	

	**Transaction 메세지 생성**
	
	nonce 값은 입력하지 않아도 괜찮습니다. 

	    Transaction transaction = TransactionBuilder.of(networkId) 
	    			.from(fromAddress)    //보낼 지갑 주소 
	    			.to(toAddress)        //받을 지갑 주소 
	    			.value(value)         //보낼 금액
	    			.stepLimit(stepLimit) //수수료 한계
	    			.timestamp(new BigInteger(Long.toString(timestamp)))
	    			.nonce(nonce)  // 
	    			.build();
	    			
	Transaction 결과 확인
	트랜잭션 정보가 맞는지 확인하기 위한 signedTransaction을 생성하고, 트랜잭션으로 hash값을 확인합니다. 

			SignedTransaction signedTransaction =new SignedTransaction(transaction, keyStoreLoad ); 
			String hash = iconService.sendTransaction(signedTransaction).execute();
			//트랜잭션 발생후 hash값 저장 
			

	 	    			
	   
* ##### 파이썬 SDK로 실행

	필요 모듈을 import 해 줍니다. 

		from iconsdk.icon_service import IconService
		from iconsdk.providers.http_provider import HTTPProvider
		from iconsdk.wallet.wallet import KeyWallet
		from iconsdk.builder.transaction_builder import TransactionBuilder
		from iconsdk.signed_transaction import SignedTransaction
	
	아이콘 네트워크 주소를 지정하여 아이콘 서비스 인스턴스를 생성합니다. 
	
		icon_service = IconService(HTTPProvider("http://localhost:9000/api/v3"))

	ICX를 송금할 지갑의 프라이빗 키를 통해 지갑을 로드할 것 이므로, 지갑의 프라이빗 키를 선언하고, 지갑을 로드합니다. 
	
		key=UserPrivateKey
		Loaded_wallet = KeyWallet.load(key)
	
	**Transaction 메세지 생성**
	
	TransactionBuilder를 통해 트랜젝션을 빌드합니다. 
	
		transaction = TransactionBuilder()\
			.from_(Loaded_wallet.get_address())\ 	## 보내는 지갑 주소
		   	.to("")\ 								## 받는 지갑 주소
		   	.value(150000000)\						##보내는 ICX
		   	.step_limit(1000000)\					## steplimit 설정
		   	.nid(3)\								## NetworkID
		   	.nonce(100)\							## 옵션값입니다. 
		   	.build()	 	
		   	
	빌드된 transaction 객체를 사인하여 전송합니다. 
		
			signed_transaction = SignedTransaction(transaction, Loaded_wallet)
			
	전송된 트랜젝션의 결과확인 		
	
			tx_hash = icon_service.send_transaction(signed_transaction)

## 2. SCORE의 함수를 호출하고, 결과를 받습니다.  

* ##### 자바 SDK로 실행
	**Transaction 메세지 생성**
	파라미터를 call 에 삽입하여, 결과를 불러옵니다. 
	SCORE마다 지정한 함수를 실행하여, 결과를 받아올 수 있습니다. 
		
		RpcObject params = new RpcObject.Builder()
				.put("_owner", new RpcValue(fromAddress))
				.build();
	
		IcxCall<RpcItem> call = new IcxCall.Builder()
				.from(fromAddress)  
				.to(scoreAddress)
				.method("balanceOf")			##  메소드가 balanceOf이면, SCORE의 잔액을 조회합니다. 
				.params(params)
				.build();
	
	SCORE를 향하여 트랜젝션을 보내고, 결과를 받아옵니다. 
	
		RpcItem result = iconService.call(call).execute(); 

		

* ##### 파이썬 SDK로 실행
	**Transaction 메세지 생성**
	자바와 동일하게 transaction을 빌드합니다.
	SCORE을 실행하고, 결과를 리턴받습니다.  
	
		transaction = CallTransactionBuilder()\
		    .from_(Loaded_wallet.get_address())\
		    .to(SCORE_주소)\
		    .step_limit(1000000)\
		    .nid(3)\
		    .nonce(100)\
		    .method("balance_of")\
		    .params(params)\
		    .build()
		    

##	3. 메세지를 트랜젝션에 넣고 전송해 보기
* ##### 자바 SDK로 실행
	**Transaction 메세지 생성**
	메세지를 transaction에 넣어 전송할 수 있습니다. 
	
		        String message = "Hello World";

        Transaction transaction = TransactionBuilder.of(networkId)
                .from(fromAddress)
                .to(toAddress)
                .stepLimit(stepLimit)
                .timestamp(new BigInteger(Long.toString(timestamp)))
                .nonce(nonce)
                .message(message)
                .build();
	
	
	
* ##### 파이썬 SDK로 실행
	**Transaction 메세지 생성**

			transaction = MessageTransactionBuilder()\
			    .from_(Loaded_wallet.get_address())\
			    .to(SCORE_주소)\
			    .step_limit(1000000)\
			    .nid(3)\
			    .nonce(100)\
			    .message("test")\
			    .build()

##	4. 만들어진 SCORE를 Deploy 해 보기
* ##### 자바 SDK로 실행
	**Transaction 메세지 생성**
	로컬에서 사용자가 만들어낸 SCORE 실행파일(파이썬)을 아이콘 네트워크에 Deploy(배포) 합니다. 
	content 파라미터에는 파일을 로드하여 삽입해 줍니다. 트랜젝션의 용량제한이 있으므로(2018/09/06기준 현재 최대 512kb) 용량을 잘 지키고, 스마트 컨트렉트 코드 작성 규칙을 지키지 않으면, 아이콘 네트쿼크에 치명적인 영향을 끼치므로, 규칙에 따라서 작성하여야 합니다.  
	
		Transaction transaction = TransactionBuilder.of(networkId)
		    .from(wallet.getAddress())
		    .to(scoreAddress)
		    .stepLimit(new BigInteger("5000000"))
		    .nonce(new BigInteger("1000000"))
		    .deploy("application/zip", content)
		    .params(params)
		    .build();

* ##### 파이썬 SDK로 실행
	**Transaction 메세지 생성**
	
		transaction = DeployTransactionBuilder()\
		    .from_(Loaded_wallet.get_address())\
		    .to(SCORE_주소)\
		    .step_limit(1000000)\
		    .nid(3)\
		    .nonce(100)\
		    .content_type("application/zip")\
		    .content(content)\
		    .params(params)\
		    .build()


<br></br>
