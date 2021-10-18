# package calculator

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
def infix_to_postfix(infix_expr) -> list:
    """
    Uses RPN
    :param infix_expr: input from calculator
    :return: A RPN expression ready for eval
    """
    tokens: list[str] = tokenize(infix_expr)
    temp = []
    postfix_expr = []
    for elem in tokens:
        if elem[0].isdigit():  # Apppend if number
            postfix_expr.append(elem)
        elif elem in "(^":  # Puts parenthesis and ^ in temp list
            temp.append(elem)
        elif elem == ")":
            while len(temp) != 0 and not temp[-1] == "(": # Appends items inside parentheseses to list.
                postfix_expr.append(temp.pop())
            temp.pop() # Removes opening parenthesis
        else:
            # If precedence of operator is larger than top of temp append operator to temp
            if len(temp) != 0 and get_precedence(elem) > get_precedence(temp[-1]):
                temp.append(elem)
            else:
                # Appends all operators in temp with higher precedence than current operator
                while len(temp) != 0 and get_precedence(elem) <= get_precedence(temp[-1]):
                    postfix_expr.append(temp.pop())
                temp.append(elem)  # Append current operator to temp
    while len(temp) != 0:
        postfix_expr.append(temp.pop())
    return postfix_expr


def find_all(a_str, sub):
    """
    Goes through string returning occurencess of given substring
    :param a_str: String to look for substring in
    :param sub: Substring
    :return: List of indexes the substring occurs in
    """
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
    """
    Calculates the postfix expression and returns string of answer
    :param postfix: String of postfix to evaluate
    :return: String of evaluated answer
    """
    index = 0
    while not len(postfix) == 1:
        if isinstance(postfix[index], str) and postfix[index] in OPERATORS: # Checks if current elem is an operator.
            expression = []
            expression.append(postfix.pop(index)) # Grabs the operator
            for i in range(1, 3):
                expression.append(float(postfix.pop(index-i))) # Grabs the two previous numbers before the operator
            result = apply_operator(*expression) # This prevents very large numbers from being used, as it uses a dictionary and always evaluates ^ everytime
            if result is nan:
                raise ValueError(DIV_BY_ZERO)
            postfix.insert(index-2, result)
            index = 0
        else:
            index += 1
    result = postfix[0] # Only thing remaining in postfix is the result, which is at index 0 in the list.
    return str(result)


# Method used in REPL
def eval_expr(expr: str):
    """
    Evaluates expression by using infix_to_postfix which error-checks the code.
    If infix_to_postfix returns an error via the error-checking functions it includes,
    we print said error here. No need for a restart. 
    """
    try: # Tries to perform the evaluation.
        if len(expr) == 0:
            return nan
        tokens = expr.split() 
        postfix_tokens = infix_to_postfix(tokens) # Performs RPN on the tokens.
        return eval_postfix(postfix_tokens) # Calculates using said RPN and returns result.
    except ValueError as err: # Prints the error if the evaluation fails.
        return f"ValueError: {err.args[0]}" # err is a tuple. Only error code is necessary. 


def apply_operator(op: str, d1: float, d2: float):
    op_switcher = {
        "+": d1 + d2,
        "-": d2 - d1,
        "*": d1 * d2,
        "/": nan if d1 == 0 else d2 / d1,
        "^": d2 ** d1 # This one fucks everything up.
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
    """
    Error checks and returns the string in a list of numbers and strings
    :param expr: infix expr
    """
    expr = "".join(expr)  # To support both GUI and REPL
    expr = comma_to_dot(expr)
    error_check_string(expr)
    return grab_elems_from_list(expr)


def error_check_string(tokens):
    """
    Checks string for errors
    :param tokens: string to errorcheck
    """
    valid_parentheses(tokens)
    contains_illegal_char(tokens)


def valid_parentheses(tokens):
    """
    Checks if there are an even amount of parenthesises in string
    :param tokens: string to validate
    """
    if not len(find_all(tokens, "(")) == len(find_all(tokens, ")")):
        raise ValueError(MISSING_OPERATOR)


def contains_illegal_char(tokens):
    """
    Goes through string looking for illegal characters and illegal character combinations
    :param tokens: String to validate
    """
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
    """Returns true if the char check in given string is not valid"""
    return not tokens[pos].isdigit() and not tokens[pos] in VALID_CHARS


def negative_number(tokens: str, pos: int) -> bool:
    """Returns true if it find a negative number (which isn't handled by this calculator)"""
    current_sign = tokens[pos] == "-"
    invalid_neighbour = (tokens[pos - 1] in OPERATORS or tokens[pos - 1] == "(")
    return current_sign and invalid_neighbour


def invalid_before_comma(tokens: str, pos: int) -> bool:
    """Returns true if current position in string is a dot/comma and character before it is invalid"""
    is_comma = tokens[pos] == '.'
    invalid_neighbour = (tokens[pos - 1] == '.' or not tokens[pos - 1].isdigit())
    return is_comma and invalid_neighbour


def trailing_operator(tokens: str, pos: int) -> bool:
    """
    Returns true if current position in string is operator and character before is invalid.
    Also returns true if at beginning or end of string    
    """
    is_operator = tokens[pos] in OPERATORS
    out_of_string = pos + 1 == len(tokens) or pos == 0
    valid_neighbour = tokens[pos - 1].isdigit() or tokens[pos - 1] == ")"
    return is_operator and (out_of_string or not valid_neighbour)


def adjacent_to_paren(tokens: str, pos: int) -> bool:
    """
    Returns true if a non-operator was found next to the outside of parenthesis
    """
    valid_operators = OPERATORS + "()"
    if tokens[pos] == "(" and pos > 0:
        return not tokens[pos - 1] in valid_operators
    elif tokens[pos] == ")" and pos + 1 < len(tokens):
        return not tokens[pos + 1] in valid_operators
    return False


def grab_elems_from_list(tokens: str) -> list:
    """
    Separates and grabs valid elements in a string, into a new list of elements.
    """
    start = end = 0  # Set start and end of element
    result = []
    while end < len(tokens):
        # If its an operator append immediately
        if not tokens[start].isdigit():
            result.append(tokens[end])
            # Jump to next character
            start = end = start + 1
        else:
            end += 1
            if end == len(tokens) or not tokens[end].isdigit() and not tokens[end] == ".":  # Is false if end is part of a number
                # Appends element to result
                result.append(tokens[start:end])
                if end < len(tokens):
                    result.append(tokens[end])
                # Jump to next character
                end = start = end + 1
    return result


def comma_to_dot(tokens: str) -> str:
    return tokens.replace(",", ".")


def test():
    """
    This function tests the code. Only runs on main file execution. You will have to run Calculator.py to test.
    """
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
        "40/2*10+5.1",
        "2^2^3"
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
        "205.1",
        "256.0"
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
