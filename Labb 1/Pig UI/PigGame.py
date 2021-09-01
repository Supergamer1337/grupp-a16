from tkinter import *


class PigGame():
    def __init__(self, master=None):
        super().__init__(master)
        self.pack()

    def start(self):
        # Inits GUI
        root = Tk()
        frame = Frame(root)
        frame.pack()
        game_frame = Frame(root)
        game_frame.pack(side=BOTTOM)
        btn_roll = Button(game_frame, text = "Roll dice", fg = "Black")
        run = True
        while run:
            root.mainloop()


if __name__ == "__main__":
    game = PigGame()
    game.master.title("Pig Game")
    game.master.maxsize(1600,900)
    game.start()
https://www.tutorialspoint.com/python/tk_pack.htm
https://docs.python.org/3/library/tkinter.html#a-very-quick-look-at-tcl-tk