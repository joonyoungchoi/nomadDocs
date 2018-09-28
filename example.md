Example
==============

### Example - loop
```
@external
def loop(count: int):
    rate = 10
    num = 0
    for i in range(count):
        if offset >= rate:
            count=count-offset
            if count > 0:
                loop(count)
            break
        num += 1
        
```
반복문을 사용하실 경우에는 종료 조건을 확인하여야합니다
반복문이 무한이 돌면 어느 순간 프로그램이 중단됩니다

### Example - from abc, sha3
```
# abc,hashlib 따로 import 없이 iconservice만 import 하여 사용
from iconservice import *

class abcexample(ABC):

    @abstractmethod
    def sha3(self, data: bytes):
        pass
    
    
class example(abcexample):
    
    @external
    def sha3(self, data: bytes):
        result = sha3_256(data)
        return result
        
```
abc,sha3 iconservice에서 기본적으로 제공하고있습니다.
ABC, abstractmethod, sha3 등 이용시
iconservice만 import하여 사용가능합니다.
