# Minesweeper CLI

## Features

- Beginner, Intermediate and Expert difficulties
- Input validation (so you can't break it... easily)
- Color-coded cell rendering
- Cascading reveal of cells without adjacent mines
- Supports a game restart when you inevitably lose

## How to Play

**1. Run the app:**

> javac Main.java && java Main

**2. Choose a difficulty:**


> B = Beginner (10x10, 10 mines)
>   
> I = Intermediate (16x16, 40 mines)
>
> E = Expert (26x20, 99 mines)
>
>Q = Quit

**3. Enter your move in the format: "A5"**


> Column as a letter, row as a number (case-insensitive)

**4. Key playing until you:**


> Reveal all non-mine cells -- (WIN!)
> 
> OR...
> 
> Hit a mine -- (LOSE!)



## Project Structure

- Main.java — Entry point
- Game.java — Controls game flow and user interaction
- Board.java — Manages the grid, mines, and cascade logic
- Cell.java — Represents each cell on the board
- Coordinate.java — Encapsulates cell positions and equality logic

## Future?
- Flag/unflag cells
- Mine/score tracking
- Dynamic board size
- Writing tests

## Notes
Built as a learning project (no frameworks, no external libraries), just Java and tears. Designed to mimic real Minesweeper mechanics, including the brain-melting cascading reveal.

## Trello Board
https://trello.com/b/s2FIqRrR/minesweeper