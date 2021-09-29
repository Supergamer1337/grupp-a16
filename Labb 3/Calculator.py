# package calculator

from collections import deque
from math import nan
from enum import Enum

# A calculator for rather simple arithmetic expressions.
# Your task is to implement the missing functions so the
# expressions evaluate correctly. Your program should be
# able to correctly handle precedence (including parentheses)
# and associativity - see helper functions.
# The easiest way to evaluate infix expressions is to transform
# them into postfix expressions, using a stack structure.
# For example, the expression 2*(3+4)^5 is first transformed
# to [ 3 -> 4 -> + -> 5 -> ^ -> 2 -> * ] and then evaluated
# left to right. This is known as Reverse Polish Notation,
# see: https://en.wikipedia.org/wiki/Reverse_Polish_notation
#
# NOTE:
# - You do not need to implement negative numbers
#
# To run the program, run either CalculatorREPL or CalculatorGUI

MISSING_OPERAND:  str = "Missing or bad operand"
DIV_BY_ZERO:      str = "Division with 0"
MISSING_OPERATOR: str = "Missing operator or parenthesis"
OP_NOT_FOUND:     str = "Operator not found"
OPERATORS:        str = "+-*/^"
### TEST NUMBERS ###
# (5+4)*(7-3)
# (5*(3-1)+2)
# (5+10)*64^8+(8 + (4*(3+4))

def infix_to_postfix(tokens):
    tokens = "".join(tokens)
    ### Add in this order to stack
    stack = deque()
    # First check for parenthesis
    
    if find_all(tokens, "(") != find_all(tokens, ")"):
        # TODO: error
        pass
    positions = find_all(tokens, "(")
    print(f"Input: {tokens}")
    for i in range(len(positions), 0, -1):
        
        first_paranthesis = positions[i-1]
        second_paranthesis = tokens.rfind(")") + 1
        substring = tokens[first_paranthesis: second_paranthesis]
        tokens = tokens.replace(substring, "")
        print(f"Substr: {substring}")
        print(f"Input removed substr: {tokens}")

        

    
    # while subtoken.find("(") != -1:
    #     if tokens.rfind(")") == -1:
    #         return ValueError(MISSING_OPERATOR)
    #     subtoken = tokens[tokens.find("("): tokens.rfind(")")]
    
    # Second check power
    # Third check multiplication and division
    # Fourth check addition and subtraction
    return []  # TODO


def find_all(a_str, sub):
    start = 0
    result = []
    while True:
        start = a_str.find(sub, start)
        if start == -1: return result
        result.append(start)
        start += len(sub) # use start += 1 to find overlapping matches

# -----  Evaluate RPN expression -------------------
def eval_postfix(postfix_tokens):
    return "inte dab"  # TODO


# Method used in REPL
def eval_expr(expr: str):
    if len(expr) == 0:
        return nan
    tokens = expr.split()
    postfix_tokens = infix_to_postfix(tokens)
    return eval_postfix(postfix_tokens)


def apply_operator(op: str, d1: float, d2: float):
    op_switcher = {
        "+": d1 + d2,
        "-": d2 - d1,
        "*": d1 * d2,
        "/": nan if d1 == 0 else d2 / d1,
        "^": d2 ** d1
    }
    return op_switcher.get(op, ValueError(OP_NOT_FOUND))


def get_precedence(op: str):
    op_switcher = {
        "+": 2,
        "-": 2,
        "*": 3,
        "/": 3,
        "^": 4
    }
    return op_switcher.get(op, ValueError(OP_NOT_FOUND))


class Assoc(Enum):
    LEFT = 1
    RIGHT = 2


def get_associativity(op: str):
    if op in "+-*/":
        return Assoc.LEFT
    elif op in "^":
        return Assoc.RIGHT
    else:
        return ValueError(OP_NOT_FOUND)


# ---------- Tokenize -----------------------
def tokenize(expr: str):
    return None   # TODO

# TODO Possibly more methods
