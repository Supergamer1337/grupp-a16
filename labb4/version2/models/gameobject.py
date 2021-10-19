from abc import ABC, abstractmethod
import pygame.image
from pygame.surface import Surface


class GameObject(ABC):
    def __init__(self, pos: (int, int) = (0, 0), image: Surface = None):
        # TODO: Kanske byta till två variabler istället för en tuple?
        self.pos: (int, int) = pos
        self.image: Surface = image

    @abstractmethod
    def destroy(self):
        raise NotImplementedError

    def get_pos(self):
        return self.pos

    def get_size(self):
        if self.image is not None:
            return self.image.get_size()

    def load_image(self, path):
        self.image = pygame.image.load(path)
