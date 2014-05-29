clear all;
clc;
%%
%贝叶斯网络结构，确定结点总数和有哪些边
N = 4;                                                %给出节点数
dag = false(N,N);                                %初始化邻接矩阵为全假，表示无边图
C = 1; S = 2; R = 3; W = 4;                %给出节点序
dag(C,[R,S])=true;                             %给出有向边C-R，C-S
dag([R,S],W)=true;                           %给出有向边R-W，S-W
%%
%结点类型
%discrete_nodes = 1:N;                       %给各节点标号
node_sizes = 2*ones(1,N);                %设定每个节点只有两个值
%%
%生成网络
bnet = mk_bnet(dag, node_sizes);    %定义贝叶斯网络bnet
%%
%设置贝叶斯网络参数
%bnet结构定义之后，接下来需要设定其参数。
bnet.CPD{C} = tabular_CPD(bnet, C, [0.5 0.5]);
bnet.CPD{R} = tabular_CPD(bnet, R, [0.8 0.2 0.2 0.8]);
bnet.CPD{S} = tabular_CPD(bnet, S, [0.5 0.9 0.5 0.1]);
bnet.CPD{W} = tabular_CPD(bnet, W, [1 0.1 0.1 0.01 0 0.9 0.9 0.99]);
%%
%画图
G=bnet.dag;
draw_graph(G);