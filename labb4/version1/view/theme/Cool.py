# package labb4.view.theme

from labb4.model.Ball import Ball
from labb4.view.Assets import Assets

"""
   Specific theme

   *** Nothing to do here ***
"""


class Cool(Assets):
    # ------------ Handling Images ------------------------

    background = Assets.get_image("coolBg.png")

    Assets.bind(Ball, "coolBall.png")

    @classmethod
    def get_background(cls):
        return cls.background

    # -------------- Audio handling -----------------------------
