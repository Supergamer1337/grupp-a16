import pygame
from labb4.version2.pong import Pong
from labb4.version2.view.pongGUI import PongGUI


def run_game():
    pygame.init()
    # pygame.mixer.init()  # Should not be needed
    pong_game = Pong()
    game_view = PongGUI(pong_game)
    pong_game.run()


if __name__ == '__main__':
    run_game()
