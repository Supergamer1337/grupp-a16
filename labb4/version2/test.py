from labb4.version2.models.ball import Ball
from math import *
from random import *


def test():
    print("Testing started!")
    # TODO: Add tests
    print(Ball.angle_to_2dvector(pi/2))
    print(Ball.angle_to_2dvector(pi/2 + pi/4))
    offset = uniform(-pi/4, pi/4)
    print((pi + offset)*180/pi)
    print(Ball.angle_to_2dvector(pi + offset))
    print("Testing ended!")


if __name__ == "__main__":
    test()
