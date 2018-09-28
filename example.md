Example
==============

### Example - loop
```
class example():

    def __init__(self):
        self._count = 10000
        
    @external
    def loop(self):
        count = self_count
        rate = 100
        num = 0
        for i in range(count):
            if num >= rate:
                count=count-num
                if count > 0:
                    self._count = count
                    self.loop()
                    break
                break
            num += 1
        
        
```
반복문이 길어질경우 끊어서 실행하시길 권합니다.

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
