# SudokuSolver

This program allow the user to play at Sudoku on their computers and ultimately, solve these puzzles.
How to use:
Use the keyboard arrows or the mouse to select a cell. Type a number to set the cell value to this number. To erase a cell value, just press delete with the cell selected. The highlight buttons make all possible cell for the button’s number appear in yellow.

The algorithm buttons:
Algorithm 1 find cells with only one possibility and fills it.
Algorithm 2 find numbers that can only fit in one place in a column, row or zone.
“Auto” just apply the algorithms successively until the game doesn’t change.
“Auto Adv” applies the “auto” and when the game doesn’t change anymore, makes recursive call to different possible game outcome. This algorithm solve them all (so far).

The two pictures show the program solving one of the hardest sudoku known according to this forum: http://forum.enjoysudoku.com/the-hardest-sudokus-new-thread-t6539-420.html#p218845

