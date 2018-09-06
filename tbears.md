
Icon Dev Guide
==============
### 1. 설치하기
### 2. JavaSDK 활용하여 응답 받아보기
### 3. PythonSDK 활용하여 응답 받아보기
### 4. SCORE 활용해 보기

T-bears는 아이콘-더 루프팀의 개발자용 CLI 로컬 테스트 개발툴입니다.
ICON은 JSON-RPC방식으로 통신합니다. Tbears는, 로컬에서 localhostJSON-RPC 응답을 지원하는 개발툴이다. 
현재, Git에 공개가 되어있으며, 사용을 원하는 개발자는 언제든 다운로드 받아 사용 가능하다. 
<https://github.com/icon-project/t-bears.git>

---
### 1. 설치하기(macOS)

* 공통

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
#### T-Bears 설치하기
```
1. Git의 코드를 다운로드하여 설치하는 방법
2. PyPI 통해 설치하기
```

#### 1. Git의 코드를 다운로드하여 설치하는 방법
* git 설치가 완료되면, tbears Git repository에 존재하는 코드를 `t-bears` 디렉토리로 clone 합니다.

```
git clone https://github.com/icon-project/t-bears.git t-bears
cd t-bears

virtualenv -p python3 venv  
source venv/bin/activate    
(venv) ./build.sh
(venv) pip install ./dist/tbears-1.0.4-py3-none-any.whl
```

#### 2. PyPI 통해 설치하기
* work directory 생성 -> tbears 설치
```
mkdir work
cd work

virtualenv -p python3 .
source ./bin/activate

(work) pip install tbears
```
---
### 2. JavaSDK 활용하여 응답 받아보기
---
### 3. PythonSDK 활용하여 응답 받아보기
---
### 4. T-Bears 활용하기 (1.0.4 version)

###  *4.1 서비스*

*4.1.1 서비스 시작*

```
tbears start
```
출력 :
```
Started tbears service successfully
```
*4.1.2 서비스 중단*

```
tbears stop          
```
출력 : 
```
Stopped tbears service successfully
```
*4.1.3 서비스에 배포된 SCORE 삭제*

```
tbears clear         
```
출력 :
```
Cleared SCORE deployed on tbears successfully
```

### *4.2 SCORE 배포하기*

*4.2.1 init으로 생성한 SCORE 프로젝트 배포하기*

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
출력 : 
```
Send deploy request successfully.
If you want to check SCORE deployed successfully, execute txresult command
transaction hash: 0x1468dd5deab399e309732728d37786eab8ad1f5a09ac9b85c92be0cc884eed72
```

*4.2.2 samples로 생성한 SCORE 배포하기*

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
### *4.3 배포한 SCORE의 메소드 호출하기*
*4.3.1 init을 통해 생성한 SCORE의 'hello' 메소드 호출*

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
SCORE의 `hello` 메소드를 호출합니다.
```
tbears call call.json
```
출력 :
```
response : {
        "jsonrpc": "2.0",
        "result": "hello",
        "id": 1
    }
```
*4.3.2 samples를 통해 생성한 SCORE("standard_token.py")의 'name' 메소드 호출*

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
출력 :
```
response : {
    "jsonrpc": "2.0",
    "result": "SampleToken",
    "id": 1
}
```

### *4.4 트랜잭션*
*4.4.1 트랜잭션 요청*

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
출력 :
```
input your keystore password:
Made keystore file successfully
```
트랜잭션을 요청합니다.('-k' 옵션을 통해 생성한 keystore 파일을 적용합니다.)

```
tbears sendtx -k key send.json
```
출력 :
```
input your keystore password:
Send transaction request successfully.
transaction hash: 0xd8801c6b0f8c63642859fe3dbd2d4ee030149d76258abc1764335b397cc073e3
```
*4.4.2 트랜잭션 결과 확인*

`txresult`를 통해 요청한 트랜잭션의 결과를 확인합니다.
```
tbears txresult 0xd8801c6b0f8c63642859fe3dbd2d4ee030149d76258abc1764335b397cc073e3
```
출력 : 
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

### *4.5 ICX*
*4.5.1 ICX 보내기*

먼저 `cat key`를 실행하여 address를 확인한다
```
{"address": "hx62d878f8c21fbbd61fcbb5f5a1a6ff3c86996ffb", "crypto": {"cipher": "aes-128-ctr", "cipherparams": {"iv": "bda6f1ff9f0bebf5fa6f0d8e435cc5a0"}, "ciphertext": "a4b6ae5e6a8a478214bbd13300983167b1500d5bff80e14b6d173407ba20f392", "kdf": "scrypt", "kdfparams": {"dklen": 32, "n": 16384, "r": 1, "p": 8, "salt": "0d85bcf3cf41f6114626a4cbbb202838"}, "mac": "8eaccb70cbaa74cce0f40b9ef38e2e7c0969f0b8a285bf5d28be901c1b482e7e"}, "id": "cc69678b-21df-4cae-91b4-1cc71fd149fa", "version": 3, "coinType": "icx"}%
```
제네시스 블록에서 key에서 확인한 주소로 1icx를 보낸다
```
tbears transfer -f hx0000000000000000000000000000000000000000 hx62d878f8c21fbbd61fcbb5f5a1a6ff3c86996ffb 1e18
```
출력 :
```
Send transfer request successfully.
transaction hash: 0x33f4c69eabf45ab62f216e7498e01b3c684324543fa92b8b9a3c288d340813c8
```

*4.5.2 ICX 잔고 확인하기* 

`tbears balance`를 통해 해당 주소의 잔고를 확인할 수 있다.
```
tbears balance hx62d878f8c21fbbd61fcbb5f5a1a6ff3c86996ffb
```
출력 :

```
balance in hex: 0xde0b6b3a7640000
balance in decimal: 1000000000000000000
```

---
* server 명령어

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
---
* utility 명령어

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

---

* SCORE 명령어

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
---

* ICX, 트랜잭션, 블록과 관련된 명렁어

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

---
* 설정 파일

`tbears_server_config.json` : T-Bears 서비스를 시작할때 초기 설정과 파라미터를 "tbears_server_config.json" 통해 설정합니다.
```
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



