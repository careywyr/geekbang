6.（必做）思考和设计自定义 MQ 第二个版本或第三个版本，写代码实现其中至少一个功能点，把设计思路和实现代码，提交到 GitHub。
1) 自定义内存 Message 数组模拟 Queue。
2) 使用指针记录当前消息写入位置。
3) 对于每个命名消费者，用指针记录消费位置。

加上了使用指针记录当前消息写入位置，包括读取位置也针对每个消费者单独记录。