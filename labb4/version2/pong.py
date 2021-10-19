import pygame
from models.board import Board
from config import WINDOW_SIZE


class Pong:
    def __init__(self):
        self.observers = []
        self.clock = pygame.time.Clock()
        self.player_points = [0 for i in range(2)]
        self.board = Board()
        self.__init_positions__()

    def run(self):
        running = True
        while running:
            # Ensure program maintains a rate of 60 frames per second
            self.clock.tick(60)
            # Update Game components
            self.update()
            # Pump unused events
            pygame.event.pump()
            # Update Render
            self.notify_observers()

    def __init_positions__(self):
        #TODO: set starting positions for all game components
        pass

    def update(self):
        # TODO: Update game
        self.board.update()
        x_pos_ball = self.board.ball.get_pos()[0]
        if x_pos_ball < 0:
            self.player_points[0] += 1
        elif x_pos_ball > WINDOW_SIZE[0]:
            self.player_points[1] += 1

    def add_observer(self, observer):
        self.observers.append(observer)

    def notify_observers(self):
        for observer in self.observers:
            observer.notify()