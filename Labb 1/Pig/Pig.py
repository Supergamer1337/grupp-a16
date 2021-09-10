# /*
#  * The Pig game
#  * See http://en.wikipedia.org/wiki/Pig_%28dice_game%29
#  */
from random import randrange


def run():
    win_points = 20  # Points to win
    players = get_players()
    welcome_msg(win_points)
    status_msg(players)
    current_player = randomize_starting_player(players)
    game_loop(current_player, players, win_points)


class Player:

    def __init__(self, name=''):
        self.name = name  # default ''
        self.totalPts = 0  # Total points for all rounds
        self.roundPts = 0  # Points for a single round

    def clear_round_points(self):
        self.roundPts = 0

    def add_round_points(self, points):
        if points != 1:
            self.roundPts += points

    def add_total_points(self):
        self.totalPts += self.roundPts
        self.clear_round_points()


# ---- Game logic methods --------------
def randomize_starting_player(players):
    return players[randrange(0, len(players))]


def game_loop(current_player, players, win_pts):
    won = False
    while not won:
        choice = get_player_choice(current_player)
        if choice == "q":
            print("Aborted")
            break
        elif choice == "n":
            current_player = next_round(current_player, players)
        elif choice == "r":
            current_player = roll(current_player, players)
            won = check_win(current_player, win_pts)
        else:
            print("Invalid input; Commands are: r = roll , n = next, q = quit")


def roll(current_player, players):
    result = roll_dice()
    current_player.add_round_points(result)
    round_msg(result, current_player)
    if result == 1:
        current_player.clear_round_points()
        return next_round(current_player, players)
    return current_player


def roll_dice():
    return randrange(1, 6)


def next_round(current_player, players):
    current_player.add_total_points()
    status_msg(players)
    return next_player(current_player, players)


def next_player(current_player, players):
    player_index = players.index(current_player)
    if player_index == len(players) - 1:
        return players[0]
    return players[player_index + 1]


def check_win(current_player, win_points):
    if (current_player.roundPts + current_player.totalPts) >= win_points:
        game_over_msg(current_player)
        return True
    return False


# ---- IO Methods --------------
def welcome_msg(win_pts):
    print("Welcome to PIG!")
    print("First player to get " + str(win_pts) + " points will win!")
    print("Commands are: r = roll , n = next, q = quit")


def status_msg(the_players):
    print("Points: ")
    for player in the_players:
        print("\t" + player.name + " = " + str(player.totalPts) + " ")


def round_msg(result, current_player):
    if result > 1:
        print("Got " + str(result) + " running total are " + str(current_player.roundPts))
        print("Your total now is %r" % (current_player.totalPts + current_player.roundPts))
    else:
        print("Got 1 lost it all!")


def game_over_msg(player):
    print("Game over! Winner is player " + player.name + " with "
          + str(player.totalPts + player.roundPts) + " points")


def get_player_choice(player):
    return input("Player is " + player.name + " > ")


def get_players():
    amount_players = 0
    while amount_players < 2:
        try:
            amount_players = int(input("How many players are playing? "))
        except:
            pass

        if amount_players < 2 or amount_players > 10:
            print("Invalid input, must be an integer larger than 1 and less than 10")

    players = []
    for i in range(amount_players):
        name = input("Name of player %r? " % (i + 1))
        players.append(Player(name))

    return players


if __name__ == "__main__":
    run()
