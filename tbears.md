
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

* Tbears 에서 할 수 있는 일
1. SCORE()

1. Tbears 설치하기

Home Brew[<https://brew.sh>] 를 통하여 wget, git을 설치합니다.

* 터미널에서 실행합니다.
```
brew install wget // wget 설치
brew install git // git 설치
```

* 설치가 완료되면, tbears Git repository에 존재하는 코드를 ```Documents/t-bears```위치에  clone 합니다.

```
mkdir ~/Documents/t-bears & git clone https://github.com/icon-project/t-bears.git ~/Documents/t-bears
```
* LevelDB를 설치합니다.
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

5. SCORE 활용해보기

