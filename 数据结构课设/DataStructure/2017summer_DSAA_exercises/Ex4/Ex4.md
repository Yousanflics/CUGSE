<h1>4、木材抛光</h1>
<br>【问题描述】</br>
<br>一堆木材共有N根，已知它们的长度和重量。现在要用一台木工机床把它们依次抛光。机床抛光一根木材之前需要一段准备时间，来进行清理操作和调整机器。机床的准备时间可以这样计算：</br>
<br>（a）第一根木材的准备时间为1分钟；</br>
<br>（b）抛光一根长度为L重量为W的木材后，再抛光一根长度为L’重量为W’的木材，如果L≤L’并且W≤W’，则不需要准备时间；否则需要1分钟准备时间。</br>
    <br>对给出的N根木材，安排合适的抛光顺序，使得总的准备时间最短。例如：共有5根木材，长度和重量分别为（9，4）、（2，5）、（1，2）、（5，3）和（4，1），则最短准备时间为2分钟，抛光顺序为（4，1）、（5，3）、（9，4）、（1，2）、（2，5）。</br>
<br>【输入数据】</br>
<br>包含T组测试数据，T在第一行给出。每组测试数据包括两行，第一行是一个整数N（1≤N≤5000），表示木材的根数；第二行包括用空格隔开的2N个整数L1,W1,L2,W2,…Ln,Wn，每个整数最大不超过10000，Li和Wi分别表示第i根木材的长度和重量。</br>
<br>【输出数据】</br>
<br>对每组输入数据输出两行，第一行输出该组的最短准备时间（分钟），第二行以（Li，Wi）的形式依次输出抛光顺序。</br>
<br>【样例输入】</br>
<br>3</br>
<br>5</br>
<br>4 9 5 2 2 1 3 5 1 4</br>
<br>3</br>
<br>2 2 1 1 2 2</br>
<br>3</br>
<br>1 3 2 2 3 1</br>

