import pygame
from labb4.version2.pong import Pong
from labb4.version2.view.pongGUI import PongGUI


class Game:
    def __init__(self):
        pygame.init()
        # pygame.mixer.init()
        pong_game = Pong()
        game_view = PongGUI(pong_game)
        pong_game.run()


if __name__ == '__main__':
    game_instance = Game()
