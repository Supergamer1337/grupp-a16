import time

import pygame
from models.board import Board
from config import WINDOW_SIZE
from config import WIN_POINTS


class Pong:
    def __init__(self):
        self.observers = []
        self.clock = pygame.time.Clock()
        self.player_points = [0 for i in range(2)]
        self.player_has_won = False
        self.board = Board()

    def run(self):
        """
        Runs as long as no one wins or quits the game
        """
        self.board.init_positions()
        while not self.player_has_won:
            self.clock.tick(60)  # Ensure program maintains a rate of 60 frames per second
            self.update()  # Update Game components
            for event in pygame.event.get():  # Check if quit pygame
                if event.type == pygame.QUIT:
                    self.quit_game()
            pygame.event.pump()  # Pump unused events
            if WIN_POINTS > 0:
                if self.player_points[0] == WIN_POINTS or self.player_points[1] == WIN_POINTS:
                    self.player_has_won = True
            self.notify_observers()
        time.sleep(5)  # If player has won, wait 5 seconds to quit game
        self.quit_game()

    def quit_game(self):
        """
        Quits the game
        """
        self.board.destroy()
        pygame.quit()
        quit()

    def update(self):
        """
        Updates current game state
        """
        self.board.update()
        self.update_round()

    def update_round(self):
        """
        Checks if player has WON THE ROUND
        """
        x_pos_ball = self.board.ball.get_pos()[0]
        # Checks if ball went past a paddle
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
