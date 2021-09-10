# /*
#  * The Pig game
#  * See http://en.wikipedia.org/wiki/Pig_%28dice_game%29
#  */
from random import randrange


def run():
    win_points = 20  # Points to win
    aborted = False
    players = get_players()
    welcome_msg(win_points)
    status_msg(players)
    current_player = randomize_starting_player(players)

    aborted = game_loop(current_player, players, win_points)
    game_over_msg(current_player, aborted)


class Player:

    def __init__(self, def_id, name=''):
        self.name = name  # default ''
        self.totalPts = 0  # Total points for all rounds
        self.roundPts = 0  # Points for a single round
        self.id = def_id  # Unique ID for each player


# ---- Game logic methods --------------
def randomize_starting_player(players):
    return players[randrange(0, len(players))]


def game_loop(current_player, players, win_pts):
    while True:
        choice = get_player_choice(current_player)
        if choice == "q":
            return True
        elif choice == "n":
            current_player = next_player(current_player, players)
        elif choice == "r":
            is_1 = roll(current_player)
            if is_1:
                current_player = next_player(current_player, players)
            else:
                if check_win(current_player, win_pts):
                    return False
        else:
            print("Invalid input; Commands are: r = roll , n = next, q = quit")


def roll(player):
    result = randrange(1, 6)
    player.roundPts += result
    round_msg(result, player)
    if result == 1:
        player.roundPts = 0
        return True
    return False


def next_player(player, players):
    player.totalPts += player.roundPts
    player.roundPts = 0
    if player.id == len(players) - 1:
        return players[0]
    return players[player.id+1]


def check_win(current_player, win_points):
    if (current_player.roundPts + current_player.totalPts) >= win_points:
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


def game_over_msg(player, is_aborted):
    if is_aborted:
        print("Aborted")
    else:
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
            
        if amount_players <= 1:
            print("Invalid input, must be an integer larger than 1")

    players = []
    for i in range(amount_players):
        name = input("Name of player %r? " % (i + 1))
        players.append(Player(i, name))

    return players


if __name__ == "__main__":
    run()
