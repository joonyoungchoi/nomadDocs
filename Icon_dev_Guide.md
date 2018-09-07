Icon Dev Guide
==============
아이콘은 아이콘 재단의 블록체인입니다.
본 문서에서는 아이콘의 개발자용 툴인 T-bears 와, 아이콘에서 제공하는 파이썬 SDK, 자바 SDK를 활용하여 기초적인 개발을 도와주는 Guide입니다.
따라하기 쉬운 Sample 코드를 활용하여 주어진 상황별로 튜토리얼을 진행해 봅니다. 본가이드는 아래의 아이콘 github의 내용을 기반으로 작성되었습니다.

<br></br>
아이콘 홈페이지 : <https://icon.foundation/?lang=ko>. 

아이콘 T-bears : <https://github.com/icon-project/t-bears>. 

아이콘 파이썬 SDK : <https://github.com/icon-project/icon-sdk-python>. 

아이콘 자바 SDK : <https://github.com/icon-project/icon-sdk-java>

<br></br>


## 순서
1. T-Bears 설치하기
    * 공통

    * Git의 코드를 다운로드하여 설치하기
    
    * PyPI 통해 설치하기

2. T-Bears, SCORE 활용하기
    * 서비스
        1. 서비스 시작 
        2. 서비스 중단
        3. 서비스에 배포된 SCORE 삭제

    * SCORE 배포하기
        1. init으로 생성한 SCORE 프로젝트 배포하기
        2. samples로 생성한 SCORE 배포하기

    * 배포한 SCORE의 메소드 호출하기
        1. init을 통해 생성한 SCORE의 'hello' 메소드 호출
        2. samples를 통해 생성한 SCORE("standard_token.py")의 'name' 메소드 호출

    * 트랜잭션
        1. 트랜잭션 요청
        2. 트랜잭션 결과 확인

    * ICX
        1. ICX 보내기
        2. ICX 잔고 확인하기

    * 명령어 정리
        1. server 명령어
        2. utility 명령어
        3. SCORE 명령어
        4. ICX, 트랜잭션, 블록과 관련된 명렁어
        5. 설정 파일

3. SDK 개발환경 구축하기
	* 파이썬 
		* 파이썬이 설치되어있지 않다면?  
		* Git이 설치되어있지 않다면?
		* 파이썬SDK 설치하기
		* 1. Git의 코드를 다운로드하여 설치하는 방법
		* 2. pip install 을 활용한 방법

	* 자바
		* 자바가 설치되어있지 않다면?
		* IDE가 설치되어있지 않다면?(이클립스)  
		* Git이 설치되어있지 않다면?
		* 자바SDK 설치하기
		* 1. Git의 코드를 다운로드하여 설치하는 방법
		* 2. Maven dependency setting
			* Maven dependency 적용 확인
		* 3. Gradle dependency setting
			* Gradle dependency 적용 확인
			
3. SDK 활용하기
	* 지갑사용하기
		*  wallet create (지갑생성)
		* wallet load (지갑로드)
		* wallet store (지갑저장)
		
	
	* 네트워크와 연결하기
	
	
	* 트랜젝션 만들어보기
		* ICX를 전송하는 트랜젝션
		* 토큰을 전송하는 트랜젝션
		* 메세지를 전송하는 트랜젝션
		* SCORE를 Deploy 하는 트랜젝션


<br></br>

---
### T-Bears 설치하기 (macOS)
T-Bears를 설치하는 방법에는 두가지가 있으며 다음과 같습니다.
```
1. Git의 코드를 다운로드하여 설치하기
2. PyPI 통해 설치하기
```
>두가지 방법을 진행하기에 앞서 공통적으로 구성해야할 환경은 아래와 같습니다.


#### 공통

* Home Brew(macOS용 패키지 관리자)를 설치합니다.

```
/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
```
* LevelDB, libsecp256k1를 설치합니다.
```
brew install leveldb
brew install autoconf automake libtool pkg-config
```

* RabbitMQ를 설치합니다.
```
brew install rabbitmq
brew services start rabbitmq
```

* wget, git을 설치합니다.
```
brew install wget // wget 설치
brew install git // git 설치
```

* Anaconda를 설치합니다.
```
wget https://repo.anaconda.com/archive/Anaconda3-5.2.0-MacOSX-x86_64.sh
bash ./Anaconda3-5.2.0-MacOSX-x86_64.sh
```

* Virtualenv (python3 venv 대체 가능)를 설치합니다.
```
pip3 install virtualenv
```

<br></br>
#### 1. Git의 코드를 다운로드하여 설치하기
* git 설치가 완료되면, tbears Git repository에 존재하는 코드를 `t-bears` 디렉토리로 clone 합니다.

```
git clone https://github.com/icon-project/t-bears.git t-bears
cd t-bears

virtualenv -p python3 venv  
source venv/bin/activate    
(venv) ./build.sh
(venv) pip install ./dist/tbears-1.0.4-py3-none-any.whl
```
<br></br>
#### 2. PyPI 통해 설치하기
* work 디렉토리 생성 -> tbears 설치
```
mkdir work
cd work

virtualenv -p python3 .
source ./bin/activate

(work) pip install tbears
```
---
### T-Bears, SCORE 활용하기
T-bears는 아이콘-더 루프팀의 개발자용 CLI 로컬 테스트 개발툴입니다.
ICON은 JSON-RPC방식으로 통신합니다. Tbears는, 로컬에서 localhostJSON-RPC 응답을 지원하는 개발툴이다. 
현재, Git에 공개가 되어있으며, 사용을 원하는 개발자는 언제든 다운로드 받아 사용 가능하다. 
<https://github.com/icon-project/t-bears.git>

### T-Bears 활용하기 (1.0.4 version)
>T-Bears 활용하여 실행할 간단한 예제를 아래에 두었습니다. 

---
###  *1. 서비스*

*1.1 서비스 시작*

```
tbears start
```
출력되는 메시지를 통해 서비스가 시작되는 것을 확인할 수 있습니다.
```
Started tbears service successfully
```
<br></br>
*1.2 서비스 중단*

```
tbears stop          
```
출력되는 메시지를 통해 서비스가 중단되는 것을 확인할 수 있습니다.
```
Stopped tbears service successfully
```
<br></br>
*1.3 서비스에 배포된 SCORE 삭제*

```
tbears clear         
```
출력되는 메시지를 통해 서비스에 배포된 SCORE가 삭제되는 것을 확인할 수 있습니다.
```
Cleared SCORE deployed on tbears successfully
```
---
### *2. SCORE 배포하기*

*2.1 init으로 생성한 SCORE 프로젝트 배포하기*

init 명령어를 통해 SCORE 프로젝트를 생성합니다.
```
tbears genconf (optional : 미실행시 init단계에서 설정파일을 생성합니다.)
tbears init myproject ABCToken
```
출력되는 메시지를 통해 프로젝트가 성공적으로 생성됨을 확인할 수 있습니다.
```
Initialized tbears successfully
```

생성된 프로젝트를 T-Bears 서비스에 배포합니다. (tbears deploy의 경우 tbears start를 통해 T-Bears 서비스가 가동 중인 상태에서만 가능합니다.)
```
tbears deploy myproject
```
출력 
```
Send deploy request successfully.
If you want to check SCORE deployed successfully, execute txresult command
transaction hash: 0x1468dd5deab399e309732728d37786eab8ad1f5a09ac9b85c92be0cc884eed72
```
<br></br>
*2.2 samples로 생성한 SCORE 배포하기*

`tbears samples` 명령을 통해 샘플을 생성합니다.
```
tbears samples
```
출력되는 메시지를 통해 샘플이 생성됨을 확인할 수 있습니다.
```
Made samples successfully
```

생성된 standard_token 디렉토리로 이동하여 설정파일을 생성합니다.
```
cd standard_token
tbears genconf (명령어 실행 후 생성된 설정 파일 중 tbears_cli_config.json을 수정합니다.)
```

수정한 `tbears_cli_config.json`의 내용은 다음과 같습니다.
```
{
    "uri": "http://127.0.0.1:9000/api/v3",
    "nid": "0x3",
    "keyStore": null,
    "from": "hxaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
    "to": "cx0000000000000000000000000000000000000000",
    "stepLimit": "0x300000",
    "deploy": {
        "contentType": "tbears",
        "mode": "install",
        "scoreParams": {"initialSupply":"100", "decimals" : "10"}
    },
    "txresult": {},
    "transfer": {}
}
```
> "scoreParams" 에 들어가는 파라미터의 경우 SCORE파일의 on_install 메소드의 파라미터를 통해 확인할 수 있습니다.

생성된 프로젝트를 T-Bears 서비스에 배포합니다.('-c' 옵션을 통해 수정한 설정파일을 적용합니다.)
```
tbears deploy -c ./tbears_cli_config.json .
```
출력:
```
Send deploy request successfully.
If you want to check SCORE deployed successfully, execute txresult command
transaction hash: 0x7047a50fcad1cffb3390d161cbbd34915dab0ef0f7159b10683ae32159f8fbde
```
---
### *3. 배포한 SCORE의 메소드 호출하기*

*3.1 init을 통해 생성한 SCORE의 'hello' 메소드 호출*

`tbears call`의 필수 요소인 json파일(call.json)을 다음과 같이 작성합니다
```
{
  "jsonrpc": "2.0",
  "method": "icx_call",
  "params": {
    "from": "hxef73db5d0ad02eb1fadb37d0041be96bfa56d4e6",
    "to": "cx60b12af828d4caf960cfbf031f2027093dca2c08",
    "dataType": "call",
    "data": {
      "method": "hello"
    }
  },
  "id": 1
}
```
> "method" 에 들어갈 메소드명은 SCORE파일의 `@external`을 통해 확인할 수 있습니다.

SCORE의 `hello` 메소드를 호출합니다.
```
tbears call call.json
```
출력
```
response : {
        "jsonrpc": "2.0",
        "result": "hello",
        "id": 1
    }
```
<br></br>
*3.2 samples를 통해 생성한 SCORE("standard_token.py")의 'name' 메소드 호출*

`tbears call`의 필수 요소인 json파일(call.json)을 다음과 같이 작성합니다
```
{
  "jsonrpc": "2.0",
  "method": "icx_call",
  "params": {
    "from": "hxef73db5d0ad02eb1fadb37d0041be96bfa56d4e6",
    "to": "cx02b13428a8aef265fbaeeb37394d3ae8727f7a19",
    "dataType": "call",
    "data": {
      "method": "name"
      ----------------------------------------------------------
      // 입력해야할 parameter가 있을 경우
      "params":{ 
          "address":"hx1f9a3310f60a03934b917509c86442db703cbd52"
          }        
      ----------------------------------------------------------
    }
  },
  "id": 1
}
```
SCORE의 `name` 메소드를 호출합니다.

```
tbears call call.json
```
출력
```
response : {
    "jsonrpc": "2.0",
    "result": "SampleToken",
    "id": 1
}
```
---
### *4. 트랜잭션*

*4.1 트랜잭션 요청*

`tbears sendtx`의 필수 요소인 json파일(send.json)을 다음과 같이 작성합니다
```
{
  "jsonrpc": "2.0",
  "method": "icx_sendTransaction",
  "params": {
    "version": "0x3",
    "from": "hxe7af5fcfd8dfc67530a01a0e403882687528dfcb",
    "value": "0x0",
    "stepLimit": "0x3000000",
    "timestamp": "0x573117f1d6568",
    "nid": "0x3",
    "nonce": "0x1",
    "to": "hxaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
    "dataType": "call",
    "data": {
      "method": "setValue",
      "params": {
        "value": "0x123"
      }
    }
  },
  "id": 1
}
```
트랜잭션 서명에 필요한 keystore 파일을 생성합니다.
```
tbears keystore key
```
출력
```
input your keystore password:
Made keystore file successfully
```
트랜잭션을 요청합니다.('-k' 옵션을 통해 생성한 keystore 파일을 적용합니다.)

```
tbears sendtx -k key send.json
```
출력
```
input your keystore password:
Send transaction request successfully.
transaction hash: 0xd8801c6b0f8c63642859fe3dbd2d4ee030149d76258abc1764335b397cc073e3
```
<br></br>
*4.2 트랜잭션 결과 확인*

`txresult`를 통해 요청한 트랜잭션의 결과를 확인합니다.
```
tbears txresult 0xd8801c6b0f8c63642859fe3dbd2d4ee030149d76258abc1764335b397cc073e3
```
출력 
```
Transaction result: {
    "jsonrpc": "2.0",
    "result": {
        "txHash": "0xd8801c6b0f8c63642859fe3dbd2d4ee030149d76258abc1764335b397cc073e3",
        "blockHeight": "0x12e",
        "blockHash": "0x291d3f995475811402e2db9cf44f577d2571ef0d2c225d49829f522f6d0ef28b",
        "txIndex": "0x0",
        "to": "hxaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
        "stepUsed": "0xf4a10",
        "stepPrice": "0x0",
        "cumulativeStepUsed": "0xf4a10",
        "eventLogs": [],
        "logsBloom": "0x00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
        "status": "0x1"
    },
    "id": 1
}
```
---
### *5. ICX*

*5.1 ICX 보내기*

먼저 `cat key`를 실행하여 address를 확인한다
```
{"address": "hx62d878f8c21fbbd61fcbb5f5a1a6ff3c86996ffb", "crypto": {"cipher": "aes-128-ctr", "cipherparams": {"iv": "bda6f1ff9f0bebf5fa6f0d8e435cc5a0"}, "ciphertext": "a4b6ae5e6a8a478214bbd13300983167b1500d5bff80e14b6d173407ba20f392", "kdf": "scrypt", "kdfparams": {"dklen": 32, "n": 16384, "r": 1, "p": 8, "salt": "0d85bcf3cf41f6114626a4cbbb202838"}, "mac": "8eaccb70cbaa74cce0f40b9ef38e2e7c0969f0b8a285bf5d28be901c1b482e7e"}, "id": "cc69678b-21df-4cae-91b4-1cc71fd149fa", "version": 3, "coinType": "icx"}%
```
제네시스 블록에서 key에서 확인한 주소로 1 icx를 보낸다
```
tbears transfer -f hx0000000000000000000000000000000000000000 hx62d878f8c21fbbd61fcbb5f5a1a6ff3c86996ffb 1e18
```
출력
```
Send transfer request successfully.
transaction hash: 0x33f4c69eabf45ab62f216e7498e01b3c684324543fa92b8b9a3c288d340813c8
```
<br></br>
*5.2 ICX 잔고 확인하기* 

`tbears balance`를 통해 해당 주소의 잔고를 확인할 수 있다.
```
tbears balance hx62d878f8c21fbbd61fcbb5f5a1a6ff3c86996ffb
```
출력

```
balance in hex: 0xde0b6b3a7640000
balance in decimal: 1000000000000000000
```
---
### *6. 명령어 정리*
*6.1 server 명령어*

`tbears start` : T-Bears 서비스를 시작합니다. 
```
tbears start [-h] [-a ADDRESS] [-p PORT] [-c CONFIG]

examples : tbears start            // 모든 옵션을 디폴트로 설정
```
옵션 :

>-h, --help : 화면에 명령어에 대한 도움말을 출력합니다.

>-a HOSTADDRESS, --address HOSTADDRESS : 디폴트 값으로 `127.0.0.1` 을 가지며, T-Bears 서비스를 host하는 IP주소를 의미합니다.

>-p PORT, --port PORT : 디폴트 값으로 ```9000``` 을 가지며, T-Bears 서비스를 host하는 포트번호를 의미합니다.

>-c CONFIG, --config CONFIG : 디폴트 값으로 `./tbears_server_config.json` 를 가지며, 서비스 시작시 사용할 설정 파일의 위치를 의미합니다.

`tbears stop` : 가동 중인 모든 SCORE와 T-Bears 서비스를 중단합니다.
```
tbears stop [-h]

examples : tbears stop
```
옵션 :

>-h, --help : 화면에 명령어에 대한 도움말을 출력합니다.

`tbears clear` : T-Bears 서비스에 배포된 모든 SCORE를 제거합니다. T-Bears 서비스가 중단된 상태에서만 실행 가능합니다.
```
tbears clear [-h]
```
옵션 :

>-h, --help : 화면에 명령어에 대한 도움말을 출력합니다.

<br></br>
*6.2 utility 명령어*

`tbears keystore` : keystore 파일을 해당 path에 생성합니다. secp256k1 라이브러리를 사용하여 개인키와 공개키를 생성합니다.
```
tbears keystore [-h] [-p PASSWORD] path

examples : 
1) tbears keystore key         // 현재 디렉토리에 key라는 파일명으로 생성

2) mkdir keys                  // keys 디렉토리 생성
   tbears keys ./keys/key      // keys 디렉토리에 key라는 파일명으로 생성

3) tbears keystore -p passw0rd~! key // 현재 디렉토리에 비밀번호(passw0rd~!)를 설정하여 key라는 파일명으로 생성
``` 
필수 요소:
>path : keystore 파일을 해당 path(ex : ./파일명)에 생성합니다.

옵션 :

>-h, --help : 화면에 명령어에 대한 도움말을 출력합니다.

>-p PASSWORD : keystore의 비밀번호를 설정합니다.
```
expected result : 
1), 2)
    input your keystore password: (비밀번호 설정)

    Made keystore file successfully

3) 
    Made keystore file successfully
```


`tbears genconf` : T-Bears의 설정 파일들을 생성합니다. (tbears_cli_config.json, tbears_cli_config.json)
```
tbears genconf [-h]

examples : tbears genconf
```
옵션 :

>-h, --help : 화면에 명령어에 대한 도움말을 출력합니다.
```
expected result :
    Made tbears_cli_config.json, tbears_server_config.json successfully
```

<br></br>

*6.3 SCORE 명령어*

`tbears init` : SCORE 프로젝트를 생성합니다. [project].py 와 package.json 파일을 [project] 디렉토리 안에 생성합니다. SCORE class의 클래스명은 [socreClass]를 따라 생성됩니다.
`tbears_server_config.json` 파일과 `tbears_cli_config.json` 이 없을 시 현재 디렉토리에 함께 생성됩니다.
```
tbears init [-h] project scoreClass

examples : 
1) tbears init myproject ABCToken
2) ls myproject                      // myproject 디렉토리 생성 및 파일 확인
3) cat ./myproject/myproject.py      // myproject.py 의 클래스명 확인
```
필수 요소 :
>project : 프로젝트 디렉토리의 위치 또는 zip 파일의 위치를 의미합니다.

>scoreClass : 

옵션 :

>-h, --help : 화면에 명령어에 대한 도움말을 출력합니다.

```
expected result :
1) 
    Initialized tbears successfully

2)  
    __init__.py  myproject.py package.json tests 

3) 
    class ABCToken(IconScoreBase):           // class명 ABCToken으로 생성됨.

        def __init__(self, db: IconScoreDatabase) -> None:
            super().__init__(db)

        def on_install(self) -> None:
            super().on_install()

        def on_update(self) -> None:
            super().on_update()

        @external(readonly=True)
        def hello(self) -> str:
            print(f'Hello, world!')
            return "Hello"
```
설명:

>`__init__.py` : 프로젝트 디렉토리가 python package로 인식되도록 만드는 파일입니다.

>`package.json` : SCORE가 load 되었을 때 필요한 정보(main_file, main_class)를 가지고 있습니다.

>`myproject.py` : SCORE의 메인 파일입니다. ABCToken 클래스가 선언되어 있습니다.

`tbears samples` : 두개의 SCORE 샘플인 "standard_crowd_sale" 와 "standard_token"를 생성합니다.
```
tbears samples [-h]

examples : 
1) tbears samples           // SCORE 샘플 생성
2) ls standard*             // 생성 결과 확인
```
옵션 :

>-h, --help : 화면에 명령어에 대한 도움말을 출력합니다.

```
expected result: 
    1) 
        Made samples successfully

    2) 
        standard_crowd_sale:
            __init__.py            package.json           standard_crowd_sale.py tests

        standard_token:
            __init__.py       package.json      standard_token.py tests
```

`tbears deploy` : SCORE를 배포합니다. 로컬 T-Bears 서비스 또는 ICON 네트워크에 배포할 수 있습니다.

```
tbears deploy [-h] [-u URI] [-t {tbears,zip}] [-m {install,update}] [-f FROM] [-o TO] [-k KEYSTORE] [-n NID] [-c CONFIG] [-p PASSWORD] project

examples : tbears deploy myproject

```
필수 요소 :

>project : 프로젝트 디렉토리의 위치 또는 zip파일의 위치를 의미합니다.

옵션 :

>-h, --help : 화면에 명령어에 대한 도움말을 출력합니다.

>-u URI, --node-uri URI : 디폴트 값으로 "http://127.0.0.1:9000/api/v3" 를 가지며, 노드의 URI를 의미합니다.

>-t {tbears,zip}, --type {tbears,zip} : 디폴트 값으로 "tbears" 를 가지며, 배포하는 SCORE의 타입을 의미합니다.

>-m {install,update}, --mode {install,update} : 디폴트 값으로 "install" 을 가지며, 배포 모드를 의미합니다.

>-f FROM, --from FROM : SCORE 소유자의 주소를 의미합니다. (예 : SCORE owner address)

>-o TO, --to TO : 보내는 주소를 의미합니다. (예 : SCORE address)

>-k KEYSTORE, --key-store KEYSTORE : Keystore 파일 위치를 의미하며, "from" 주소와 트랜잭션 사인을 생성하는데 사용됩니다.

>-n NID, --nid NID : 네트워크 아이디를 의미합니다.

>-c CONFIG, --config CONFIG : 디폴트 값으로 "./tbears_cli_config.json"을 가지며, 배포 설정파일을 의미합니다.

>-p PASSWORD, --password PASSWORD : keystore 파일의 비밀번호를 의미합니다.

```
expected result :
    Send deploy request successfully.
    If you want to check SCORE deployed successfully, execute txresult command
    transaction hash: 0x43af98a464cf78d3cd681512926090e6c56e5bc0abe270851f9b6d6610e09d62
```

`tbears sendtx` : json_file을 input으로 받아 icx_sendTransaction을 요청합니다.
(send.json 파일을 생성하여 input으로 입력하였습니다.)
```
tbears sendtx [-h] [-u URI] [-k KEYSTORE] [-c CONFIG] json_file

examples : 
cat send.json
{
  "jsonrpc": "2.0",
  "method": "icx_sendTransaction",
  "params": {
    "version": "0x3",
    "from": "hxef73db5d0ad02eb1fadb37d0041be96bfa56d4e6",
    "value": "0x0",
    "stepLimit": "0x3000000",
    "timestamp": "0x573117f1d6568",
    "nid": "0x3",
    "nonce": "0x1",
    "to": "hxaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
    "dataType": "call",
    "data": {
      "method": "setValue",
      "params": {
        "value": "0x123"
      }
    }
  },
  "id": 1
}

tbears sendtx -k key send.json
```
필수 요소 :

>json_file : icx_transaction을 위한 request object를 포함하는 json 파일의 위치를 의미합니다.

옵션 :

>-h, --help : 화면에 명령어에 대한 도움말을 출력합니다.

>--u URI, --node-uri URI : 디폴트 값으로 "http://127.0.0.1:9000/api/v3" 를 가지며, 노드의 URI를 의미합니다.
  
>-k KEYSTORE, --key-store KEYSTORE : Keystore 파일 위치를 의미하며, "from" 주소와 트랜잭션 사인을 생성하는데 사용됩니다.
  
>-c CONFIG, --config CONFIG : 디폴트 값으로 "./tbears_cli_config.json"을 가지며, "keyStore", "uri" 와 "from"의 값을 정의하는 파일을 의미합니다.

`tbears call` : json 파일을 입력받아 icx_call을 요청합니다.
```
tbears call [-h] [-u URI] [-c CONFIG] json_file

examples : 
    cat call.json
    {
    "jsonrpc": "2.0",
    "method": "icx_call",
    "params": {
        "from": "hxef73db5d0ad02eb1fadb37d0041be96bfa56d4e6",
        "to": "cx53d5080a7d8a805bb10eb9bc64637809dc910832",
        "dataType": "call",
        "data": {
        "method": "hello"
        }
    },
    "id": 1
    }

    tbears call call.json
```
필수 요소 : 

> json_file : icx_call을 위한 request object를 포함하는 json 파일의 위치를 의미합니다.

옵션 :

>-h, --help : 화면에 명령어에 대한 도움말을 출력합니다.

>--u URI, --node-uri URI : 디폴트 값으로 "http://127.0.0.1:9000/api/v3" 를 가지며, 노드의 URI를 의미합니다.

>-c CONFIG, --config CONFIG : 디폴트 값으로 "./tbears_cli_config.json"을 가지며, "keyStore", "uri" 와 "from"의 값을 정의하는 파일을 의미합니다.
```
expected result :
    response : {
        "jsonrpc": "2.0",
        "result": "hello",
        "id": 1
    }
```


`tbears scoreapi` : 입력받은 주소의 SCORE가 제공하는 API의 리스트를 출력합니다.
자세한 설명은 [ICON JSON-RPC API v3](https://github.com/icon-project/icon-rpc-server/blob/develop/docs/icon-json-rpc-v3.md#icx_getscoreapi) 의 icx_getScoreApi를 통해 확인할 수 있습니다.

```
tbears scoreapi [-h] [-u URI] [-c CONFIG] address

examples :
    tbears scoreapi cx0123456789abcdef0123456789abcdefabcdef12
```
필수 요소 : 

> address : API를 요청할 SCORE의 주소를 의미하며 'cx' 로 시작합니다.

옵션 :

>-h, --help : 화면에 명령어에 대한 도움말을 출력합니다.

>--u URI, --node-uri URI : 디폴트 값으로 "http://127.0.0.1:9000/api/v3" 를 가지며, 노드의 URI를 의미합니다.

>-c CONFIG, --config CONFIG : 디폴트 값으로 "./tbears_cli_config.json"을 가지며, "uri"의 값을 정의하는 파일을 의미합니다.

```
expected result :
    scoreAPI: [
        {
            "type": "fallback",
            "name": "fallback",
            "inputs": []
        },
        {
            "type": "function",
            "name": "hello",
            "inputs": [],
            "outputs": [
                {
                    "type": "str"
                }
            ],
            "readonly": "0x1"
        }
    ]
```
<br></br>

*6.4 ICX, 트랜잭션, 블록과 관련된 명렁어*

`tbears transfer` : ICX 코인을 전송합니다.
```
tbears transfer [-h] [-f FROM] [-k KEYSTORE] [-n NID] [-u URI] [-c CONFIG] to value

examples :
    tbears transfer -f hx0000000000000000000000000000000000000000 hx62d878f8c21fbbd61fcbb5f5a1a6ff3c86996ffb 1e18
```
필수 요소 : 

>to : icx를 받을 주소를 의미합니다.

>value : 'to' 주소로 보내지는 icx의 Amount를 의미합니다.

옵션 :

>-h, --help : 화면에 명령어에 대한 도움말을 출력합니다.

>-f FROM, --from FROM : icx를 보내는 주소를 의미합니다.

>-k KEYSTORE, --key-store KEYSTORE : Sender의 keystore file을 의미합니다.

>-n NID, --nid NID : 디폴트 값으로 "0x3"을 가지며, 네트워크 ID를 의미합니다.

>--u URI, --node-uri URI : 디폴트 값으로 "http://127.0.0.1:9000/api/v3" 를 가지며, 노드의 URI를 의미합니다.

>-c CONFIG, --config CONFIG : 디폴트 값으로 "./tbears_cli_config.json"을 가지며, "keyStore", "uri" 와 "from"의 값을 정의하는 파일을 의미합니다.
```
expected result :
    Send transfer request successfully.
    transaction hash:        
    0x8849b8f601d4a12654d0af3236b1fb9f976edc6e57fda2c5cc8f0dc52ebbfcfb
```

`tbears balance` : 해당 address의 ICX 잔고를 나타냅니다.
```
tbears balance [-h] [-u URI] [-c CONFIG] address

examples :
    tbears balance hx0000000000000000000000000000000000000000
```
필수 요소 :

>address : ICX 잔고를 확인할 주소를 의미합니다.

옵션 :

>-h, --help : 화면에 명령어에 대한 도움말을 출력합니다.

>--u URI, --node-uri URI : 디폴트 값으로 "http://127.0.0.1:9000/api/v3" 를 가지며, 노드의 URI를 의미합니다.

>-c CONFIG, --config CONFIG : 디폴트 값으로 "./tbears_cli_config.json"을 가지며, "uri"의 값을 정의하는 파일을 의미합니다.
```
expected result :
    balance in hex: 0x2961fff8ca4a62327800000
    balance in decimal: 800460000000000000000000000
```

`tbears totalsupply` : ICX의 총 공급량을 나타냅니다.
```
tbears totalsupply [-h] [-u URI] [-c CONFIG]

examples :
    tbears totalsupply
```
옵션 :

>-h, --help : 화면에 명령어에 대한 도움말을 출력합니다.

>--u URI, --node-uri URI : 디폴트 값으로 "http://127.0.0.1:9000/api/v3" 를 가지며, 노드의 URI를 의미합니다.

>-c CONFIG, --config CONFIG : 디폴트 값으로 "./tbears_cli_config.json"을 가지며, "uri"의 값을 정의하는 파일을 의미합니다.
```
expected result :
    Total supply of ICX in hex: 0x2961fff8ca4a62327800000
    Total supply of ICX in decimal: 800460000000000000000000000
```

`tbears txresult` : 트랜잭션 해시값을 통해 트랜잭션의 결과를 출력합니다.
```
tbears txresult [-h] [-u URI] [-c CONFIG] hash

examples :
    tbears txresult 0x227fb3e6fdc89de8d24e019b1ddc88538633c4202102297da204444d393249c2
```
필수 요소 :

>hash : 요청하는 트랜잭션의 hash값을 의미합니다.

옵션 :

>-h, --help : 화면에 명령어에 대한 도움말을 출력합니다.

>--u URI, --node-uri URI : 디폴트 값으로 "http://127.0.0.1:9000/api/v3" 를 가지며, 노드의 URI를 의미합니다.

>-c CONFIG, --config CONFIG : 디폴트 값으로 "./tbears_cli_config.json"을 가지며, "uri"의 값을 정의하는 파일을 의미합니다. 

```
expected result :
    Transaction result: {
        "jsonrpc": "2.0",
        "result": {
            "txHash": "0x227fb3e6fdc89de8d24e019b1ddc88538633c4202102297da204444d393249c2",
            "blockHeight": "0x2",
            "blockHash": "28e6e4710c56e053920b95df0058317a4ac641b16d17d64db7f958e8a5650391",
            "txIndex": "0x0",
            "to": "cx0000000000000000000000000000000000000000",
            "scoreAddress": "cx6bd390bd855f086e3e9d525b46bfe24511431532",
            "stepUsed": "0xe2a4",
            "stepPrice": "0x0",
            "cumulativeStepUsed": "0xe2a4",
            "eventLogs": [],
            "logsBloom": "0x00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
            "status": "0x1"
        },
        "id": 1
    }
```

`tbears txbyhash` : 트랜잭션 해시값을 통해 해당 트랜잭션을 출력합니다.
```
tbears txbyhash [-h] [-u URI] [-c CONFIG] hash

examples :
    tbears txbyhash 0x95be9f0247bc3b7ed07fe07c53613c580642ef991c574c85db45dbac9e8366df

```
필수 요소 :

>hash : 요청하는 트랜잭션의 hash값을 의미합니다.

옵션 :

>-h, --help : 화면에 명령어에 대한 도움말을 출력합니다.

>--u URI, --node-uri URI : 디폴트 값으로 "http://127.0.0.1:9000/api/v3" 를 가지며, 노드의 URI를 의미합니다.

>-c CONFIG, --config CONFIG : 디폴트 값으로 "./tbears_cli_config.json"을 가지며, "uri"의 값을 정의하는 파일을 의미합니다. 
```
expected result :
    Transaction: {
        "jsonrpc": "2.0",
        "result": {
            "version": "0x3",
            "from": "hxaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
            "value": "0x0",
            "stepLimit": "0x3000000",
            "timestamp": "0x572e8fd95db26",
            "nid": "0x3",
            "nonce": "0x1",
            "to": "cx0000000000000000000000000000000000000000",
            "data": {
                "contentType": "application/zip",
                "content": "0x32b34cfa39993fa093e",
                "params": {}
            },
            "dataType": "deploy",
            "signature": "sig",
            "txIndex": "0x0",
            "blockHeight": "0x2",
            "blockHash": "0x28e6e4710c56e053920b95df0058317a4ac641b16d17d64db7f958e8a5650391"
        },
        "id": 1
    }
```

`tbears lastblock` : 마지막 블록의 정보를 나타냅니다.
```
tbears lastblock [-h] [-u URI] [-c CONFIG]

examples : 
    tbears lastblock 
```
옵션 :

>-h, --help : 화면에 명령어에 대한 도움말을 출력합니다.

>--u URI, --node-uri URI : 디폴트 값으로 "http://127.0.0.1:9000/api/v3" 를 가지며, 노드의 URI를 의미합니다.

>-c CONFIG, --config CONFIG : 디폴트 값으로 "./tbears_cli_config.json"을 가지며, "uri"의 값을 정의하는 파일을 의미합니다. 
```
expected result :
    block info : {
        "jsonrpc": "2.0",
        "result": {
            "version": "tbears",
            "prev_block_hash": "815c0fd7a0dd4594bb19ee39030c1bd91c200878f1f456fe8dd7ff4e0a19b839",
            "merkle_tree_root_hash": "tbears_does_not_support_merkel_tree",
            "time_stamp": 1533719896011654,
            "confirmed_transaction_list": [
                {
                    "version": "0x3",
                    "from": "hxaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                    "value": "0x0",
                    "stepLimit": "0x3000000",
                    "timestamp": "0x572e8fd95db26",
                    "nid": "0x3",
                    "nonce": "0x1",
                    "to": "cx0000000000000000000000000000000000000000",
                    "data": {
                        "contentType": "application/zip",
                        "content": "0x32b34cfa39993fa093e",
                        "params": {}
                    },
                    "dataType": "deploy",
                    "signature": "sig"
                }
            ],
            "block_hash": "28e6e4710c56e053920b95df0058317a4ac641b16d17d64db7f958e8a5650391",
            "height": 2,
            "peer_id": "fb5f43dc-9aeb-11e8-a31b-acde48001122",
            "signature": "tbears_does_not_support_signature"
        },
        "id": 1
    }
```

`tbears blockbyheight` : 주어진 blockheight를 통해 해당 블록의 정보를 나타냅니다.
```
tbears blockbyheight [-h] [-u URI] [-c CONFIG] height

examples :
    tbears blockbyheight 0x1
```
필수 요소 :

> height : 요청하는 블록의 height 값을 의미합니다

옵션 :

>-h, --help : 화면에 명령어에 대한 도움말을 출력합니다.

>--u URI, --node-uri URI : 디폴트 값으로 "http://127.0.0.1:9000/api/v3" 를 가지며, 노드의 URI를 의미합니다.

>-c CONFIG, --config CONFIG : 디폴트 값으로 "./tbears_cli_config.json"을 가지며, "uri"의 값을 정의하는 파일을 의미합니다. 
```
expected result :
    block info : {
        "jsonrpc": "2.0",
        "result": {
            "version": "tbears",
            "prev_block_hash": "859083707985809a8b52982b9d8d86bfe48c0020a478b3a99d7eeb3c74c38e7c",
            "merkle_tree_root_hash": "tbears_does_not_support_merkel_tree",
            "time_stamp": 1533719753948440,
            "confirmed_transaction_list": [
                {
                    "version": "0x3",
                    "from": "hxef73db5d0ad02eb1fadb37d0041be96bfa56d4e6",
                    "value": "0x8ac7230489e80000",
                    "stepLimit": "0x2000",
                    "timestamp": "0x572e8f51e4481",
                    "nid": "0x3",
                    "nonce": "0x1",
                    "to": "hxbbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
                    "signature": "f2B3r27u7peL3I9uBnKA8yn82odqlMECU+UBkRiZTJIwWFo57AmlUjKhoK8OZBBRdaWWmLF+JTZNs70yF8+zIwA="
                }
            ],
            "block_hash": "815c0fd7a0dd4594bb19ee39030c1bd91c200878f1f456fe8dd7ff4e0a19b839",
            "height": 1,
            "peer_id": "a6b22354-9aeb-11e8-a0ae-acde48001122",
            "signature": "tbears_does_not_support_signature"
        },
        "id": 1
    }
```

`tbears blockbyhash` : 블록해시값을 통해 해당 블록의 정보를 나타냅니다.
```
tbears blockbyhash [-h] [-u URI] [-c CONFIG] hash

examples :
    tbears blockbyhash 0xce00facd0ac3832e1e6e623d8f4b9344782da881e55abb48d1494fde9e465f78

```
필수 요소 :

> hash : 요청하는 블록의 hash 값을 의미합니다

옵션 :

>-h, --help : 화면에 명령어에 대한 도움말을 출력합니다.

>--u URI, --node-uri URI : 디폴트 값으로 "http://127.0.0.1:9000/api/v3" 를 가지며, 노드의 URI를 의미합니다.

>-c CONFIG, --config CONFIG : 디폴트 값으로 "./tbears_cli_config.json"을 가지며, "uri"의 값을 정의하는 파일을 의미합니다. 
```
expected result :
    block info : {
        "jsonrpc": "2.0",
        "result": {
            "version": "tbears",
            "prev_block_hash": "859083707985809a8b52982b9d8d86bfe48c0020a478b3a99d7eeb3c74c38e7c",
            "merkle_tree_root_hash": "tbears_does_not_support_merkel_tree",
            "time_stamp": 1533719753948440,
            "confirmed_transaction_list": [
                {
                    "version": "0x3",
                    "from": "hxef73db5d0ad02eb1fadb37d0041be96bfa56d4e6",
                    "value": "0x8ac7230489e80000",
                    "stepLimit": "0x2000",
                    "timestamp": "0x572e8f51e4481",
                    "nid": "0x3",
                    "nonce": "0x1",
                    "to": "hxbbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
                    "signature": "f2B3r27u7peL3I9uBnKA8yn82odqlMECU+UBkRiZTJIwWFo57AmlUjKhoK8OZBBRdaWWmLF+JTZNs70yF8+zIwA="
                }
            ],
            "block_hash": "815c0fd7a0dd4594bb19ee39030c1bd91c200878f1f456fe8dd7ff4e0a19b839",
            "height": 1,
            "peer_id": "a6b22354-9aeb-11e8-a0ae-acde48001122",
            "signature": "tbears_does_not_support_signature"
        },
        "id": 1
    }
```

`tbears console` : IPython을 사용하여 T-Bears 서비스의 interactive 모드로 진입합니다. 앞서 설명한 명령어들을 tbears를 생략하고 실행할 수 있게 되며 몇가지 추가적인 기능들을 제공합니다. 자세한 내용은 [https://github.com/icon-project/t-bears](https://github.com/icon-project/t-bears)에서 확인할 수 있다.

<br></br>
*6.5 설정 파일*

`tbears_server_config.json` : T-Bears 서비스를 시작할때 초기 설정과 파라미터를 "tbears_server_config.json" 통해 설정합니다.
```ls
examples : 
    {
        "hostAddress": "0.0.0.0",
        "port": 9000,
        "scoreRootPath": "./.score",
        "stateDbRootPath": "./.statedb",
        "log": {
            "logger": "tbears",
            "level": "info",
            "filePath": "./tbears.log",
            "colorLog": true,
            "outputType": "console|file",
            "rotate": {
                "type": "bytes",
                "maxBytes": 10485760,
                "backupCount": 10
            }
        },
        "service": {
            "fee": false,
            "audit": false,
            "deployerWhiteList": false
        },
        "genesis": {
            "nid": "0x3",
            "accounts": [
                {
                    "name": "genesis",
                    "address": "hx0000000000000000000000000000000000000000",
                    "balance": "0x2961fff8ca4a62327800000"
                },
                {
                    "name": "fee_treasury",
                    "address": "hx1000000000000000000000000000000000000000",
                    "balance": "0x0"
                }
            ]
        },
        "channel": "loopchain_default",
        "amqpKey": "7100",
        "amqpTarget": "127.0.0.1",
        "blockConfirmInterval": 10,
        "blockConfirmEmpty": true
    }
```

`tbears_cli_config.json` : start, stop, samples, clear, init, keystore를 제외한 모든 T-Bears CLI 명령어를 수행할 때 디폴트 파라미터와 초기 설정을 위해 사용된다.
```
examples : 
    {
        "uri": "http://127.0.0.1:9000/api/v3",
        "nid": "0x3",
        "keyStore": null,
        "from": "hxaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
        "to": "cx0000000000000000000000000000000000000000",
        "stepLimit": "0x3000000",
        "deploy": {
            "mode": "install",
            "scoreParams": {}
        },
        "txresult": {},
        "transfer": {}
    }
```

---
# 3. SDK 개발환경 구축하기
현재 아이콘의 개발자 배포용 SDK는 파이썬, 자바 두 가지 종류가 있습니다. 개발자들을 위해서 Git을 통해 배포되고 있으며, 각각 언어들을 활용해 개발자들은 지갑을 만들거나, Dapp을 만들어볼 수 있습니다. 

## 파이썬 개발 환경 구축하기

작업환경

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

작업환경

---------------------

	OS X
	
	Java(TM) SE Runtime Environment 18.3
	
	Eclipse Version: 2.2.200.v20180611-0500
	
	Maven 
	OR
	Gradle
	
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
* 만들어진 Maven 패키지 안의 POM.xml 파일에 아래와 같이 삽입합니다. 

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

 ``` <dependencies> ``` 부터 ``` </dependencies> ``` 까지 내용을 붙여 넣습니다. 
 
* 본 Sample Code에서는 
logging-interceptor를 활용한 Log를 위하여 dependencies에 logging-interceptor도 추가하였습니다.(옵션입니다. )

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

위와 같이 dependency 설정을 마치면,  refreash를 해 주어야 원격 아카이브에서 jar 파일을 다운받습니다. 

```[생성된 패키지 우클릭 --> Maven --> UpdateProject]```

#### Maven dependency 적용 확인


정상적으로 다운로드가 완료 되면, 프로젝트 내에 있는 
```[Maven Dependencies]```에서 
```icon-sdk-버전.jar``` 
```logging-interceptor-버전.jar``` 가 다운로드 되어있는것을 확인 할 수 있습니다. 


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

위와 같이 dependency 설정을 마치면,  Refreash를 해 주어야 원격 아카이브에서 jar 파일을 다운받습니다. 

```[생성된 패키지 우클릭 --> Gradle --> Refreash Gradle Project]```

#### Gradle dependency 적용 확인

정상적으로 다운로드가 완료 되면, 프로젝트 내에 있는 
```[Project and External Dependencies]```에서 
```icon-sdk-버전.jar```  가 다운로드 되어있는것을 확인 할 수 있습니다. 



<br></br>


# 4. SDK 활용하기
현재 아이콘의 개발자 배포용 SDK는 파이썬, 자바 두 가지 종류가 있습니다. 개발자들을 위해서 Git을 통해 배포되고 있으며, 각각 언어들을 활용해 개발자들은 지갑을 만들거나, 트랜젝션을 보내거나, 내 지갑의 토큰 balnce 를 조회할 수 있습니다.


## 지갑만들기
아이콘의 지갑은 여러 방법을 통하여 만들 수 있습니다. KeyWallet은 Keystore 파일을 통하여 백업할 수 있고, Keystore 파일을 통하여 다시 불러들일 수 있습니다. 가장 간단하게 아이콘의 지갑을 만나볼 수 있는 방법중 하나는 크롬의 [확장프로그램](<https://chrome.google.com/webstore/detail/iconex/flpiciilemghbmfalicajoolhkkenfel>)을 다운로드받아 사용하는 것입니다.

지갑에 관련된 작업은 크게 3가지로 분류됩니다.

#### wallet create (지갑생성)
>지갑을 임의로 생성.

#### wallet load (지갑로드)
> Private Key를 통하여 지갑을 로드.

> 비밀번호와 Keystore 파일을 통하여 로드.

#### wallet store (지갑저장)
> Keystore파일을 경로와 비밀번호를 지정하여 저장.

Keystore파일을 저장할 경우, 비밀번호를 통해서 Keystore파일 내의 정보를 암호화합니다. 따라서, Keystore파일을 불러올 경우에는 저장할 때 입력했던 비밀번호를 통해서 복호화를 통하여 불러오게 됨으로, 저장할 경우에 사용하였던 비밀번호가 필수적으로 필요합니다. 지갑을 만들경우 생성되는 개인키는 토큰 또는 ICX를 전송할 때 사용됨으로, 반드시 본인이 별도로 보안이 유지되는 곳에 기록해 두어야 합니다. 각 언어별 예제는 아래 순서대로 안내되어 있습니다.

1. 자바 SDK로 지갑 만들기
2. 파이썬 SDK로 지갑 만들기

<br></br>


**wallet create (지갑생성)**

 SDK에 내장된 함수를 통하여 지갑 인스턴스를 생성합니다.

* ##### 자바 SDK로 실행

	wallet = KeyWallet.create()
    create.getAddress();
     
    System.out.println("privateKey로 로드한 지갑 주소 : " +  
    load.getAddress());
    
출력

    hx4873b94352c8c1f3b2f09aaeccea31ce9e90bd31
    // 지갑 주소를 출력합니다
    
*위 출력값은 예시입니다. 실행하실 때 마다 출력값은 달라집니다.*

* ##### 파이썬 SDK로 실행

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



---



