p=polyfit(x,y,2)
xi=linspace(34,48,1000);     %��ͼ��X������
z=polyval(p,xi);     %�õ�����ʽ�����ݵ㴦��ֵ
close;  
plot(x,y,'ko',xi,z,'r-')
