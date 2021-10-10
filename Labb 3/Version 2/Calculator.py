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


# 2*((5-1)*(2+2)) = 32
# ((5-1)-(3-(2+3)))
# 5 1 - 2 2 + * 2 *
def infix_to_postfix(tokens):
    tokens = "".join(tokens)  # To support both GUI and REPL
    test()
    ### Add in this order to stack
    stack = deque()
    valid_parentheses(tokens)
    print(f"Input was: {tokens}")
    token_to_stack(tokens, stack)
    # Second check power
    # Third check multiplication and division
    # Fourth check addition and subtraction
    return stack


def token_to_stack(tokens: str, stack: deque) -> deque:
    tokens = split_parenthesis(tokens, stack)
    # print(f"Result after split: {tokens}")
    fill_stack(tokens, stack)
    print(stack)
    return stack


def split_parenthesis(tokens, stack):
    depth = 0
    # Breaks down parentheses
    occurrence = tokens.find("(")
    while len(find_all(tokens, "(")) > 0:
        for i in range(len(tokens)):
            if tokens[i] == "(":
                depth += 1
            elif tokens[i] == ")":
                depth -= 1
            if depth == 0 and tokens[i] == ")":
                # occurrence + 1 only takes the arguments inside the parentheses
                # print(f"Split: {tokens[occurrence + 1: i]}")  # Uncomment for more debug info
                token_to_stack(tokens[occurrence + 1: i], stack)
                tokens = tokens.replace(tokens[occurrence: i + 1], "")  # Removes parentheses from tokens
                break
        occurrence = tokens.find("(")
    return tokens


def fill_stack(tokens, stack):
    for precedence in range(2, -1, -1):
        # print(f"Current precedence: {precedence}")
        operator_positions = get_operator_positions(tokens, precedence)
        while len(operator_positions) != 0:
            operator = tokens[operator_positions[0]]
            # print(f"Current op: {operator}")
            left, right = get_adjacent(tokens, operator)
            if left is not None:
                stack.append(left)
            else:
                left = ""
            if right is not None:
                stack.append(right)
            else:
                right = ""
            stack.append(operator)
            string = f"{left}{operator}{right}"
            # print(string)
            tokens = tokens.replace(string, "")
            operator_positions = get_operator_positions(tokens, precedence)


def get_operator_positions(tokens, precedence):
    operator_positions: list[int] = []
    for pos, char in enumerate(tokens):
        op_precedence = get_precedence(char)
        if op_precedence == precedence:
            operator_positions.append(pos)
    return operator_positions


def get_adjacent(tokens: str, operator: str):
    right = convert_int(tokens, operator, 1)
    left = convert_int(tokens, operator, -1)
    return left, right


def convert_int(tokens: str, operator: str, offset: int):
    try:
        pos = tokens.index(operator) + offset
        if -1 < pos < len(tokens):
            num = int(tokens[pos])
        else:
            return None
    except ValueError:
        return None
    return num
    

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
    return "DAB"  # TODO


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
        "+": 0,
        "-": 0,
        "*": 1,
        "/": 1,
        "^": 2
    }
    return op_switcher.get(op, -1)


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


def valid_parentheses(tokens):
    if not find_all(tokens, "(") == find_all(tokens, ")"):
        return ValueError(MISSING_OPERATOR)


def test():
    tokens = [
        "2*((5-1)*(2+2))", 
        "(5*(3-1)+2)", 
        "(2+(3-1)*5)", 
        "(2+5*1)",
        "6/3*5"
        ]
    assert len(find_all(tokens[0], "(")) == 3
    assert find_all(tokens[0], "(") == [2, 3, 9]

    for i in range(len(tokens)):
        stack = deque()
        print(tokens[i])
        token_to_stack(tokens[i], stack)

    print("Testing Completed")
