Example
==============

### Example - loop
```
# 종료 조건이 없을시 잘못된 결과를 가져올 수 있습니다.
def loop()
    while True:
        pass

# 종료 조건을 항상 확인하여야합니다.
def loop()
    i = 1
    while i < 10:
        i += 1 

def loop()
    for i in range(10): 
        pass 
    
```
반복문을 사용하실 경우에는 종료 조건을 확인하여야합니다
반복문이 무한이 돌면 어느 순간 프로그램이 중단됩니다
번복 횟수가 많을 경우 역시 위험할수있습니다.

### Example - from abc
```
# iconservice만 임포트 하여 사용
from iconservice import *

class ABC(ABC):
    @abstractmethod
    def abc(self):
        pass
```
abc는 iconservice에서 기본적으로 제공하고있습니다.
ABC, abstractmethod등 이용시
iconservice만 import하여 사용가능합니다.

### Example - sha3_256
```
def sha3_256()
    data = create_tx_hash()
    hash_value = hashlib.sha3_256(data).digest()
    return hash_value

```
sha3_256을 이용 암호화 예제입니다. data는 bytes형식입니다.