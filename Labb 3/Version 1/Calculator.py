# package calculator

from collections import deque
from math import nan

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

OPERATORS:        str = "+-*/^"
VALID_CHARS:      str = "()." + OPERATORS
### TEST NUMBERS ###
# (5+4)*(7-3) = 36
# (5*(3-1)+2) = 
# (5+10)*64^8+(8 + (4*(3+4))


# 2*((5-1)*(2+2)) = 32
# ((5-1)-(3-(2+3)))
# 5 1 - 2 2 + * 2 *
def infix_to_postfix(tokens):
    tokens = "".join(tokens)  # To support both GUI and REPL
    tokens = comma_to_dot(tokens)
    error_check_string(tokens)
    tokens = int_to_float(tokens)
    return tokens


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
        if not tokens[i].isdigit() and not tokens[i] in VALID_CHARS:
            raise ValueError(MISSING_OPERAND)
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
        i += 1

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
    valid_neighbour = tokens[pos - 1].isdigit() or tokens[pos - 1] in ")"
    return is_operator and (out_of_string or not valid_neighbour)
    
def comma_to_dot(tokens: str) -> str:
    return tokens.replace(",", ".")

def int_to_float(tokens: str):
    start = end = 0
    result = []
    while end < len(tokens):
        if not tokens[start].isdigit():
            result.append(tokens[end])
            start = end = start + 1
        else:
            end += 1
            if end == len(tokens) or not tokens[end].isdigit() and not tokens[end] == ".":
                to_replace = tokens[start:end]
                replace_with = str(float(to_replace))
                result.append(replace_with)
                if end < len(tokens):
                    result.append(tokens[end])
                end = start = end + 1
                #print(f"Result: {result}")
    return "".join(result)


def calc_expression(tokens: str):
    # Expression inside parenthesis
    depth = 0
    occurrence = tokens.find("(")
    while len(find_all(tokens, "(")):
        for pos, char in enumerate(tokens):
            if char == "(":
                depth += 1
            elif char == ")":
                depth -= 1
            if depth == 0 and char == ")":
                content = tokens[occurrence + 1: pos]  # Content of parenthesis
                content_parenthesis = tokens[occurrence: pos + 1] # used to removes parentheses from tokens
                tokens = tokens.replace(content_parenthesis, calc_expression(content))
                break
        occurrence = tokens.find("(")
    # Calculate what's left
    for precedence in reversed(range(0, 3)):
        # print(f"Current precedence: {precedence}")
        operator_positions = get_operator_positions(tokens, precedence)
        while len(operator_positions):
            operator = tokens[operator_positions[0]]
            # print(f"Current op: {operator}") 
            left, right = get_adjacent_numbers(tokens, operator)
            string = f"{left}{operator}{right}"
            #print(string)
            result = apply_operator(operator, left, right)
            if result is nan:
                raise ValueError(DIV_BY_ZERO)
            tokens = tokens.replace(string, str(result))
            operator_positions = get_operator_positions(tokens, precedence)
    # print(f"Result is: {tokens}")
    return tokens  # Return resulting number


def get_operator_positions(tokens, precedence):
    operator_positions: list[int] = []
    for pos, char in enumerate(tokens):
        op_precedence = get_precedence(char)
        if op_precedence == precedence:
            operator_positions.append(pos)
    return operator_positions


def get_adjacent_numbers(tokens: str, operator: str):
    right = get_float(tokens, operator, 1)
    left = get_float(tokens, operator, -1)
    return left, right
    

def get_float(tokens: str, operator: str, direction: int):
    pos = tokens.find(operator) + direction
    num = ""
    #print(f"Is part of number? {is_part_of_number(tokens, pos)}")
    while is_part_of_number(tokens, pos):
        num += tokens[pos]
        pos += direction
    if direction < 0:
        num = num[::-1]  # Reverses string
    #print(f"Number {direction}: {num}")
    return float(num)
        

def is_part_of_number(tokens: str, pos: int):
    in_string = -1 < pos < len(tokens)
    if in_string:
        is_a_number = tokens[pos].isdigit()
        is_separator = tokens[pos] == "."
        return in_string and (is_a_number or is_separator)
    return False


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
    return calc_expression(postfix_tokens)


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
        "-": d1 - d2,
        "*": d1 * d2,
        "/": nan if d2 == 0 else d1 / d2,
        "^": d1 ** d2
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
        "65536.0"
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
        "abc*123"
    ]
    assert len(find_all(tokens[0], "(")) == 0
    assert len(find_all(tokens[1], "(")) == 3
    assert find_all(tokens[1], "(") == [2, 3, 9]

    for i in range(len(tokens)):
        stack = deque()
        print(tokens[i])
        new_token = infix_to_postfix(tokens[i])
        print(new_token)
        assert eval_expr(new_token) == number_answer[i]
    
    for i in range(len(error_tokens)):
        print(error_tokens[i])
        print(eval_expr(error_tokens[i]))
    

if __name__ == "__main__":
    test()
