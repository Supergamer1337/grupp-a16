import pygame.event
import pygame
from labb4.version2.models.ball import Ball
from labb4.version2.models.paddle import Paddle
from labb4.version2.models.gameobject import GameObject


class Board(GameObject):
    def __init__(self):
        self.paddles = [
            Paddle(pygame.K_q, pygame.K_a),  # Left Paddle
            Paddle(pygame.K_UP, pygame.K_DOWN)  # Right Paddle
        ]
        self.ball = Ball()

    def update(self):
        key_list = pygame.key.get_pressed()
        for paddle in self.paddles:
            paddle.update(key_list)
        self.ball.update()
        if not self.ball.in_collision:
            for paddle in self.paddles:
                if self.__is_colliding__(self.ball, paddle):
                    # TODO: Handle the collision
                    print("Paddle collision!")

    # Does not work if the ball is too fast, should make a new based on previous position, 
    # new position and the collision objects position to see if it goes through the object
    @staticmethod
    def __is_colliding__(object1: GameObject, object2: GameObject) -> bool:
        pos1 = object1.get_pos()
        pos2 = object2.get_pos()
        size1 = object1.get_size()
        size2 = object2.get_size()
        x_coll = pos1[0] + size1[0] < pos2[0] and pos1[0] < size2[0] + pos2[0]
        y_coll = pos1[1] + size1[1] < pos2[1] and pos1[1] < size2[1] + pos2[0]
        if x_coll and y_coll:
            return True
        return False
