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

    # Draws card and puts it in drawn pile
    def draw_card(self):
        # Conditional shuffle function
        self.refill_draw_pile()
        card = self.card_pile.pop()
        self.drawn_cards.append(card)

    # Draws and removes card from deck
    def deal_card(self):
        return self.card_pile.pop()

    # Shuffles back drawn cards into deck if deck is empty
    def refill_draw_pile(self):
        if len(self.card_pile) < 1:
            for card in self.drawn_cards:
                self.card_pile.append(card)

    # Returns a shuffled and full deck
    def gen_new_deck(self):
        new_deck = self.get_all_cards()
        shuffle(new_deck)
        return new_deck

    # Returns all cards that are going to be in play
    def get_all_cards(self):
        all_cards = deque()
        for suite in Suite:
            all_cards += self.get_suite(suite)
        return all_cards

    # Returns all the cards in a suite
    @staticmethod
    def get_suite(suite: Suite):
        suite_cards = deque()
        for rank in Rank:
            suite_cards.append(Card(suite, rank))
        return suite_cards

    def print_cards(self):
        print(f"Cards in draw pile: {len(self.card_pile)}")
        for card in self.card_pile:
            print(f"Suite: {card.suite.name}, Rank: {card.rank.name}")
        print(f"Cards in discard pile: {len(self.drawn_cards)}")
        for card in self.drawn_cards:
            print(f"Suite: {card.suite.name}, Rank: {card.rank.name}")


class Foundation:
    def __init__(self):
        self.foundations = [
            [],  # Hearts
            [],  # Diamonds
            [],  # Spades
            []  # Clubs
        ]

    def get_foundations(self):
        return self.foundations


class Board:
    def __init__(self, deck: Deck):
        self.board = self.gen_board(deck)
        self.flip_top_cards()
        self.move_pile = deque()

    # Moves a set of cards from one column to another
    def move_cards(self):
        # TODO: Move cards logic
        pass

    def get_board(self):
        return self.board

    def flip_top_cards(self):
        for column in self.board:
            card = column.pop()
            card.flip()
            column.append(card)

    # Prints cards in board to console for debugging
    def print_board(self):
        for column in self.board:
            print(f"Column {self.board.index(column)}")
            for card in column:
                print(f"{card.rank.name} {card.suite.name}")

    # Generates board by drawing cards from deck
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
        self.foundation = Foundation()
        self.deck = Deck()
        self.board = Board(self.deck)
        self.clock = pygame.time.Clock()

    # Game update (tick)
    def update(self):
        # TODO: Update board, take input, etc
        self.notify_observers()  # Update Render

    # Executed at game start, main game loop
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

    __transform_value = 0.2
    img_card_back = pygame.transform.rotozoom(pygame.image.load("cards/card_back.png"), 0, __transform_value)

    def __init__(self, suite: Suite, rank: Rank, hidden: bool = True):
        self.suite = suite
        self.rank = rank
        self.hidden = hidden
        self.size = self.img_card_back.get_size()
        self.img_card_front = pygame.transform.smoothscale(self.get_card_front(), self.get_card_size())

    def flip(self):
        self.hidden = not self.hidden

    # Loads card front image for the specific card
    def get_card_front(self):
        return pygame.image.load(f"cards/{self.suite.name}_{self.rank.name}.png")

    def get_card_size(self):
        return self.img_card_back.get_size()


class GameView:
    # Currently disgusting green
    color_background = (31, 125, 50)
    # Currently yellow/gold-ish
    color_card_highlight = (255, 221, 0)
    margin_card = 20
    margin_game_window = 30

    def __init__(self, game: Game):
        pygame.display.set_caption('Solitaire')
        self.game = game
        self.screen_width = Game.SCREEN_WIDTH
        self.screen_height = Game.SCREEN_HEIGHT
        self.screen = pygame.display.set_mode((self.screen_width, self.screen_height))
        self.game.add_observer(self)
        # Init render variables
        self.offset_card = Card.img_card_back.get_size()
        self.margin_foundation = (
                                    self.margin_game_window + 2 * self.margin_card + self.offset_card[0],
                                    self.margin_game_window + self.margin_card + self.offset_card[1]
                                 )
        # Game images
        self.img_card_absent = pygame.transform.smoothscale(
            pygame.image.load("cards/no_card.png"),
            Card.img_card_back.get_size())
        self.img_card_highlight = self.img_card_absent.copy()
        self.img_card_highlight.fill(self.color_card_highlight, None, special_flags=pygame.BLEND_ADD)

    # Called by observers when its time to render
    def notify(self):
        self.render()

    # Renders the screen
    def render(self):
        self.screen.fill(self.color_background)
        self.render_foundations()
        self.render_deck()
        self.render_board()

        pygame.display.flip()

    # Renders the game board
    def render_board(self):
        curr_board = self.game.board.get_board()
        selected_cards = 0  # TODO: Implement card selection visual
        for i, column in enumerate(curr_board):
            for j, card in enumerate(column):
                pos = (
                    self.margin_game_window + i * self.margin_card + i * self.offset_card[0],  # X pos
                    self.margin_foundation[0] + self.margin_card * j  # Y pos
                )
                self.render_card(card, pos)
        pass

    # Renders the deck
    def render_deck(self):
        # TODO: Render Deck logic
        pass

    def render_foundations(self):
        curr_foundations = self.game.foundation.get_foundations()
        for i, foundation in enumerate(curr_foundations):
            pos = self.margin_game_window + i * self.margin_card + i * self.offset_card[0], self.margin_game_window
            if len(foundation) > 0:
                self.render_card(foundation[len(foundation) - 1], pos)
            else:
                self.render_absent_card(pos)

    # Renders a card and takes into account if its hidden or not
    def render_card(self, card: Card, position: (int, int), selected: bool = False):
        if card.hidden:
            self.screen.blit(card.img_card_back, position)
        else:
            self.screen.blit(card.img_card_front, position)
        if selected:
            self.screen.blit(self.img_card_highlight, position)

    def render_absent_card(self, position):
        self.screen.blit(self.img_card_absent, position)


def klondike_game():
    pygame.init()
    game = Game()
    GameView(game)
    game.run()
    pass


if __name__ == "__main__":
    klondike_game()
