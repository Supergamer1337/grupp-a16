import pygame.event

from labb4.version2.models.gameobject import GameObject


class Paddle(GameObject):
    def __init__(self, up_key: pygame.key, down_key: pygame.key):
        super().__init__()
        self.up_key = up_key
        self.down_key = down_key
        self.base_speed: float = 0.5
        self.direction: int = 0

    def update(self, key_list):
        self.direction = self.take_input(key_list)
        self.pos_y = self.pos_y + self.direction * self.base_speed

    def take_input(self, key_list):
        new_direction = 0
        for key in key_list:
            if key == self.up_key:
                new_direction -= 1
            elif key == self.down_key:
                new_direction += -1
        return new_direction

    def destroy(self):
        self.image = None
