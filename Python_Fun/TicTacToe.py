
# coding: utf-8



# In[3]:

from IPython.display import clear_output
def print_board(board):
    
    clear_output()
    print('    |    |   ')
    print('  '+board[0]+' |  '+board[1]+' | '+board[2]+'  ')
    print('    |    |   ')
    print('--------------')
    print('    |    |   ')
    print('  '+board[3]+' |  '+board[4]+' | '+board[5]+'  ')
    print('    |    |   ')
    print('--------------')
    print('    |    |   ')
    print('  '+board[6]+' |  '+board[7]+' | '+board[8]+'  ')
    print('    |    |   ')
    


# In[4]:

def choose_marker():
    marker=raw_input('Do you want to be X or O')
    return marker


# In[5]:

def getUserInput(player):
    userInput=0
    while True:
        flagcheck=False
        if player=='Player1':
            userInput=int(raw_input('Player1: '))
        elif player=='Player2':
            userInput=int(raw_input('Player2: '))
        for x in userInputList:
            if x==userInput:
                flagcheck=True
                #print('cannot go out')
                break
        if flagcheck==False:
            userInputList.append(userInput)
            #print('going out')
            break
    return userInput
    


# In[6]:

def checkWinner(board):
    if board[0]==board[1]==board[2]:
        return board[0]
    elif board[3]==board[4]==board[5]:
        return board[3]
    elif board[6]==board[7]==board[8]:
        return board[6]
    elif board[0]==board[3]==board[6]:
        return board[0]
    elif board[1]==board[4]==board[7]:
        return board[1]
    elif board[2]==board[5]==board[8]:
        return board[2]
    elif board[0]==board[4]==board[8]:
        return board[0]
    elif board[2]==board[4]==board[6]:
        return board[6]
    else:
        return False


    
# In[7]:

print("Welcome to Tic Tac Toe\n".upper())
answer=True
while answer:
    board=[' ']*9
    print_board(board)
    marker=choose_marker()
    flag_first='NoPlayer'
    if marker=='X':
        print('Player 1 will go first')
        flag_first='Player1'
    elif marker=='O':
        print('Player 2 will go first')
        flag_first='Player2'
    print("Enter inputs\n")
    gameOn=0
    userInput=0
    userInputList=[' ']*9
    while gameOn<9:
        if flag_first=='Player1':
            userInput=getUserInput('Player1')
            board[userInput-1]='X'
            print_board(board)
            #print('came1')
            flag_first='Player2'
        elif flag_first=='Player2':
            userInput=getUserInput('Player2')
            board[userInput-1]='O'
            print_board(board)
           # print('came2')
            flag_first='Player1'
        
        winnermarker=checkWinner(board)
        if winnermarker=='X':
            print('Player1 Won')
            break
        elif winnermarker=='O':
            print('Player2 Won')
            break
        gameOn +=1 
        if gameOn==9 and winnermarker==False:
            print("It's a Tie")
     
    answer=raw_input('Want to play again(yes/no)')
    if answer=='yes':
        answer=True
    elif answer=='no':
        answer=False
    
# In[ ]:




# In[ ]:



