function draw_line(step_times)
start_point=[0,0];
route=zeros(2,step_times);
route(:,1)=start_point';
end_point=start_point;
for i=2:step_times
    step_len=floor(rand()*10);
    end_point=step_len*rand_direction()+end_point;
    route(:,i)=end_point';
end
plot(start_point(1),start_point(2),'r*');
line_array(route);

%%
%能走回头路
function direction=rand_direction()
    r=rand();
    if r<=0.25
        direction=[0,1];
    elseif r<=0.5
         direction=[1,0];
    elseif r<=0.75
        direction=[0,-1];
    else
        direction=[-1,0];
    end
end
%%
%不能走回头路
    function road=rand_direction2(old_direction)
        road=rand_direction();
        while road==old_direction
            road=rand_direction;
        end
    end
%%

    function line_array(array)
       [~,n]=size(array);
       for j=1:n-1
           line(array(1,j:j+1),array(2,j:j+1));
           hold on;
       end
    end

end