from labb4.version2.pong import Pong
from labb4.version2.config import THEME

class PongGUI:
    def __init__(self, game: Pong):
        self.game = game

    def render(self):
        pass

    def __init_textures__(self):
        pass

    # Called by observers when its time to render
    def notify(self):
        self.render()