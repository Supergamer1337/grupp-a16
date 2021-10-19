import pygame
from labb4.version2.pong import Pong
from labb4.version2.config import *

class PongGUI:
    ASSET_PATH = "assets/img/"
    
    def __init__(self, game: Pong):
        pygame.display.set_caption("Pong")
        self.screen = pygame.display.set_mode(WINDOW_SIZE)
        self.game = game
        self.game.add_observer(self)
        self.game_components = [
            self.game.board,
            self.game.board.ball,
            self.game.board.paddles[0],
            self.game.board.paddles[1]
        ]
        self.__init_textures__()

    def render(self):
        board = self.game.board
        for component in self.game_components:
            self.__render_image__(component.get_image(), component.get_pos())
        pygame.display.flip()

    def __render_image__(self, image: pygame.Surface, pos: (int, int)):
        self.screen.blit(image, pos)

    def __init_textures__(self):
        board = self.game.board
        board.load_image(f"{self.ASSET_PATH}{THEME}Bg.png")
        board.ball.load_image(f"{self.ASSET_PATH}{THEME}Ball.png")
        board.paddles[0].load_image(f"{self.ASSET_PATH}coolredpaddle.png")
        board.paddles[1].load_image(f"{self.ASSET_PATH}coolbluepaddle.png")

    # Called by observers when its time to render
    def notify(self):
        self.render()