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
    size = img_card_back.get_size()

    def __init__(self, suite: Suite, rank: Rank, hidden: bool = True):
        self.suite = suite
        self.rank = rank
        self.hidden = hidden
        self.position = (0, 0)
        self.is_selected = False
        self.size = self.img_card_back.get_size()
        self.img_card_front = pygame.transform.smoothscale(self.get_card_front(), self.get_card_size())

    # Changes whether the card is hidden or not
    def set_visibility(self, hidden: bool = False):
        self.hidden = hidden

    # If the card isn't hidden it toggles if its selected
    def toggle_selected(self):
        if not self.hidden:
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
        card.set_visibility()
        self.drawn_cards.append(card)

    # Draws and removes card from deck
    def deal_card(self):
        return self.card_pile.pop()

    # Returns card at the top of the drawn pile
    def get_top_card(self):
        return self.drawn_cards[len(self.drawn_cards) - 1]

    # Set position of drawn cards and card pile
    def set_position(self, position_drawn_cards: tuple[int, int], position_card_pile: tuple[int, int]):
        self.positions[0] = position_drawn_cards
        self.positions[1] = position_card_pile

    # Returns a list of the position of drawn cards and card pile
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
        self.column_start_positions: list[tuple[int, int]] = [
            (0, 0),
            (0, 0),
            (0, 0),
            (0, 0),
            (0, 0),
            (0, 0),
            (0, 0)
        ]

    # Moves a set of cards from one column to another
    def move_cards(self):
        # TODO: Move cards logic
        pass

    # Sets start position of indexed column
    def set_column_position(self, index: int, position: tuple[int, int]):
        if -1 < index < len(self.column_start_positions):
            self.column_start_positions[index] = position
        else:
            print("Error: Faulty index given to column position")

    # Returns board
    def get_board(self):
        return self.board

    # Flips all cards at the top of each column
    def __flip_top_cards(self):
        for column in self.board:
            card = column.pop()
            card.set_visibility()
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
        # Mouse click variables
        self.click_last_tick = False

    # Game update (tick)
    def update(self):
        # TODO: Update board, take input, etc
        if self.get_left_mouse_click():
            # TODO: Check what got clicked on
            print("Registered click")
            self.get_object_clicked()
        self.notify_observers()  # Update Render

    def get_object_clicked(self):
        # TODO: Implement function
        pass

    # Returns True if left mouse button was clicked
    def get_left_mouse_click(self) -> bool:
        did_click = False
        press_curr_tick = pygame.mouse.get_pressed(num_buttons=3)[0]
        if press_curr_tick is False and self.click_last_tick is True:
            did_click = True
        self.click_last_tick = press_curr_tick
        return did_click

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
            pygame.event.pump()

    def add_observer(self, observer):
        self.observers.append(observer)

    def notify_observers(self):
        for observer in self.observers:
            observer.notify()


class GameView:
    use_second_layout = True
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
        self.offset_card = Card.size
        self.margin_foundation = (
                                    self.margin_game_window + 2 * self.margin_card + self.offset_card[0],
                                    self.margin_game_window + self.margin_card + self.offset_card[1]
                                 )
        # Game images
        self.img_card_absent = pygame.transform.smoothscale(
            pygame.image.load("cards/no_card.png"),
            Card.size)
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

    def render_layout(self):
        self.render_foundations()
        self.render_deck()
        self.render_board()

    # Renders the game board
    def render_board(self):
        positions = self.game.board.column_start_positions
        for i, column in enumerate(self.game.board.get_board()):
            if len(column) < 1:
                self.render_absent_card(positions[i])
            else:
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
                if j == 1:
                    self.game.board.set_column_position(i, (pos_x, pos_y))
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


def klondike_game():
    pygame.init()
    game = Game()
    GameView(game)
    game.run()


if __name__ == "__main__":
    klondike_game()
