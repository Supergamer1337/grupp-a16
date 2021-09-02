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

    def start(self, player_list):
        self.in_game = True
        self.current_screen = GameScreen(self.width, self.height, player_list)

    def switch_game_state(self):
        if self.in_game:
            self.in_game = False
            self.current_screen = MenuScreen(self.width, self.height)
        else:
            self.in_game = True
            self.current_screen = GameScreen(self.width, self.height)


class MenuScreen:
    def __init__(self, width, height):
        self.frame = Frame(root, height=height, width=width)
        # Labels
        self.label_title = Label(self.frame, text="Pig Game")
        # Buttons
        self.btn_name_add = Button(self.frame, text="Add Player", command=self.add_name)
        self.btn_start = Button(self.frame, text="Start", command=self.start_game)
        self.btn_name_remove = Button(self.frame, text="Remove Player", command=self.remove_name)
        # Textbox
        self.input_player = Text(self.frame, height=1, width=10)
        # Listbox
        self.list_player = Listbox(self.frame)

        self.set_style()

    def set_style(self):
        # https://www.pythontutorial.net/tkinter/tkinter-pack/
        # TOD style the game
        # Frame
        self.frame.pack(side=BOTTOM)
        # Labels
        self.label_title.pack(side=TOP)
        # Buttons
        self.btn_start.pack(side=BOTTOM)
        self.btn_name_remove.pack(side=RIGHT)
        self.btn_name_add.pack(side=RIGHT)
        # Textbox
        self.input_player.pack(side=RIGHT)
        # Listbox
        self.list_player.pack(side=TOP)

    def add_name(self):
        name = self.input_player.get("1.0", "end-1c")
        self.list_player.insert(END, name)
        self.input_player.delete("1.0", "end-1c")
        pass

    def remove_name(self):
        index = self.list_player.curselection()
        self.list_player.delete(index)
        pass

    def start_game(self):
        length = int(self.list_player.size())
        if not length < 2:
            game.start(self.list_player.get(0, length - 1))
            self.frame.destroy()


class GameScreen:
    def __init__(self, width, height, player_list):
        self.frame = Frame(root, height=height, width=width)
        self.frame.pack(side=BOTTOM)
        self.current_player = randrange(0, len(player_list))
        # Labels
        self.label_current_player = Label(self.frame, text="Current player: " + str(player_list[self.current_player]))
        self.label_result = Label(self.frame)
        # Buttons
        self.btn_roll = Button(self.frame, text="Roll")
        self.btn_next = Button(self.frame, text="Next")
        self.btn_quit = Button(self.frame, text="Quit")

        self.set_style()

    def set_style(self):
        # Label
        self.label_current_player.pack(side=TOP)
        # Buttons
        self.btn_roll.pack(side=LEFT)
        self.btn_next.pack(side=LEFT)
        self.btn_quit.pack(side=LEFT)

    def create_players(self, player_list):
        pass


    def roll(self):
        pass

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
