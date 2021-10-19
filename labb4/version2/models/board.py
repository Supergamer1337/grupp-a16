import pygame.event
import pygame
from labb4.version2.models.ball import Ball
from labb4.version2.models.paddle import Paddle
from labb4.version2.models.gameobject import GameObject
from labb4.version2.config import WINDOW_SIZE


class Board(GameObject):
    def __init__(self):
        super().__init__()
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
        for paddle in self.paddles:
            if self.__is_colliding__(self.ball, paddle) and self.ball.last_collision_target is not paddle:
                self.ball.paddle_collision(paddle)

    def new_round(self):
        self.init_positions()
        self.ball.new_ball()

    def init_positions(self):
        paddle_offset_x: int = int(WINDOW_SIZE[0] * 0.05)
        paddle_y: int = int(WINDOW_SIZE[1] / 2 - self.paddles[0].get_size()[1] / 2)

        self.paddles[0].set_pos((paddle_offset_x, paddle_y))
        self.paddles[1].set_pos((WINDOW_SIZE[0]-paddle_offset_x, paddle_y))

        ball_offset = int(self.ball.get_size()[0] / 2), int(self.ball.get_size()[1] / 2)
        ball_pos = int(WINDOW_SIZE[0] / 2) - ball_offset[0], int(WINDOW_SIZE[1] / 2) - ball_offset[1]
        self.ball.set_pos(ball_pos)

    # Does not work if the ball is too fast, should make a new based on previous position, 
    # new position and the collision objects position to see if it goes through the object
    @staticmethod
    def __is_colliding__(object1: GameObject, object2: GameObject) -> bool:
        pos1 = object1.get_pos()
        pos2 = object2.get_pos()
        size1 = object1.get_size()
        size2 = object2.get_size()
        x_coll = pos1[0] < size2[0] + pos2[0] and pos1[0] + size1[0] > pos2[0]
        y_coll = pos1[1] < pos2[1] + size2[1] and pos1[1] + size1[1] > pos2[1]
        if x_coll and y_coll:  # If both are 0
            return True
        return False

    def load_image(self, path):
        super(Board, self).load_image(path)
        self.image = pygame.transform.smoothscale(self.image, WINDOW_SIZE)

    def destroy(self):
        self.image = None
        self.ball.destroy()
        for paddle in self.paddles:
            paddle.destroy()
