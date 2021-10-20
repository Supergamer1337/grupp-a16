import pygame
from labb4.version2.pong import Pong
from labb4.version2.config import *
from labb4.version2.config import WIN_POINTS


class PongGUI:
    ASSET_PATH = "assets/img/"
    
    def __init__(self, game: Pong):
        pygame.display.set_caption("Pong")
        self.screen = pygame.display.set_mode(WINDOW_SIZE)
        self.game = game
        self.game.add_observer(self)
        # Made as list to avoid writing repetitive render logic
        self.game_components = [
            self.game.board,
            self.game.board.paddles[0],
            self.game.board.paddles[1],
            self.game.board.ball
        ]
        self.__init_textures__()
        # Different font sizes
        self.font = pygame.font.Font("assets/ARCADE.TTF", 128)
        self.win_font = pygame.font.Font("assets/ARCADE.TTF", 256)

    def render(self):
        """
        Renders the game
        """
        for component in self.game_components:
            self.__render_image__(component.get_image(), component.get_pos())
        self.render_text()
        self.render_fps_counter()
        if self.game.player_has_won:
            self.player_won()
        pygame.display.flip()

    def render_fps_counter(self):
        """
        Method to render FPS counter in top left
        """
        text = self.font.render(f"{round(self.game.clock.get_fps())}", True, (255, 0, 0))
        textRect = text.get_rect()
        self.screen.blit(text, textRect)

    def render_text(self):
        """
        Method to render the points in the top-middle part of the screen
        """
        text = self.font.render(f"{self.game.player_points[0]} - {self.game.player_points[1]}", True, (255, 255, 255))
        textRect = text.get_rect()
        textRect.x = WINDOW_SIZE[0] / 2 - textRect.width / 2
        textRect.y = WINDOW_SIZE[1] * 0.05
        self.screen.blit(text, textRect)

    def player_won(self):
        """
        Render logic for when a player has won (massive text in middle of screen)
        """
        winner = self.game.player_points.index(WIN_POINTS)
        if winner == 0:
            player = "Red"
        else:
            player = "Blue"

        text = self.win_font.render(f"{player} won!", True, (255, 255, 255))
        textRect = text.get_rect()
        textRect.x = WINDOW_SIZE[0] / 2 - textRect.width / 2
        textRect.y = WINDOW_SIZE[1] / 3
        self.screen.blit(text, textRect)

    def __render_image__(self, image: pygame.Surface, pos: (int, int)):
        """
        Renders a sprite, or image, for given game objects position
        """
        self.screen.blit(image, pos)

    def __init_textures__(self):
        """
        Load all sprites (or images) for different game objects
        """
        board = self.game.board
        board.load_image(f"{self.ASSET_PATH}{THEME}Bg.png")
        board.ball.load_image(f"{self.ASSET_PATH}{THEME}Ball.png")
        board.paddles[0].load_image(f"{self.ASSET_PATH}coolredpaddle.png")
        board.paddles[1].load_image(f"{self.ASSET_PATH}coolbluepaddle.png")

    # Called by observers when its time to render
    def notify(self):
        self.render()
