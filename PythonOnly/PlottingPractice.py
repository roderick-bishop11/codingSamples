import matplotlib.pyplot as cartGraph
import matplotlib.ticker as ticker
import numpy as np


# start and end- global so that we can manipulate it in one place. 
start = 0
end = 500

# making & setting x and y
global x,y 
x = np.arange(0,end)
y = [1]
for i in range(1,end):
    y.append((1+1.01**i)) # 1% better everyday equation

def formatter(y, pos):
    if y == 100:
        return 'Natural Limit'
    else:
        return y


# Adding params to the cartGraph
cartGraph.xlabel('Number of failures')
cartGraph.ylabel('Percent of natural Potential Reached (%)')
cartGraph.title('Failure Is Exponentially Beneficial-Fail and get better 1%% everyday')
cartGraph.hlines(100,0,end, label = 'Natural Limit')
markers = [10, 20, 25, 50, 100]
verts = [10,20,25,50,100]
cartGraph.vlines(verts,0,end,colors="blue", linestyles = "--")
cartGraph.gca().yaxis.set_major_formatter(ticker.FuncFormatter(formatter))


# formatting X & Y components
cartGraph.xlim(start, end)
cartGraph.ylim(start, end)


#adding a horizontal line


# create the graph
cartGraph.plot(x, y, '-gD', markevery=markers)
cartGraph.grid()
cartGraph.show()
