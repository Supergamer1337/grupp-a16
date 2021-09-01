from tkinter import *


class PigGame(Frame):
    def __init__(self, master=None):
        super().__init__(master)
        self.master = master
        self.pack()
        self.width = 800
        self.height = 640
        self.run = True
        self.in_game = False
        self.current_screen = MenuScreen(self.width, self.height)

    def start(self):
        while self.run:
            root.mainloop()

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
        self.btn_name_add = Button()
        self.btn_start = Button(self.frame, command=self.hej())
        self.btn_name_remove = Button()
        self.textbox = Text()
        self.set_style()

    def set_style(self):
        # https://www.pythontutorial.net/tkinter/tkinter-pack/
        # TOD style the game
        # Frame
        self.frame.pack(side=BOTTOM)
        # Buttons
        self.btn_start.pack(side=BOTTOM)
        # Textbox
        # Listbox

    def set_commands(self):
        self.btn_name_add
        self.btn_name_remove

    def hej(self):
        print("Dabs")
    pass

class GameScreen:
    def __init__(self, width, height):
        self.frame = Frame(root, height=height, width=width)
        self.frame.pack(side=BOTTOM)


if __name__ == "__main__":
    root = Tk()
    game = PigGame(master=root)
    game.master.title("Pig Game")
    game.master.maxsize(1600, 900)
    game.start()

# https://www.tutorialspoint.com/python/tk_pack.htm
# https://docs.python.org/3/library/tkinter.html#a-very-quick-look-at-tcl-tk
