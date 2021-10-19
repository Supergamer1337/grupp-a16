from abc import ABC, abstractmethod
import pygame.image
from pygame.surface import Surface


class GameObject(ABC):
    def __init__(self, pos_x: int = 0, pos_y: int = 0, image: Surface = None):
        self.pos_x: int = pos_x
        self.pos_y: int = pos_y
        self.image: Surface = image

    @abstractmethod
    def destroy(self):
        raise NotImplementedError

    def get_pos(self):
        return self.pos_x, self.pos_y

    def get_size(self):
        if self.image is not None:
            return self.image.get_size()

    @abstractmethod
    def load_image(self, path):
        self.image = pygame.image.load(path)

    def get_image(self):
        return self.image
