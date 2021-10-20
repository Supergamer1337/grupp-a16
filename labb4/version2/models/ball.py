from random import uniform, randint
from labb4.version2.models.paddle import Paddle
from labb4.version2.config import WINDOW_SIZE
from labb4.version2.models.gameobject import GameObject
from math import sqrt, sin, cos, pi
import pygame.mixer


class Ball(GameObject):
    def __init__(self):
        super().__init__()
        self.direction: (float, float) = self.new_direction()
        self.speed = 7
        self.speed_multiplier = 1.05
        self.last_collision_target: Paddle = None
        self.sound_paddle_hit = pygame.mixer.Sound("assets/sound/ballhitpaddle.wav")
        self.max_angle = pi / 4

    def paddle_collision(self, paddle: Paddle):
        """
        Called when the ball hits a paddle.
        Plays a pretty sound!
        Increases ball speed and sets new direction.
        """
        self.last_collision_target = paddle
        self.sound_paddle_hit.play()
        self.speed *= self.speed_multiplier
        self.direction = self.collision_bounce(paddle)

    def collision_bounce(self, paddle: Paddle):
        """
        Sets the new direction of the ball once it hits a paddle.
        """
        lower_bound = paddle.get_pos()[1] - self.get_size()[1]  # Lower bound of the paddle's surface
        upper_bound = paddle.get_pos()[1] + paddle.get_size()[1]  # Upper bound of the paddle's surface
        ball_pos = self.pos_y - lower_bound

        # Percentage of how far down on the paddle the ball hit
        percentage = ball_pos / (upper_bound - lower_bound)
        # New angle that the ball will bounce away in
        offset_angle = percentage * self.max_angle * 2 - self.max_angle

        if self.direction[0] > 0:  # Mirrors angle if ball is moving left to right
            offset_angle += pi
            new_direction = self.normalize_vector(self.angle_to_2Dvector(offset_angle))
            new_direction = new_direction[0], -new_direction[1]
            return new_direction
        else:
            return self.normalize_vector(self.angle_to_2Dvector(offset_angle))

    def __update_movement__(self):
        """
        Moves the ball on the board.
        """
        self.pos_x = self.pos_x + self.direction[0] * self.speed
        self.pos_y = self.pos_y + self.direction[1] * self.speed

    def update(self):
        """
        Updates the ball's position on the board.
        """
        self.__update_movement__()
        self.__collide_wall__()

    def __collide_wall__(self):
        """
        Checks if the ball collided with the roof or floor.
        """
        if self.pos_y < 0:
            self.__wall_collision__()
            self.pos_y = 0
        elif self.pos_y + self.get_size()[1] > WINDOW_SIZE[1]:
            self.__wall_collision__()
            self.pos_y = WINDOW_SIZE[1] - self.get_size()[1]

    def __wall_collision__(self):
        """
        Changes y direction to simulate a wall bounce.
        """
        self.direction = self.direction[0], self.direction[1] * -1

    def destroy(self):
        """
        Unloads images and sounds.
        """
        self.image = None
        self.sound_paddle_hit = None

    def load_image(self, path):
        """
        Literally just applies the damn texture :))
        """
        super(Ball, self).load_image(path)
        new_size = int(WINDOW_SIZE[1] * 0.05)
        self.image = pygame.transform.smoothscale(self.image, (new_size, new_size))

    def new_direction(self) -> (float, float):
        """
        Returns a randomized direction for the ball
        """
        angle = uniform(-pi/6, pi/6)  # Angle
        if randint(0, 1):
            angle += pi
        vector = self.angle_to_2Dvector(angle)
        return self.normalize_vector(vector)

    def new_ball(self):
        """
        Resets ball stats
        """
        self.direction = self.new_direction()
        self.speed = uniform(6, 10)
        self.last_collision_target = None

    @staticmethod
    def angle_to_2Dvector(angle: float) -> (float, float):
        """
        Converts an angle ta a 2d vector
        """
        x = cos(angle)
        y = sin(angle)
        return x, y

    @staticmethod
    def normalize_vector(vector: (float, float)) -> (float, float):
        """
        Normalizes a vector -> ball always has the same speed
        """
        length = sqrt(vector[0] ** 2 + vector[1] ** 2)
        normalized = vector[0] / length, vector[1] / length
        return normalized
