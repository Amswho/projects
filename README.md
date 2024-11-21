# Projects
collection of projects

# Wordfail + words_five

A console-based word guessing game inspired by Wordle. Players guess a five-letter word within six attempts, with feedback provided after each guess.

---

## Features
- **Random Word Selection**: Chooses a five-letter word from `words_five.txt`.
- **Feedback System**:
  - **Green**: Correct letter in the correct position.
  - **Yellow**: Correct letter in the wrong position.
  - **Dimmed/White**: Incorrect letters.
- **Lives System**: Six chances to guess the word.
- **Styled Console**: Uses the `rich` library for better visuals.

---

## How to Play
1. Run the game:
   ```bash
   python guess_wordy.py
   ```
2. Guess the five-letter word within six attempts.
3. Get feedback after each guess:
   - Green for correct letters in the correct position.
   - Yellow for correct letters in the wrong position.
   - Dimmed for incorrect letters.
4. Win by guessing correctly or lose if all lives are used.

---

## Requirements
- Python 3.7+
- Install `rich` for styled console output:
  ```bash
  pip install rich
  ```

---

## Files
- **`guess_wordy.py`**: Main game script.
- **`words_five.txt`**: List of five-letter words (one word per line). Ensure this file is in the same directory as the script.

---

Enjoy playing Wordy! 🎮
