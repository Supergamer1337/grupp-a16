from typing import List
from enum import Enum, auto
from random import *

import pygame as pg


#  Program to simulate segregation.
#  See : http:#nifty.stanford.edu/2014/mccown-schelling-model-segregation/
#
# Enumeration type for the Actors
class Actor(Enum):
    RED = auto()
    BLUE = auto()
    NONE = auto()  # NONE used for empty locations


# Enumeration type for the state of an Actor
class State(Enum):
    UNSATISFIED = auto()
    SATISFIED = auto()
    NA = auto()  # Not applicable (NA), used for NONEs


World = List[List[Actor]]  # Type alias
SIZE = 300


def neighbours():
    pg.init()
    test()
    model = NeighboursModel(SIZE)
    _view = NeighboursView(model)
    model.run()


class NeighboursModel:

    # Tune these numbers to test different distributions or update speeds
    FRAME_RATE = 20            # Increase number to speed simulation up
    DIST = [0.4, 0.4, 0.20]  # % of RED, BLUE, and NONE
    THRESHOLD = 0.7            # % of surrounding neighbours that should be like me for satisfaction
    cells_satisfied = False

    # ########### These following two methods are what you're supposed to implement  ###########
    # In this method you should generate a new world
    # using randomization according to the given arguments.
    def __create_world(self) -> World:
        brave_new_world = self.gen_world_list()
        shuffle(brave_new_world)  # Randomizes to positions of the list
        return list_to_matrix(self.size, brave_new_world)

    def gen_world_list(self):
        """
            Adds RED, BLUE and NONE actors according to set distribution values.
        """
        world_list = add_element(Actor.RED, self.DIST[0], self.size)
        world_list += add_element(Actor.BLUE, self.DIST[1], self.size)
        world_list += add_element(Actor.NONE, self.DIST[2], self.size)
        return world_list

    # This is the method called by the timer to update the world
    # (i.e move unsatisfied) each "frame".
    def __update_world(self):
        # Will not run if all cells are satisfied to save performance
        if not self.cells_satisfied:
            self.world = self.new_cell_world()

    def new_cell_world(self):
        """
            Generates new cell map based on satisfied and unsatisfied actors.
        """
        (new_cell_map, valid_pos, cell_pos) = self.get_satisfied_cells()
        # If cell_pos is empty all cells are satisfied
        if len(cell_pos) < 1:
            self.cells_satisfied = True
            print("Done!")
        # Place every unsatisfied actor onto new location
        return self.shuffle_unsatisfied_cells(list_to_matrix(self.size, new_cell_map), valid_pos, cell_pos)
    
    def get_satisfied_cells(self):
        """
            Places satisfied cells in new cell map, and returns map, valid positions and unsatisfied cell positions.
        """
        new_cell_map = []
        valid_pos = []
        cell_pos = []
        for x in range(len(self.world)):
            for y in range(len(self.world)):
                current_state = self.check_cell(x, y)
                current_cell = self.world[x][y]
                if current_state == State.SATISFIED:
                    new_cell_map.append(current_cell)
                else:
                    valid_pos.append((x, y))
                    new_cell_map.append(Actor.NONE)
                    if current_cell != Actor.NONE:
                        cell_pos.append((x, y))
        return (new_cell_map, valid_pos, cell_pos)

    def check_cell(self, x, y):
        """
            Check state of given cell and returns the state.
        """
        if self.world[x][y] == Actor.NONE:
            return State.NA
        result = self.neighbour_cell_threshold(x, y)
        if result > self.THRESHOLD:
            return State.SATISFIED
        return State.UNSATISFIED

    def neighbour_cell_threshold(self, x: int, y: int):
        """
            Checks surrounding cells to calculate the given cell's threshold value
        """
        surrounding_cells = 0
        same_colored_cells = 0
        for i in range(-1, 2):
            for j in range(-1, 2):
                if not (i == 0 and j == 0) and is_valid_location(len(self.world), i + x, j + y):
                    curr_cell = self.world[i + x][j + y]
                    if curr_cell != Actor.NONE:
                        surrounding_cells += 1
                        if curr_cell == self.world[x][y]:
                            same_colored_cells += 1
        if surrounding_cells == 0:
            return 1
        return same_colored_cells / surrounding_cells

    def shuffle_unsatisfied_cells(self, new_cell_map, valid_pos, cell_pos):
        """
            Shuffles location of unsatisfied cells to all eligible cell locations, and returns it.
        """
        shuffle(valid_pos)
        for i in range(len(cell_pos)):
            old_pos = cell_pos[i]
            new_pos = valid_pos[i]
            # print(f"Old: {old_pos[0]}, {old_pos[1]}\nNew: {new_pos[0]}, {new_pos[1]}")
            new_cell_map[new_pos[0]][new_pos[1]] = self.world[old_pos[0]][old_pos[1]]
        return new_cell_map

    # ########### the rest of this class is already defined, to handle the simulation clock  ###########
    def __init__(self, size):
        self.size = size
        self.world: World = self.__create_world()
        self.observers = []  # for enabling discoupled updating of the view, ignore

    def run(self):
        clock = pg.time.Clock()
        running = True
        while running:
            running = self.__on_clock_tick(clock)
        # stop running
        print("Goodbye!")
        pg.quit()

    def __on_clock_tick(self, clock):
        clock.tick(self.FRAME_RATE)  # update no faster than FRAME_RATE times per second
        self.__update_and_notify()
        return self.__check_for_exit()

    # What to do each frame
    def __update_and_notify(self):
        self.__update_world()
        self.__notify_all()

    @staticmethod
    def __check_for_exit() -> bool:
        keep_going = True
        for event in pg.event.get():
            # Did the user click the window close button?
            if event.type == pg.QUIT:
                keep_going = False
        return keep_going

    # Use an Observer pattern for views
    def add_observer(self, observer):
        self.observers.append(observer)

    def __notify_all(self):
        for observer in self.observers:
            observer.on_world_update()


# ---------------- Helper methods ---------------------

# Check if inside world
def is_valid_location(size: int, row: int, col: int):
    return 0 <= row < size and 0 <= col < size


def add_element(actor_type, percentage, size):
    """
        Adds elements to a list. 
    """
    amount = round(size * size * percentage) # Sets the amount of elements to input to the given specifications.
    array = []
    for i in range(amount):
        array.append(actor_type) # Adds the elements to the array.
    return array


def list_to_matrix(size, array):
    """
        Converts given list to a matrix.
    """
    matrix = []
    start = 0
    end = add = size
    for i in range(size):
        matrix.append(array[start:end]) # Adds array to the matrix given start and end points of elements in the array.
        start += add 
        end += add # Once all elements are in the matrix, go to next row and do the same in the matrix and continue adding upcoming elements.
    return matrix


# ------- Testing -------------------------------------

# Here you run your tests i.e. call your logic methods
# to see that they really work
def test():
    # A small hard coded world for testing
    test_world = [
        [Actor.RED, Actor.RED, Actor.NONE],
        [Actor.NONE, Actor.BLUE, Actor.NONE],
        [Actor.RED, Actor.NONE, Actor.BLUE]
    ]

    test_world_list = matrix_to_list(test_world) # Turns the matrix into an array.

    th = 0.5  # Simpler threshold used for testing

    size = len(test_world)
    print(is_valid_location(size, 0, 0))
    print(not is_valid_location(size, -1, 0))
    print(not is_valid_location(size, 0, 3))
    print(is_valid_location(size, 2, 2))
    # TODO More tests
    print(count(test_world_list, Actor.NONE) == 4)
    print(count(test_world_list, Actor.RED) == 3)
    print(count(test_world_list, Actor.BLUE) == 2)


    exit(0)


# Helper method for testing
def count(a_list, to_find):
    the_count = 0
    for a in a_list:
        if a == to_find:
            the_count += 1
    return the_count


def matrix_to_list(matrix):
    temp_list = []
    for row in matrix:
        for elem in row:
            temp_list.append(elem)
    return temp_list

# ###########  NOTHING to do below this row, it's pygame display stuff  ###########
# ... but by all means have a look at it, it's fun!
class NeighboursView:
    # static class variables
    WIDTH = 700   # Size for window
    HEIGHT = 700
    MARGIN = 50

    WHITE = (255, 255, 255)
    RED   = (255,   0,   0)
    BLUE  = (  0,   0, 255)

    # Instance methods

    def __init__(self, model: NeighboursModel):
        pg.init()  # initialize pygame, in case not already done
        self.dot_size = self.__calculate_dot_size(len(model.world))
        self.screen = pg.display.set_mode([self.WIDTH, self.HEIGHT])
        self.model = model
        self.model.add_observer(self)

    def render_world(self):
        # # Render the state of the world to the screen
        self.__draw_background()
        self.__draw_all_actors()
        self.__update_screen()

    # Needed for observer pattern
    # What do we do every time we're told the model had been updated?
    def on_world_update(self):
        self.render_world()

    # private helper methods
    def __calculate_dot_size(self, size):
        return max((self.WIDTH - 2 * self.MARGIN) / size, 2)

    @staticmethod
    def __update_screen():
        pg.display.flip()

    def __draw_background(self):
        self.screen.fill(NeighboursView.WHITE)

    def __draw_all_actors(self):
        for row in range(len(self.model.world)):
            for col in range(len(self.model.world[row])):
                self.__draw_actor_at(col, row)

    def __draw_actor_at(self, col, row):
        color = self.__get_color(self.model.world[row][col])
        xy = self.__calculate_coordinates(col, row)
        pg.draw.circle(self.screen, color, xy, self.dot_size / 2)

    # This method showcases how to nicely emulate 'switch'-statements in python
    @staticmethod
    def __get_color(actor):
        return {
            Actor.RED: NeighboursView.RED,
            Actor.BLUE: NeighboursView.BLUE
        }.get(actor, NeighboursView.WHITE)

    def __calculate_coordinates(self, col, row):
        x = self.__calculate_coordinate(col)
        y = self.__calculate_coordinate(row)
        return x, y

    def __calculate_coordinate(self, offset):
        x: float = self.dot_size * offset + self.MARGIN
        return x


if __name__ == "__main__":
    neighbours()
