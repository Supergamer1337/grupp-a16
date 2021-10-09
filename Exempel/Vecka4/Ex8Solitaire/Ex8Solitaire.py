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
from enum import Enum
from collections import deque
import pygame


class Suite(Enum):
    Hearts = 0
    Diamonds = 1
    Spades = 2
    Clubs = 3


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
    __transform_value = 0.3
    img_card_back = pygame.transform.rotozoom(pygame.image.load("cards/card_back.png"), 0, __transform_value)

    def __init__(self, suite: Suite, rank: Rank, hidden: bool = True):
        self.suite = suite
        self.rank = rank
        self.hidden = hidden
        self.position = (0, 0)
        self.is_selected = False
        self.size = self.img_card_back.get_size()
        self.img_card_front = pygame.transform.smoothscale(self.get_card_front(), self.get_card_size())

    def flip(self):
        self.hidden = not self.hidden

    def set_selected(self, selected: bool):
        self.is_selected = selected

    def toggle_selected(self):
        self.is_selected = not self.is_selected

    def set_position(self, position: tuple[int, int]):
        self.position = position

    # Loads card front image for the specific card
    def get_card_front(self):
        return pygame.image.load(f"cards/{self.suite.name}_{self.rank.name}.png")

    def get_card_size(self):
        return self.img_card_back.get_size()


class Deck:
    def __init__(self):
        self.card_pile = self.__gen_new_deck()
        self.drawn_cards = deque()
        self.draw_card()
        self.positions = [
            (0, 0),  # drawn cards pos
            (0, 0)  # card pile pos
        ]

    # Draws card and puts it in drawn pile
    def draw_card(self):
        # Conditional shuffle function
        self.__refill_draw_pile()
        card = self.card_pile.pop()
        card.flip()
        self.drawn_cards.append(card)

    # Draws and removes card from deck
    def deal_card(self):
        return self.card_pile.pop()

    # Returns card at the top of the drawn pile
    def get_top_card(self):
        return self.drawn_cards[len(self.drawn_cards) - 1]

    def set_position(self, position_drawn_cards: tuple[int, int], position_card_pile: tuple[int, int]):
        self.positions[0] = position_drawn_cards
        self.positions[1] = position_card_pile

    def get_positions(self):
        return self.positions

    # Shuffles back drawn cards into deck if deck is empty
    def __refill_draw_pile(self):
        if len(self.card_pile) < 1:
            for card in self.drawn_cards:
                self.card_pile.append(card)

    # Returns a shuffled and full deck
    def __gen_new_deck(self):
        new_deck = self.__get_all_cards()
        shuffle(new_deck)
        return new_deck

    # Returns all cards that are going to be in play
    def __get_all_cards(self):
        all_cards = deque()
        for suite in Suite:
            all_cards += self.__get_suite(suite)
        return all_cards

    # Returns all the cards in a suite
    @staticmethod
    def __get_suite(suite: Suite):
        suite_cards = deque()
        for rank in Rank:
            suite_cards.append(Card(suite, rank))
        return suite_cards

    # Prints cards in both piles to console for debugging
    def __print_cards(self):
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
        self.f_positions: list[tuple[int, int]] = [(0, 0), (0, 0), (0, 0), (0, 0)]

    def get_foundation(self, suite) -> list[Card]:
        return self.foundations[suite.value]

    def get_foundation_position(self, suite):
        return self.f_positions[suite.value]

    def set_foundation_position(self, suite: Suite, position: tuple[int, int]):
        self.f_positions[suite.value] = position


class Board:
    def __init__(self, deck: Deck):
        self.board = self.__gen_board(deck)
        self.__flip_top_cards()
        self.move_pile = deque()

    # Moves a set of cards from one column to another
    def move_cards(self):
        # TODO: Move cards logic
        pass

    def get_board(self):
        return self.board

    # Flips all cards at the top of each column
    def __flip_top_cards(self):
        for column in self.board:
            card = column.pop()
            card.flip()
            column.append(card)

    # Generates board by drawing cards from deck
    @staticmethod
    def __gen_board(deck):
        temp_board = []
        for i in range(7):
            column = deque()
            for j in range(i+1):
                column.append(deck.deal_card())
            temp_board.append(column)
        return temp_board

    # Prints cards in board to console for debugging
    def __print_board(self):
        for column in self.board:
            print(f"Column {self.board.index(column)}")
            for card in column:
                print(f"{card.rank.name} {card.suite.name}")


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


class GameView:
    use_second_layout = False
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
        self.set_positions()

    # Called by observers when its time to render
    def notify(self):
        self.render()

    # Renders the screen
    def render(self):
        self.screen.fill(self.color_background)
        self.render_layout()
        pygame.display.flip()

    def set_positions(self):
        self.set_foundations_position()
        self.set_board_position()
        self.set_deck_position()

    def set_foundations_position(self):
        for i, suite in enumerate(Suite):
            pos = self.margin_game_window + i * self.margin_card + i * self.offset_card[0], self.margin_game_window
            if self.use_second_layout:
                base_x = self.screen_width - self.margin_game_window - self.offset_card[0]
                base_y = self.margin_game_window
                pos = base_x - i * self.margin_card - i * self.offset_card[0], base_y
                if i > 1:
                    pos = base_x - (i - 2) * self.margin_card - (i - 2) * self.offset_card[0], base_y + \
                          self.offset_card[1] + self.margin_card
            self.game.foundation.set_foundation_position(suite, pos)

    def set_board_position(self):
        curr_board = self.game.board.get_board()
        for i, column in enumerate(curr_board):
            for j, card in enumerate(column):
                pos_x = self.margin_game_window + i * self.margin_card + i * self.offset_card[0]
                pos_y = self.margin_foundation[1] + self.margin_card * j
                if self.use_second_layout:
                    pos_y = self.margin_game_window + self.margin_card * j
                card.set_position((pos_x, pos_y))

    def set_deck_position(self):
        drawn_cards = self.margin_game_window + self.margin_card * 5 + self.offset_card[0] * 4, self.margin_game_window
        rotate_offset = int((self.offset_card[1] - self.offset_card[0]) / 2)
        card_pile = (drawn_cards[0] + self.offset_card[0] + self.margin_card, drawn_cards[1] + rotate_offset)
        if self.use_second_layout:
            card_pile = (
                self.screen_width - self.margin_game_window - self.offset_card[0],
                self.screen_height * 0.75 - self.offset_card[1] / 2
            )
            drawn_cards = (card_pile[0] - self.margin_card - self.offset_card[0], card_pile[1])
        self.game.deck.set_position(drawn_cards, card_pile)

    def render_layout(self):
        self.render_foundations()
        self.render_deck()
        self.render_board()

    # Renders the game board
    def render_board(self):
        for column in self.game.board.get_board():
            for card in column:
                self.render_card(card, card.position)

    # Renders the deck
    def render_deck(self):
        deck_positions = self.game.deck.get_positions()
        self.render_card(self.game.deck.get_top_card(), deck_positions[0])
        if not self.use_second_layout:
            self.screen.blit(pygame.transform.rotate(Card.img_card_back, 90), deck_positions[1])
        else:
            self.screen.blit(Card.img_card_back, deck_positions[1])

    def render_foundations(self):
        for suite in Suite:
            pos = self.game.foundation.get_foundation_position(suite)
            foundation = self.game.foundation.get_foundation(suite)
            if len(foundation) > 1:
                self.render_card(foundation[len(foundation) - 1], pos)
            else:
                self.render_absent_card(pos)

    # Renders a card and takes into account if its hidden or not
    def render_card(self, card: Card, position: (int, int)):
        if card.hidden:
            self.screen.blit(card.img_card_back, position)
        else:
            self.screen.blit(card.img_card_front, position)
        # TODO: Decide if this is best approach
        if card.is_selected:
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
