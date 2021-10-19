import pygame
from pong import Pong
import view.pongGUI

class Game:
    def __init__(self):
        pygame.init()
        pong_game = Pong()
        game_view = PongGUI(game)
        game.run()

if __name__ == '__main__':
    game_instance = Game()
