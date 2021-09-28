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
from random import shuffle
from enum import Enum, auto
from collections import deque
import pygame


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


class Deck:
    def __init__(self):
        self.card_pile = self.gen_new_deck()
        self.drawn_cards = deque()

    def draw_card(self):
        # Conditional shuffle function
        self.shuffle_discard_pile()
        card = self.card_pile.pop()
        self.drawn_cards.append(card)

    def deal_card(self):
        return self.card_pile.pop()

    # TODO: Set a better name
    # Shuffles back drawn cards into deck if deck is empty
    def shuffle_discard_pile(self):
        if len(self.card_pile) < 1:
            for card in self.drawn_cards:
                self.card_pile.append(card)

    def gen_new_deck(self):
        new_deck = self.get_all_cards()
        shuffle(new_deck)
        return new_deck

    def get_all_cards(self):
        all_cards = deque()
        for suite in Suite:
            all_cards += self.add_suite(suite)
        return all_cards

    @staticmethod
    def add_suite(suite: Suite):
        suite_cards = deque()
        for rank in Rank:
            suite_cards.append(Card(suite, rank))
        return suite_cards


class Board:
    def __init__(self, deck: Deck):
        self.board = self.gen_board(deck)
        # self.print_board()
        pass

    def move_cards(self):
        pass

    # Print cards in board for debugging
    def print_board(self):
        for column in self.board:
            print(f"Column {self.board.index(column)}")
            for card in column:
                print(f"{card.rank.name} {card.suite.name}")

    @staticmethod
    def gen_board(deck):
        temp_board = []
        for i in range(7):
            column = deque()
            for j in range(i+1):
                column.append(deck.deal_card())
            temp_board.append(column)

        return temp_board


class Game:

    observers = []

    SCREEN_WIDTH = 1600
    SCREEN_HEIGHT = 900
    SCREEN_RATIO = SCREEN_WIDTH / SCREEN_HEIGHT

    def __init__(self):
        self.deck = Deck()
        self.board = Board(self.deck)
        self.clock = pygame.time.Clock()

    def update(self):
        self.notify_observers()  # Update Render

    def run(self):
        run = True
        # Main loop
        while run:
            self.update()
            # Ensure program maintains a rate of 60 frames per second
            self.clock.tick(60)
            # Look at every event in the queue
            for event in pygame.event.get():
                # Did the user hit a key?
                if event.type == pygame.QUIT:
                    run = False

    def add_observer(self, observer):
        self.observers.append(observer)

    def notify_observers(self):
        for observer in self.observers:
            observer.notify()


class Card:

    img_card_back = pygame.transform.rotozoom(pygame.image.load("cards/card_back.png"), 0, 0.2)

    def __init__(self, suite: Suite, rank: Rank, hidden: bool = True):
        self.suite = suite
        self.rank = rank
        self.hidden = hidden
        self.size = self.img_card_back.get_size()
        self.img_card_front = self.get_card_front()

    def get_card_front(self):
        return pygame.image.load(f"cards/{self.suite.name}_{self.rank.name}.png")


class GameView:

    # Currently disgusting green
    color_background = (31, 125, 50)

    def __init__(self, game: Game):
        pygame.display.set_caption('Solitaire')
        self.game = game
        self.screen_width = Game.SCREEN_WIDTH
        self.screen_height = Game.SCREEN_HEIGHT
        self.screen = pygame.display.set_mode((self.screen_width, self.screen_height))
        self.game.add_observer(self)

    def notify(self):
        self.render()

    def render(self):
        self.screen.fill(self.color_background)
        self.render_card(Card(Suite.Clubs, Rank.Seven), (100, 100))
        pygame.display.flip()

    def render_board(self):
        pass

    def render_deck(self):
        pass

    def render_card(self, card: Card, position: (int, int)):
        if card.hidden:
            self.screen.blit(card.img_card_back, position)
        else:
            self.screen.blit(card.img_card_front, position)


def klondike_game():
    pygame.init()
    game = Game()
    GameView(game)
    game.run()
    pass


if __name__ == "__main__":
    klondike_game()
