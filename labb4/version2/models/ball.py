from random import random
from labb4.version2.config import WINDOW_SIZE
from labb4.version2.models.gameobject import GameObject
import pygame.mixer

class Ball(GameObject):
    def __init__(self):
        super().__init__()
        self.direction = (random(), random())  # TODO: Randomize direction as well
        self.speed = 1
        self.speed_multiplier = 1.05
        self.in_collision = False
        self.collision_timer_value: float = 0.1
        self.sound_paddle_hit = pygame.mixer.Sound("labb4/version2/assets/sound/ballhitpaddle.wav")

    def paddle_collision(self):
        if not self.in_collision:
            self.in_collision = True
            self.speed *= self.speed_multiplier
            # TODO: Set angel of bounce
            self.sound_paddle_hit.play()

    def __update_movement__(self):
        self.pos = self.pos[0] + self.direction[0] * self.speed, self.pos[1] + self.direction[1] * self.speed

    def update(self):

        self.__update_movement__()
        self.__collide_wall__()

    def __collide_wall__(self):
        if self.pos[1] < 0:
            self.__wall_collision__()
            self.pos = self.pos[0], 0
        elif self.pos[1] < WINDOW_SIZE[1]:
            self.__wall_collision__()
            self.pos = self.pos[0], WINDOW_SIZE[1]

    def __wall_collision__(self):
        self.direction = self.direction[0] * -1, self.direction[1] * -1

    def destroy(self):
        # TODO: Remove all image assets
        pass

