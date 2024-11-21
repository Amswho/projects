# -*- coding: utf-8 -*-
"""
Created on Tue Nov 21 11:34:16 2023

@author: amyhc
"""
import random
from rich.console import Console
from rich.align import Align


console = Console()

def get_word(filename):
    '''
    
    '''
    with open(filename,'r') as file:
        line = file.readlines() 
        return(random.choice(line))
    filename.close


def display(guesses, word):
    '''

    '''
    alpha = {x: x for x in 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'}
    for guess in guesses:
        style_guess = []
        for g,correct in zip(guess,word):
            if g == correct:
                style = 'bold white on green'
            elif g in word:
                style = 'bold white on yellow'
            elif g in alpha:
                style = 'white'
            else:
                style = 'dim'
            style_guess.append(f'[{style}]{g}[/]')
            if g != '_':
                alpha[g] = f'[{style}]{g}[/]'
                
        console.print(Align.center(''.join(style_guess)))
    console.print(Align.center('\n'+''.join(alpha.values())))
   
def guess_word(previous):
    '''
   

    '''
    guess = console.input('\nGuess word: ').upper()
    if len(guess) != 5:
        console.print('Word must be 5 letters long')
        
    flag = ''
    for i in guess:
        if i not in 'ABCDEFGHIJKLMNOPQRSTUVWXYZ':
            flag += i
    if len(flag) > 0:
        console.print(f'{flag} is invalid input')

    if guess in previous:
        console.print(f'{guess} has already been used')
        return guess_word(previous)
    
    
def guess_wordy():
    
    word = get_word("words_five.txt")
    lives = 6
    letters = 5
    guesses = ['_'*letters]*lives
    
    console.print(Align.center('GUESS WORDY'))
    console.print(Align.center("Guess the word before all your lives have been lost"))
    
    while lives > 0:
        for i in range(lives):
            console.print(Align.center(f'Guess  {i+1}'))
            display(guesses, word)
            
            guesses[i] = input("\nGuess word: ").upper()

            if guesses[i] == word:
                break
            lives -= 1
        
        
if __name__ == '__main__':
    guess_wordy()
