
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

1. 설치하기

```
examples
```
> 박스 안에 있는 examples를 따라 터미널에서 실행

* Home Brew(macOS용 패키지 관리자)를 설치합니다.

```
/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
```

* wget, git을 설치합니다.
```
brew install wget // wget 설치
brew install git // git 설치
```

* 설치가 완료되면, tbears Git repository에 존재하는 코드를 ```Documents/t-bears```위치에  clone 합니다.

```
mkdir ~/Documents/t-bears & git clone https://github.com/icon-project/t-bears.git ~/Documents/t-bears
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
* work directory 생성 -> virtualenv 설치(python3 venv 대체 가능) -> tbears 설치
```
mkdir work
cd work

pip3 install virtualenv
virtualenv -p python3 .
source ./bin/activate

(work) pip install tbears
```


5. Tbears 활용하기
---
* server 명령어

```tbears start``` : T-Bears 서비스를 시작합니다. 
```
tbears start [-h] [-a ADDRESS] [-p PORT] [-c CONFIG]

examples : tbears start // 모든 옵션을 디폴트로 설정
```
옵션 :

>-h, --help : 화면에 명령어에 대한 도움말을 출력합니다.

>-a ADDRESS, --address ADDRESS : 디폴트 값으로 ```127.0.0.1``` 을 가지며, T-Bears 서비스가 Listen하는 IP주소를 의미합니다.

>-p PORT, --port PORT : 디폴트 값으로 ```9000``` 을 가지며, T-Bears 서비스가 Listen하는 포트번호를 의미합니다.

>-c CONFIG, --config CONFIG : 디폴트 값으로 ```./tbears_server_config.json``` 를 가지며, 서비스 시작시 사용할 설정 파일의 위치를 의미합니다.

```tbears stop``` : 가동 중인 모든 SCORE와 T-Bears 서비스를 중단합니다.
```
tbears stop [-h]

examples : tbears stop
```
옵션 :

>-h, --help : 화면에 명령어에 대한 도움말을 출력합니다.

```tbears clear``` : T-Bears 서비스에 배포된 모든 SCORE를 제거합니다.
```
tbears clear [-h]
```
옵션 :

>-h, --help : 화면에 명령어에 대한 도움말을 출력합니다.
---
* utility 명령어

tbears keystore

tbears genconf

---

* SCORE 명령어

tbears init

tbears samples

tbears deploy

tbears sendtx

tbears call

tbears scoreapi

---

* ICX, 트랜잭션, 블록과 관련된 명렁어

tbears transfer

tbears balance

tbears totalsupply

tbears txresult

tbears txbyhash

tbears lastblock

tbears blockbyheight

tbears blockbyhash

tbears console

---
configuration file

tbears_server_config.json

tbears_cli_config.json

init, start, stop, deploy, clear, samples, genconf, transfer, txresult, balance, totalsupply, scoreapi, txbyhash, lastblock, blockbyheight, blockbyhash, keystore, sendtx and call.

