# package exercises
#  * Program for Conway's game of life.
#  * See https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
#  *
#  * This is a graphical program using pygame to draw on the screen.
#  * There's a bit of "drawing" code to make this happen (far below).
#  * You don't need to implement (or understand) any of it.
#  * NOTE: To run tests must uncomment in init() method, see comment
#  *
#  * Use functional decomposition!
#  *
#  * See:
#  * - UseEnum
#  * - PygameSample (don't need to understand, just if you're curious)
from enum import Enum
from typing import List
import pygame
from math import sqrt
from random import random


# Main program
def game_of_life():
    pygame.init()
    world = World()
    GameOfLifeView(world)
    world.run()


class Cell(Enum):
    DEAD = 0
    ALIVE = 1


class World:

    all_cells: List[List[Cell]] = []
    observers = []

    def __init__(self):
        # test()        # <--------------- Uncomment to test!
        n_locations = 10000
        distribution = 0.20   # % of locations holding a Cell
        self.create_world(n_locations, distribution)
        self.clock = pygame.time.Clock()

    def create_world(self, n_locations, distribution):
        self.all_cells = []
        row = int(sqrt(n_locations))
        for x in range(row):
            cell_list = []
            for y in range(row):
                if random() > distribution:
                    cell_list.append(Cell.DEAD)
                else:
                    cell_list.append(Cell.ALIVE)
            self.all_cells.append(cell_list)

    def add_observer(self, observer):
        self.observers.append(observer)

    def notify_observers(self):
        for observer in self.observers:
            observer.notify()

    def update(self):
        new_cells = []
        for x in range(len(self.all_cells)):
            row = []
            for y in range(len(self.all_cells)):
                neighbouring_cells = self.check_neighbour_cells(x, y)
                if self.all_cells[x][y] == Cell.ALIVE:
                    if neighbouring_cells < 2 or 3 < neighbouring_cells:
                        row.append(Cell.DEAD)
                    else:
                        row.append(Cell.ALIVE)
                else:
                    if neighbouring_cells == 3:
                        row.append(Cell.ALIVE)
                    else:
                        row.append(Cell.DEAD)
            new_cells.append(row)
        self.all_cells = new_cells
        self.notify_observers()  # Tell the view to render

    def run(self):
        # Variable to keep the main loop running
        running = True
        # Main loop
        while running:
            self.update()
            # Ensure program maintains a rate of 2 frames per second
            self.clock.tick(2)
            # Look at every event in the queue
            for event in pygame.event.get():
                # Did the user hit a key?
                if event.type == pygame.QUIT:
                    running = False


# -------- Write methods below this --------------
    def check_neighbour_cells(self, x:int, y:int):
        surr_cell = 0
        times_ran = 0
        for i in range(-1, 2):
            for j in range(-1, 2):
                if not (i == 0 and j == 0) and not self.is_out_of_bounds(x + i, y + j):
                    times_ran += 1
                    if self.all_cells[i+x][j+y] == Cell.ALIVE:
                        surr_cell += 1
        # print("Times ran: %r" % times_ran)
        # print("Surr Cells; %r, %r: %r" % (x, y, surr_cell))
        return surr_cell

    def is_out_of_bounds(self, x, y):
        if x < 0 or x > len(self.all_cells) - 1:
            return True
        elif y < 0 or y > len(self.all_cells) - 1:
            return True
        return False

# ---------- Testing -----------------
# Here you run your tests i.e. call your logic methods
# to see that they really work
def test():
    # Hard coded test world
    test_world: List[List[Cell]] = [
        [Cell.ALIVE, Cell.ALIVE, Cell.DEAD],
        [Cell.ALIVE, Cell.DEAD, Cell.DEAD],
        [Cell.DEAD, Cell.DEAD, Cell.ALIVE]]

    size = len(test_world)


# -------- Below is Pygame View stuff, nothing to do --------------
class GameOfLifeView:

    SCREEN_WIDTH = 625
    SCREEN_HEIGHT = 625

    def __init__(self, world_ref: World):
        self.the_world = world_ref
        self.screen = pygame.display.set_mode((self.SCREEN_WIDTH, self.SCREEN_HEIGHT))
        self.the_world.add_observer(self)

    def notify(self):
        self.render()

    def render(self):
        self.screen.fill((255, 255, 255))
        size = len(self.the_world.all_cells)
        for row in range(size):
            for col in range(size):
                x = 5 * col + 50
                y = 5 * row + 50
                self.render_cell(x, y, self.the_world.all_cells[row][col])

        pygame.display.flip()

    def render_cell(self, x, y, cell: Cell):
        if cell == Cell.ALIVE:
            color = (255, 0, 0)
        else:
            color = (255, 255, 255)
        surf = pygame.Surface((10, 10))
        surf.fill(color)
        self.screen.blit(surf, (x, y))


if __name__ == "__main__":
    game_of_life()
