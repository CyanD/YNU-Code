x0=-0.21503361460851339;
y0=0.67999116792639069;
resx=200; % ����ֱ���
resy=200; % ����ֱ���
R=8; % ���z�ľ���ֵ����R����Ϊ��ɢ
n=100; % ����������
clear F;%�����ʹ��F��im������������������������
clear im;
fc=1;
figure('units','normalized','position',[0.1 0.05 0.8 0.8]);%ȷ������λ�á����� ���ϵ�����ٷֱȡ�
tic;  %tic-toc�����������ʱ��
zmmx=10;  %֡��
lc=1;
for zm=1:1:zmmx
    % x (real(c))
    zmf=exp(-zm/20);
    x1=x0-2*zmf;
    x2=x0+2*zmf;
    %x1=x0-sz;
    %x2=x0+sz;

    % y (imag(c))
    y1=y0-1.5*exp(-zm/20);
    y2=y0+1.5*exp(-zm/20);
    
    %y1=y0-sz;
   %y2=y0+sz;

    x=linspace(x1,x2,resx);
    y=linspace(y1,y2,resy);

    [X,Y] = meshgrid(x,y); % for vectorized calculation

    c=X+1i*Y;

    
    z=zeros(size(c)); % starts from zeros

    I=zeros(size(c));
    for nc=1:n
        z=z.^2+c; % vectorized
        bw=abs(z)<R;
        I(bw)=nc;
    end

    cla;
    imagesc(x,y,I);
    set(gca,'YDir','normal');
    xlabel('Re(c)');
    ylabel('Im(c)');
    axis equal;
    %colorbar;
    set(gca,'clim',[0 n]);
    axis off;
    %zoom(1.2);
    set(gca,'units','normalized','position',[0 0 1 1]);
    drawnow;
    f=getframe(gcf);
    
    if lc==1
        colorbar;
        [im,map] = rgb2ind(f.cdata,256,'nodither');
        colorbar('off');
        im(1,1,1,20) = 0;
    else
        im(:,:,1,lc-1) = rgb2ind(f.cdata,map,'nodither');
    end
    fc=fc+1;
    jr=zm/zmmx;
    v=jr/toc;
    et=(1-jr)/v;
    disp(['zm=' num2str(zm) '  et=' num2str(et)]);
    lc=lc+1;
end
%movie2avi(F,'mandelbrot11','fps',20,'compression','Cinepak');
imwrite(im,map,'mandelbrot_gif.gif','DelayTime',0,'LoopCount',inf) 