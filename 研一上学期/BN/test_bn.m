clear all;
clc;
%%
%��Ҷ˹����ṹ��ȷ���������������Щ��
N = 10 ;                                                %�����ڵ���
dag = false(N,N);                                %��ʼ���ڽӾ���Ϊȫ�٣���ʾ�ޱ�ͼ
dag(1,[2,3])=true;                             %���������C-R��C-S
dag(2,[4,5])=true;
dag(3,[6,7])=true;
dag(4,8)=true;
dag(5,9)=true;
dag(8,10)=true;
dag(7,9)=true;
%%
%�������
%discrete_nodes = 1:N;                       %�����ڵ���
node_sizes = 2*ones(1,N);                %�趨ÿ���ڵ�ֻ������ֵ
%%
%��������
onodes=[7 9];
bnet = mk_bnet(dag, node_sizes,'observed',onodes);    %���屴Ҷ˹����bnet
%%
%���ñ�Ҷ˹�������
%bnet�ṹ����֮�󣬽�������Ҫ�趨�������

%%
%��ͼ
G=bnet.dag;
draw_graph(G);