function test()
a=[0.5 2.5 1 1.5 2 0.5;2 2 0.5 2.5 0.5 2];
line_array(a);
function line_array(array)
       [~,n]=size(array);
       for j=1:n-1
           line(array(1,j:j+1),array(2,j:j+1));
           hold on;
       end
    end
end