2.（必做）按自己设计的表结构，插入 100 万订单模拟数据，测试不同方式的插入效率

单条的慢的夸张，一万条都要30多s，就不测试一百万的了 
测试代码是batch插入，同时开启了多线程，耗时20s。
代码在InsertApplicationTests#insert();