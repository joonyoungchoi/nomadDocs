
tbears 사용해보기
==============
### 1. 설치하기
### 2. 지갑설정
### 3. JavaSDK 활용하여 응답 받아보기
### 4. PythonSDK 활용하여 응답 받아보기
### 5. SCORE 활용해 보기

T-bears는 아이콘-더 루프팀의 개발자용 CLI 로컬 테스트 개발툴입니다.
ICON은 JSON-RPC방식으로 통신합니다. Tbears는, 로컬에서 localhostJSON-RPC 응답을 지원하는 개발툴이다. 
현재, Git에 공개가 되어있으며, 사용을 원하는 개발자는 언제든 다운로드 받아 사용 가능하다. 
<https://github.com/icon-project/t-bears.git>

1. 설치하기(macOS)
파이썬 3 설치 추가 / pip용 과 깃 클론 따로 분리
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

* anaconda3를 설치합니다.
```
wget https://repo.anaconda.com/archive/Anaconda3-5.2.0-MacOSX-x86_64.sh
bash ./Anaconda3-5.2.0-MacOSX-x86_64.sh
```

#### T-Bears 설치 (git clone을 통한 설치)
* git 설치가 완료되면, tbears Git repository에 존재하는 코드를 `t-bears` 디렉토리로 clone 합니다.

```
git clone https://github.com/icon-project/t-bears.git t-bears
cd t-bears

virtualenv -p python3 venv  
source venv/bin/activate    
(venv) ./build.sh
(venv) pip install ./dist/tbears-1.0.4-py3-none-any.whl
```

#### T-Bears 설치 (pip install tbears를 통한 설치)
* work directory 생성 -> virtualenv 설치(python3 venv 대체 가능) -> tbears 설치
```
mkdir work
cd work

pip3 install virtualenv
virtualenv -p python3 .
source ./bin/activate

(work) pip install tbears
```


5. T-Bears 활용하기
---
* server 명령어

```tbears start``` : T-Bears 서비스를 시작합니다. 
```
tbears start [-h] [-a ADDRESS] [-p PORT] [-c CONFIG]

examples : tbears start            // 모든 옵션을 디폴트로 설정
```
옵션 :

>-h, --help : 화면에 명령어에 대한 도움말을 출력합니다.

>-a HOSTADDRESS, --address HOSTADDRESS : 디폴트 값으로 ```127.0.0.1``` 을 가지며, T-Bears 서비스를 host하는 IP주소를 의미합니다.

>-p PORT, --port PORT : 디폴트 값으로 ```9000``` 을 가지며, T-Bears 서비스를 host하는 포트번호를 의미합니다.

>-c CONFIG, --config CONFIG : 디폴트 값으로 ```./tbears_server_config.json``` 를 가지며, 서비스 시작시 사용할 설정 파일의 위치를 의미합니다.

```tbears stop``` : 가동 중인 모든 SCORE와 T-Bears 서비스를 중단합니다.
```
tbears stop [-h]

examples : tbears stop
```
옵션 :

>-h, --help : 화면에 명령어에 대한 도움말을 출력합니다.

```tbears clear``` : T-Bears 서비스에 배포된 모든 SCORE를 제거합니다. T-Bears 서비스가 중단된 상태에서만 실행 가능합니다.
```
tbears clear [-h]
```
옵션 :

>-h, --help : 화면에 명령어에 대한 도움말을 출력합니다.
---
* utility 명령어

```tbears keystore``` : keystore 파일을 해당 path에 생성합니다. secp256k1 라이브러리를 사용하여 개인키와 공개키를 생성합니다.
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


```tbears genconf``` : T-Bears의 설정 파일들을 생성합니다. (tbears_cli_config.json, tbears_cli_config.json)
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

```tbears init``` : SCORE 프로젝트를 생성합니다. [project].py 와 package.json 파일을 [project] 디렉토리 안에 생성합니다. SCORE class의 클래스명은 [socreClass]를 따라 생성됩니다.
```tbears_server_config.json``` 파일과 ```tbears_cli_config.json``` 이 없을 시 현재 디렉토리에 함께 생성됩니다.
```
tbears init [-h] project scoreClass

examples : 
1) tbears init myproject ABCToken
2) ls myproject                      // myproject 디렉토리 생성 및 파일 확인
3) cat ./myproject/myproject.py      // myproject.py 의 클래스명 확인
```
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
tbears deploy [-h] [-u URI] [-t {tbears,zip}] [-m {install,update}] [-f FROM] [-o TO] [-k KEYSTORE] [-n NID] [-c CONFIG] [-p PASSWORD]

examples : 

```
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

`tbears sendtx`

`tbears call`

`tbears scoreapi`

---

* ICX, 트랜잭션, 블록과 관련된 명렁어

`tbears transfer`

`tbears balance`

`tbears totalsupply`

`tbears txresult`

`tbears txbyhash`

`tbears lastblock`

`tbears blockbyheight`

`tbears blockbyhash`

`tbears console`

---
* 설정 파일

```tbears_server_config.json```

```tbears_cli_config.json```
