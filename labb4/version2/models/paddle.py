import pygame.event
from labb4.version2.config import WINDOW_SIZE

from labb4.version2.models.gameobject import GameObject


class Paddle(GameObject):
    def __init__(self, up_key: pygame.key, down_key: pygame.key, pos: (int, int) = (0, 0)):
        super().__init__(*pos)
        self.up_key = up_key
        self.down_key = down_key
        self.base_speed: float = 5
        self.direction: int = 0

    def update(self, key_list):
        self.direction = self.take_input(key_list)
        self.pos_y = self.pos_y + self.direction * self.base_speed

    def take_input(self, key_list):
        new_direction = 0
        if key_list[self.up_key]:
            new_direction -= 1
        if key_list[self.down_key]:
            new_direction += 1
        return new_direction

    def destroy(self):
        self.image = None
    
    def load_image(self, path):
        super(Paddle, self).load_image(path)
        self.image = pygame.transform.smoothscale(self.image, (int(WINDOW_SIZE[0] * 0.01), int(WINDOW_SIZE[1] * 0.2)))
        print("Loaded paddle image")