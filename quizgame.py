def new_game():
    guesses = []
    current_guesses=0
    question_num=1
    for key in questions:
       print("------------------------")
       print(key)
       for i in options[question_num-1]:
        print(i)
       guess= input("Enter A, B, C, or D")
       guess = guess.upper()
       guesses.append(guess)

       current_guesses += check_answer(questions.get(key), guess)

       question_num+=1
    display_score(current_guesses, guesses)

#

def check_answer(answer, guess):
    if answer == guess:
        print("Correct")
        return 1
    else:
        print("Wrong")
        return 0
#
def display_score(correct_guesses, guesses):
    print("------------------------")
    print("Results")
    print("------------------------")
    print("Answers: ", end ="")
    for i in questions:
        print(questions.get(i), end =" ")
    print()

    print("guesses: ", end ="")
    for i in guesses:
        print(i, end = " ,")
    print()

    score = int((correct_guesses/len(questions))*100)
    print("Your score is: "+str(score)+"%")


#
def play_again():
    response = input("Do You want to play again?: ")
    response= response.upper()
    if response == "YES":
        return True
    else:
        return False

questions = {
    "When was Oratory Prep founded?: " : "C",
    "What is the mascot of Oratory Prep?: " : "C",
    "Who is Arnav Chahal's older Brother?: " : "A",
    "How old is DaGaeta Hall?: " : "A"
}

options = [[ "A. 1908", "B. 1913", "C. 1907", "D. 1912"],
           ["A. The Peacock", "B. The Dog", "C. The Ram", "D. The WildCats"],
            ["A. Arya Chahal", "B. Arnav Chahal", "C. Emmett Gaffney", "D. Joe Sannito"],
           ["A. It is about 5 years old", "B. Around 15 years old", "C. It is 50 years old", "D. It is 1 year old"]]
new_game()

while play_again():
    new_game()
print("Play again soon")
