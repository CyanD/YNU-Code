p=polyfit(x,y,2)
xi=linspace(34,48,1000);     %绘图的X轴数据
z=polyval(p,xi);     %得到多项式在数据点处的值
close;  
plot(x,y,'ko',xi,z,'r-')
