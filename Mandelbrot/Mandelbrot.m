function [ ] = Mandelbrot(n,nmax,u,v)
% n is the number to devide the interval in points, nmax is the maximum
% number of iterations.

if ~exist('u')
u = [-2 1];
end
if ~exist('v')
v = [-1.5 1.5];
end

% Setup
x = linspace( u(1), u(2), n );
y = linspace( v(1), v(2), n );

while true
t = tic();
[xGrid,yGrid] = meshgrid( x, y );

% Calculate
count = arrayfun( @(xGrid,yGrid) processMandelbrotElement(xGrid,yGrid,nmax), xGrid, yGrid );

m = max(count);
maxcount = max(m);
c = zeros(size(count));
c = c + maxcount;
count = c - count;

% Show
cpuTime = toc( t );
set( gcf, 'Position', [200 200 600 600] );
imagesc( x, y, count );
axis image
colormap('hot');
%colormap( [hot();flipud( hot() );0 0 0] );
title( sprintf( '%1.2fsecs', cpuTime ) );

[xi,yi] = ginput;

zoomsize = (x(end)-x(1))/6;

x=linspace(xi-zoomsize,xi+zoomsize,n);
y=linspace(yi-zoomsize,yi+zoomsize,n);

end

end

function count = processMandelbrotElement(x0,y0,maxIterations)
z0 = complex(x0,y0);
z = z0;
count = 1;
while (count <= maxIterations) ...
&& ((real(z)*real(z) + imag(z)*imag(z)) <= 4)
count = count + 1;
z = z*z + z0;
end
count = log(count);
end