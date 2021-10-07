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
    tokens = comma_to_dot(tokens)
    return tokens


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
                print(f"Start, end: {start},{end}\nTo replace: {to_replace}\nReplace with: {replace_with}")
                result.append(replace_with)
                if end < len(tokens):
                    result.append(tokens[end])
                end = start = end + 1
                print(f"Result: {result}")
    return "".join(result)
                
    
# troligtvis inte bajs
def int_to_float4(tokens: str):
    tokens = list(tokens)
    number = []
    numbers = []
    temp = []
    i: int = 0
    for elem in tokens:
        if elem.isdigit() or elem == ".":
            number.append(elem)
        else:
            for elem in number:
                temp.append(number)
            numbers.append(temp)
            number.clear()
    print(numbers)


def int_to_float3(tokens: str):
    temp = ""
    temp1 = []
    temp2 = []
    signs = []
    for pos, elem in enumerate(tokens):
        if elem.isdigit() or elem == ".":
            temp += elem
        else:
            signs.append(elem)
            temp1.append(temp)
            temp2.append(str(float(temp)))
            temp = ""
        if pos == len(tokens) - 1:
            temp1.append(temp)
            temp2.append(str(float(temp)))
    print(temp1)
    print(temp2)
    print(len(temp1))
    temp4 = ""
    for i in range(len(temp1)):
        print(f"Current number to replace: {temp1[i]}")
        if not str(float(temp1[i])) == temp1[i]:
            print(f"FABBO To replace: {temp1[i]}")
            print(f"FABBO Replace with: {temp2[i]}")
            print(f"Signs: {signs}")
        temp4 += str(temp2[i])
        temp4 += signs[i]
        print(temp4)
    print(temp4)
        
            # current_index = tokens.find(temp1[i])
            # print(current_index)
            # tokens = list(tokens)
            # print(tokens)
            # for j in range(len(temp1[i])):
            #     tokens.pop(current_index)
            #     print(tokens)
            # for elem in reversed(temp2[i]):
            #     tokens.insert(current_index, elem)
            # print(f"Added {temp2[i]} to tokens")
            # temp3 = ""
            # for elem in tokens:
            #     temp3 += elem
            # tokens = temp3
            # print(tokens)
    
    print(tokens)
    print("Fabbo is done!")
    return tokens


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
    # TODO: Calc tokens
    # Calculate what's left
    for precedence in reversed(range(0, 2)):
        # print(f"Current precedence: {precedence}")
        operator_positions = get_operator_positions(tokens, precedence)
        while len(operator_positions):
            operator = tokens[operator_positions[0]]
            # print(f"Current op: {operator}") 
            left, right = get_adjacent_numbers(tokens, operator)
            string = f"{left}{operator}{right}"
            print(string)
            result = apply_operator(operator, left, right)
            tokens = tokens.replace(string, str(result))
            operator_positions = get_operator_positions(tokens, precedence)
    print(tokens)
    return tokens # Return resulting number


def get_operator_positions(tokens, precedence):
    operator_positions: list[int] = []
    for pos, char in enumerate(tokens):
        op_precedence = get_precedence(char)
        if op_precedence == precedence:
            operator_positions.append(pos)
    return operator_positions


def get_adjacent_numbers(tokens: str, operator: str):
    right = convert_float(tokens, operator, 1)
    left = convert_float(tokens, operator, -1)
    return left, right
    

def convert_float(tokens: str, operator: str, direction: int):
    pos = tokens.find(operator) + direction
    num = ""
    while is_number(tokens, pos):
        print(pos)
        num += tokens[pos]
        pos += direction
    if direction < 0:
        num = num[::-1] # Reverses string
    return float(num)
        
    
def is_number(tokens: str, pos: int):
    in_string = -1 < pos < len(tokens)
    if in_string:
        is_a_number = tokens[pos].isdigit()
        is_separator = tokens[pos] == "," or "."
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
    # calc_expression(postfix_tokens)
    return "inte dab"


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


def valid_parentheses(tokens):
    if not find_all(tokens, "(") == find_all(tokens, ")"):
        return ValueError(MISSING_OPERATOR)


def test():
    tokens = [
        "2*((5-1)*(2+2))", 
        "(5*(3-1)+2)", 
        "(2+(3-1)*5)", 
        "(2+5*1)",
        "6/3*5",
        "((6*(3/6))*3-9*(1/9))^2/(5-3)" # Multiplicerar tal utanför parantes i fel ordning. 
        "3*(3+2)"
        ]
    stack_answer = [
        deque([5, 1, "-", 2, 2, "+", "*", 2, "*"]),
        deque([3, 1, "-", 5, "*", 2, "+"]),
        deque([3, 1, "-", 5, "*", 2, "+"]),
        deque([5, 1, '*', 2, '+']),
        deque([6, 3, "/", 5, "*"]),
        deque([3, 6, "/", 6, "*", 3, "*", 1, 9, "/", 9, "*", "-", 2, "^", 5, 3, "-", "/"])
        ]
    number_answer = [
        32,
        12,
        12,
        7,
        10,
        32
    ]
    assert len(find_all(tokens[0], "(")) == 3
    assert find_all(tokens[0], "(") == [2, 3, 9]

    for i in range(len(tokens)):
        stack = deque()
        print(tokens[i])
        # assert 
        # token_to_stack(tokens[i], stack)#  == stack_answer[i]
        # calc_expression(tokens[i])
    print("Testing Completed")
    number = "2.5/(7+61)-3"
    print(number)
    print(int_to_float2(number))
    

if __name__ == "__main__":
    test()