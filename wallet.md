wallet
==============

## wallet
지갑은 ICON에 중요한 역할을합니다. 지갑에는 잔액 및 지갑정보가 들어있습니다. 지갑 상태는 블록에 저장되면 지갑은 ICON 이용에 필수적인 기능입니다.
지갑은 암호화를 사용하여 안전한 보안을 보장합니다.

## Keyfiles

## T-bears 사용하기


#### keystore 생성
``` 
tbears keystore 
```
Example
``` 
tbears keystore [keystore name]

Input your keystore password : 패스워드 입력
Retype your keystore password : 패스워드 확인

```
result
```
Made keystore file successfully
```

## Python 사용하기


#### 지갑 생성하기

Example
``` 
wallet = KeyWallet.create()
```

#### 지갑 가져오기

Example
``` 
# PrivateKey 지갑 가져오기
key=UserPrivateKey
wallet = KeyWallet.load(key)

# Keystore 파일로 지갑 가져오기
wallet = KeyWallet.load("./keystore", "password")
```

#### 지갑(keystore파일) 내보내기



## Java 사용하기


#### 지갑 생성하기

Example
``` 
wallet = KeyWallet.create()
```
#### 지갑가져오기

Example
``` 
wallet = KeyWallet.create()
```
result
```

```


## ICONex 사용하기
GUI 기반의 구글 크롬 확장 프로그램입니다.


1. [ICONex 설치](<https://chrome.google.com/webstore/detail/iconex/flpiciilemghbmfalicajoolhkkenfel>)

![img001](./img/iconex001.png)

