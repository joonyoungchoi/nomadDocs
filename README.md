ICON Dev Guide
==============
아이콘은 아이콘 재단의 블록체인입니다.
본 문서에서는 아이콘의 개발자용 툴인 T-Bears 와, 아이콘에서 제공하는 파이썬 SDK, 자바 SDK를 활용하여 기초적인 개발을 도와주는 Guide입니다.
따라하기 쉬운 Sample 코드를 활용하여 주어진 상황별로 튜토리얼을 진행해 봅니다. 본 가이드는 아래의 아이콘 github의 내용을 기반으로 작성되었습니다.

<br></br>
아이콘 홈페이지 : <https://icon.foundation/?lang=ko>. 

아이콘 T-Bears : <https://github.com/icon-project/t-bears>. 

아이콘 파이썬 SDK : <https://github.com/icon-project/icon-sdk-python>. 

아이콘 자바 SDK : <https://github.com/icon-project/icon-sdk-java>

<br></br>


## 순서

1. [T-Bears 설치하기](https://github.com/Life4honor/nomadDocs/blob/master/Icon_dev_Guide.md#1-t-bears-설치하기)
    * 공통
    	* MacOS
    	* Ubuntu Linux

    * Git의 코드를 다운로드하여 설치하기
    
    * PyPI 통해 설치하기
    
    * Docker 활용하기

2. [T-Bears 활용하기](https://github.com/Life4honor/nomadDocs/blob/master/Icon_dev_Guide.md#2-t-bears-활용하기)
    * 서비스
        * 서비스 시작 
        * 서비스 중단
        * 서비스에 배포된 SCORE 삭제

    * SCORE 배포하기
        * init으로 생성한 SCORE 프로젝트 배포하기
        * samples로 생성한 SCORE 배포하기

    * 배포한 SCORE의 메소드 호출하기
        * init을 통해 생성한 SCORE의 'hello' 메소드 호출
        * samples를 통해 생성한 SCORE("standard_token.py")의 'name' 메소드 호출

    * 트랜잭션
        * 트랜잭션 요청
        * 트랜잭션 결과 확인

    * ICX
        * ICX 보내기
        * ICX 잔고 확인하기

3. [SDK 개발환경 구축하기](https://github.com/Life4honor/nomadDocs/blob/master/Icon_dev_Guide.md#3-sdk-개발환경-구축하기)
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
			
3. [SDK 활용하기](https://github.com/Life4honor/nomadDocs/blob/master/Icon_dev_Guide.md#4-sdk-활용하기)
	* 지갑사용하기
		* wallet create (지갑생성)
		* wallet load (지갑로드)
		* wallet store (지갑저장)
		
	
	* 네트워크와 연결하기
	
	
	* 트랜젝션 만들어보기
		* ICX를 전송하는 트랜젝션
		* 토큰을 전송하는 트랜젝션
		* 메세지를 전송하는 트랜젝션
		* SCORE를 Deploy 하는 트랜젝션


	* Block에서 정보 가지고오기
	
	* 간단한 조회 해 보기
	 
	* 불러온 Block에서-정보-불러오기
		* 1.특정 주소의 Balance 호출하기
		* 2.현재 발행된 코인의 총 수 호출하기
		* 3.score주소를 통해서 score api를 모두 호출하기
		
	* Block을 불러오는 세 가지 방법
		* 1.가장 마지막에 만들어진 Block 불러오기
		* 2.특정높이의 Block 불러오기
		* 3.Block의 hash 값을 통하여 불러오기
		
	* 불러들인 Block에서 정보 불러오기
	
	* 내가 보낸 트랜젝션의 결과 확인해 보기

---

