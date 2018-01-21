
# coding: utf-8

# In[9]:

from random import shuffle
class Deck(object):
    def __init__(self):
        self.deckofcards=range(1,11)*4
        self.deckofcards.extend([10]*4)
              
    def getCards(self):
        shuffle(self.deckofcards)
        return self.deckofcards.pop()


# In[10]:

class Hand(object):
    def __init__(self,totAm):
        self.total_amount=totAm
        self.playingcards=[]
        
    def pickBetAmount(self,betAmount):
        total_amount =- betAmount
        return total_amount
    
    def setPlayingCards(self,card):
        self.playingcards.append(card)
        
    def getPlayingCards(self):
        return self.playingcards
        
    def getSum(self):
        return sum(self.playingcards)
    
    def hit(self):
        self.playingcards.append(deck.getCards())


# In[11]:

def getBetAmount():
    try:
        betAmount=int(raw_input('Choose you betting amount(min:1|max:300)'))
        if(betAmount!=0):
            return True
        else:
            return False
    except ValueError:
        return False


# In[12]:

from IPython.display import clear_output
def gameSetup(deck,dealer,player,firstTimePlayFlag,hit):
    clear_output()
    print('Dealer distributing cards')   
    if firstTimePlayFlag:
        cardsDistribution(deck,dealer,player,firstTimePlayFlag)
        dealerCardlist=dealer.getPlayingCards()
        playerCardlist=player.getPlayingCards()
        print("Dealer's Cards: {}    ?".format(dealerCardlist[0]))
        print("Player's Cards: {}    {}".format(playerCardlist[0],playerCardlist[1]))
    elif firstTimePlayFlag==False and hit:
        dealerCardlist=dealer.getPlayingCards()
        playerCardlist=player.getPlayingCards()
        print("Dealer's Cards: {}    ?".format(dealerCardlist[0]))
        print("Player's Cards: {}".format(playerCardlist))
    elif firstTimePlayFlag==False and hit==False:
        dealerCardlist=dealer.getPlayingCards()
        playerCardlist=player.getPlayingCards()
        print("Dealer's Cards: {}".format(dealerCardlist))
        print("Player's Cards: {}".format(playerCardlist))
            
    


# In[13]:

def cardsDistribution(deck,dealer,player,firstTimeFlag):
    if firstTimeFlag:
        dealer.setPlayingCards(deck.getCards())
        dealer.setPlayingCards(deck.getCards())
        player.setPlayingCards(deck.getCards())
        player.setPlayingCards(deck.getCards())


# In[14]:

def result(bust,stand,hit,player,dealer,deck):
    if bust:
        print("You are busted")
    elif stand:
        dealerPlay(deck,player,dealer,hit)
        dealerSum=dealer.getSum()
        playerSum=player.getSum()
        print('Sum is %s %s'%(dealerSum,playerSum))
        if dealerSum>21 or playerSum>dealerSum or playerSum==21:
            print("You won")
        elif dealerSum==playerSum:
            print("Draw")
        else:
            print("You Lost")
        


# In[15]:

def dealerPlay(deck,player,dealer,hit):
    if dealer.getSum()>=17:
        gameSetup(deck,dealer,player,False,hit)
    while (dealer.getSum())<17:
        print('hello')
        dealer.hit()
        gameSetup(deck,dealer,player,False,hit)


# In[16]:

print('Welcome to BlackJack')
answer=True
while answer:
    firstTimePlayFlag=True
    hit=True
    stand=bust=False
    while getBetAmount()==False:
        print("Try Again")
    print 'Getting ready'
    deck=Deck()
    dealer=Hand(100000)
    player=Hand(1000)
    gameSetup(deck,dealer,player,firstTimePlayFlag,hit)
    firstTimePlayFlag=False
    while stand==False:
        s_b=raw_input('Stand or Hit:')
        if s_b=='stand':
            stand=True
            gameSetup(deck,dealer,player,firstTimePlayFlag,hit)
            hit=False
        elif s_b=='hit':
            #hit=True #not used yet
            player.hit()
            gameSetup(deck,dealer,player,firstTimePlayFlag,hit)
            if player.getSum()>21:
                bust=True
                break
            elif player.getSum==21:
                break
            
    '''playerCardlist=player.getPlayingCards()
    print playerCardlist '''
    result(bust,stand,hit,player,dealer,deck)
    
    answer=raw_input('Want to play again(yes/no)')
    if answer=='yes':
        answer=True
    elif answer=='no':
        answer=False
    


# In[ ]:




# In[ ]:



