clear all;
clc;
%%
%��Ҷ˹����ṹ��ȷ���������������Щ��
N = 4;                                                %�����ڵ���
dag = false(N,N);                                %��ʼ���ڽӾ���Ϊȫ�٣���ʾ�ޱ�ͼ
C = 1; S = 2; R = 3; W = 4;                %�����ڵ���
dag(C,[R,S])=true;                             %���������C-R��C-S
dag([R,S],W)=true;                           %���������R-W��S-W
%%
%�������
%discrete_nodes = 1:N;                       %�����ڵ���
node_sizes = 2*ones(1,N);                %�趨ÿ���ڵ�ֻ������ֵ
%%
%��������
bnet = mk_bnet(dag, node_sizes);    %���屴Ҷ˹����bnet
%%
%���ñ�Ҷ˹�������
%bnet�ṹ����֮�󣬽�������Ҫ�趨�������
bnet.CPD{C} = tabular_CPD(bnet, C, [0.5 0.5]);
bnet.CPD{R} = tabular_CPD(bnet, R, [0.8 0.2 0.2 0.8]);
bnet.CPD{S} = tabular_CPD(bnet, S, [0.5 0.9 0.5 0.1]);
bnet.CPD{W} = tabular_CPD(bnet, W, [1 0.1 0.1 0.01 0 0.9 0.9 0.99]);
%%
%��ͼ
G=bnet.dag;
draw_graph(G);