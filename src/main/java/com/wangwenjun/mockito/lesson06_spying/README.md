spy和mock的stub实例恰好互补。

spy的实例都调用真实方法,通过when().thenxxx可以录制覆盖！
mock的实例默认都调用假方法，通过when().thenxxx可以进行录制覆盖！要想调用真实方法得手动指定thenCallRealMethod方法。