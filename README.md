# easyInterpreter

## Pipeline
- 将原文法拆分成上下文无关文法与上下文有关文法。
- 对上下文有关文法构造正则式NFA,并将NFA最小化为DFA
- 对上下文无关文法利用Trie树进行词法分析，并产生记号流
- 对词法分析得输入进行LL(1)文法分析，构造分析树
- 对分析树中的值进行计算并规约。
- 按step生成对应得数据点,将数据点存入文件，并用java Runtime调用Python程序
- 使用Python的matplot包进行绘制
