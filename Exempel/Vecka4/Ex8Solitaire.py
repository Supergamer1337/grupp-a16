# package exercises

# Implement the classic solitaire card game Klondike:
# https://en.wikipedia.org/wiki/Klondike_(solitaire)
# You can choose whether to implement a graphical interface using pygame,
# or a text based command-line interface.
#
# See:
# - UseAStack
# - Exceptions
# - UseAList
# - UseString
# - SwitchStatement

from enum import Enum, auto


class Suite(Enum):
    Hearts = auto()
    Spades = auto()
    Clubs = auto()
    Diamonds = auto()


class Rank(Enum):
    Ace = 1
    Two = 2
    Three = 3
    Four = 4
    Five = 5
    Six = 6
    Seven = 7
    Eight = 8
    Nine = 9
    Ten = 10
    Jack = 11
    Queen = 12
    King = 13


class Card:
    def __init__(self, suite: Suite, rank: Rank, hidden: bool = True):
        self.suite = suite
        self.rank = rank
        self.hidden = hidden


class Board:
    def __init__(self):
        self.gen_board()
        pass

    def gen_board(self):
        pass

    def move_card(self):
        pass


class Game:
    pass


class GameView:
    def __init__(self, game: Game):
        pass


def klondike_game():
    game = Game()
    view = GameView(game)
    game.run()
    pass


if __name__ == "__main__":
    klondike_game()
