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
# (5+4)*(7-3) = 36
# (5*(3-1)+2) = 
# (5+10)*64^8+(8 + (4*(3+4))


# 2*((5-1)*(2+2)) = 16
# ((5-1)-(3-(2+3)))
# 5 1 - 2 2 + * 2 *
def infix_to_postfix(tokens):
    tokens = "".join(tokens)  # To support both GUI and REPL
    test()
    ### Add in this order to stack
    stack = deque()
    # First check for parenthesis
    if len(find_all(tokens, "(")) > 0 or len(find_all(tokens, ")")) > 0:
        paren_depth(tokens)
        # handle_parenthesis()
    else:
        pass
    positions = find_all(tokens, "(")
    print(f"Positions of opening parenthesis: {positions}")
    print(f"Input was: {tokens}")
    
    # Second check power
    # Third check multiplication and division
    # Fourth check addition and subtraction
    return []  # TODO


# Rekursiv metod som appendar till en stack för paranteser?
def handle_parenthesis():
    if not find_all(tokens, "(") == find_all(tokens, ")"):
        return ValueError(MISSING_OPERATOR)
    parentheses = {"open": "(", "closed": ")"}


def paren_depth(tokens) -> list:
    print(tokens)
    depth: int = -1
    parentheses = {"open": "(", "closed": ")"}
    paren_pos_pairs = []
    open: int = 0
    close: int = 0
    for i in range(len(tokens)):
        if tokens[i] in parentheses["open"]:
            print(f'Token "{tokens[i]}" at {i} was open')
            open = i
            depth += 1
        elif tokens[i] in parentheses["closed"]:
            print(f'Token "{tokens[i]}" at {i} was closed')
            close = i
            while len(paren_pos_pairs) <= depth:
                paren_pos_pairs.append([])
            paren_pos_pairs[depth].append((open, close))
            depth -= 1
    print(paren_pos_pairs)

def paren_depth_v2(tokens: str) -> list:
    depth = 0
    #handles parenthesis splits
    occurence = tokens.find("(")
    while len(find_all(tokens, "(")) > 0:
        for i in range(len(tokens)):
            if tokens[i] == "(":
                depth += 1
            elif tokens[i] == ")":
                depth -= 1
            if depth == 0 and tokens[i] == ")":
                # occurence + 1 only takes the arguments inside the parentheses
                # print(f"Split: {tokens[occurence + 1 : i]}")
                paren_depth_v2(tokens[occurence + 1: i])
                tokens = tokens.replace(tokens[occurence: i + 1], "")  # Removes parantheses from tokens
                break
        occurence = tokens.find("(")
    print(f"Result after split: {tokens}")
    # Handle adding to stack
    

def find_all(a_str, sub):
    start = 0
    result = []
    while True:
        start = a_str.find(sub, start)
        if start == -1:
            return result
        result.append(start)
        start += len(sub)  # use start += 1 to find overlapping matches


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

def test():
    tokens = ["2*((5-1)*(2+2))", "(5*(3-1)+2)", "(2+(3-1)*5)"]
    assert len(find_all(tokens[0], "(")) == 3
    assert find_all(tokens[0], "(") == [2, 3, 9]
    # paren_depth(tokens)
    # assert paren_depth(tokens) == [[(2, 14)], [(3, 7), (9, 13)]]

    for i in range(len(tokens)):
        print(tokens[i])
        paren_depth_v2(tokens[i])

    print("Testing Completed")