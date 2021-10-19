from random import uniform
from labb4.version2.config import WINDOW_SIZE
from labb4.version2.models.gameobject import GameObject
from math import sqrt
import pygame.mixer


class Ball(GameObject):
    def __init__(self):
        super().__init__()
        self.new_direction()
        self.speed = 5  # TODO: Change speed
        self.speed_multiplier = 1.05
        self.in_collision = False
        self.collision_timer_value: float = 0.1
        self.collision_timer: float = 0
        self.sound_paddle_hit = pygame.mixer.Sound("assets/sound/ballhitpaddle.wav")

    def paddle_collision(self):
        if not self.in_collision:
            self.in_collision = True
            self.speed *= self.speed_multiplier
            # TODO: Set angel of bounce
            self.pos_x * -1
            self.sound_paddle_hit.play()

    def __update_movement__(self):
        self.pos_x = self.pos_x + self.direction[0] * self.speed
        self.pos_y = self.pos_y + self.direction[1] * self.speed

    def update(self):
        self.__update_movement__()
        self.__collide_wall__()

    def __collide_wall__(self):
        if self.pos_y < 0:
            self.__wall_collision__()
            self.pos_y = 0
        elif self.pos_y + self.get_size()[1] > WINDOW_SIZE[1]:
            self.__wall_collision__()
            self.pos_y = WINDOW_SIZE[1] - self.get_size()[1]

    def __wall_collision__(self):
        self.direction = self.direction[0], self.direction[1] * -1

    def destroy(self):
        self.image = None
        self.sound_paddle_hit = None

    def load_image(self, path):
        super(Ball, self).load_image(path)
        new_size = int(WINDOW_SIZE[1] * 0.05)
        self.image = pygame.transform.smoothscale(self.image, (new_size, new_size))
        print("Loaded ball image")

    def new_direction(self) -> (float, float):
        dir_x = dir_y = 0
        while dir_x == 0:
            dir_x = uniform(-1, 1)
        while dir_y == 0:
            dir_y = uniform(-1, 1)
        print(f"{dir_x}, {dir_y}")
        self.direction = self.normalize_vector((dir_x, dir_y))

    @staticmethod
    def normalize_vector(vector: (float, float)) -> (float, float):
        length = sqrt(vector[0] * vector[0] + vector[1] * vector[1])
        normalized = vector[0] / length, vector[1] / length
        return normalized
