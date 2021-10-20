import pygame
from labb4.version2.pong import Pong
from labb4.version2.view.pongGUI import PongGUI


def run_game():
    pygame.init()
    pong_game = Pong()  # Initialize game logic
    game_view = PongGUI(pong_game)  # Initialize GUI for game
    pong_game.run()  # Start game


if __name__ == '__main__':
    run_game()
