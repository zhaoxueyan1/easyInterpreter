# -*- coding: GB18030 -*-
import matplotlib.pyplot as plt
import numpy as np


def init_draw():
    ax.set_ylim(-100, 100)
    ax.set_xlim(-100, 100)
    # ax.set_axis_off()
    ax.set_aspect('equal', adjustable='box')


def update_fig():
    dotsA = np.transpose(points_xy)
    dots = dotsA.tolist()
    # plot is faster than scatter     #plt.scatter(dots[0], dots[1], s=points_size*points_num,          c=points_color*points_num,  marker='.')
    plt.plot(dots[0], dots[1], markersize=points_size[0], c=points_color[0], marker='.', lw=0)
    plt.pause(0.1)



# plt.rcParams['font.sans-serif']=['SimHei']
plt.rcParams['axes.unicode_minus'] = False

fig, ax = plt.subplots(figsize=(16, 9))
fig.canvas.set_window_title('FuncDrawLang Interpreter')
plt.subplots_adjust(left=0.06, top=0.88, bottom=0.06, right=0.95)

ax.set_title('heart.dl.txt')
init_draw()

#
# DATA BEGIN
#
xy = np.loadtxt('./output',dtype='float',delimiter=',')
points_xy = xy
points_color = ['#FF0000']
points_size = [1]
points_num = len(points_xy)
update_fig()

#
# DATA END
#

# plt.show()
plt.savefig('./output.png')