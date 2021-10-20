import time

import pygame
from models.board import Board
from config import WINDOW_SIZE


class Pong:
    def __init__(self):
        self.observers = []
        self.clock = pygame.time.Clock()
        self.player_points = [0 for i in range(2)]
        self.player_has_won = False
        self.board = Board()

    def run(self):
        self.board.init_positions()
        while not self.player_has_won:
            # Ensure program maintains a rate of 60 frames per second
            self.clock.tick(60)  # Base 60
            # Update Game components
            self.update()
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    self.quit_game()
            # Pump unused events
            pygame.event.pump()
            # Update Render
            if self.player_points[0] == 5 or self.player_points[1] == 5:
                self.player_has_won = True
            self.notify_observers()
        time.sleep(5)
        self.quit_game()

    def quit_game(self):
        self.board.destroy()
        pygame.quit()
        quit()

    def update(self):
        self.board.update()
        x_pos_ball = self.board.ball.get_pos()[0]
        # Checks if ball out of board
        if x_pos_ball < 0:
            self.player_points[1] += 1
            self.board.new_round()
        elif x_pos_ball > WINDOW_SIZE[0]:
            self.player_points[0] += 1
            self.board.new_round()

    def add_observer(self, observer):
        self.observers.append(observer)

    def notify_observers(self):
        for observer in self.observers:
            observer.notify()
