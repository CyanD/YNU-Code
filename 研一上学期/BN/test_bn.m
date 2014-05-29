clear all;
clc;
%%
%贝叶斯网络结构，确定结点总数和有哪些边
N = 10 ;                                                %给出节点数
dag = false(N,N);                                %初始化邻接矩阵为全假，表示无边图
dag(1,[2,3])=true;                             %给出有向边C-R，C-S
dag(2,[4,5])=true;
dag(3,[6,7])=true;
dag(4,8)=true;
dag(5,9)=true;
dag(8,10)=true;
dag(7,9)=true;
%%
%结点类型
%discrete_nodes = 1:N;                       %给各节点标号
node_sizes = 2*ones(1,N);                %设定每个节点只有两个值
%%
%生成网络
onodes=[7 9];
bnet = mk_bnet(dag, node_sizes,'observed',onodes);    %定义贝叶斯网络bnet
%%
%设置贝叶斯网络参数
%bnet结构定义之后，接下来需要设定其参数。

%%
%画图
G=bnet.dag;
draw_graph(G);