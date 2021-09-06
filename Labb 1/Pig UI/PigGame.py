from tkinter import *
from random import randrange
# https://www.tutorialspoint.com/python/tk_pack.htm
# https://docs.python.org/3/library/tkinter.html#a-very-quick-look-at-tcl-tk


class PigGame(Frame):
    def __init__(self, master=None):
        super().__init__(master)
        self.master = master
        self.pack()
        self.width = 800
        self.height = 640
        self.run_game = True
        self.in_game = False
        self.current_screen = MenuScreen(self.width, self.height)

    def run(self):
        while self.run_game:
            root.mainloop()

    def start_game(self, player_list):
        self.in_game = True
        self.current_screen = GameScreen(self.width, self.height, player_list)

    def exit(self):
        root.destroy()
        self.run_game = False
        exit()

    def start_menu(self):
        self.in_game = False
        self.current_screen = MenuScreen(self.width, self.height)


class MenuScreen:
    def __init__(self, width, height):
        self.frame_root = Frame(root, height=height, width=width)
        self.frame_input_cont = Frame(self.frame_root)
        self.frame_list_cont = Frame(self.frame_root)
        self.frame_title_cont = Frame(self.frame_root)
        self.frame_start_cont = Frame(self.frame_root)
        # Labels
        self.label_title = Label(self.frame_title_cont, text="Pig Game")
        # Buttons
        self.btn_name_add = Button(self.frame_input_cont, text="Add Player", command=self.add_name)
        self.btn_name_remove = Button(self.frame_input_cont, text="Remove Player", command=self.remove_name)
        self.btn_start = Button(self.frame_start_cont, text="Start", command=self.start_game)
        # Textbox
        self.input_player = Text(self.frame_input_cont, height=1, width=19)
        # Listbox
        self.list_player = Listbox(self.frame_list_cont)

        self.set_style()

        # Set enter key to run function
        root.bind('<Return>', (lambda event: self.add_name()))

    def set_style(self):
        # https://www.pythontutorial.net/tkinter/tkinter-pack/
        # Frame
        self.frame_root.pack()
        self.frame_title_cont.pack(side=TOP, ipadx=10, ipady=10)
        self.frame_start_cont.pack(side=BOTTOM, ipadx=10, ipady=10)
        self.frame_input_cont.pack(side=RIGHT)
        self.frame_list_cont.pack(side=LEFT, ipadx=10, ipady=10)
        # Title & Start Container
        self.label_title.pack(side=TOP, expand=True)
        self.btn_start.pack(side=BOTTOM, expand=True)
        # Input Container
        self.input_player.pack(side=BOTTOM)
        self.btn_name_remove.pack(side=RIGHT)
        self.btn_name_add.pack(side=RIGHT)
        # List Container
        self.list_player.pack(side=TOP, expand=True)

    def add_name(self):
        name = self.input_player.get("1.0", "end-1c").rstrip('\n')
        if not len(name) < 1:
            self.list_player.insert(END, name)
            self.input_player.delete("1.0", "end-1c")

    def remove_name(self):
        index = self.list_player.curselection()
        if index:
            self.list_player.delete(index)

    def start_game(self):
        length = int(self.list_player.size())
        if not length < 2:
            game.start_game(self.list_player.get(0, length - 1))
            self.frame_root.destroy()


class GameScreen:
    def __init__(self, width, height, player_list):
        self.players = self.create_players(player_list)
        self.current_player = self.players[randrange(0, len(self.players))]
        # Graphics
        self.frame = Frame(root, height=height, width=width)
        self.frame.pack(side=TOP)
        # tk string var
        self.label_cp_str = StringVar()
        self.label_cp_str.set("Current player: " + self.current_player.name)
        self.label_result_str = StringVar()
        self.label_result_str.set("Dice result: ")
        self.label_curr_pts_str = StringVar()
        self.update_display_points()
        self.label_win_str = StringVar()
        # Labels
        self.label_current_player = Label(self.frame, textvariable=self.label_cp_str)
        self.label_result = Label(self.frame, textvariable=self.label_result_str)
        self.label_curr_pts = Label(self.frame, textvariable=self.label_curr_pts_str)
        self.label_win = Label(self.frame, textvariable=self.label_win_str)
        # Buttons
        self.btn_roll = Button(self.frame, text="Roll", command=self.roll)
        self.btn_next = Button(self.frame, text="Next", command=self.next_player)
        self.btn_quit = Button(self.frame, text="Quit", command=self.quit_game)

        self.set_style()

        self.win_pts = 20
        root.bind('<r>', (lambda event: self.roll()))
        root.bind('<n>', (lambda event: self.next_player()))
        root.bind('<q>', (lambda event: self.quit_game()))

    def set_style(self):
        # Label
        self.label_current_player.pack(side=TOP)
        self.label_result.pack(side=BOTTOM)
        self.label_curr_pts.pack(side=RIGHT)
        # Buttons
        self.btn_roll.pack(side=LEFT)
        self.btn_next.pack(side=LEFT)
        self.btn_quit.pack(side=LEFT)

    def roll(self):
        result = randrange(1, 6)
        self.label_result_str.set(self.current_player.name + " rolled: " + str(result))
        if result == 1:
            self.current_player.rnd_pts = 0
            self.next_player()
        else:
            self.current_player.rnd_pts += result
            self.win()
        self.update_display_points()

    def update_display_points(self):
        self.label_curr_pts_str.set(self.current_player.name +
                                    " Points:\nTotal: " + str(self.current_player.tot_pts) +
                                    "\nRound: " + str(self.current_player.rnd_pts))

    def create_players(self, player_list):
        players = []
        for i in range(len(player_list)):
            players.append(Player(i, player_list[i]))
        return players

    def next_player(self):
        self.current_player.tot_pts += self.current_player.rnd_pts
        self.current_player.rnd_pts = 0
        # Set next player
        if self.current_player.id == len(self.players) - 1:
            self.current_player = self.players[0]
        else:
            self.current_player = self.players[self.current_player.id + 1]
        # Update label
        self.label_cp_str.set("Current player: " + self.current_player.name)
        self.update_display_points()

    def quit_game(self):
        self.frame.destroy()
        game.start_menu()

    def win(self):
        if self.current_player.rnd_pts + self.current_player.tot_pts >= self.win_pts:
            self.btn_roll.destroy()
            self.btn_next.destroy()
            self.label_curr_pts.destroy()
            self.label_result.destroy()
            self.label_current_player.destroy()
            self.btn_quit.pack(side=BOTTOM)
            self.label_win_str.set(self.current_player.name + " has WON with a total of " +
                                   str(self.current_player.tot_pts + self.current_player.rnd_pts))
            self.label_win.pack(side=TOP)


class Player:
    def __init__(self, def_id, name=''):
        self.name = name
        self.tot_pts = 0
        self.rnd_pts = 0
        self.id = def_id


if __name__ == "__main__":
    root = Tk()
    game = PigGame(master=root)
    game.master.title("Pig Game")
    game.master.maxsize(1600, 900)
    game.run()
