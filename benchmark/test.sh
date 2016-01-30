#!/usr/bin/env bash

# This script uses coprocesses and associative arrays introduced in Bash 4.0
echo "NOTE: Require Bash 4.0 or above";
echo "NOTE: Your Bash version: $BASH_VERSION"; echo

# Check if ruby is installed

if ! command -v ruby >/dev/null 2>&1; then
    echo "ERROR: ruby is not installed"
    exit
fi

# Map single-letter names to full names
declare -A lengthen=( [R]=rock [P]=paper [S]=scissors [L]=lizard [V]=spock )

# Map full names to single-letter names
declare -A shorten=( [rock]=R [paper]=P [scissors]=S [lizard]=L [spock]=V )

# Top RPSLV bots in http://codegolf.stackexchange.com/questions/35079/the-rock-paper-scissors-lizard-spock-tournament-of-epicness/35509#35509
bots=(
    "python bots/Dienstag.py"
    "python bots/TobiasFuenke.py"
    "python bots/Pony.py"
    "python bots/SuperMarkov.py"
    "perl bots/BayesianBot.pl"
    "java -cp bots SmarterBot"
    "java -cp bots Gazzr"
    "ruby bots/Alternator.rb"
    "ruby bots/Markov.rb"
)

# Compile Java bots
cd bots && javac *.java && cd ..

# Play 1 match with each bot
for bot in "${bots[@]}"; do
    # History of my moves and the opponent's moves
    myHistory=""
    opponentHistory=""

    # Run Nickel in command line mode
    coproc java -cp ../target/Nickel-1.0-SNAPSHOT.jar com.byronlai.nickel.App --cli

    # Get the name of the bot
    botName=$(basename $(echo "$bot" | grep -Eo '[^ ]+$'))
    printf "Nickel vs $botName "

    # Each match consists of 300 rounds
    for round in {1..300}; do
        # Get the opponent's move
        opponentMove=$($bot $opponentHistory $myHistory)
        opponentHistory+=$opponentMove

        # Tell Nickel about the opponent's move
        echo ${lengthen[$opponentMove]} >&${COPROC[1]}

        # Read Nickel's move
        read -u ${COPROC[0]} -a words
        myHistory+=${shorten[${words[-1]}]}

        # Ignore Nickel's other output
        read -u ${COPROC[0]} line
        read -u ${COPROC[0]} -a stats
        read -u ${COPROC[0]} line

        # Show the progress
        if (( $round % 10 == 0 )); then
            printf .
        fi
    done

    # Show stats
    opponentWins=${stats[1]}
    nickelWins=${stats[3]}
    ties=${stats[5]}
    echo; echo "Nickel Wins: $nickelWins  Nickel Losses: $opponentWins  Ties:  $ties"; echo

    # Terminate Nickel
    kill $COPROC_PID
    wait $COPROC_PID
done
