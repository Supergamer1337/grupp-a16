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
NEGATIVE_NUMBER:  str = "Negative number found"  
INCORRECT_COMMA:  str = "Comma found in incorrect location"
ILLEGAL_CHAR:     str = "Illegal char found"

OPERATORS:        str = "+-*/^"
VALID_CHARS:      str = "()." + OPERATORS
### TEST NUMBERS ###
# (5+4)*(7-3) = 36
# (5*(3-1)+2) = 
# (5+10)*64^8+(8 + (4*(3+4))


# 2*((5-1)*(2+2)) = 32
# ((5-1)-(3-(2+3)))
# 5 1 - 2 2 + * 2 *
def infix_to_postfix(tokens) -> list:
    tokens: list[str] = tokenize(tokens)
    temp = []
    postfix_expr = []
    for index, elem in enumerate(tokens):
        if elem[0].isdigit():
            postfix_expr.append(elem)
        elif elem == "(":
            temp.append(elem)
        elif elem == ")":
            while len(temp) != 0 and not temp[-1] == "(":
                postfix_expr.append(temp.pop())
            temp.pop()
        else:
            if len(temp) != 0 and get_precedence(elem) > get_precedence(temp[-1]):
                temp.append(elem)
            else:
                while len(temp) != 0 and get_precedence(elem) <= get_precedence(temp[-1]):
                    postfix_expr.append(temp.pop())
                temp.append(elem)
    while len(temp) != 0:
        postfix_expr.append(temp.pop())
    return postfix_expr

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
def eval_postfix(postfix: list) -> str:
    index = 0
    while not len(postfix) == 1:
        if isinstance(postfix[index], str) and postfix[index] in OPERATORS:
            variables = []
            for i in range(2):
                variables.append(float(postfix.pop(index-2)))
            variables.append(postfix.pop(index-2))
            variables.reverse()
            result = apply_operator(*variables)
            if result is nan:
                raise ValueError(DIV_BY_ZERO)
            postfix.insert(index-2, result)
            index = 0
        else:
            index += 1
    result = postfix[0]
    return str(result)


# Method used in REPL
def eval_expr(expr: str):
    try:
        if len(expr) == 0:
            return nan
        tokens = expr.split()
        postfix_tokens = infix_to_postfix(tokens)
        return eval_postfix(postfix_tokens)
    except ValueError as err:
        return f"ValueError: {err.args[0]}"


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
        "+": 1,
        "-": 1,
        "*": 2,
        "/": 2,
        "^": 3
    }
    return op_switcher.get(op, -1)


# ---------- Tokenize -----------------------
def tokenize(expr: str) -> list[str]:
    expr = "".join(expr)  # To support both GUI and REPL
    expr = comma_to_dot(expr)
    error_check_string(expr)
    return string_to_list(expr)


def error_check_string(tokens):
    valid_parentheses(tokens)
    contains_illegal_char(tokens)


def valid_parentheses(tokens):
    if not len(find_all(tokens, "(")) == len(find_all(tokens, ")")):
        raise ValueError(MISSING_OPERATOR)


def contains_illegal_char(tokens):
    if tokens[0] == "-":
        raise ValueError(MISSING_OPERAND)
    i = 0
    while i < len(tokens):
        # Checks that require current char
        if illegal_char(tokens, i):
            raise ValueError(ILLEGAL_CHAR)
        # Checks that require last char
        if i > 0:
            # Negative number
            if negative_number(tokens, i):
                raise ValueError(NEGATIVE_NUMBER)
            # Double comma
            if invalid_before_comma(tokens, i):
                raise ValueError(INCORRECT_COMMA)
        # Checks that require last and next char
        if trailing_operator(tokens, i):
            raise ValueError(MISSING_OPERAND)
        if adjacent_to_paren(tokens, i):
            raise ValueError(MISSING_OPERATOR)
        i += 1


def illegal_char(tokens: str, pos: int) -> bool:
    return not tokens[pos].isdigit() and not tokens[pos] in VALID_CHARS


def negative_number(tokens: str, pos: int) -> bool:
    current_sign = tokens[pos] == "-"
    invalid_neighbour = (tokens[pos - 1] in OPERATORS or tokens[pos - 1] == "(")
    return current_sign and invalid_neighbour


def invalid_before_comma(tokens: str, pos: int) -> bool:
    is_comma = tokens[pos] == '.'
    invalid_neighbour = (tokens[pos - 1] == '.' or not tokens[pos - 1].isdigit())
    return is_comma and invalid_neighbour


def trailing_operator(tokens: str, pos: int) -> bool:
    is_operator = tokens[pos] in OPERATORS
    out_of_string = pos + 1 == len(tokens) or pos  == 0
    valid_neighbour = tokens[pos - 1].isdigit() or tokens[pos - 1] == ")"
    return is_operator and (out_of_string or not valid_neighbour)


def adjacent_to_paren(tokens: str, pos: int) -> bool:
    valid_ops = OPERATORS + "()"
    if tokens[pos] == "(" and pos > 0:
        return not tokens[pos - 1] in valid_ops
    elif tokens[pos] == ")" and pos + 1 < len(tokens):
        return not tokens[pos + 1] in valid_ops
    return False


def string_to_list(tokens: str):
    start = end = 0
    result = []
    while end < len(tokens):
        if not tokens[start].isdigit():
            result.append(tokens[end])
            start = end = start + 1
        else:
            end += 1
            if end == len(tokens) or not tokens[end].isdigit() and not tokens[end] == ".":
                result.append(tokens[start:end])
                if end < len(tokens):
                    result.append(tokens[end])
                end = start = end + 1
    return result


def comma_to_dot(tokens: str) -> str:
    return tokens.replace(",", ".")


def get_operator_positions(tokens, precedence):
    operator_positions: list[int] = []
    for pos, char in enumerate(tokens):
        op_precedence = get_precedence(char)
        if op_precedence == precedence:
            operator_positions.append(pos)
    return operator_positions


def test():
    tokens = [
        "2^3",
        "2*((5-1)*(2+2))",
        "(5*(3-1)+2)", 
        "(2+(3-1)*5)", 
        "(2+5*1)",
        "6/3*5",
        "((6*(3/6))*3-9*(1/9))^2/(5-3)",
        "3*(3+2)",
        "2^(2^(2^2))",
        "40/2*10+5.1"
        ]
    number_answer = [
        "8.0",
        "32.0",
        "12.0",
        "12.0",
        "7.0",
        "10.0",
        "32.0",
        "15.0",
        "65536.0",
        "205.1"
    ]
    error_tokens = [
        "3,.2",
        "3+2+",
        "3.2.+",
        "3,2-.",
        "3..2",
        "3+(-2)",
        "-2+3",
        "1/(5-5)",
        "0/0",
        "abc*123",
        "((3-2)+2",
        "2.(5+5)"
    ]

    assert len(find_all(tokens[0], "(")) == 0
    assert len(find_all(tokens[1], "(")) == 3
    assert find_all(tokens[1], "(") == [2, 3, 9]
    
    # Check right answer
    for i in range(len(tokens)):
        assert eval_expr(tokens[i]) == number_answer[i]

    # Display errors
    for i in range(len(error_tokens)):
        print(error_tokens[i])
        print(eval_expr(error_tokens[i]))


    print("Testing Completed")


if __name__ == "__main__":
    test()